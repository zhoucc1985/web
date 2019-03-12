package com.cloud.service.datacollect;

import com.cloud.common.qo.CheckQo;
import com.cloud.common.vo.datacollect.CollectBatchSearchVo;
import com.cloud.entity.datacollect.CollectImportDetail;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * 数据采集反馈-导入批次列表
 *
 * @author Pan jianneng
 * @time 2018/10/21 15:50
 */
@Service
public interface DataCollectService {
    /**
     * 批次列表查询
     * @param cbs
     * @return
     */
     PageInfo<T> queryPage(CollectBatchSearchVo cbs);
    /**
     * 删除当前批次数据
     * @param batchId
     * @return
     */
    void deleteBatchData(String batchId);
    /**
     * 完成当前批次导入 数据写到真实表
     * @param batchId 批次编号
     * @return
     */
    void saveDateToRealTable(String batchId);

    /**
     * 根据批次编号查询模板编号
     * @param batchId   批次编号
     * @return
     */
    Integer getTemplateIdByBatchId(String batchId);

    /**
     * 查询数据收集列表
     *
     * @param qo 数据收集列表
     * @return 分页对象
     */
    PageInfo showCollectCheckList(CheckQo qo);

    /**
     * 数据收集列表--查看详情
     * @param templateId 模板id
     * @return
     */
    Map<String,Object> showCollectCheckListDetails(Integer templateId);

    /**
     * 数据收集列表导出报告
     * @param userAgent 浏览器信息
     * @param response
     */
    void export(String userAgent, HttpServletResponse response) throws Exception;

    /**
     * 根据批次Id查找isImport是什么状态
     */
    String findisImport(String batchId);



    /**
     * 查询下拉框
     * @return map
     */
    Map<String,Object> showDropDownBox();
}
