package com.cloud.service.qualityReport;

import com.cloud.common.enums.QualityReportTypeEnum;
import com.cloud.common.utils.ConnectionUtil;
import com.cloud.common.utils.DBUtil;
import com.cloud.common.utils.DateUtils;
import com.cloud.common.utils.MyExcelUtil;
import com.cloud.common.utils.UserUtils;
import com.cloud.entity.datamanagement.DBDatasource;
import com.cloud.entity.datamanagement.TableInfo;
import com.cloud.entity.vo.PageParam;
import com.cloud.entity.vo.qualityReport.*;
import com.cloud.mapper.master.datamanagement.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.*;

/**
 * 数据质量校验报告 业务层
 *
 * @Author daituo
 * @Date 2019-1-17
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class QualityReportService {

    private static final Logger LOG = LoggerFactory.getLogger(QualityReportService.class);

    @Resource
    private DBDatasourceMapper dbDatasourceMapper;
    @Resource
    private TableInfoMapper tableInfoMapper;
    @Resource
    private FieldInfoMapper fieldInfoMapper;
    @Resource
    private FtpInfoMapper ftpInfoMapper;
    @Resource
    private TableStatMapper tableStatMapper;
    @Resource
    private FtpStatMapper ftpStatMapper;

    /**
     * 按系统分组查询系统下的所有表
     */
    public List<TableMenuVo> getAllTablesGroupBySystem() {
        List<TableMenuVo> list = dbDatasourceMapper.findAllTablesGroupBySystem();
        for (TableMenuVo tableMenuVo : list) {
            if (tableMenuVo.getTableInfoList() != null && tableMenuVo.getTableInfoList().size() > 0) {
                List<TableInfo> tableInfoList = tableMenuVo.getTableInfoList();
                tableMenuVo.setTableCount(tableInfoList.size());
            } else {
                tableMenuVo.setTableCount(0);
            }
        }
        return list;
    }


    /**
     * 查询指定系统下的所有表
     *
     * @param pageParam    分页对象
     * @param dataSourceId 数据源Id,一个系统对应一个数据源
     * @param sort
     * @return
     */
    public PageInfo<TableInfo> getAllTablesByDataSourceId(PageParam pageParam, Long dataSourceId, Long sort) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<TableInfo> tableInfoList = tableInfoMapper.getAllTablesByDataSourceId(dataSourceId, sort);
        return new PageInfo(tableInfoList);
    }


    /**
     * 查询指定table下的所有字段详情
     *
     * @param tableId   表Id
     * @param pageParam
     * @param sort
     * @return
     */
    public PageInfo<FieldDetailVo> getFieldsByTableId(Long tableId, PageParam pageParam, Long sort) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<FieldDetailVo> fieldDetailVoList = fieldInfoMapper.getFieldsByTableId(tableId, sort);
        return new PageInfo<>(fieldDetailVoList);
    }


    /**
     * 查询FTP质量详情列表
     *
     * @param pageParam
     * @param ftpId
     * @return
     */
    public PageInfo<FtpDetailVo> getFtps(PageParam pageParam, Long ftpId) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<FtpDetailVo> ftpDetailVoList = ftpInfoMapper.getFtps(ftpId);
        return new PageInfo<>(ftpDetailVoList);
    }


    /**
     * 查询数据库校验报告
     *
     * @param pageParam
     * @return
     */
    public PageInfo<DbQualityReportVo> getDbQualityReport(PageParam pageParam) {
    	//权限控制加入orgid
        String orgId = UserUtils.getInstance().getFindDataOrgCode();
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<DbQualityReportVo> dbQualityReportVoList = tableStatMapper.getDbQualityReport(orgId);
        return new PageInfo<>(dbQualityReportVoList);
    }


    /**
     * 查询FTP数据校验报告
     *
     * @param pageParam
     * @return
     */
    public PageInfo<FtpQualityReportVo> getFtpQualityReport(PageParam pageParam) {
    	//权限控制加入orgid
        String orgId = UserUtils.getInstance().getFindDataOrgCode();
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<FtpQualityReportVo> ftpQualityReportVoList = ftpStatMapper.getFtpQualityReport(orgId);
        return new PageInfo<>(ftpQualityReportVoList);
    }


    /**
     * 导出报告
     *
     * @param userAgent 浏览器信息
     * @param response
     * @param type      报告类型【 D-数据库 F-FTP】
     */
    public void export(String userAgent, HttpServletResponse response, String type) throws Exception {
        Workbook workbook = null;
        String fileName = null;
        QualityReportTypeEnum qualityReportType = QualityReportTypeEnum.getByCode(type);
        switch (qualityReportType) {
            case FTP:
            	//权限控制加入orgid
                String orgId = UserUtils.getInstance().getFindDataOrgCode();
                List<FtpQualityReportVo> ftpQualityReportVoList = ftpStatMapper.getFtpQualityReport(orgId);
                if (ftpQualityReportVoList.size() == 0) {
                    throw new Exception("当前页面没有数据");
                }
//                ftpQualityReportVoList.forEach(e -> e.setReportStatusDesc(e.getReportStatusDesc()));
                for (FtpQualityReportVo ftpQualityReportVo : ftpQualityReportVoList) {
                    ftpQualityReportVo.setReportStatusDesc(ftpQualityReportVo.getReportStatusDesc());
                    ftpQualityReportVo.setConnectionStatusDesc(ftpQualityReportVo.getConnectionStatusDesc());
                }
                fileName = QualityReportTypeEnum.FTP.getMessage() + DateUtils.formatDateTimeBreif(new Date()) + ".xls";
                workbook = MyExcelUtil.exportExecl(ftpQualityReportVoList, true, QualityReportTypeEnum.FTP.getMessage());
                break;
            case DATABASE:
            	//权限控制加入orgid
                String findDataOrgCode = UserUtils.getInstance().getFindDataOrgCode();
                List<DbQualityReportVo> dbQualityReportVoList = tableStatMapper.getDbQualityReport(findDataOrgCode);
                if (dbQualityReportVoList.size() == 0) {
                    throw new Exception("当前页面没有数据");
                }
                dbQualityReportVoList.forEach(e -> e.setReportStatusDesc(e.getReportStatusDesc()));
                fileName = QualityReportTypeEnum.DATABASE.getMessage() + DateUtils.formatDateTimeBreif(new Date()) + ".xls";
                workbook = MyExcelUtil.exportExecl(dbQualityReportVoList, true, QualityReportTypeEnum.DATABASE.getMessage());
                break;
            default:
                break;
        }
        MyExcelUtil.writeToResponse(fileName, userAgent, response, workbook);
    }


    /**
     * 指定表的数据预览，默认查询该表10条数据，封装成List<Map<field,value>>
     *
     * @param tableId
     * @param isPaging 是否分页
     * @return
     */
    public List<Map<String, Object>> dataPreview(Long tableId, boolean isPaging) {
        Connection connection = null;
        QueryRunner queryRunner = new QueryRunner();
        String pageSql = null;
        try {
            TableInfo tableInfo = tableInfoMapper.selectByPrimaryKey(tableId);
            DBDatasource datasource = dbDatasourceMapper.selectByPrimaryKey(tableInfo.getSourceId());
            connection = ConnectionUtil.getConnection(datasource.getDatabaseType(), datasource.getServiceIp(),
                    Integer.parseInt(datasource.getServiceProt()), datasource.getUserName(), datasource.getPassWord(),
                    datasource.getDbName(), datasource.getJdbcurl(), datasource.getVersion(),
                    datasource.getOracleType(),datasource.getDbName(),datasource.getDbName());
            //判断数据源的数据库类型
            if (datasource.getDatabaseType().equalsIgnoreCase("mysql")) {
                if (isPaging) {
                    pageSql = "select * from " + tableInfo.getTableName() + " limit 10";
                } else {
                    pageSql = "select * from " + tableInfo.getTableName();
                }
            }
            if (datasource.getDatabaseType().equalsIgnoreCase("oracle")) {
                if (isPaging) {
                    pageSql = "select * from " + tableInfo.getTableName() + " where rownum <= 10";
                } else {
                    pageSql = "select * from " + tableInfo.getTableName();
                }
            }
            if (datasource.getDatabaseType().equalsIgnoreCase("sqlserver")) {
                if (isPaging) {
                    /**
                     * 在sqlserver2005之前一直借助top关键字来实现分页查询，不过效率低
                     * 在sqlserver2005及其之后的版本都使用row_number() over(order by id)函数来完成分页查询，效率有了很大的提高，不过sql语句比较复杂
                     */
                    //pageSql = "select top 10 o.* from (select row_number() over(order by id) as rownumber,* from(select * from "+ tableInfo.getTableName() +") as oo ) as o where rownumber > 0";
                    pageSql = "select top 10 * from " + tableInfo.getTableName();
                } else {
                    pageSql = "select * from " + tableInfo.getTableName();
                }
            }
            return queryRunner.query(connection, pageSql, new MapListHandler());
        } catch (Exception e) {
            LOG.error("QualityReportService.dataPreview() has exception:{}", e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection);
        }
    }


    /**
     * 导出指定表的预览数据详情
     *
     * @param userAgent
     * @param response
     * @param tableId   表id
     */
    public void dataPreviewExport(String userAgent, HttpServletResponse response, Long tableId) {
        TableInfo tableInfo = tableInfoMapper.selectByPrimaryKey(tableId);
        List<Map<String, Object>> dataList = dataPreview(tableId, false);
        Workbook workbook = MyExcelUtil.exportDynamicTableExecl(dataList, true, tableInfo.getTableName());
        MyExcelUtil.writeToResponse(tableInfo.getTableName() + ".xls", userAgent, response, workbook);
    }

    /**
     * 查询数据库下所有的空表的名称List
     *
     * @param id
     * @return
     */
    public Map<String, Object> viewEmptyTableList(Integer id) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        List<Map<String, Object>> emptyTableList = tableStatMapper.findEmptyTableList(id);
        resultMap.put("emptyTableTotal", emptyTableList.size());
        resultMap.put("emptyTableTotalList", emptyTableList);
        return resultMap;
    }

    /**
     * 将空表list导成Excel
     *
     * @param response
     * @param id
     */
    public void exportEmptyTableList(HttpServletResponse response, HttpServletRequest request, Integer id) throws UnsupportedEncodingException {
        String fileName = "EmptyTables.xls";
        List<Map<String, Object>> emptyTableList = tableStatMapper.findEmptyTableList(id);
        Workbook workbook = MyExcelUtil.exportDynamicTableExecl(emptyTableList, true, fileName);
        MyExcelUtil.writeToResponse("EmptyTables.xls", request.getHeader("User-Agent"), response, workbook);
    }

    /**
     * 查询FTP下所有的空表List
     *
     * @param id ftpid
     * @return map
     */
    public Map<String, Object> viewEmptyFTPTableList(Integer id) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        List<Map<String, Object>> emptyTableList = fieldInfoMapper.findEmptyTableList(id);
        resultMap.put("emptyTableTotal", emptyTableList.size());
        resultMap.put("emptyTableTotalList", emptyTableList);
        return resultMap;
    }

    /**
     * 数据校验报告对每个数据源导出报告
     *
     * @param userAgent
     * @param response
     * @param id        id
     */
    public void exportBySystemId(String userAgent, HttpServletResponse response, Long id) throws Exception {
        Workbook workbook = null;
        String fileName = null;

        DbQualityReportVo dbQualityReportVo = dbDatasourceMapper.getDBQualityReportById(id);
        dbQualityReportVo.setReportStatusDesc(dbQualityReportVo.getReportStatusDesc());
        List<EmptyTableVo> emptyTableVoList = dbDatasourceMapper.getEmptyTableById(id);
        List<TableInfoVo> tableInfoVoList = tableInfoMapper.getTableRecord(id);
        Map<String, Object> map = new HashMap<>();
        map.put("dbQualityReportVo", dbQualityReportVo);
        map.put("emptyTableVoList", emptyTableVoList);
        map.put("tableInfoVoList", tableInfoVoList);



//                ftpQualityReportVoList.forEach(e -> e.setReportStatusDesc(e.getReportStatusDesc()));

        fileName = "数据源检验报告-" + dbQualityReportVo.getSystemName() + ".xls";
        workbook = MyExcelUtil.exportExeclOfMostSheet(map, true);
        MyExcelUtil.writeToResponse(fileName, userAgent, response, workbook);
    }
    /**
     * 同步数据库校验详情
     * @param id db数据源Id
     */
    public void synchroDataBase(Integer id){
    	//删除对应id的统计表信息（t_table_stat）
    	tableStatMapper.deleteTableStatBySourceId(id);
    	//删除对应id的t_table_info
    	tableInfoMapper.deleteTableInfoBySourceId(id);
    	//修改dbDatesource的report_status状态为初始化（4）
    	dbDatasourceMapper.updateDBDataSourceById(id);
    }
}
