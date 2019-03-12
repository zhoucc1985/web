package com.cloud.service.datacollect.impl;

import com.cloud.common.enums.QualityReportTypeEnum;
import com.cloud.common.qo.CheckQo;
import com.cloud.common.utils.DateUtils;
import com.cloud.common.utils.MyExcelUtil;
import com.cloud.common.utils.UserUtils;
import com.cloud.common.utils.WebUtils;
import com.cloud.common.vo.datacollect.CollectBatchSearchVo;
import com.cloud.controller.datacollect.DataCollectController;
import com.cloud.entity.datacollect.CollectEnum;
import com.cloud.entity.datacollect.CollectImportDetail;
import com.cloud.entity.vo.dataCollect.CollectDetailsVo;
import com.cloud.entity.vo.dataCollect.DetailsListVo;
import com.cloud.mapper.master.datacollect.CollectEnumMapper;
import com.cloud.mapper.master.datacollect.CollectImportDetailMapper;
import com.cloud.mapper.master.datacollect.CollectTemplateMapper;
import com.cloud.mapper.master.datacollect.UnValidDataDetailMapper;
import com.cloud.service.datacollect.DataCollectService;
import com.cloud.service.datacollect.RealTableService;
import com.cloud.service.datacollect.TempTableService;
import com.cloud.service.datacollect.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

@Service
public class DataCollectServiceImpl implements DataCollectService {
	private static final Logger logger = LoggerFactory.getLogger(DataCollectController.class);
	@Autowired
	private CollectImportDetailMapper collectImportDetailMapper;
    @Autowired
    private CollectTemplateMapper collectTemplateMapper;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private TempTableService tempTableService;
	@Autowired
	private RealTableService realTableService;
	@Resource
	private UnValidDataDetailMapper unValidDataDetailMapper;
    @Resource
    private CollectEnumMapper collectEnumMapper;

	@Override
	public PageInfo queryPage(CollectBatchSearchVo cbs) {
		//权限控制加入orgid
        String orgId = UserUtils.getInstance().getFindDataOrgCode();
		PageHelper.startPage(cbs.getPageNum(), cbs.getPageSize());
		List<CollectImportDetail> list = collectImportDetailMapper.queryPage(cbs.getReportName(), cbs.getStartTime(),
				cbs.getEndTime() , orgId);
		return new PageInfo(list);
	}

    @Transactional
    @Override
    public void deleteBatchData(String batchId) {
        // 1、查询到表名称
        String tempTableName = null;
        String realTableName = null;
        List<Map<String, Object>> list = templateService.findTableNamesByBatchId(batchId);
        if (list.size() > 0) {
            tempTableName = WebUtils.getString(list.get(0).get("tempTableName"));
            realTableName = WebUtils.getString(list.get(0).get("realTableName"));
        }
        // 2、删除临时表数据
        if(tempTableName!=null && tempTableName!="" && batchId!=null && batchId!=""){
        	tempTableService.deleteTempDateByBatchId(batchId, tempTableName);
        }
        // 3、删除真实表数据
        if(realTableName!=null && realTableName!="" && batchId!=null && batchId!=""){
        	realTableService.deleteRealTableDataByBatchId(batchId, realTableName);
        }
        // 4、删除批次信息
        collectImportDetailMapper.deleteByBatchId(batchId);
    }

    @Override
    public void saveDateToRealTable(String batchId) {
        // 查询到表名称
        String tempTableName = null;
        String realTableName = null;
        List<Map<String, Object>> list = templateService.findTableNamesByBatchId(batchId);
        if (list.size() > 0) {
            tempTableName = WebUtils.getString(list.get(0).get("tempTableName"));
            realTableName = WebUtils.getString(list.get(0).get("realTableName"));
        }
        // 查询到对应的字段名称
        List<Map<String, Object>> columnList = unValidDataDetailMapper.findColumnsTbaleList(batchId, tempTableName);
        String[] enTitleArr = new String[columnList.size()];
        int i = 0;
        for (Map<String, Object> map : columnList) {
            enTitleArr[i] = map.get("en_column").toString();
            i += 1;
        }
        String resultStr = "";
        for (int x = 0; x < enTitleArr.length; x++) {
            if (x == enTitleArr.length - 1) {
                resultStr += enTitleArr[x];
            } else {
                resultStr += enTitleArr[x];
                resultStr += ", ";
            }
        }
        //临时表有效数据导入到真实表,修改表状态
        realTableService.insertRealTableName(resultStr, tempTableName, realTableName,batchId);
        realTableService.updateImportDetail(batchId);
    }

