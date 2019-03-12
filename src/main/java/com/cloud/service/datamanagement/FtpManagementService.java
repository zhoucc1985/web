package com.cloud.service.datamanagement;

import com.cloud.common.qo.FtpManagementQo;
import com.cloud.common.utils.FileUtil;
import com.cloud.common.utils.FtpUtil;
import com.cloud.common.utils.JschUtil;
import com.cloud.common.utils.MyExcelUtil;
import com.cloud.common.utils.UserUtils;
import com.cloud.entity.datamanagement.FileInfo;
import com.cloud.entity.datamanagement.FtpStat;
import com.cloud.entity.datamanagement.Ftpdatasource;
import com.cloud.mapper.master.datamanagement.FtpInfoMapper;
import com.cloud.mapper.master.datamanagement.FtpStatMapper;
import com.cloud.mapper.master.datamanagement.FtpdatasourceMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcraft.jsch.ChannelSftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

@Service
@Transactional(rollbackFor = Exception.class)
public class FtpManagementService {

    private static Logger logger = LoggerFactory.getLogger(FtpManagementService.class);

    @Autowired
    private FtpdatasourceMapper ftpdatasourceMapper;
    @Autowired
    private FtpInfoMapper ftpInfoMapper;
    @Autowired
    private FtpStatMapper ftpStatMapper;
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
    public PageInfo queryPage(FtpManagementQo qo) {
    	String orgId = UserUtils.getInstance().getFindDataOrgCode();
        qo.setOrgId(orgId);
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<Ftpdatasource> list = ftpdatasourceMapper.queryPage(qo);
        return new PageInfo(list);
    }

    /**
     * 编辑ftp数据源
     *
     * @param ftpdatasource 数据源对象
     */
    public void editDataSource(Ftpdatasource ftpdatasource) throws Exception {
        Pattern p = Pattern.compile("[a-zA-z]");
        if (p.matcher(ftpdatasource.getFtpIp()).find()) {
            throw new Exception("IP不得含有英文");
        }
        if (p.matcher(ftpdatasource.getFtpPort()).find()) {
            throw new Exception("端口号不得含有英文");
        }
        Map<String, Object> map = stat(ftpdatasource);
        Ftpdatasource ftpDS = (Ftpdatasource) map.get("ftpdatasource");
        ftpDS.setModifiedTime(new Date());
        ftpDS.setReportStatus(4);
        ftpDS.setType(1);
        ftpDS.setStatus(1);
        //权限控制加入orgid
        String findDataOrgCode = UserUtils.getInstance().getFindDataOrgCode();
        ftpdatasource.setOrgId(findDataOrgCode);
        if (ftpdatasource.getId() != null) {
            ftpdatasourceMapper.updateByPrimaryKey(ftpdatasource);
        } else {
            ftpdatasourceMapper.insert(ftpdatasource);
        }

    }

    /**
     * 拼装对象
     *
     * @param ftpdatasource 数据源对象
     * @return map
     */
    public Map<String, Object> stat(Ftpdatasource ftpdatasource) {
        String ip = ftpdatasource.getFtpIp();
        String connType = ftpdatasource.getConnType();
        Map<String, Object> map = new HashMap<>();
        if ("ftp".equals(connType)) {//ftp连接
            FTPClient ftpClient = new FTPClient();
            boolean isLogin = FtpUtil.ftpLogin(ftpClient, ip, (int) Double.parseDouble(ftpdatasource.getFtpPort()),
                    ftpdatasource.getUserName(), ftpdatasource.getPassWord());
            ftpdatasource = toLogin(isLogin, ip, ftpdatasource);
            map.put("util", ftpClient);

        } else {//sftp连接
            JschUtil jschUtil = new JschUtil(ip, (int) Double.parseDouble(ftpdatasource.getFtpPort()), 60000,
                    ftpdatasource.getUserName(), ftpdatasource.getPassWord());
            boolean isLogin = jschUtil.login();
            ftpdatasource = toLogin(isLogin, ip, ftpdatasource);
            map.put("util", jschUtil);
        }

        map.put("ftpdatasource", ftpdatasource);
        return map;
    }

    /**
     * 判断是否登录并修改状态
     *
     * @param isLogin       连接状态
     * @param ip            当前ftpIp
     * @param ftpdatasource 数据源对象
     * @return 数据源对象
     */
    private Ftpdatasource toLogin(boolean isLogin, String ip, Ftpdatasource ftpdatasource) {
        if (isLogin) {
            ftpdatasource.setIsConnection(1);
            logger.info("恭喜" + ip + "成功登陆FTP服务器");
        } else {
            ftpdatasource.setIsConnection(2);
            logger.error(ip + "登录FTP服务失败！");
        }
        return ftpdatasource;
    }

