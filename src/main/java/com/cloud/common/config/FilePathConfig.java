package com.cloud.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 文件路径配置类
 */
 @Configuration
public class FilePathConfig {
    /**
     * 模板下载根目录
     */
    public static String downLoadBasePath;

    @Value("${filepath.downloadbashpath}")
    public void setDownLoadBasePath(String downLoadBasePath){
        this.downLoadBasePath=downLoadBasePath;
    }


}
