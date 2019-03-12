package com.cloud.service.report;

import com.cloud.entity.report.ReportDetailAttribute;
import com.cloud.mapper.master.report.ReportDetailAttributeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 报告详情属性 service
 *
 * @author huangYl
 * @date 2018/11/19 13:55
 **/
@Service
public class ReportDetailAttributeService {

    private static final Logger logger = LoggerFactory.getLogger(ReportDetailAttributeService.class);

    @Autowired
    private ReportDetailAttributeMapper attributeMapper;

    /**
     * 根据模板id获取该模板需要呈现的所有字段
     *
     * @param rtId 模板id
     * @return java.util.Map<java.lang.String , java.lang.Long>
     * @author huangYl
     * @date 2018/11/19 13:59
     */
    public Map<String, Long> getAllFieldByRtId(Long rtId) {
        List<ReportDetailAttribute> list = attributeMapper.getAllFieldByRtId(rtId);
        Map<String, Long> result = list.stream().collect(Collectors.toMap(ReportDetailAttribute::getName, ReportDetailAttribute::getId));
        return result;
    }
}
