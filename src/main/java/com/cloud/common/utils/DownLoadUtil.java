package com.cloud.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloud.controller.datacollect.TemplateController;

public class DownLoadUtil {
    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    /**
     * 下载Excel文件工具类
     * @param request  请求对象
     * @param response 响应对象
     * @param filePath 文件全路径
     * @throws Exception 异常对象
     */
    public static void downLoadExcel(HttpServletRequest request, HttpServletResponse response, String filePath) throws Exception {
        OutputStream outputStream = null;
        File file = new File(filePath);
        if (!file.exists()) {
          throw new Exception ("文件不存在");
        }
        response.reset();
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;" + encodeFileName(request, file.getName()));
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int i;
            while ((i = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
        } catch (Exception e) {
            throw new Exception ("下载失败"+e.getMessage());
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    /**
     * 获取客户端浏览器类型、编码下载文件名
     *
     * @param request  请求对象
     * @param fileName 文件名称
     * @return String  编码后的文件名称 filename=filename
     */
    private static String encodeFileName(HttpServletRequest request, String fileName) {
        String rtn = "";
        String new_filename;
        try {
            new_filename = URLEncoder.encode(fileName, "UTF8");
            // 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
            rtn = "filename=\"" + new_filename + "\"";
            String userAgent = request.getHeader("User-Agent");
            if (userAgent != null) {
                userAgent = userAgent.toLowerCase();
                // IE浏览器，只能采用URLEncoder编码
                if (userAgent.contains("msie")) {
                    rtn = "filename=\"" + new_filename + "\"";
                }
                // Opera浏览器只能采用filename*
                else if (userAgent.contains("opera")) {
                    rtn = "filename*=UTF-8''" + new_filename;
                }
                // Safari浏览器，只能采用ISO编码的中文输出
                else if (userAgent.contains("safari")) {
                    rtn = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
                }
                // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
                else if (userAgent.contains("applewebkit")) {
                    new_filename = MimeUtility.encodeText(fileName, "UTF8", "B");
                    rtn = "filename=\"" + new_filename + "\"";
                }
                // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
                else if (userAgent.contains("mozilla")) {
                    rtn = "filename*=UTF-8''" + new_filename;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rtn;
    }
}
