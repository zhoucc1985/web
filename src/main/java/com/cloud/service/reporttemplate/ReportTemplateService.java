package com.cloud.service.reporttemplate;

import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.ExcelUtil;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.entity.datacollection.ReportTemplate;
import com.cloud.entity.datacollection.ReportTemplatePage;
import com.cloud.entity.datacollection.ReportTemplatePageField;
import com.cloud.mapper.master.datacollection.ReportTemplateMapper;
import com.cloud.mapper.master.datacollection.ReportTemplatePageFieldMapper;
import com.cloud.mapper.master.datacollection.ReportTemplatePageMapper;
import com.cloud.mapper.master.sys.OrgInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类描述
 *
 * @program cloudIAS
 * @author: Pan Jianneng
 * @create: 2018/11/14
 */
@Service
public class ReportTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(ReportTemplateService.class);

    @Resource
    private ReportTemplateMapper reportTemplateMapper;

    @Resource
    private ReportTemplatePageMapper reportTemplatePageMapper;

    @Resource
    private ReportTemplatePageFieldMapper reportTemplatePageFieldMapper;

    @Resource
    private OrgInfoMapper orgInfoMapper;

    /**
     * 导入报告模板表头数据-测试
     * @author Pan Jianneng
     * @date 2018/11/14
     * @param file 文件
     * @return void
     */
    public Map<String,Object> createReportDataTest(MultipartFile file) throws Exception {
        ReportTemplate reportTemplate = new ReportTemplate();
        reportTemplate.setName("迎新报告数据");
        int rtId = 1;
        if(reportTemplateMapper.selectByPrimaryKey(1L)==null){
            rtId = reportTemplateMapper.insertSelective(reportTemplate);
        }
        ReportTemplatePage reportTemplatePage = new ReportTemplatePage();
        reportTemplatePage.setCode("01");
        reportTemplatePage.setRtId(Long.valueOf(String.valueOf(rtId)));
        int rtpId = 1;
        if(reportTemplatePageMapper.selectByPrimaryKey(1L)==null){
            rtpId = reportTemplatePageMapper.insertSelective(reportTemplatePage);
        }
        ////ExcelUtil.getExcelAllSheetData(file);
        Map<String, List<Map<String, Object>>> excelDataMap = null;
        for(String sheel:excelDataMap.keySet()){
            List<Map<String,Object>> sheelData = excelDataMap.get(sheel);
            for(Map<String,Object> map:sheelData){
                for(String key:map.keySet()){
                    ReportTemplatePageField reportTemplatePageField = new ReportTemplatePageField();
                    reportTemplatePageField.setCode(map.get(key).toString());
                    reportTemplatePageField.setName(key);
                    reportTemplatePageField.setRtId(Long.valueOf(String.valueOf(rtId)));
                    reportTemplatePageField.setRtpId(Long.valueOf(String.valueOf(rtpId)));
                    reportTemplatePageFieldMapper.insert(reportTemplatePageField);
                }
            }

        }

        return CommonUtils.getSucResultMap();
    }

    /**
     * 通过报告模板ID查询模板字段数据，并导出Excel
     * @author Pan Jianneng
     * @date 2018/11/15
     * @param response
     * @param reportTemplateId
     * @param batchId
     * @return void
     */
    public void downloadReportTemplateFiledDataExcel(HttpServletResponse response,Long batchId, Long reportTemplateId) {
        List<ReportTemplatePageField> reportTemplatePageFields = reportTemplatePageFieldMapper.queryReportTemplatePageFieldsByReportTemplateId(reportTemplateId);
        if(ObjectUtils.isEmptyList(reportTemplatePageFields)){
            throw new BusinessException("下载失败，该报告模板没有字段数据！");
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("迎新数据");
        sheet.enableLocking();
        //设置要导出的文件的名字
        String fileName = "迎新数据"  + ".xlsx";
        //设置隐藏列，保存批次ID
        /// TODO ExcelUtil.setNoneCell(workbook,sheet,String.valueOf(batchId));
        List<String> titleName = new ArrayList<String>();
        for (ReportTemplatePageField rtpf : reportTemplatePageFields) {
            titleName.add(rtpf.getName());
        }
        String[] tempHeaders = {};
        String[]  headers = titleName.toArray(tempHeaders);
        //添加批次ID
        //headers[headers.length+1] = "batchId";
        //headers表示excel表中第二行的表头
        XSSFRow row = sheet.createRow(0);
        //设置单元格为文本格式
       XSSFCellStyle s = workbook.createCellStyle();
        XSSFDataFormat format = workbook.createDataFormat();
        s.setDataFormat(format.getFormat("@"));
        //设置表头样式
        XSSFCellStyle headStyle = ExcelUtil.setHeadStyle(workbook);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            sheet.setColumnWidth(i,4000);
            sheet.setDefaultColumnStyle(i,s);
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(headStyle);
        }
        try {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.flushBuffer();
            //上锁写保护
            ExcelUtil.setLockCell(sheet);
            //上锁写保护
            sheet.disableLocking();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            logger.error("报告字段数据模板下载失败：{1}",e.getMessage());
            throw new BusinessException("报告字段数据模板下载失败");
        }
    }


    /**
     * 查询报告模板列表
     * @author Pan Jianneng
     * @date 2018/11/24 12:05 PM
     * @param searchContent 预留的查询条件字段
     * @param pageInfo
     * @return com.github.pagehelper.PageInfo<com.cloud.entity.datacollection.ReportTemplate>
     */
    public PageInfo<ReportTemplate> findReportTemplateList(String searchContent,PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        return new PageInfo<ReportTemplate>(reportTemplateMapper.findReportTemplateList());
    }

    /**
     * 查询报告模板详情
     * @author Pan Jianneng
     * @date 2018/11/24 1:33 PM
     * @param reportTemplateId
     * @return com.cloud.entity.datacollection.ReportTemplate
     */
    public ReportTemplate findReportTemplateInfo(Long reportTemplateId){
        ReportTemplate rt = reportTemplateMapper.findReportTemplateInfo(reportTemplateId);
        return rt;
    }



}

