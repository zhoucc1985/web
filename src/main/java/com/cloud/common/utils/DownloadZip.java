package com.cloud.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

public class DownloadZip {
	public static HttpServletResponse downLoadFiles(List<File> files, HttpServletResponse response) throws Exception {

		try {

			// List<File> 作为参数传进来，就是把多个文件的路径放到一个list里面

			// 创建一个临时压缩文件

			// 临时文件可以放在CDEF盘中，但不建议这么做，因为需要先设置磁盘的访问权限，最好是放在服务器上，方法最后有删除临时文件的步骤
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateString = formatter.format(currentTime);
			String zipFilename = "模板下载"+ dateString +".zip";
			File file = new File(zipFilename);
//			file.createNewFile();
			if (!file.exists()) {
//				file.createNewFile();
			}
			response.reset();
			// response.getWriter()
			// 创建文件输出流
			FileOutputStream fous = new FileOutputStream(file);
			ZipOutputStream zipOut = new ZipOutputStream(fous);
			zipFile(files, zipOut);
			zipOut.close();
			fous.close();
			return downloadZip(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 把接受的全部文件打成压缩包
	 * 
	 * @param List<File>;
	 * @param org.apache.tools.zip.ZipOutputStream
	 */
	public static void zipFile(List files, ZipOutputStream outputStream) {
		int size = files.size();
		for (int i = 0; i < size; i++) {
			File file = (File) files.get(i);
			zipFile(file, outputStream);
		}
	}

	/**
	 * 根据输入的文件与输出流对文件进行打包
	 * 
	 * @param File
	 * @param org.apache.tools.zip.ZipOutputStream
	 */
	public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
		try {
			if (inputFile.exists()) {
				if (inputFile.isFile()) {
					FileInputStream IN = new FileInputStream(inputFile);
					BufferedInputStream bins = new BufferedInputStream(IN, 512);
					ZipEntry entry = new ZipEntry(inputFile.getName());
					ouputStream.putNextEntry(entry);
					// 向压缩文件中输出数据
					int nNumber;
					byte[] buffer = new byte[512];
					while ((nNumber = bins.read(buffer)) != -1) {
						ouputStream.write(buffer, 0, nNumber);
					}
					// 关闭创建的流对象
					bins.close();
					IN.close();
				} else {
					try {
						File[] files = inputFile.listFiles();
						for (int i = 0; i < files.length; i++) {
							zipFile(files[i], ouputStream);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HttpServletResponse downloadZip(File file, HttpServletResponse response) {
		if (file.exists() == false) {
			System.out.println("待压缩的文件目录：" + file + "不存在.");
		} else {
			try {
				// 以流的形式下载文件。
				InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				// 清空response
				response.reset();

				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/octet-stream");

				// 如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
				response.setHeader("Content-Disposition",
						"attachment;filename=" + new String(file.getName().getBytes("GB2312"), "ISO8859-1"));
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					File f = new File(file.getPath());
					f.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return response;
	}


	
	/*public void downLoad(HttpServletResponse response){
	    String filename="劳动合同.doc";
	    String filePath = "C:\\Users\\Liang Qizhao\\Desktop\\" ;
	    File file = new File(filePath + "/" + filename);
	    if(file.exists()){ //判断文件父目录是否存在
	    	try {
			filename = java.net.URLEncoder.encode(filename, "UTF-8");
			filename = new String(filename.getBytes(), "iso-8859-1");
	        response.setContentType("application/force-download");
	        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
	        
	        byte[] buffer = new byte[1024];
	        FileInputStream fis = null; //文件输入流
	        BufferedInputStream bis = null;
	        
	        OutputStream os = null; //输出流
	       
	            os = response.getOutputStream();
	            fis = new FileInputStream(file); 
	            bis = new BufferedInputStream(fis);
	            int i = bis.read(buffer);
	            while(i != -1){
	                os.write(buffer);
	                i = bis.read(buffer);
	            }
	            bis.close();
	            fis.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println("----------file download" + filename);
	    }
	}*/
}
