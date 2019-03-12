package com.cloud.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
/**
 * 类描述
 *
 * @author: Pan Jianneng
 * @create: 2018/11/28 21:06
 */
public class ExcelCovertToCsvAndReader {

    /**
     *
     * @author Pan Jianneng
     * @date 2018/12/15 1:36 PM
    */
    enum DataType {
        /**
         * sst
         */
        SST_INDEX,
        /**
         *数字
         */
        NUMBER,
        /**
         *字符
         */
        INLINE_STR
    }

    /**
     * Excel路径
     */
    private String path;
    /**
     * 列总数
     */
    private int columns;

    private ExcelCovertToCsvAndReader(String path, int columns) {
        this.path = path;
        this.columns = columns;
    }

    /**
     * Excel处理
     * @author Pan Jianneng
     * @date 2018/12/12 11:40 AM
     * @param
     * @return
    */
    private List<String[]> process() throws IOException, SAXException, OpenXML4JException, ParserConfigurationException {

        OPCPackage opcPackage = OPCPackage.open(path, PackageAccess.READ);
        //poi会显示excel单元格字符全貌，中文会出现拼音，在ssTable构造函数不显示全貌
        ReadOnlySharedStringsTable stringsTable = new ReadOnlySharedStringsTable(opcPackage,false);
        XSSFReader xssfReader = new XSSFReader(opcPackage);
        List<String[]> list = new ArrayList<>();

        Iterator<InputStream> sheets = xssfReader.getSheetsData();
        while (sheets.hasNext()) {

            InputStream stream = sheets.next();
            list.addAll(processSheet(stringsTable, stream));
            stream.close();
        }
        opcPackage.close();
        return list;
    }

    /**
     * 读取流，查看xml文件内容
     * @author Pan Jianneng
     * @date 2018/12/15 12:57 PM
     * @param
     * @return
    */
    public static void streamOut(InputStream in) throws Exception{
        byte[] buf = new byte[1024];
        int len;
        while ((len=in.read(buf))!=-1){
            System.out.write(buf,0,len);
        }
    }

    /**
     * 处理sheet
     * @author Pan Jianneng
     * @date 2018/12/12 11:40 AM
     * @param
     * @return
    */
    private List<String[]> processSheet(ReadOnlySharedStringsTable strings, InputStream sheetInputStream)
            throws IOException, ParserConfigurationException, SAXException {

        InputSource sheetSource = new InputSource(sheetInputStream);
        //使用SAX解析xml文件
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();
        XMLReader sheetParser = saxParser.getXMLReader();
        //转csv
        ExcelCovertToCsvAndReader.SheetHandler handler = new ExcelCovertToCsvAndReader.SheetHandler(strings, columns);
        sheetParser.setContentHandler(handler);
        sheetParser.parse(sheetSource);
        return handler.getRows();
    }

    /**
     * 读取Excel入口方法
     * @author Pan Jianneng
     * @date 2018/12/12 11:41 AM
     * @param
     * @return
    */
    public static List<String[]> readExcel(String path, int columns) throws OpenXML4JException, IOException, ParserConfigurationException, SAXException {

        ExcelCovertToCsvAndReader excelReader = new ExcelCovertToCsvAndReader(path, columns);
        return excelReader.process();
    }

    private class SheetHandler extends DefaultHandler {

        private ReadOnlySharedStringsTable sharedStringsTable;
        /**
         * 最大列数
         */
        private final int maxColumnCount;
        /**
         * 是否是Value
         */
        private boolean isValue;
        /**
         * 下一个数据类型
         */
        private DataType nextDataType;
        /**
         * 当前列
         */
        private int currentColumn = -1;
        private StringBuffer value;
        /**
         *  Excel行数据
         */
        private String[] record;
        /**
         * Excel表数据
         */
        private List<String[]> rows = new ArrayList<>();
        private DecimalFormat df = new DecimalFormat("0");

