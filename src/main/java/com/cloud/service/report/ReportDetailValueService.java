package com.cloud.service.report;

import com.cloud.entity.report.ReportDetailValue;
import com.cloud.mapper.master.report.ReportDetailValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 报告详情属性值 service
 *
 * @author huangYl
 * @date 2018/11/19
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportDetailValueService {

    private static final Logger logger = LoggerFactory.getLogger(ReportDetailValueService.class);

    @Autowired
    private ReportDetailValueMapper valueMapper;

    /**
     * 保存报告详情的属性值
     *
     * @param repId           报告id
     * @param repDetailAttrId 报告属性id
     * @param value           属性值
     * @author huangYl
     * @date 2018/11/19 14:27
     */
    public void save(Long repId, Long repDetailAttrId, String value) {
        ReportDetailValue reportDetailValue = ReportDetailValue.builder().reportId(repId).reportDetailAttributeId(repDetailAttrId).value(value).build();
        valueMapper.insert(reportDetailValue);
    }

    /**
     * 根据报告ID删除所有对应的属性值
     *
     * @param reportId 报告id
     * @return void
     * @author huangYl
     * @date 2018/11/22 20:09
     */
    public void deleteByReportId(Long reportId) {
        valueMapper.deleteByReportId(reportId);
    }
}
