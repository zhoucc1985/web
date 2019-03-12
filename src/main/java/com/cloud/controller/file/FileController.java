package com.cloud.controller.file;

import com.cloud.common.utils.CommonUtils;
import com.cloud.service.fileService.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件采集
 *
 * @author zhujt
 * @date 2019/1/25
 **/
@Api(tags = "文件采集", description = "文件采集")
@RestController
@RequestMapping(value = "/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;
    /**
     * 模板下载目录地址
     */
    @Value("${user.home}/excel")
    private String serverPath;

    @ApiOperation(value = "文件采集")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, Object> getFiles(@RequestParam(value = "path",required = false) String path,@RequestParam(value = "sub",required = false) String sub) {
        try {

            fileService.getFileDetails(path,sub,new HashMap(),new ArrayList());
            return CommonUtils.getSucResultMap();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件采集", e);
            return CommonUtils.getErrorResultMap("文件采集出错"+e.getMessage());
        }
    }
}
