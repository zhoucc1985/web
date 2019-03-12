package com.cloud.service.datamanagement;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.qo.DBManagementQo;
import com.cloud.common.utils.ConnectionUtil;
import com.cloud.common.utils.MyExcelUtil;
import com.cloud.common.utils.UserUtils;
import com.cloud.common.utils.cloud.HttpUtils;
import com.cloud.entity.datamanagement.DBDatasource;
import com.cloud.entity.datamanagement.HttpDBDataSource;
import com.cloud.mapper.master.datamanagement.DBDatasourceMapper;
import com.cloud.mapper.master.datamanagement.FieldInfoMapper;
import com.cloud.mapper.master.datamanagement.TableInfoMapper;
import com.cloud.mapper.master.datamanagement.TableStatMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional(rollbackFor = Exception.class)
public class DBManagementService {

    private static Logger logger = LoggerFactory.getLogger(DBManagementService.class);
    private static  String bigdataServerPath;
    @Value("${bigdataserverpath}")
    public void setBigdataServerPath(String bigdataServerPath){
        this.bigdataServerPath=bigdataServerPath;
    }

    @Autowired
    private DBDatasourceMapper datasourceMapper;
    @Autowired
    private TableStatMapper tableStatMapper;
    @Autowired
    private TableInfoMapper tableInfoMapper;
    @Autowired
    private FieldInfoMapper fieldInfoMapper;
    /**
     * 文件上传临时保存目录地址
     */
    @Value("${data-collection-upload-file-path}")
    private String path;

    /**
     * 分页查询列表
     *
     * @param qo 查询对象
     * @return 分页对象
     */
    public PageInfo queryPage(DBManagementQo qo) {
    	String orgId = UserUtils.getInstance().getFindDataOrgCode();
        qo.setOrgId(orgId);
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<DBDatasource> list = datasourceMapper.queryPage(qo);
        return new PageInfo(list);
    }

    /**
     * 连接测试
     *
     * @param datasource 数据源对象
     * @throws Exception 抛出异常
     */
    public Connection connectTest(DBDatasource datasource) {
        Connection conn = ConnectionUtil.getConnection(datasource.getDatabaseType(), datasource.getServiceIp(),
                Integer.parseInt(datasource.getServiceProt()), datasource.getUserName(), datasource.getPassWord(),
                datasource.getDbName(), datasource.getJdbcurl(), datasource.getVersion(),
                datasource.getOracleType(),datasource.getDbName(),datasource.getDbName());
        return conn;
    }

    /**
     * 删除数据源
     *
     * @param dataSourceId db数据源id
     */
    public void delete(Long dataSourceId) {
        datasourceMapper.changeStatus(dataSourceId);
    }

    /**
     * 编辑DB数据源
     *
     * @param datasource 数据源对象
     */
    public void editDataSource(DBDatasource datasource) throws Exception {
        Pattern p = Pattern.compile("[a-zA-z]");
        if (p.matcher(datasource.getServiceIp()).find()) {
            throw new Exception("IP不得含有英文");
        }
        if (p.matcher(datasource.getServiceProt()).find()) {
            throw new Exception("端口号不得含有英文");
        }

        String systemVersion = datasource.getSystemVersion();
        String systemName = datasource.getSystemName();
        String brandName = datasource.getBrandName();
        String dbFile = datasource.getDbFile();
        String sourceName = datasource.getSourceName();
        if (sourceName != null && sourceName.length() > 50) {
            throw new Exception("数据源名称长度不能超过50");
        }
        if (systemVersion != null && systemVersion.length() > 10) {
            throw new Exception("系统版本号长度不能超过10");
        }
        if (systemName != null && systemName.length() > 50) {
            throw new Exception("系统名称长度不能超过50");
        }
        if (brandName != null && brandName.length() > 20) {
            throw new Exception("品牌名称长度不能超过20");
        }
        if (dbFile != null && dbFile.length() > 128) {
            throw new Exception("数据库文件名长度不能超过128");
        }
        Connection connection = connectTest(datasource);
        if (connection != null) {
            datasource.setIsConnection(1);
        } else {
            datasource.setIsConnection(2);
        }
        datasource.setReportStatus(4);//修改状态为4.初始化
        datasource.setModifiedTime(new Date());
        datasource.setType(1);
        datasource.setStatus(1);
        datasource.setIsPush(2);
        //权限控制加入orgid
        String findDataOrgCode = UserUtils.getInstance().getFindDataOrgCode();
        datasource.setOrgId(findDataOrgCode);
        if (datasource.getId() == null) {
            datasourceMapper.insert(datasource);
        } else {
            datasourceMapper.updateByPrimaryKey(datasource);
        }

    }