	/**
	 * 根据批次编号查询模板ID
	 * @param batchId   批次编号
	 * @return
	 */
	@Override
	public Integer getTemplateIdByBatchId(String batchId) {

		return collectImportDetailMapper.getTemplateIdByBatchId(batchId);
	}
    @Override
    public PageInfo showCollectCheckList(CheckQo qo) {
    	 //权限控制加入orgid
        String orgId = UserUtils.getInstance().getFindDataOrgCode();
        qo.setOrgId(orgId);
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<CollectDetailsVo> list = collectTemplateMapper.queryPage(qo);
        //设置枚举列
        list = setEnum(list);
        return new PageInfo(list);
    }
    @Override
    public Map<String, Object> showCollectCheckListDetails(Integer templateId) {
    	String orgId = UserUtils.getInstance().getFindDataOrgCode();
        List<DetailsListVo> list = collectTemplateMapper.selectDetailsList(templateId,orgId);
        Integer allEffectiveNum = 0;
        for (DetailsListVo vo : list) {
            allEffectiveNum += vo.getEffectiveNum();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("all", allEffectiveNum);
        map.put("list", list);
        return map;
    }

    @Override
    public void export(String userAgent, HttpServletResponse response) throws Exception {
        Workbook workbook = null;
        String fileName = null;
        List<CollectDetailsVo> list = collectTemplateMapper.selectAll();
        if (list.size() == 0) {
            throw new Exception("当前页面没有数据");
        }
        //设置枚举列
        list = setEnum(list);
        fileName = QualityReportTypeEnum.COLLECTION.getMessage() + DateUtils.formatDateTimeBreif(new Date()) + ".xls";
        workbook = MyExcelUtil.exportExecl(list, true, QualityReportTypeEnum.COLLECTION.getMessage());
        MyExcelUtil.writeToResponse(fileName, userAgent, response, workbook);
    }

    /**
     * 设置枚举列
     * @param list 集合
     * @return
     */
    private List<CollectDetailsVo> setEnum(List<CollectDetailsVo> list) {
        for (CollectDetailsVo vo : list) {
            Integer systemNameCode = vo.getSystemNameCode();
            Map<String,Object> map = collectTemplateMapper.selectSystemCode(systemNameCode);
            vo.setSystemName((String) map.get("name"));

            Integer firstTypeCode = vo.getFirstTypeCode();
            String firstType = collectEnumMapper.selectEnumFirst(firstTypeCode,(Integer)map.get("id"));
            vo.setFirstType(firstType);

            Integer secondTypeCode = vo.getSecondTypeCode();
            String secondType = collectEnumMapper.selectEnumSecond(secondTypeCode);
            vo.setSecondType(secondType);
        }
        return list;
    }
    @Override
    public Map<String, Object> showDropDownBox() {
        List<CollectEnum> list = collectEnumMapper.selectCollectEnum();
        List<CollectEnum> system = new ArrayList<>();
        List<CollectEnum> firstType = new ArrayList<>();
        List<CollectEnum> secondType = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (CollectEnum e : list) {
            if ("系统".equals(e.getType())) {
                system.add(e);
            } else if ("业务系统分类".equals(e.getType())) {
                firstType.add(e);
            } else {
                secondType.add(e);
            }
        }

        map.put("system", system);
        map.put("firstType", firstType);
        map.put("secondType", secondType);
        return map;
    }

	@Override
	public String findisImport(String batchId) {
		return collectTemplateMapper.findisImport(batchId);
	}
}
