package com.cloud.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Stack;

/**
 * 文件工具类
 *
 * @author huangYl
 * @date 2018/11/30
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 创建文件.目标文件，文件夹不存在也无所谓
     *
     * @param fileArg
     * @return
     */
    public static File createFile(String fileArg) {
        File file = new File(fileArg);
        // 建立缺失目录
        createMissDir(file);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error(String.format("创建文件失败：%s", e.getMessage()), e);
            }
        }
        return file;
    }

    /**
     * @description: 建立路径中缺失的目录
     * @param: file 一个不存在的文件
     * @return:
     * @throws:
     */
    public static void createMissDir(File file) {
        if (file.exists()) {
            file.delete();
            return;
        }
        Stack<File> stack = new Stack<File>();
        File current = file.getParentFile();
        // 把缺失的目录放到栈里
        while (current != null && !current.exists()) {
            stack.push(current);
            current = current.getParentFile();
        }
        // 建立缺失的目录
        while (!stack.isEmpty()) {
            stack.pop().mkdir();
        }
    }

    /**
     * @description: 拷贝文件
     * @param: src 源文件
     * @param: dst 目标文件，文件夹不存在也无所谓
     * @return:
     * @throws:
     */
    public static void copy(File src, File dst) {
        // 建立缺失目录
        createMissDir(dst);
        // 交换数组大小
        int bufferSize = 512;
        int len;
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(src),
                        bufferSize);
                out = new BufferedOutputStream(new FileOutputStream(dst),
                        bufferSize);
                byte[] buffer = new byte[bufferSize];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            logger.error(String.format("复制文件失败：%s", e.getMessage()), e);
        }
    }

    /**
     * @description: 写文件
     * @param: src 源文件
     * @param: dst 目标文件，文件夹不存在也无所谓
     * @return:
     * @throws:
     */
    public static void writeFile(String filePath, HttpServletResponse response) throws IOException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            response.setContentType("text/html;charset=utf-8");
            File file = new File(filePath);
            long fileLength = file.length();
            String fileName = file.getName();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(filePath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            logger.error(String.format("下载文件path:%s", filePath), e);
        } finally {
            if (null != bis) {
                bis.close();
            }
            if (null != bos) {
                bos.close();
            }
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        boolean flag = false;
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 将字符串数据保存至目标文件中
     *
     * @param file 目标文件
     * @param data 字符串
     * @return java.lang.String
     * @author huangYl
     */
    public static String writeFile(File file, String data) {
        File targetDirectory = file.getParentFile();
        if (!targetDirectory.exists()) {
            targetDirectory.mkdirs();
        }
        if (!file.exists()) {
            file.delete();
        }
        try (
                FileOutputStream out = new FileOutputStream(file)
        ) {
            out.write(data.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.toString();
    }

}
