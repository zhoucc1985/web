package com.cloud.service.report;

import com.cloud.common.utils.ObjectUtils;
import com.cloud.entity.report.ReportVisitRecord;
import com.cloud.mapper.master.report.ReportVisitRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 报告详情访问记录 service
 *
 * @author huangYl
 * @date 2018/11/27
 **/
@Service
public class ReportVisitRecordService {

    private static final Logger logger = LoggerFactory.getLogger(ReportVisitRecordService.class);

    @Autowired
    private ReportVisitRecordMapper visitRecordMapper;

    /**
     * 新增
     *
     * @param record 报告详情访问实体
     * @author huangYl
     * @date 2018/11/27 11:33
     */
    public void save(ReportVisitRecord record) {
        visitRecordMapper.insert(record);
    }

    /**
     * 根据报告ID查询访问次数
     *
     * @param reportId 报告id
     * @return java.lang.Integer 报告访问次数
     * @author huangYl
     * @date 2018/11/27 11:34
     */
    public Integer selectCountByReportId(Long reportId) {
        Integer count = visitRecordMapper.selectCountByReportId(reportId);
        if (null == count) {
            count = 0;
        }
        return count;
    }

    /**
     * 根据报告ID查询全部IP地址列表
     *
     * @param reportId 报告id
     * @return java.util.Set
     * @author huangYl
     * @date 2018/11/27 11:51
     */
    public Set<String> selectIpListByReportId(Long reportId) {
        Set<String> reportVisitRecords = visitRecordMapper.selectIpListByReportId(reportId);
        if (null == reportVisitRecords || reportVisitRecords.size() == 0) {
            return new HashSet<>();
        }
        return reportVisitRecords;
    }

    /**
     * 查询根据报告id分组后的IP地址数据
     *
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/27 16:06
     */
    public Map<Long, List<String>> selectIpListGroupByReportId() {
        List<ReportVisitRecord> list = visitRecordMapper.selectAll();
        if (ObjectUtils.isEmptyList(list)) {
            return new ConcurrentHashMap<>(0);
        }
        Map<Long, List<String>> ipMap = new ConcurrentHashMap<>(20);
        for (ReportVisitRecord record : list) {
            if (ipMap.containsKey(record.getReportId())) {
                List<String> strings = ipMap.get(record.getReportId());
                strings.add(record.getIpAddress());
            } else {
                ArrayList<String> ipList = new ArrayList<>();
                ipList.add(record.getIpAddress());
                ipMap.put(record.getId(), ipList);
            }
        }
        return ipMap;
    }

    /**
     * 根据报告ID删除浏览记录
     *
     * @param reportId 报告id
     * @return void
     * @author huangYl
     * @date 2018/11/30 10:45
     */
    public void deleteByReportId(Long reportId) {
        visitRecordMapper.deleteByReportId(reportId);
    }
}
