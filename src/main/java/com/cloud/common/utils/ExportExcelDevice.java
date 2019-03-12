package com.cloud.common.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public class ExportExcelDevice<T> {
	/**
	 * 
	 * @param tableName   sheet名字
	 * @param headers 表头
	 * @param dataset 数据
	 * @param out
	 * @param pattern
	 */
	public void exportExcel(String tableName, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(tableName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式

		HSSFCellStyle style = workbook.createCellStyle();

		// 设置这些样式

		style.setFillForegroundColor(HSSFColor.WHITE.index);

		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		style.setBorderBottom(BorderStyle.THIN);

		style.setBorderLeft(BorderStyle.THIN);

		style.setBorderRight(BorderStyle.THIN);

		style.setBorderTop(BorderStyle.THIN);

		style.setAlignment(HorizontalAlignment.CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();

		font.setColor(HSSFColor.BLACK.index);

		font.setFontHeightInPoints((short) 12);

		//font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式

		style.setFont(font);

		// 生成并设置另一个样式

		HSSFCellStyle style2 = workbook.createCellStyle();

		style2.setFillForegroundColor(HSSFColor.WHITE.index);

		//style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		style2.setBorderBottom(BorderStyle.THIN);

		style2.setBorderLeft(BorderStyle.THIN);

		style2.setBorderRight(BorderStyle.THIN);

		style2.setBorderTop(BorderStyle.THIN);

		style2.setAlignment(HorizontalAlignment.CENTER);

		style2.setVerticalAlignment(VerticalAlignment.CENTER);

		// 生成另一个字体

		HSSFFont font2 = workbook.createFont();
		font2.setBold(true);//设置是否加粗
		//font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));

		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.

		comment.setAuthor("leno");
		// 产生表格标题行

		HSSFRow row = sheet.createRow(0);

		for (short i = 0; i < headers.length; i++) {

			HSSFCell cell = row.createCell(i);

			cell.setCellStyle(style);

			HSSFRichTextString text = new HSSFRichTextString(headers[i]);

			cell.setCellValue(text);

		}

		// 遍历集合数据，产生数据行

		Iterator<T> it = dataset.iterator();

		int index = 0;
		HSSFFont font3 = workbook.createFont();
		font3.setColor(HSSFColor.BLACK.index);
		while (it.hasNext()) {

			index++;

			row = sheet.createRow(index);

			T t = (T) it.next();

			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值

			Field[] fieldsAttr = t.getClass().getDeclaredFields();
			Field[] fields = new Field[fieldsAttr.length - 1];
			List<Field> fieldList = new ArrayList<Field>();

			int indexArr = 0;
			for (int k = 0; k < fieldsAttr.length; k++) {
				String fieldName = fieldsAttr[k].getName();
				if ("serialVersionUID".equals(fieldName)) {
					continue;
				}
				fields[indexArr] = fieldsAttr[k];
				indexArr++;
				if (indexArr == headers.length) {
					break;
				}
			}

			for (Field field : fields) {
				if (field != null) {
					fieldList.add(field);
				}
			}
			for (short i = 0; i < fieldList.size(); i++) {
				Field field = fieldList.get(i);
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				try {
					Class tCls = t.getClass();
					/*System.out.println(tCls);
					if(tCls.toString().contains("java.util.HashMap")){
						getMethodName="get";
						//Method getMethod = tCls.getMethod(getMethodName, new HashMap());
					}*/
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));

						// sheet.autoSizeColumn(i);

						/*byte[] bsValue = (byte[]) value;

						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,

								1023, 255, (short) 6, index, (short) 6, index);

						anchor.setAnchorType(2);

						patriarch.createPicture(anchor, workbook.addPicture(

								bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));*/

					} else {

						// 其它数据类型都当作字符串简单处理
						if (value == null) {
							value = "";
						}
						textValue = value.toString();

					}

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

					if (textValue != null && !textValue.equalsIgnoreCase("")) {

						Pattern p = Pattern.compile("^//d+(//.//d+)?$");

						Matcher matcher = p.matcher(textValue);

						if (matcher.matches()) {

							// 是数字当作double处理

							cell.setCellValue(Double.parseDouble(textValue));

						} else {

							HSSFRichTextString richString = new HSSFRichTextString(textValue);

							richString.applyFont(font3);

							cell.setCellValue(richString);

						}

					}

				} catch (SecurityException e) {

					e.printStackTrace();

				} catch (NoSuchMethodException e) {

					e.printStackTrace();

				} catch (IllegalArgumentException e) {

					e.printStackTrace();

				} catch (IllegalAccessException e) {

					e.printStackTrace();

				} catch (InvocationTargetException e) {

					e.printStackTrace();

				} finally {

					// 清理资源
				}
			}

		}
		try {

			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public interface FieldMap{
		/**
		 *
		 * @param cnName
		 * @return
		 */
		String getFieldName(String cnName);
	}


	public void exportExcelImg(String title, OutputStream out, String fileName) {
		String path = System.getProperty("user.dir") + File.separator + "queueXmlFile/" + fileName;// echar.jpg
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		int index = 0;
		HSSFRow row = sheet.createRow(index + 3);
		HSSFCell headerCell = row.createCell(0);
		headerCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
		headerCell.setCellValue(title);

		row = sheet.createRow(index + 6);
		HSSFCell cells = row.createCell(0);
		cells.setCellType(HSSFCell.CELL_TYPE_BLANK);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(); // 将图片写入流中
		BufferedImage bufferImg;
		try {
			bufferImg = ImageIO.read(new File(path));
			ImageIO.write(bufferImg, "PNG", outStream); // 利用HSSFPatriarch将图片写入EXCEL
			HSSFPatriarch patri = sheet.createDrawingPatriarch();
			HSSFClientAnchor anchor = new HSSFClientAnchor(5, 5, 5, 5, (short) 1, index + 6, (short) 6, 45);
			patri.createPicture(anchor, workbook.addPicture(outStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
			workbook.write(out);
			out.flush();
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