    /**
     * 导入DBExcel版本
     *
     * @param file
     */
//    @Async("asyncExecutor")
    public String importDB(MultipartFile file) throws Exception {
        String filePath = MyExcelUtil.saveFilePath(file, path);
        return filePath;

    }


    @Async("asyncExecutor")
    public void asyncInsertDB(List<DBDatasource> list) throws Exception {
    	String findDataOrgCode = UserUtils.getInstance().getFindDataOrgCode();
        for (DBDatasource dbDatasource : list) {
            //连接是否成功
            Connection connection = connectTest(dbDatasource);
            dbDatasource.setModifiedTime(new Date());
            dbDatasource.setStatus(1);
            dbDatasource.setType(2);
            dbDatasource.setReportStatus(4);
            if (connection != null) {//连接成功
                dbDatasource.setIsConnection(1);
                Long ftpId = datasourceMapper.selectIncrId();
                dbDatasource.setId(ftpId);
                dbDatasource.setOrgId(findDataOrgCode);
                //生成报告
//                TaskQueue.getTaskQueue().put(ftpdatasource);
            } else {//连接失败
                dbDatasource.setIsConnection(2);
                dbDatasource.setOrgId(findDataOrgCode);
            }
            datasourceMapper.insert(dbDatasource);
        }

    }


    public boolean  collectDataSource(Long dataSourceId) {
        DBDatasource datasource = datasourceMapper.selectByPrimaryKey(dataSourceId);
        HttpDBDataSource httpDBDataSource=getHttpDBDataSource(datasource);
       String resultStr =  HttpUtils.sendPost(bigdataServerPath,"obj="+httpDBDataSource.toString());
        JSONObject obj= JSONObject.parseObject(resultStr);
        if(obj.get("success")!=null&&(Boolean)obj.get("success")==true){
            datasourceMapper.updateToIsPushById(dataSourceId);
            return true;
        }
        return false;
    }
    public HttpDBDataSource getHttpDBDataSource( DBDatasource datasource ){
        HttpDBDataSource httpDBDataSource=new HttpDBDataSource();
        httpDBDataSource.setSource_id(datasource.getId());
        httpDBDataSource.setHost(datasource.getServiceIp());
        httpDBDataSource.setPort(datasource.getServiceProt());
        httpDBDataSource.setOracle_param_name(datasource.getDbName());
        httpDBDataSource.setOracle_type(datasource.getOracleType()==null?null:datasource.getOracleType()-1);
        httpDBDataSource.setVersion_type(datasource.getVersion()==null?null:Integer.parseInt(datasource.getVersion())-1);
        httpDBDataSource.setDatabase_file(null);
        httpDBDataSource.setUser_name(datasource.getUserName());
        httpDBDataSource.setPassword(datasource.getPassWord());
        httpDBDataSource.setDatabase_name(datasource.getDbName());
        httpDBDataSource.setDatabase_type(getDataBaseType(datasource.getDatabaseType()));
        httpDBDataSource.setSource_name(datasource.getSourceName());
        httpDBDataSource.setDb_target_name(datasource.getDbName());
        httpDBDataSource.setDb_type("1");
        System.out.println(httpDBDataSource.toString());
        return httpDBDataSource;
    }
    /**
     *     MySQL:1,SQLServer:2,Oracle:3,
     */

    public Integer getDataBaseType(String databaseType){
        if(databaseType==null) return null;
        Integer num=null;
        switch (databaseType.toLowerCase()){
            case "mysql":
                num=1; break;
            case "sqlserver":
                num=2;break;
            case "oracle":
                num=3;break;
        }
        return num;
    }

}
