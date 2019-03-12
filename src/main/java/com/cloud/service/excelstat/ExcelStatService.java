package com.cloud.service.excelstat;

import com.cloud.common.utils.MyExcelUtil;
import com.cloud.entity.datamanagement.DBDatasource;
import com.cloud.entity.datamanagement.Ftpdatasource;
import com.cloud.mapper.master.datamanagement.DBDatasourceMapper;
import com.cloud.mapper.master.datamanagement.FtpdatasourceMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExcelStatService {

    private static Logger logger = LoggerFactory.getLogger(ExcelStatService.class);

    @Autowired
    private FtpdatasourceMapper ftpdatasourceMapper;
    @Autowired
    private DBDatasourceMapper datasourceMapper;

    /**
     * 文件上传临时保存目录地址
     */
    @Value("${data-collection-upload-file-path}")
    private String filePath;

    public void ftpExcelExport(HttpServletRequest request, HttpServletResponse response, String userAgent) {
        List<Ftpdatasource> list = ftpdatasourceMapper.select();

        excelExport(request, response, userAgent, list, null);

    }

    /**
     * Excel导出
     *
     * @param request   request
     * @param response  response
     * @param userAgent userAgent
     * @param ftpList   ftp对象集合
     * @param DBList    DB对象集合
     */
    private void excelExport(HttpServletRequest request, HttpServletResponse response, String userAgent, List<Ftpdatasource> ftpList, List<DBDatasource> DBList) {
        Workbook workbook = null;
        //导出到Excel
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            String[] headers = new String[]{"Sheet1"};
            String fileName = null;
            if (ftpList != null) {
                workbook = MyExcelUtil.exportExecl(ftpList, true, headers, null);
                fileName = "Ftp数据源" + ".xls";
            } else {
                workbook = MyExcelUtil.exportExecl(DBList, true, headers, null);
                fileName = "DB数据源" + ".xls";
            }
           
            //Excel文件名
            String fileName11 = MyExcelUtil.encodeFileName(fileName, userAgent);
            String headStr = "attachment; filename=\"" + fileName11 + "\"";
            response.setHeader("Content-Disposition", new String(headStr.getBytes("utf-8"),"ISO-8859-1" ));
            response.setContentType("multipart/form-data;charset=utf-8");
            workbook.write(out);
        } catch (Exception e) {
            throw new RuntimeException("导出表信息模板异常!");
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * DB数据源Excel导出
     *
     * @param request   request
     * @param response  response
     * @param userAgent userAgent
     */
    public void DBExcelExport(HttpServletRequest request, HttpServletResponse response, String userAgent) {
        List<DBDatasource> dbList = datasourceMapper.select();
        excelExport(request, response, userAgent, null, dbList);
    }
}
