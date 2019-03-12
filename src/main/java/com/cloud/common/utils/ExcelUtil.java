package com.cloud.common.utils;

import com.cloud.common.vo.ExcelResolve;
import com.cloud.common.vo.datacollection.CollectionDataErrorObjVO;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * execl的工具类
 *
 * @author Liu chang
 * @ClassName ExcelUtil.java
 * @date 2017年10月8日上午10:51:32
 */
public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static final String FILE_SUFFIS_XLS = ".xls";

    /**
     *转化对象
     *@param list
     *@param clazz
     *@return  java.util.List<T>
     *@author  Liu chang
     *@date  2018/5/9 0009 上午 11:50
     *@desc
     */
    public static <T> List<T> coverEntity(List<String[]> list, Class<T> clazz) throws Exception {
        List<T> res = new ArrayList<T>();
        if(list == null){ return res;}
        try {
            //将列头存放到有序的list中
            List<String> sheetHeads = new ArrayList<String>();
            //得到工作表所有的行，除开第一二行，后面的是数据
            int rows = list.size();
            if (rows < 1) {
                return res;
            }
            //得到工作表所有的列，第二列才是表头
            int clos = list.get(0).length;
            setHeadTitleName(list, sheetHeads, clos);
            Field[] fields = clazz.getDeclaredFields();
            List<String> isNullFields =new ArrayList<String>();
            for (int i = 1; i < rows; i++) {
                String[] row = list.get(i);
                //这里需要判断他的这一行是不是都是非空的数据 如果遇到是全空则直接break;
                boolean isNull = true;
                for(int m=0;m<clos;m++){
                    Object value = row[m];
                    if(value != null){
                        isNull = false;
                        break;
                    }
                }
                if(isNull){ break; }
                T t = clazz.newInstance();
                setAndCheckField(sheetHeads, fields, isNullFields, row, t);
                if(ObjectUtils.isNotEmptyList(isNullFields)){
                    throw new Exception("必须录入的字段值为空，请检查:"+ObjectUtils.getJsonStrByObject(isNullFields));
                }
                res.add(t);
            }
        } catch (Exception e) {
            System.out.println("execl导入的数据转化成实体报错："+ e);
            throw e;
        }
        list = null;
        return res;
    }

    /**
     * 设置字段与检查字段值
     * @author Pan Jianneng
     * @date 2018/12/9 6:29 PM
     * @params [sheetHeads, fields, isNullFields, row, t]
     * @return void
     */
    private static <T> void setAndCheckField(List<String> sheetHeads, Field[] fields, List<String> isNullFields, String[] row, T t) throws Exception {
        Map<String,Integer> allImportFieldMap = new LinkedHashMap<String,Integer>();
        //实体类字段顺序，用于与模板字段顺序对比
        Integer i=0;
        for (Field f : fields) {
            if (f.isAnnotationPresent(ExcelResolve.class)) {
                //获取注解对象
                ExcelResolve exelResolve = f.getAnnotation(ExcelResolve.class);
                //如果这个字段不需要导入则直接跳过
                if (!exelResolve.isImport()) { continue; }
                String key = exelResolve.titleName();
                allImportFieldMap.put(key,i);
                i++;
            }
        }
        for(String key:allImportFieldMap.keySet()){
            if(!key.equals(sheetHeads.get(allImportFieldMap.get(key)))){
                throw new Exception("导入文件不符合要求，确认按照下载模板导入");
            }
        }
        for (Field f : fields) {
            if (f.isAnnotationPresent(ExcelResolve.class)) {
                //获取注解对象
                ExcelResolve exelResolve = f.getAnnotation(ExcelResolve.class);
                //如果这个字段不需要导入则直接跳过
                if (!exelResolve.isImport()) { continue; }
                String key = exelResolve.titleName();
                if(!sheetHeads.contains(key)){
                    throw new Exception("导入文件不符合要求，确认按照下载模板导入");
                }
                //根据key获取列索引
                int cellIndex = sheetHeads.indexOf(key);
                String cell = cellIndex>-1 ? row[cellIndex] : null;
                Object value = cell;
                if(value != null){
                    // 判断类型是否可以强制转化
                    Class<?> tpye = f.getType();
                    Converter converter = BeanUtilsBean.getInstance()
                            .getConvertUtils().lookup(tpye);
                    if (converter != null) {
                        value = converter.convert(tpye, value);
                    } else {
                        throw new Exception("导入的数据类型与数据库保存类型冲突！");
                    }
                    //匹配正则表达式,目前只有性别有校验规则，这里特殊处理
                    if(exelResolve.regexText()!=null && exelResolve.regexText().length()!=0){
                        if(value.toString().length()>1){
                         throw new Exception(exelResolve.regexDesc());
                        }
                    }
                    //字段设置属性值
                    f.setAccessible(true);
                    f.set(t, value);
                }else{
                    //必填字段为空
                    if(exelResolve.isRequired()){
                        isNullFields.add(key);
                    }
                    //字段设置属性值
                    f.setAccessible(true);
                    f.set(t, value);
                }
            }
        }
    }

    /**
     * 设置表头名称
     * @author Pan Jianneng
     * @date 2018/12/9 6:27 PM
     * @params [list, sheetHeads, clos]
     * @return void
     */
    private static void setHeadTitleName(List<String[]> list, List<String> sheetHeads, int clos) {
        for (int j = 0; j < clos; j++) {
            String headCell = list.get(0)[j];
                sheetHeads.add(headCell);
        }
    }

    ////////////////////////////////////////////////
    ////导出
    ///////////////////////////////////////////////

    /**
     * 将实体类列表entityList转换成excel
     * @param param 包含headMapping信息，key为属性名，value为列名<br>
     * @param entityList
     * @param excel
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IOException
     */
    public static <T> boolean transform(XSSFWorkbook workbook,XSSFSheet sheet,XSSFDrawing patriarch,Map<String, String> param,
                                        List<T> entityList,ServletOutputStream excel) throws NoSuchMethodException,
            SecurityException,
            IllegalArgumentException, IOException {
        // 产生表格标题行从第二行开始
        XSSFRow row = sheet.createRow(0);
        //设置单元格文本格式
        XSSFCellStyle s = workbook.createCellStyle();
        XSSFDataFormat format = workbook.createDataFormat();
        s.setDataFormat(format.getFormat("@"));
        row.setRowStyle(s);
        int i = 0;
        List<String> proList = new ArrayList<String>();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(setHeadStyle(workbook));
            XSSFRichTextString text = new XSSFRichTextString(entry.getValue());
            cell.setCellValue(text);
            proList.add(entry.getKey());
            i++;
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = entityList.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            for (i = 0; i < proList.size(); i++) {
                sheet.setDefaultColumnStyle(i,s);
                XSSFCell cell = row.createCell(i);
                String propertyName = proList.get(i);
                try {
                    getPropertyValue(propertyName,t,cell,patriarch,workbook);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("第"+index+1+"行，列名："+param.get(propertyName)+"，字段："+propertyName+"，数据错误！");
                }
            }

        }
        //上锁写保护
        ////ExcelUtil.setLockCell(sheet);
        workbook.write(excel);
        excel.flush();
        excel.close();
        return true;
    }

    public static class NumberPattern{
        private static Pattern NUMBER_PATTERN = Pattern.compile("^//d+(//.//d+)?$");
    }

    /**
     * 设置表头样式
     * @author Pan Jianneng
     * @date 2018/11/15
     * @param workbook
     * @return org.apache.poi.hssf.usermodel.XSSFCellStyle
     */
    public static XSSFCellStyle setHeadStyle(XSSFWorkbook workbook) {
        XSSFCellStyle headStyle = workbook.createCellStyle();
        XSSFFont headFont = workbook.createFont();
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 11);
        headStyle.setFont(headFont);
        headStyle.setBorderTop(BorderStyle.THIN);
        headStyle.setBorderRight(BorderStyle.THIN);
        headStyle.setBorderBottom(BorderStyle.THIN);
        headStyle.setBorderLeft(BorderStyle.THIN);
        headStyle.setAlignment(HorizontalAlignment.CENTER);
       // headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headStyle.setLocked(true);
        return headStyle;
    }

    /**
     * 单元格锁定的样式
     * @author Pan Jianneng
     * @date 2018/11/29 2:55 PM
     * @param workbook
     * @return org.apache.poi.hssf.usermodel.HSSFCellStyle
     */
    public static XSSFCellStyle lockStyle(XSSFWorkbook workbook){
       XSSFCellStyle lockStyle = workbook.createCellStyle();
        lockStyle.setLocked(true);
        return lockStyle;
    }

    /**
     * 单元格不锁定的样式
     * @author Pan Jianneng
     * @date 2018/11/29 2:57 PM
     * @param workbook
     * @return org.apache.poi.hssf.usermodel.HSSFCellStyle
     */
    public static HSSFCellStyle unLockStyle(HSSFWorkbook workbook){
        HSSFCellStyle unlockStyle = workbook.createCellStyle();
        unlockStyle.setLocked(false);
        return unlockStyle;
    }

    /**
     * 设置文件保护
     * @author Pan Jianneng
     * @date 2018/11/29 3:14 PM
     * @param sheet
     * @return void
     */
    public static void setLockCell(XSSFSheet sheet){
        //sheet表加密：等效excel的审阅菜单下的保护工作表
        sheet.protectSheet("123456");
    }

    /**
     * 获取实体instance的propertyName属性的值
     * @param instance
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private static <T> void getPropertyValue(String propertyName,T instance, XSSFCell cell,
                                             XSSFDrawing patriarch,XSSFWorkbook workbook)
            throws SecurityException, IllegalArgumentException {
        Class<?> tCls = instance.getClass();
        if(tCls.getName().equals(HashMap.class.getName())){
            Map map = (Map) instance;
            if(ObjectUtils.isNotNullObject(map)){
                for(Object key:map.keySet()){
                    Object obj =map.get(key);
                    if(ObjectUtils.isNotNullObject(obj)){
                        if(!propertyName.equals(key)){
                            continue;
                        }
                        //错误属性
                        if(obj.getClass().getName().equals(CollectionDataErrorObjVO.class.getName())){
                            CollectionDataErrorObjVO vo = (CollectionDataErrorObjVO) obj;
                            //设置批注
                            //cell.setCellComment(setErrorComment(patriarch,vo.getMsg()));
                            //样式，背景色,解锁
                            cell.setCellStyle(setErrorStyle(workbook));
                            cell.setCellValue(vo.getValue());
                        }else{
                            // 利用正则表达式判断textValue是否全部由数字组成
                            Matcher matcher = NumberPattern.NUMBER_PATTERN.matcher(obj.toString());
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(obj.toString()));
                            } else {
                                XSSFRichTextString richString = new XSSFRichTextString(
                                        obj.toString());
                                cell.setCellValue(richString);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 设置错误单元格批注
     * @author Pan Jianneng
     * @date 2018/11/29 1:27 PM
     * @params [patriarch, errorMsg]
     * @return org.apache.poi.hssf.usermodel.HSSFComment
     */
    //private static XSSFComment setErrorComment(XSSFDrawing patriarch, String errorMsg){
    //    XSSFComment comment = patriarch.createCellComment(new XSSFClientAnchor(0,
    //            0, 0, 0, (short) 4, 2, (short) 6, 5));
    //    // 设置注释内容
    //    comment.setString(new XSSFRichTextString(errorMsg));
    //    return comment;
    //}

    /**
     * 设置错误单元格样式，还添加不锁定
     * @author Pan Jianneng
     * @date 2018/11/29 1:27 PM
     * @param workbook
     * @return org.apache.poi.hssf.usermodel.HSSFCellStyle
     */
    private static XSSFCellStyle setErrorStyle(XSSFWorkbook workbook) {
        XSSFCellStyle errorStyle = workbook.createCellStyle();
        errorStyle.setAlignment(HorizontalAlignment.CENTER);
        //errorStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        errorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //解锁
        errorStyle.setLocked(false);
        return errorStyle;
    }

    /**
     * 设置隐藏列，保存批次ID，用于导入时进行校验
     * 是否是当前批次的模板数据
     * @author Pan Jianneng
     * @date 2018/11/29 4:26 PM
     * @params [workbook, sheet, batchId]
     * @return void
     */
    public static void setNoneCell(XSSFWorkbook workbook,XSSFSheet sheet,String batchId){
        XSSFRow batchInfoRow = sheet.createRow(0);
        XSSFCell batchInfoCell = batchInfoRow.createCell(1);
        XSSFCellStyle batchInfoStyle = workbook.createCellStyle();
        batchInfoCell.setCellStyle(batchInfoStyle);
        batchInfoCell.setCellValue(batchId);
        batchInfoRow.setZeroHeight(true);
    }


}
