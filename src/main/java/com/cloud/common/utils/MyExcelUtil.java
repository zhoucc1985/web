package com.cloud.common.utils;

import com.cloud.annotation.execl.ExcelResolve;
import com.cloud.entity.datamanagement.DBDatasource;
import com.cloud.entity.datamanagement.Ftpdatasource;
import com.cloud.entity.vo.qualityReport.DbQualityReportVo;
import com.cloud.entity.vo.qualityReport.EmptyTableVo;
import com.cloud.entity.vo.qualityReport.TableInfoVo;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * execl的工具类
 *
 * @author shrChang.Liu
 * @ClassName ExcelUtil.java
 * @date 2017年12月8日上午10:51:32
 */
public class MyExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 获取excel的数据
     *
     * @param filePath 文件路径
     * @return 返回 List[ Map<表头,值>,Map</表头,值>]
     * @throws Exception
     */
    public static List<Map<String, Object>> getDatesFromFile(String filePath) throws Exception {
        File file = new File(filePath);
        InputStream is = new FileInputStream(file);
        return MyExcelUtil.getDatasFromFileInputStream(is, file.getName());
    }

    /**
     * 获取excel的数据
     *
     * @param filePath 文件路径
     * @return Map<表头       表头列表       List       [       Map       <           表头       ,   值>,Map</表头,值>]>
     * @throws Exception
     */
    public static Map<String, Object> getTitleAndDatasFromFile(String filePath) throws Exception {
        File file = new File(filePath);
        InputStream is = new FileInputStream(file);
        return MyExcelUtil.getTitleAndDatasFromFileInputStream(is, file.getName());
    }

    /**
     * 根据文件输出流获取execl里面的内容并返回List<Map>
     *
     * @param is
     * @return
     * @throws IOException
     * @auth shrChang.Liu
     * @date 2017年12月6日上午11:31:27
     */
    public static List<Map<String, Object>> getDatasFromFileInputStream(InputStream is, String fileName)
            throws Exception {
        List<Map<String, Object>> dataLists = new ArrayList<Map<String, Object>>();
        boolean isExcel2003 = false;
        try {
            Workbook workbook = null;
            if (fileName.endsWith(".xls")) {
                isExcel2003 = true;
                workbook = new HSSFWorkbook(new POIFSFileSystem(is));
            } else {
                workbook = new XSSFWorkbook(is);
            }
            // 获取Excel中所有工作表
            int sheetNum = workbook.getNumberOfSheets();
            for (int m = 0; m < sheetNum; m++) {
                Sheet sheet = workbook.getSheetAt(m);
                if (sheet.getLastRowNum() == 0 && sheet.getPhysicalNumberOfRows() == 0) {
                    continue;
                }
                getSheetData(dataLists, sheet);
            }
            is.close();
        } catch (Exception e) {
            if (isExcel2003) {
                throw new Exception("导入的execl2003解析失败！");
            } else {
                throw new Exception("导入的execl解析失败！");
            }
        }
        return dataLists;
    }

    /**
     * 根据文件输出流获取execl里面的内容并返回List<Map>
     *
     * @param is
     * @return
     * @throws IOException
     * @auth shrChang.Liu
     * @date 2017年12月6日上午11:31:27
     */
    public static Map<String, Object> getTitleAndDatasFromFileInputStream(InputStream is, String fileName)
            throws Exception {
        Map<String, Object> dataTitleAndDataList = new HashMap<>();
        boolean isExcel2003 = false;
        try {
            Workbook workbook = null;
            if (fileName.endsWith(".xls")) {
                isExcel2003 = true;
                workbook = new HSSFWorkbook(new POIFSFileSystem(is));
            } else {
                workbook = new XSSFWorkbook(is);
            }
            // 获取Excel中所有工作表
            int sheetNum = workbook.getNumberOfSheets();
            for (int m = 0; m < sheetNum; m++) {
                Sheet sheet = workbook.getSheetAt(m);
                if (sheet.getPhysicalNumberOfRows() == 0) {
                    continue;
                }
                getSheetData(dataTitleAndDataList, sheet);
            }
            is.close();
        } catch (Exception e) {
            if (isExcel2003) {
                throw new Exception("导入的execl2003解析失败！");
            } else {
                throw new Exception("导入的execl解析失败！");
            }
        }
        return dataTitleAndDataList;
    }

    /**
     * 获取MultipartFile中excel的数据 支持多个sheet的数据
     *
     * @param file
     * @return 对应sheet的数据集
     */
    public static Map<String, List<Map<String, Object>>> getExcelAllSheetData(MultipartFile file) throws Exception {
        Map<String, List<Map<String, Object>>> resMap = new HashMap<String, List<Map<String, Object>>>();
        boolean isExcel2003 = false;
        String fileName = file.getName();
        InputStream is = file.getInputStream();
        try {
            Workbook workbook = null;
            if (fileName.endsWith(".xls")) {
                isExcel2003 = true;
                workbook = new HSSFWorkbook(new POIFSFileSystem(is));
            } else {
                workbook = new XSSFWorkbook(is);
            }
            int sheetNum = workbook.getNumberOfSheets();
            for (int m = 0; m < sheetNum; m++) {
                List<Map<String, Object>> dataLists = new ArrayList<Map<String, Object>>();
                Sheet sheet = workbook.getSheetAt(m);
                /**
                 * 获取一个sheet里面的数据，并封装成List<Map<String, Object>>，一个Map<String,
                 * Object>代表sheet的一行数据，
                 * Map的key表示sheet标题行的数据，value表示从第二行开始的数据行的数据
                 */
                getSheetData(dataLists, sheet);
                /**
                 * 解析的Excel数据最终封装到resMap集合中，key表示sheet名称，value表示sheet页的数据
                 */
                resMap.put(sheet.getSheetName(), dataLists);
            }
            is.close();
        } catch (Exception e) {
            if (isExcel2003) {
                throw new Exception("导入的execl2003解析失败！");
            } else {
                throw new Exception("导入的execl解析失败！");
            }
        }
        return resMap;
    }

    /**
     * 获取excel Sheet数据
     *
     * @param dataLists
     * @param sheet
     */
    private static void getSheetData(List<Map<String, Object>> dataLists, Sheet sheet) {
        int rows = sheet.getLastRowNum();// 得到工作表所有的行
        int clos = sheet.getRow(0).getPhysicalNumberOfCells();// 得到工作表所有的列
        // 遍历所有行
        for (int i = 1; i <= rows; i++) {
            Map<String, Object> rowMap = new LinkedHashMap<String, Object>();
            // 遍历一行的所有列
            for (int j = 0; j < clos; j++) {
                Cell firstCell = sheet.getRow(0).getCell(j);
                String key = firstCell.getStringCellValue();// 列头:第一行第一列
                Cell dataCell = sheet.getRow(i).getCell(j); // 第二行第一列
                Object value = getCellValue(dataCell);
                // 遍历完一行后，rowMap的key始终存放着第一行标题行各单元格字符串数据, value存放着从第二行开始的各单元格数据
                rowMap.put(key, value);
            }
            boolean isNull = true;
            // 判断是不是所有的values都是空
            for (Object v : rowMap.values()) {
                if (v != null) {
                    isNull = false;
                    break;
                }
            }
            if (!isNull) {
                dataLists.add(rowMap);
            }
        }
    }

    /**
     * 获取excel Sheet数据 得到表头数据和 记录
     *
     * @param dataTitleAndDataList [columnNames 表头列表 ， dataMap 记录列表 ]
     * @param sheet                sheet表格对象
     */
    private static void getSheetData(Map<String, Object> dataTitleAndDataList, Sheet sheet) {
        int rows = sheet.getLastRowNum();// 得到工作表所有的行
        int clos = sheet.getRow(0).getPhysicalNumberOfCells();// 得到工作表所有的列
        List<String> columnNames = new ArrayList<>();
        List<Map<String, Object>> dataLists = new ArrayList<>();
        // 获取所有的表头
        for (int i = 0; i < clos; i++) {
            Cell firstCell = sheet.getRow(0).getCell(i);
            String title = firstCell.getStringCellValue();
            columnNames.add(title);
        }
        // 第二行开始 遍历所有行
        for (int i = 1; i <= rows; i++) {
            Map rowMap = new HashMap();
            // 遍历一行的所有列
            for (int j = 0; j < clos; j++) {
                String key = columnNames.get(j);
                Cell dataCell = sheet.getRow(i).getCell(j); // 第i行第j列
                Object value = getCellValue(dataCell);
                // 遍历完一行后，rowList,按顺序存放着从第二行开始的各单元格数据
                rowMap.put(key, value);
            }
            boolean isNull = true;
            // 判断是不是所有的values都是空
            for (Object v : rowMap.values()) {
                if (v != null) {
                    isNull = false;
                    break;
                }
            }
            if (!isNull) {
                dataLists.add(rowMap);
            }
        }
        dataTitleAndDataList.put("columnNames", columnNames);
        dataTitleAndDataList.put("dataLists", dataLists);
    }

    /**
     * 将一个导入的execl数据转化为javaBean
     *
     * @param datas
     * @param clazz
     * @return
     * @auth shrChang.Liu
     * @date 2017年12月7日下午4:53:12
     */
    public static <T> List<T> coverEntity(List<Map<String, Object>> datas, Class<T> clazz) throws Exception {
        List<T> res = new ArrayList<T>();
        try {
            int i = 1;// 默认是第一行
            for (Map<String, Object> data : datas) { // 一个Map<String,
                // Object>表示封装的一行数据，转换成一个javaBean
                T t = clazz.newInstance();
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field field : fields) {
                    // 判断这个字段是否有注解
                    if (field.isAnnotationPresent(ExcelResolve.class)) {
                        // 获取这个注解对象
                        ExcelResolve exelResolve = field.getAnnotation(ExcelResolve.class);
                        // 如果这个字段不需要导入则直接跳过
                        if (!exelResolve.isImport()) {
                            continue;
                        }
                        String key = exelResolve.titleName();
                        boolean isRequired = exelResolve.isRequired();
                        String requiredName = exelResolve.requiredName();
                        // 判断值存在且不为空的时候
                        if (data.containsKey(key) && data.get(key) != null) {
                            // 判断是不是要进行正则匹配
                            if (StringUtils.isNotBlank(exelResolve.regexText())) {
                                String regexText = exelResolve.regexText();
                                Pattern pattern = Pattern.compile(regexText);
                                Matcher matcher = pattern.matcher(String.valueOf(data.get(key)));
                                // 如果导入的数据不匹配正则表达式定义的数据格式
                                MatcherRegexValue(i, exelResolve, key, matcher);
                            }

                            Object value = data.get(key);
                            // 判断类型是否可以强制转化
                            Class<?> tpye = field.getType();
                            Converter converter = BeanUtilsBean.getInstance().getConvertUtils().lookup(tpye);
                            if (converter != null) {
                                value = converter.convert(tpye, value);
                            } else {
                                throw new Exception("导入的数据类型与数据库保存类型冲突！");
                            }
                            // 字段设置属性值
                            field.setAccessible(true);
                            field.set(t, value);
                        } else {
                            // 判断是否必填，如果必填就不能为空,默认为false
                            if (isRequired) {
                                // 如果是存在二次条件,需要判断他是否存在,且在导入的列表中可以查询到这个
                                if ((StringUtils.isNotBlank(requiredName) && data.containsKey(requiredName))
                                        || StringUtils.isBlank(requiredName)) {
                                    throw new Exception("缺少列【" + key + "】的数据或者出现空值，请检查!");
                                }
                            }
                        }
                    }
                }
                i++;
                res.add(t);
            }
        } catch (Exception e) {
            logger.error("execl导入的数据转化成实体报错：", e);
            throw e;
        }
        return res;
    }

    /**
     * 导出数据为execl并且返回工作簿
     *
     * @param datas
     * @param isExecl2003
     * @return
     * @auth shrChang.Liu
     * @date 2017年12月8日上午10:46:15
     */
    public static <T> Workbook exportExecl(List<T> datas, boolean isExecl2003, String[] a, List<T> datas2)
            throws Exception {
        Workbook workbook = null;
        Sheet sheet = null;
        if (isExecl2003) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        if (datas.size() > 0) {
            for (int q = 0; q < a.length; q++) {
                if (q > 0) {
                    datas = datas2;
                }
                // 定义需要导出的字段
                Map<String, String> fieldMap = getExportFieldMap(datas.get(0).getClass()); // 形参：对象的字节码
                // 返回需要导出的字段集：Map<titleName,
                // fieldName>
                // 判断是否有可以导出的项
                if (!fieldMap.isEmpty()) {
                    sheet = workbook.createSheet(a[q]);
                    // 创建表格标题行 第一行
                    Row titleRow = sheet.createRow(0);
                    // 创建 标题行样式
                    CellStyle style = workbook.createCellStyle();
                    style.setFillPattern(FillPatternType.forInt(1));// HSSFCellStyle.SOLID_FOREGROUND
                    // 这是背底色灰白
                    style.setFillForegroundColor(new HSSFColor.GREY_40_PERCENT().getIndex());
                    // 设置水平居中
                    style.setAlignment(HorizontalAlignment.forInt(2));// HSSFCellStyle.ALIGN_CENTER
                    // 设置垂直居中
                    // style.setVerticalAlignment(VerticalAlignment.forInt(1));//HSSFCellStyle.VERTICAL_CENTER
                    Font font = workbook.createFont();
                    // 设置字体宋体
                    font.setFontName("宋体");
                    // 设置字体大小
                    font.setFontHeightInPoints((short) 13);
                    // 粗体显示
                    // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    style.setFont(font);
                    int i = 0;
                    // 遍历获取所有的titleName,并设置样式
                    SetCellStyle(titleRow, fieldMap, style, i);

                    int j = 1;// 数据从第一行开始
                    if (q == 0) {

                    }

                    for (T t : datas) {
                        // 创建第二行 数据行
                        Row row = sheet.createRow(j);
                        i = 0;
                        for (String fieldName : fieldMap.values()) {
                            // 获取需要导出的指定字段
                            Field field = t.getClass().getDeclaredField(fieldName);
                            field.setAccessible(true);
                            // 获取当前对象中该字段的属性值
                            Object value = field.get(t);
                            // 调用get方法获取
                            // PropertyDescriptor pd = new
                            // PropertyDescriptor(field.getName(),
                            // t.getClass());
                            // Object value = pd.getReadMethod().invoke(t);
                            if (value instanceof Date) {
                                value = DateUtils.dateToString((Date) value);
                            }
                            if (value != null) {
                                row.createCell(i).setCellValue(String.valueOf(value));
                            }
                            i++;
                        }
                        j++;
                    }
                    setColumnWidth(sheet, fieldMap.size());
                }
            }
        }
        return workbook;
    }

    /**
     * 设置cell样式
     *
     * @param titleRow
     * @param fieldMap
     * @param style
     * @param i
     * @author shrChang.Liu
     * @date 2018-5-9
     */
    private static void SetCellStyle(Row titleRow, Map<String, String> fieldMap, CellStyle style, int i) {
        for (String title : fieldMap.keySet()) {
            Cell cell = titleRow.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(title);
            i++;
        }
    }

    /**
     * 设置列隐藏
     *
     * @param workbook    excel工作簿
     * @param sheetIndex  隐藏的列的sheet下标
     * @param columnIndex 隐藏的列下标
     */
    public static void setColumnHidden(Workbook workbook, Integer sheetIndex, Integer columnIndex) {
        workbook.getSheetAt(sheetIndex).setColumnHidden(columnIndex, true);
    }

    /**
     * 导出一个租的excel数据
     *
     * @param isExecl2003
     * @return
     * @throws Exception
     */
    public static Workbook exportExecl(Map<String, List> dataMap, boolean isExecl2003) throws Exception {
        Workbook workbook = null;
        if (isExecl2003) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new SXSSFWorkbook();
        }
        // 判断是否有数据
        for (String key : dataMap.keySet()) {
            List datas = dataMap.get(key);
            /**
             * fieldMap存储key--titleName value--fieldName
             */
            Map<String, String> fieldMap = getExportFieldMap(datas.get(0).getClass());
            // 判断是否有可以导出的项
            if (!fieldMap.isEmpty()) {
                Sheet sheet = workbook.createSheet(key);
                // 创建表格标题行 第一行
                Row titleRow = sheet.createRow(0);
                // CellStyle style = workbook.createCellStyle();
                // style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                // style.setFillForegroundColor(new
                // HSSFColor.YELLOW().getIndex());
                // Font font = workbook.createFont();
                // font.setFontHeightInPoints((short) 12);
                // font.setBoldweight((short) 700);
                // style.setFont(font);
                // int i = 0;
                // SetCellStyle(titleRow, fieldMap, style, i);
                int j = 1;// 数据从第一行开始
                Iterator<Object> iterator = datas.iterator();
                System.out.println("开始循环");
                while (iterator.hasNext()) {
                    Object t = iterator.next();

                    Row row = sheet.createRow(j);
                    int i = 0;
                    for (String fieldName : fieldMap.values()) {
                        Field field = t.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        Object value = field.get(t);
                        if (value != null) {
                            row.createCell(i).setCellValue(String.valueOf(value));
                        }
                        i++;
                    }
                    j++;
                    if (sheet instanceof SXSSFSheet) {
                        if (j % 1000 == 0) {
                            ((SXSSFSheet) sheet).flushRows();
                        }
                    }
                    System.out.println("创建完第：" + j + "行数据！");
                }
                System.out.println("结束循环");
                /*
                 * for (Object t : datas) { Row row = sheet.createRow(j); i = 0;
                 * for (String fieldName : fieldMap.values()) { Field field =
                 * t.getClass().getDeclaredField(fieldName);
                 * field.setAccessible(true); Object value = field.get(t); if
                 * (value != null) {
                 * row.createCell(i).setCellValue(String.valueOf(value)); } i++;
                 * } j++; }
                 */
            }
        }
        return workbook;
    }

    /**
     * 导出一个租的excel数据
     *
     * @param isExecl2003
     * @return
     * @throws Exception
     */
    public static Workbook exportExecl(Map<String, List<Map<String, Object>>> dataMap, List<String> titlesEn,
                                       List<String> titles, boolean isExecl2003) throws Exception {
        Workbook workbook = null;
        if (isExecl2003) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new SXSSFWorkbook();
        }
        // 判断是否有数据
        for (String key : dataMap.keySet()) {
            List<Map<String, Object>> datas = dataMap.get(key);
            /**
             * fieldMap存储key--titleName value--fieldName
             */
            // 判断是否有可以导出的项
            if (!titles.isEmpty()) {
                Sheet sheet = workbook.createSheet(key);
                // 创建表格标题行 第一行
                Row titleRow = sheet.createRow(0);
                //设置样式
                CellStyle style = workbook.createCellStyle();
                CellStyle style2 = workbook.createCellStyle();
                //加边框
                style2.setBorderBottom(BorderStyle.THIN);//下边框
                style2.setBorderLeft(BorderStyle.THIN);//左边框
                style2.setBorderRight(BorderStyle.THIN);//右边框
                style2.setBorderTop(BorderStyle.THIN); //上边框
                style.setBorderBottom(BorderStyle.THIN);//下边框
                style.setBorderLeft(BorderStyle.THIN);//左边框
                style.setBorderRight(BorderStyle.THIN);//右边框
                style.setBorderTop(BorderStyle.THIN); //上边框
                style.setAlignment(HorizontalAlignment.CENTER);//水平居中
                style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
                style.setFillForegroundColor(new HSSFColor.YELLOW().getIndex());
                Font font = workbook.createFont();
                font.setFontHeightInPoints((short) 12);//设置字号
                font.setItalic(false);//设置是否为斜体
                font.setBold(true);//设置是否加粗
                //font.setColor(IndexedColors.RED.index);//设置字体颜色
                style.setFont(font);
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setFillForegroundColor(IndexedColors.ROYAL_BLUE.index);
                titleRow.setHeightInPoints(30);//设置行的高度
                //sheet.setColumnWidth(0, 20 * 256);//设置列的宽度
                sheet.setDefaultColumnWidth((short) 15);// 设置表格默认列宽度为15个字节
                Iterator<Map<String, Object>> iterator = datas.iterator();
                for (int i = 0; i < titles.size(); i++) {
                    Cell titleCell = titleRow.createCell(i);
                    HSSFRichTextString text = new HSSFRichTextString(titles.get(i));
                    titleCell.setCellValue(text);
                    titleCell.setCellStyle(style);
                }
                int j = 1;// 数据从第二行开始
                while (iterator.hasNext()) {
                    Map<String, Object> map = iterator.next();
                    Row row = sheet.createRow(j);
                    int i = 0;
                    for (String title : titlesEn) {
                        Object value = map.get(title);
                        if (value != null) {
                            row.createCell(i).setCellValue(String.valueOf(value));
                            //row.setRowStyle(style2);
                        }
                        i++;
                    }
                    j++;
                    if (sheet instanceof SXSSFSheet) {
                        if (j % 1000 == 0) {
                            ((SXSSFSheet) sheet).flushRows();
                        }
                    }
                }
            }
        }
        return workbook;
    }

    /**
     * 导出数据为excel并且返回工作簿
     *
     * @param data        数据集
     * @param isExcel2003
     * @param sheetName
     * @return Workbook 工作簿
     * @auth daituo
     */
    public static <T> Workbook exportExecl(List<T> data, boolean isExcel2003, String sheetName) throws Exception {
        Workbook workbook;
        Sheet sheet;
        if (isExcel2003) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        if (CollectionUtils.isEmpty(data)) {
            return null;
        }
        // 定义需要导出的字段
        Map<String, String> fieldMap = getExportFieldMap(data.get(0).getClass());
        sheet = workbook.createSheet(sheetName);
        Row titleRow = sheet.createRow(0);
        CellStyle titleStyle = getTitleStyle(workbook);
        int i = 0;
        SetCellStyle(titleRow, fieldMap, titleStyle, i);

        // 数据从第一行开始
        int j = 1;
        for (T t : data) {
            // 创建第二行 数据行
            Row row = sheet.createRow(j);
            i = 0;
            for (String fieldName : fieldMap.values()) {
                // 获取需要导出的指定字段
                Field field = t.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                // 获取当前对象中该字段的属性值
                Object value = field.get(t);
                if (Objects.isNull(value)) {
                    value = "";
                } else {
                    if (value instanceof Date) {
                        value = DateUtils.dateToString((Date) value);
                    }
                }
                row.createCell(i).setCellValue(String.valueOf(value));
                i++;
            }
            j++;
        }
        setColumnWidth(sheet, fieldMap.size());
        return workbook;
    }

    /**
     * 把工作簿写入到输出流
     *
     * @param workbook  工作簿
     * @param fileName  工作簿名称
     * @param userAgent
     * @param response
     * @author daituo
     */
    public static void writeToResponse(String fileName, String userAgent, HttpServletResponse response,
                                       Workbook workbook) {
        OutputStream out = null;
        if (Objects.isNull(workbook)) {
            return;
        }
        try {
            String fileName1 = MyExcelUtil.encodeFileName(fileName, userAgent);
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName1);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            logger.error("MyExcelUtil.writeToResponse has exception:{}", e);
            throw new RuntimeException(e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取title样式
     *
     * @param workbook
     * @return
     */
    private static CellStyle getTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 这是背底色
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        // 设置水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workbook.createFont();
        // 设置字体宋体
        font.setFontName("宋体");
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        return style;
    }

    /**
     * 根据实体查询出当前实体需要导出的字段与字段对应标题的有序集合
     *
     * @param clazz
     * @return
     * @auth shrChang.Liu
     * @date 2017年12月8日上午11:10:14
     */
    private static <T> Map<String, String> getExportFieldMap(Class<T> clazz) {
        // 定义有序Map集合
        Map<String, String> filedMap = new LinkedHashMap<String, String>();
        // 获取该类的所有成员变量
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 判断字段是否存在ExcelResolve注解
            // if (field.isAnnotationPresent(ExcelResolve.class)) {
            // 如存在，则获取ExcelResolve注解
            ExcelResolve excelResolve = field.getAnnotation(ExcelResolve.class);
            if (Objects.isNull(excelResolve)) {
                continue;
            }
            String fieldName = field.getName();
            String titleName = excelResolve.titleName();
            // 判断该字段是否需要导出到Excel
            if (excelResolve.isExport()) {
                filedMap.put(titleName, fieldName);
            }
        }
        return filedMap;
    }

    /**
     * 获取所有的sheet
     *
     * @param file
     * @return java.util.Map<java.lang.String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,
                       *       org.apache.poi.ss.usermodel.Sheet>
     * @author shrChang.Liu
     * @date 2018/5/9 0009 上午 10:56
     * @desc
     */
    public static Map<String, Sheet> getExcelAllSheet(MultipartFile file) throws Exception {
        Map<String, Sheet> resMap = new HashMap<String, Sheet>();
        boolean isExcel2003 = false;
        String fileName = file.getName();
        InputStream is = file.getInputStream();
        try {
            Workbook workbook = null;
            if (fileName.endsWith(".xls")) {
                isExcel2003 = true;
                workbook = new HSSFWorkbook(new POIFSFileSystem(is));
            } else {
                workbook = new XSSFWorkbook(is);
            }
            int sheetNum = workbook.getNumberOfSheets();
            for (int m = 0; m < sheetNum; m++) {
                Sheet sheet = workbook.getSheetAt(m);
                resMap.put(sheet.getSheetName(), sheet);
            }
            is.close();
        } catch (Exception e) {
            if (isExcel2003) {
                throw new Exception("导入的execl2003解析失败！");
            } else {
                throw new Exception("导入的execl解析失败！");
            }
        }
        return resMap;
    }

    /**
     * 转化对象
     *
     * @param sheet
     * @param clazz
     * @return java.util.List<T>
     * @author shrChang.Liu
     * @date 2018/5/9 0009 上午 11:50
     * @desc
     */
    public static <T> List<T> coverEntity(Sheet sheet, Class<T> clazz) throws Exception {
        List<T> res = new ArrayList<T>();
        if (sheet == null) {
            return res;
        }
        try {
            List<String> sheetHeads = new ArrayList<String>();// 将excel列头存放到有序的list中
            int rows = sheet.getLastRowNum();// 得到工作表所有的行
            if (rows < 1) {
                // throw new Exception("没有找到单元格数据！");
                return res;
            }
            int clos = sheet.getRow(0).getPhysicalNumberOfCells();// 得到excel工作表首行的所有的列
            for (int j = 0; j < clos; j++) {
                Cell headCell = sheet.getRow(0).getCell(j);
                sheetHeads.add(headCell.getStringCellValue());
            }
            Field[] fields = clazz.getDeclaredFields();
            // 一行行遍历sheet页中的数据，每一行都封装成一个bean
            for (int i = 1; i <= rows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    break;
                }
                // 这里需要判断他的这一行是不是都是非空的数据 如果遇到是全空则直接break;
                boolean isNull = true;
                for (int m = 0; m < clos; m++) {
                    // 根据cell数据类型获取cell数据
                    Object value = getCellValue(row.getCell(m));
                    if (value != null) {
                        isNull = false;
                        break;
                    }
                }
                if (isNull) {
                    break;
                }
                T t = clazz.newInstance();
                for (Field f : fields) {
                    // 判断bean的字段上是否存在@ExcelResolve注解
                    if (f.isAnnotationPresent(ExcelResolve.class)) {
                        // 获取@ExcelResolve注解对象
                        ExcelResolve exelResolve = f.getAnnotation(ExcelResolve.class);
                        // 如果这个字段不需要导入则直接跳过
                        if (!exelResolve.isImport()) {
                            continue;
                        }
                        String key = exelResolve.titleName();
                        boolean isRequired = exelResolve.isRequired();
                        String requiredName = exelResolve.requiredName();
                        int cellIndex = sheetHeads.indexOf(key);// 根据key获取列索引
                        Cell cell = cellIndex > -1 ? row.getCell(cellIndex) : null;
                        Object value = getCellValue(cell);
                        if (value != null) {
                            // 判断是不是要进行正则匹配，目前项目没有用到正则匹配
                            if (StringUtils.isNotBlank(exelResolve.regexText())) {
                                String regexText = exelResolve.regexText();
                                Pattern pattern = Pattern.compile(regexText);
                                Matcher matcher = pattern.matcher(String.valueOf(value));
                                // 如果导入的数据不匹配正则表达式定义的数据格式
                                MatcherRegexValue(i, exelResolve, key, matcher);
                            }
                            // 判断类型是否可以强制转化
                            Class<?> tpye = f.getType();
                            Converter converter = BeanUtilsBean.getInstance().getConvertUtils().lookup(tpye);
                            if (converter != null) {
                                value = converter.convert(tpye, value);
                            } else {
                                throw new Exception("导入的数据类型与数据库保存类型冲突！");
                            }
                            // 字段设置属性值
                            f.setAccessible(true);
                            f.set(t, value);
                        } else {
                            // 判断是否必填，如果必填就不能为空,目前项目默认为false，
                            if (isRequired) {
                                // 如果是存在二次条件,需要判断他是否存在,且在导入的列表中可以查询到这个
                                if ((StringUtils.isNotBlank(requiredName) && sheetHeads.contains(requiredName))
                                        || StringUtils.isBlank(requiredName)) {
                                    throw new Exception("缺少列【" + key + "】的数据或者出现空值，请检查!");
                                }
                            }
                        }
                    }
                }
                res.add(t);
            }
        } catch (Exception e) {
            logger.error("execl导入的数据转化成实体报错：", e);
            throw e;
        }
        sheet = null;
        return res;
    }

    /**
     * 正则匹配校验
     *
     * @param i
     * @param exelResolve
     * @param key
     * @param matcher
     * @throws Exception
     */
    private static void MatcherRegexValue(int i, ExcelResolve exelResolve, String key, Matcher matcher)
            throws Exception {
        if (!matcher.matches()) {
            String desc = "列名【" + key + "】第" + i + "行下面的数据格式不对，请重新填写！";
            if (StringUtils.isNotBlank(exelResolve.regexDesc())) {
                desc = "列名【" + key + "】第" + i + "行" + exelResolve.regexDesc() + ",请重新填写！";
            }
            throw new Exception(desc);
        }
    }

    /**
     * 获取cell的值
     *
     * @param cell
     * @return cell的值
     * @author shrChang.Liu
     * @date 2018-5-9
     */
    private static Object getCellValue(Cell cell) {
        Object value = null;
        if (cell != null) {
            // 判断数据单元格的数据类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING: // spring 类型
                    value = cell.getRichStringCellValue().getString().trim();
                    break;
                case Cell.CELL_TYPE_NUMERIC: // 数值类型
                    // 判断是否是日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        value = cell.getDateCellValue();
                    } else {
                        // value = cell.getNumericCellValue();
                        /**
                         * 由于poi读取文本类型数值时，默认会读成double类型，此处需要转换成整型数值
                         */
                        DataFormatter formatter = new DataFormatter();
                        value = formatter.formatCellValue(cell);
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN: // boolean 类型
                    value = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA: // 函数类型
                    value = cell.getCellFormula();
                    break;
                default:
                    value = null;
                    break;
            }
        }
        return value;
    }

    /**
     * 根据浏览器返回对应的文件名
     *
     * @param fileName
     * @param userAgent
     * @return
     */
    public static String encodeFileName(String fileName, String userAgent) {
        try {
            if (userAgent.contains("MSIE") || userAgent.contains("Edge") || userAgent.contains("Trident")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = MimeUtility.encodeWord(fileName, "UTF-8", "B");
            }
        } catch (Exception e) {
            return fileName;
        }
        return fileName;
    }

    //
    // public static void main(String[] args) {
    // String a = "";
    // Converter converter = BeanUtilsBean.getInstance()
    // .getConvertUtils().lookup(Integer.class);
    // Object b = converter.convert(Integer.class, a);
    // System.out.println(b);
    // }

    /**
     * 导出动态表数据为execl并且返回工作簿
     *
     * @param dynamicTableData 动态表数据
     * @param fieldMap         对应的列
     * @param isExecl2003      是否2003excel
     * @return
     * @author zhaojy
     */
    public static Workbook exportDynamicTableExecl(List<Map<String, Object>> dynamicTableData,
                                                   Map<String, String> fieldMap, boolean isExecl2003) {
        Workbook workbook = null;
        if (isExecl2003) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        if (dynamicTableData.size() > 0) {
            if (!fieldMap.isEmpty()) {
                Sheet sheet = workbook.createSheet();
                CellStyle titleStyle = getTitleStyle(workbook);
                // 创建表格标题行 第一行
                Row titleRow = sheet.createRow(0);
                int i = 0;
                SetCellStyle(titleRow, fieldMap, titleStyle, i);
                int j = 1;
                for (Map<String, Object> dtd : dynamicTableData) {
                    Row row = sheet.createRow(j);
                    i = 0;
                    for (String fieldName : fieldMap.values()) {
                        if (StringUtils.isNotBlank(fieldName)) {
                            String cellValue = dtd.get(fieldName) == null ? "" : String.valueOf(dtd.get(fieldName));
                            row.createCell(i).setCellValue(cellValue);
                        }
                        i++;
                    }
                    j++;
                }
                setColumnWidth(sheet, fieldMap.size());
            }
        }
        return workbook;
    }

    /**
     * 问题：使用自适应的方式设置excel列宽,这时候虽然根据表格内容设置的列宽满足要求，但是表头的部分标题因为过长导致被遮挡。
     * 查询了poi底层实现之后，发觉它是通过SheetUtil的getColumnWidth方法获取每列中每个单元格中内容的长度进行计算的。
     * 于是，我决定使用这个方法将所有的表头的标题内容长度取出来，判断这个长度是否大于sheet中的该列计算出来的列宽，
     * 如果是，就设置该列的列宽为标题的长度
     *
     * @param sheet
     * @param titleLength 标题行数
     */
    private static void setColumnWidth(Sheet sheet, int titleLength) {
        for (int i = 0; i < titleLength; i++) {
            // 获取表头的宽度
            int titleColumnWidth = sheet.getRow(0).getCell(i).getStringCellValue().length();
            // 自适应计算的宽度
            int autoWidth = (int) SheetUtil.getColumnWidth(sheet, i, false, 1, sheet.getLastRowNum());
            if (titleColumnWidth > autoWidth) {
                sheet.setColumnWidth(i, 512 * (titleColumnWidth + 1)); // 汉字是512，数字是256
            } else {
                sheet.setColumnWidth(i, 512 * (autoWidth + 1)); // 汉字是512，数字是256
            }
        }
    }

    /**
     * 解析ftpExcel表格
     *
     * @param path 文件路径
     * @throws Exception 抛出异常
     */
    public static List<Ftpdatasource> readFtpExcel(String path) throws Exception {
        InputStream is = new FileInputStream(new File(path));
        Workbook hssfWorkbook = null;
        if (path.endsWith("xlsx")) {
            hssfWorkbook = new XSSFWorkbook(is);// Excel 2007
        } else if (path.endsWith("xls")) {
            hssfWorkbook = new HSSFWorkbook(is);// Excel 2003

        }

        List<Ftpdatasource> list = new ArrayList<Ftpdatasource>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                Row row = hssfSheet.getRow(rowNum);
                if (row != null) {
                    // 获取一行的数据
                    String ftpIp = row.getCell(0).getStringCellValue();
                    String ftpPort = (int) Double.parseDouble(row.getCell(1).getNumericCellValue() + "") + "";
                    String userName = row.getCell(2).getStringCellValue();

                    String passWord = null;
                    Cell cellPassWord = row.getCell(3);
                    if (cellPassWord.getCellTypeEnum() == CellType.NUMERIC) {
                        passWord = (int) Double.parseDouble(cellPassWord.getNumericCellValue() + "") + "";
                    } else if (cellPassWord.getCellTypeEnum() == CellType.STRING) {
                        passWord = cellPassWord.getStringCellValue();
                    }

                    String ftpPath = row.getCell(4).getStringCellValue();

                    String connType = row.getCell(5).getStringCellValue();

                    Cell cellRemarks = row.getCell(6);
                    String remarks = null;
                    if (cellRemarks != null) {
                        remarks = cellRemarks.getStringCellValue();
                    }

                    list.add(new Ftpdatasource(ftpIp, ftpPort, userName, passWord, ftpPath, remarks, connType , null));
                }
            }

        }
        return list;

    }

    /**
     * 解析DBExcel表格
     *
     * @param path 文件路径
     * @throws Exception 抛出异常
     */
    public static List<DBDatasource> readDBExcel(String path) throws Exception {
        InputStream is = new FileInputStream(new File(path));
        Workbook hssfWorkbook = null;
        if (path.endsWith("xlsx")) {
            hssfWorkbook = new XSSFWorkbook(is);// Excel 2007
        } else if (path.endsWith("xls")) {
            hssfWorkbook = new HSSFWorkbook(is);// Excel 2003

        }

        List<DBDatasource> list = new ArrayList<DBDatasource>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum < hssfSheet.getLastRowNum(); rowNum++) {
                Row row = hssfSheet.getRow(rowNum);
                if (row != null) {
                    // 获取一行的数据
                    String sourceName = row.getCell(0).getStringCellValue();
                    String systemName = row.getCell(1).getStringCellValue();
                    String brandName = row.getCell(2).getStringCellValue();

                    String systemVersion = null;
                    Cell cellSystemVersion = row.getCell(3);
                    if (cellSystemVersion.getCellTypeEnum() == CellType.NUMERIC) {
                        systemVersion = (int) Double.parseDouble(cellSystemVersion.getNumericCellValue() + "") + "";

                    } else if (cellSystemVersion.getCellTypeEnum() == CellType.STRING) {
                        systemVersion = cellSystemVersion.getStringCellValue();
                    }

                    String databaseType = row.getCell(4).getStringCellValue();
                    String serviceProt = (int) Double.parseDouble(row.getCell(6).getNumericCellValue() + "") + "";
                    String version = null;
                    Integer oracleType = null;
                    if ("sqlserver".equalsIgnoreCase(databaseType)) {
                        if ("0".equals(serviceProt)) {
                            serviceProt = "1433";
                        }

                        // 数据库类型为SQLserver，需要填写版本号
                        Cell cellVersion = row.getCell(11);
                        if (cellVersion != null) {
                            version = cellVersion.getStringCellValue();
                        } else {
                            version = "sqlserver 2005以上";
                        }

                    } else if ("oracle".equalsIgnoreCase(databaseType)) {// 数据库类型为oracle，需要填写oracle类型
                        if ("0".equals(serviceProt)) {
                            serviceProt = "1521";
                        }

                        Cell cellOracleType = row.getCell(9);
                        if (cellOracleType != null && cellOracleType.getCellTypeEnum() == CellType.NUMERIC) {
                            oracleType = (int) Double.parseDouble(cellOracleType.getNumericCellValue() + "");
                        } else {
                            oracleType = 2;
                        }

                    }
                    String serviceIp = row.getCell(5).getStringCellValue();
                    String userName = row.getCell(7).getStringCellValue();

                    String passWord = null;
                    Cell cellPassWord = row.getCell(8);
                    if (cellPassWord.getCellTypeEnum() == CellType.NUMERIC) {
                        passWord = (int) Double.parseDouble(cellPassWord.getNumericCellValue() + "") + "";
                    } else if (cellPassWord.getCellTypeEnum() == CellType.STRING) {
                        passWord = cellPassWord.getStringCellValue();
                    }

                    String dbName = row.getCell(10).getStringCellValue();

                    Cell cell = row.getCell(12);
                    String remarks = null;
                    if (cell != null) {
                        remarks = cell.getStringCellValue();
                    }
                    list.add(new DBDatasource(sourceName, systemName, brandName, systemVersion, databaseType, serviceIp,
                            serviceProt, userName, passWord, oracleType, dbName, version, remarks, null, null , null));
                }
            }

        }
        return list;

    }

    /**
     * 临时文件的存放地址
     *
     * @param file 文件
     * @return 文件路径
     * @throws Exception
     */
    public static String saveFilePath(MultipartFile file, String filePath) throws Exception {
        // 判断文件是否为空
        if (file.isEmpty()) {
            throw new Exception("文件为空");
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 判断文件格式
        if (!".xls".equals(suffix) && !".xlsx".equals(suffix)) {
            throw new Exception("文件格式错误");
        }
        // 文件存放于临时路径，添加时间戳
        filePath += new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        File dest = new File(filePath);

        // 判断文件是否已经存在
        if (dest.exists()) {
            throw new Exception("文件已经存在");
        }

        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        file.transferTo(dest); // 保存文件
//        saveFile(filePath, dest, inputStream);
        return filePath;
    }

    private static void saveFile(String path, File dest, InputStream inputStream) {
        OutputStream os = null;
        try {
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + dest.getName());

            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导出动态数据到Workbook工作簿
     *
     * @param dataList    数据集
     * @param isExcel2003 是否导出成03版本
     * @param sheetName   sheet页名称
     * @author daituo
     */
    public static Workbook exportDynamicTableExecl(List<Map<String, Object>> dataList, boolean isExcel2003,
                                                   String sheetName) {
        Workbook workbook = null;
        if (isExcel2003) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        if (CollectionUtils.isEmpty(dataList)) {
            return null;
        }
        Sheet sheet = workbook.createSheet(sheetName);
        CellStyle titleStyle = getTitleStyle(workbook);
        // 创建表格标题行 第一行
        Row titleRow = sheet.createRow(0);
        int i = 0;
        // 获取标题行
        Set<String> title = dataList.get(0).keySet();
        setCellStyle(titleRow, title, titleStyle, i);
        int j = 1;
        for (Map<String, Object> map : dataList) {
            Row row = sheet.createRow(j);
            i = 0;
            for (String fieldName : title) {
                String cellValue = map.get(fieldName) == null ? "" : String.valueOf(map.get(fieldName));
                row.createCell(i).setCellValue(cellValue);
                i++;
            }
            j++;
        }
        setColumnWidth(sheet, title.size());
        return workbook;
    }

    /**
     * 设置标题行样式
     *
     * @param titleRow   标题行
     * @param titleData  标题行数据
     * @param titleStyle 标题样式
     * @param i
     */
    private static void setCellStyle(Row titleRow, Set<String> titleData, CellStyle titleStyle, int i) {
        for (String title : titleData) {
            Cell cell = titleRow.createCell(i);
            cell.setCellStyle(titleStyle);
            cell.setCellValue(title);
            i++;
        }
    }

    /**
     * inputstream转Byte[]
     *
     * @param inStream
     * @return
     * @throws IOException
     */
    public static byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    /**
     * 下载
     *
     * @param response
     * @param request
     * @param filename
     * @param fileData
     * @return
     */
    public static boolean downloadFile(HttpServletResponse response, HttpServletRequest request, String filename,
                                       byte[] fileData) {
        try {
            OutputStream myout = null;
            // 检查时候获取到数据
            if (fileData == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return false;
            }
            try {
                if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                    filename = new String(filename.getBytes("GBK"), "iso-8859-1");
                } else {
                    filename = URLEncoder.encode(filename, "utf-8");
                }
                response.setContentType("multipart/form-data");
                /*
                 * response.setContentType("multipart/form-
                 * data;charset=utf-8");
                 */
                response.setHeader("content-disposition", "attachment;filename=" + filename);
                // 写明要下载的文件的大小
                response.setContentLength(fileData.length);
                // 从response对象中得到输出流,准备下载
                myout = response.getOutputStream();
                myout.write(fileData);
                myout.flush();
            } catch (Exception e) {
            } finally {
                if (myout != null) {
                    try {
                        myout.close();
                    } catch (Exception e) {
                    }
                }
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private static <T> Workbook createSheet(List<T> data, String sheetName, Workbook workbook) throws Exception {
        Sheet sheet;// 定义需要导出的字段
        Map<String, String> fieldMap = getExportFieldMap(data.get(0).getClass());
        sheet = workbook.createSheet(sheetName);
        Row titleRow = sheet.createRow(0);
        CellStyle titleStyle = getTitleStyle(workbook);
        int i = 0;
        SetCellStyle(titleRow, fieldMap, titleStyle, i);
            // 数据从第一行开始
            int j = 1;
            for (T t : data) {
                // 创建第二行 数据行
                Row row = sheet.createRow(j);
                i = 0;
                for (String fieldName : fieldMap.values()) {
                    // 获取需要导出的指定字段
                    Field field = t.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    // 获取当前对象中该字段的属性值
                    Object value = field.get(t);
                    if (Objects.isNull(value)) {
                        value = "";
                    } else {
                        if (value instanceof Date) {
                            value = DateUtils.dateToString((Date) value);
                        }
                    }
                    row.createCell(i).setCellValue(String.valueOf(value));
                    i++;
                }
                j++;
            }

        setColumnWidth(sheet, fieldMap.size());
        return workbook;
    }


    public static Workbook exportExeclOfMostSheet(Map<String, Object> map, boolean isExcel2003) throws Exception {
        Workbook workbook = null;
        if (isExcel2003) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new SXSSFWorkbook();
        }
        DbQualityReportVo dbQualityReportVo = (DbQualityReportVo) map.get("dbQualityReportVo");
        List<DbQualityReportVo> list = new ArrayList<>();
        list.add(dbQualityReportVo);
        workbook = createSheet(list, "数据库报告", workbook);

        List<EmptyTableVo> emptyTableVoList = (List<EmptyTableVo>) map.get("emptyTableVoList");
        if (emptyTableVoList.size() == 0) {
            workbook = createEmptySheet(workbook, "空表报告", new EmptyTableVo(),null);
        } else {

            workbook = createSheet(emptyTableVoList, "空表报告", workbook);
        }

        List<TableInfoVo> tableInfoVoList = (List<TableInfoVo>) map.get("tableInfoVoList");
        if (tableInfoVoList.size() == 0) {
            workbook = createEmptySheet(workbook, "表记录报告", null,new TableInfoVo());
        } else {
            workbook = createSheet(tableInfoVoList, "表记录报告", workbook);
        }
        return workbook;
    }

    private static Workbook createEmptySheet(Workbook workbook, String sheetName, EmptyTableVo emptyTableVo,TableInfoVo tableInfoVo) {
        Sheet sheet;// 定义需要导出的字段
        Map<String, String> fieldMap ;
        if (emptyTableVo != null) {
            fieldMap = getExportFieldMap(emptyTableVo.getClass());
        } else {
            fieldMap = getExportFieldMap(tableInfoVo.getClass());
        }
        sheet = workbook.createSheet(sheetName);
        Row titleRow = sheet.createRow(0);
        CellStyle titleStyle = getTitleStyle(workbook);
        SetCellStyle(titleRow, fieldMap, titleStyle, 0);
        setColumnWidth(sheet, fieldMap.size());
        return workbook;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Administrator\\excel\\excel\\学生基本信息.xls");
        InputStream is = new FileInputStream(file);
        List<Map<String, Object>> list = MyExcelUtil.getDatasFromFileInputStream(is, file.getName());
        System.out.println(list.size());
    }

}