    /**
     * sftp扫描
     *
     * @param jschUtil sftp工具类
     * @param path     路径
     * @param ftpId    ftpId
     * @param num      文件数
     * @param notNull  非空文件数
     * @param size     文件空间大小
     */
    public void sftpScanning(JschUtil jschUtil, String path, Long ftpId, int num, int notNull, int size) {
        Vector<ChannelSftp.LsEntry> list = jschUtil.listWithAttrs(path);
        if (list != null && list.size() > 0) {
            num += list.size();
            for (ChannelSftp.LsEntry entry : list) {
                if (jschUtil.filter(entry, JschUtil.Filter.FILE)) {
                    if (entry.getAttrs().getSize() > 0) {
                        //统计非空文件和文件空间大小
                        notNull++;
                        size += entry.getAttrs().getSize();
                    }
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFtpId(ftpId);
                    fileInfo.setFileName(entry.getFilename());
                    fileInfo.setFilePath(path);
                    fileInfo.setFileSize(entry.getAttrs().getSize() + "");
                    ftpInfoMapper.insert(fileInfo);
                } else if (jschUtil.filter(entry, JschUtil.Filter.DIR)) {//文件夹
                    sftpScanning(jschUtil, path + "/" + entry.getFilename(), ftpId, num, notNull, size);
                }
            }
        }
        toStat(ftpId, num, notNull, size);
    }

    /**
     * ftp文件扫描
     *
     * @param ftpClient ftp扫描工具类
     * @param path      路径
     * @param ftpId     ftpId
     * @param num       文件数
     * @param notNull   非空文件数
     * @param size      文件空间大小
     */
    public void ftpScanning(FTPClient ftpClient, String path, Long ftpId, int num, int notNull, int size) {
        try {
            FTPFile[] files = ftpClient.listFiles(path);
            num += files.length;
            for (FTPFile ftpFile : files) {
                String fileName = ftpFile.getName();
                if (ftpFile.getType() == 1) { // 扫描子目录
                    path = path + "/" + fileName;
                    ftpScanning(ftpClient, path, ftpId, num, notNull, size);
                } else { // 处理文件
                    if (ftpFile.getSize() > 0) {
                        notNull++;
                        size += ftpFile.getSize();
                    }
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFtpId(ftpId);
                    fileInfo.setFilePath(path);
                    fileInfo.setFileName(fileName);
                    fileInfo.setFileSize(ftpFile.getSize() + ""); // Return the file size in bytes.
                    ftpInfoMapper.insert(fileInfo);
                }
            }

            toStat(ftpId, num, notNull, size);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 统计ftp信息
     *
     * @param ftpId   ftpId
     * @param num     文件数
     * @param notNull 非空文件数
     * @param size    文件空间大小
     */
    public void toStat(Long ftpId, int num, int notNull, int size) {
        FtpStat ftpStat = new FtpStat();
        int isnull = num - notNull;//空文件数
        BigDecimal rate = new BigDecimal(isnull).divide(new BigDecimal(num), 2, BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal(100));//空表率
        ftpStat.setFtpId(ftpId);
        ftpStat.setFileNum(num);
        ftpStat.setEmptyNum(isnull);
        ftpStat.setNoemptyNum(notNull);
        ftpStat.setFileSize(size + "");
        ftpStat.setRate(rate + "");
        ftpStatMapper.insert(ftpStat);
    }

    /**
     * 测试连接
     *
     * @param ftpdatasource 数据源对象
     */
    public boolean connectTest(Ftpdatasource ftpdatasource) {
        FTPClient ftpClient = new FTPClient();
        String connType = ftpdatasource.getConnType();
        boolean isLogin = false;
        if ("ftp".equals(connType)) {
            //ftp
            isLogin = FtpUtil.ftpLogin(ftpClient, ftpdatasource.getFtpIp(), (int) Double.parseDouble(ftpdatasource.getFtpPort()),
                    ftpdatasource.getUserName(), ftpdatasource.getPassWord());
        } else {
            //sftp
            isLogin = new JschUtil(ftpdatasource.getFtpIp(), (int) Double.parseDouble(ftpdatasource.getFtpPort()), 60000,
                    ftpdatasource.getUserName(), ftpdatasource.getPassWord()).login();
        }

        return isLogin;
    }

    /**
     * 导入ftp模板
     *
     * @param file 文件对象
     * @throws Exception 异常
     */
//    @Async("asyncExecutor")
    public String importFtp(MultipartFile file) throws Exception {
        String filePath = MyExcelUtil.saveFilePath(file, path);
        return filePath;
    }

    @Async("asyncExecutor")
    public void asyncImportFtp(List<Ftpdatasource> list) {
    	String findDataOrgCode = UserUtils.getInstance().getFindDataOrgCode();
    	for (Ftpdatasource ftpdatasource : list) {
            //连接是否成功
            boolean isLogin = connectTest(ftpdatasource);
            ftpdatasource.setModifiedTime(new Date());
            ftpdatasource.setStatus(1);
            ftpdatasource.setType(2);
            ftpdatasource.setReportStatus(4);
            if (isLogin) {//连接成功
                ftpdatasource.setIsConnection(1);
                Long ftpId = ftpdatasourceMapper.selectIncrId();
                ftpdatasource.setId(ftpId);
                ftpdatasource.setOrgId(findDataOrgCode);
                //生成报告
//                TaskQueue.getTaskQueue().put(ftpdatasource);
            } else {//连接失败
                ftpdatasource.setIsConnection(2);
                ftpdatasource.setOrgId(findDataOrgCode);
            }

            ftpdatasourceMapper.insert(ftpdatasource);
        }
    }

    /**
     * 删除数据源（修改状态为-1）
     *
     * @param ftpId ftp数据源id
     */
    public void delete(Long ftpId) {
        ftpdatasourceMapper.changeStatus(ftpId);
    }
}