        /**
         * 行
         */
        String row ="row";
        /**
         * 单元格
         */
        String cell = "c";
        /**
         * 单元格位置（A1、B2）
         */
        String reference = "r";
        /**
         * 样式索引属性
         */
        String style = "s";
        /**
         * 单元格类型
         */
        String type = "t";
        /**
         * 内联字符串类型
         */
        String inlineStr = "inlineStr";
        /**
         * 单元格映射值
         */
        String v = "v";

        private SheetHandler(ReadOnlySharedStringsTable strings, int maxColumnCount) {

            this.sharedStringsTable = strings;
            this.maxColumnCount = maxColumnCount;
            this.value = new StringBuffer();
            record = new String[this.maxColumnCount];
            rows.clear();
        }

        @Override
        public void startElement(String uri, String localName, String name, Attributes attributes) {
            if (cell.equals(name)) {

                currentColumn = getCurrentColumn(attributes.getValue(reference));
                nextDataType = DataType.NUMBER;

                if (style.equals(attributes.getValue(type))) {
                    nextDataType = DataType.SST_INDEX;
                }
                if (inlineStr.equals(attributes.getValue(type))) {
                    nextDataType = DataType.INLINE_STR;
                }
            }

            if (v.equals(name) || type.equals(name)) {

                isValue = true;
                value.setLength(0);
            }
        }

        @Override
        public void endElement(String uri, String localName, String name) {

            String str = null;
            if (v.equals(name) || type.equals(name)) {

                if (DataType.SST_INDEX.equals(nextDataType)) {
                    str = new XSSFRichTextString(sharedStringsTable.getEntryAt(Integer.parseInt(value.toString()))).toString();
                }
                if (DataType.INLINE_STR.equals(nextDataType)) {
                    str = value.toString();
                }
                if (DataType.NUMBER.equals(nextDataType)) {
                    str = df.format(new BigDecimal(value.toString()));
                }

                if (null == str) {
                    str = "";
                }
                record[currentColumn] = str;
            }

            if (row.equals(name)) {

                if (columns > 0) {
                    if (isNullArray()) {
                        return;
                    }
                    rows.add(record.clone());
                    for (int i = 0; i < record.length; i++) {
                        record[i] = null;
                    }
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {

            if (isValue) {
                value.append(ch, start, length);
            }
        }

        private List<String[]> getRows() {
            return rows;
        }
        /**
         * 获取当前列数
         * @author Pan Jianneng
         * @date 2018/12/12 11:42 AM
         * @param
         * @return
        */
        private int getCurrentColumn(String str) {

            int firstDigit = -1;
            for (int c = 0; c < str.length(); c++) {
                if (Character.isDigit(str.charAt(c))) {
                    firstDigit = c;
                    break;
                }
            }
            return nameToColumn(str.substring(0, firstDigit));
        }

        /**
         * 通过名字转换为列数（从0开始）
         * @author Pan Jianneng
         * @date 2018/12/12 11:42 AM
         * @param
         * @return
        */
        private int nameToColumn(String name) {

            int column = -1;
            for (int i = 0; i < name.length(); i++) {
                int j = name.charAt(i);
                column = (column + 1) * 26 + j - 'A';
            }
            return column;
        }

        /**
         * 判断是否为空数组
         * @author Pan Jianneng
         * @date 2018/12/12 11:42 AM
         * @param
         * @return
        */
        private Boolean isNullArray() {

            if (Arrays.stream(record).filter(str -> null != str).count() > 0) {
                return false;
            }
            return true;
        }
    }
    public static void main(String[] args) throws Exception {

        List<String[]> readerExcelList = ExcelCovertToCsvAndReader.readExcel(
                        // "C:\\Users\\Administrator\\excel\\excel\\学生基本信息.xls",
                        "C:/Users/Administrator/excel/excel/学生基本信息.xls",
                1);
        for (String[] strings:readerExcelList){
            System.out.println("----"+CommonUtils.getJsonStrByObject(strings));
        }
    }

}