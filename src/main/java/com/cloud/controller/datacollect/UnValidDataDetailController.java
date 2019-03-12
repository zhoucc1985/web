package com.cloud.controller.datacollect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.MyExcelUtil;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.mapper.master.datacollect.CollectImportDetailMapper;
import com.cloud.mapper.master.datacollect.UnValidDataDetailMapper;
import com.cloud.service.datacollect.UnValidDataDetailService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 无效数据详情
 * 
 * @author zengqh
 * @time 2019/01/10 15:42
 */
@Api(tags = "数据采集反馈2", description = "无效数据详情-UnValidDataDetailController")
@RestController
@RequestMapping(value = "/UnValidDataDetail")
public class UnValidDataDetailController {

	private static final Logger logger = LoggerFactory.getLogger(UnValidDataDetailController.class);

	@Resource
	private UnValidDataDetailService unValidDataDetailService;
	@Resource
	private UnValidDataDetailMapper unValidDataDetailMapper;
	@Resource
	protected CollectImportDetailMapper collectImportDetailMapper;

	/**
	 * 无效数据信息统计（批次号、导入时间、无效数据数量等）
	 * 
	 * @return
	 */
	@ApiOperation(value = "无效数据信息统计--程涛--已完成", notes = "不合法数据信息统计（批次号、导入时间、无效数据数量等）")
	@RequestMapping(value = "/invalidDataStat", method = RequestMethod.GET)
	@ApiImplicitParam(name = "batchId", value = "批次ID", paramType = "query", dataType = "String")
	public Map<String, Object> invalidDataStat(@RequestParam(value = "batchId") String batchId) {

		Map<String, Object> resultMap = new HashMap<>();

		try {
			return CommonUtils.getSucResultMap(unValidDataDetailService.invalidDataStat(batchId));
		} catch (Exception e) {
			logger.error("查询无效数据信息统计出错了" + e);
			resultMap = CommonUtils.getErrorResultMap("查询无效数据信息统计失败了");
		}

		return resultMap;
	}

	/**
	 * 分页查询错误列表(新 pageInfo)
	 * @author Pan Jianneng
	 * @date 2018/11/23 2:56 PM
	 * @param batchId
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 */
	@ApiOperation(value = "查询无效数据列表(分页)-程涛-已完成（改后）",notes = "根据批次ID查询该批次导入有错误的数据列表")
	@RequestMapping(value = "/fail-list/{batchId}",method = RequestMethod.GET)
	public Map<String,Object> showInvalidDataList(@RequestParam(value="page") Integer page,
												  @RequestParam(value="pageSize") Integer pageSize,
												  @RequestParam(value="batchId") String batchId){
		try {
			PageInfo<List<Map<String,Object>>> pageInfo=unValidDataDetailService.showInvalidDataList(page,pageSize,batchId);
			return CommonUtils.getSucResultMap(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonUtils.getErrorResultMap("分页查询错误列表出错了");
		}
	}

	/**
	 * 修改保存更新无效数据(NEW 新的) entity 查询信息，包含分页信息
	 * 
	 * @return
	 */
	@ApiOperation(value = "修改更新无效数据--程涛--已完成", notes = "修改无效数据项后，更新不合法数据")
	@RequestMapping(value = "/updateInvalidData", method = RequestMethod.POST)
	public Map<String, Object> updateInvalidData(@RequestParam(name = "batchId") String batchId,
			@RequestParam(name = "id") Long id, @RequestBody Map<String, Object> columnDataMap) {


		try {
			if (ObjectUtils.isNullObject(columnDataMap)) {
				return CommonUtils.getErrorResultMap("服务器未能获取修改的数据");
			}
			return CommonUtils.getSucResultMap(unValidDataDetailService.updateByEntity(batchId, id, columnDataMap));
		} catch (Exception e) {
			e.printStackTrace();
			return CommonUtils.getErrorResultMap("保存失败");
		}
	}

	/**
	 * 删除不合法数据(可实现多选)
	 * 
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "删除不合法数据--程涛--已完成")
	@RequestMapping(value = "/deleteInvalidData", method = RequestMethod.DELETE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "要删除的无效数据的Id，删除多条数据用逗号分隔Id，如：11,12,13", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "batchId", value = "批次Id", paramType = "query", dataType = "String") })
	public Map<String, Object> deleteInvalidData(@RequestParam("ids") List<String> ids,
			@RequestParam("batchId") String batchId) {

		try {
			unValidDataDetailService.deleteInvalidData(ids, batchId);
			return CommonUtils.getSucResultMap();
		} catch (Exception e) {
			logger.error("删除不合法数据出错了:" + e);
			return CommonUtils.getWarnResultMap("删除不合法数据失败");
		}

	}

	/**
	 * 导出不合法数据
	 * 
	 * @return
	 */
	@ApiOperation(value = "导出不合法数据--完成", notes = "将不合法的数据导成Excel表格，然后用户下载后去编辑修改")
	@RequestMapping(value = "/exportInvalidData", method = RequestMethod.GET)
	public Map<String, Object> exportInvalidData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "batchId") String batchId) throws IOException {
		String name = unValidDataDetailMapper.findTmpTableChName(batchId);
		String fileName = name + ".xls";
		response.setCharacterEncoding("UTF-8");
		response.setContentType("octets/stream");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
		// 获取表名
		String tableName = unValidDataDetailMapper.findTmpTableNameByBatchId(batchId);
		// 通过表名获取表头
		List<Map<String, Object>> columnList = unValidDataDetailMapper.findTmpTableColumns(batchId, tableName); // 得到临时表的所有字段名
		String[] chTileArr = new String[columnList.size()];
		int i = 0;
		for (Map<String, Object> map : columnList) {
			chTileArr[i] = map.get("ch_column").toString();
			i += 1;
		}
		List<String> chHead = new ArrayList<String>();
		chHead.add("导出编号");
		chHead.addAll(Arrays.asList(chTileArr));
		String[] enTitleArr = new String[columnList.size()];
		int j = 0;
		for (Map<String, Object> map : columnList) {
			enTitleArr[j] = map.get("en_column").toString();
			j += 1;
		}
		List<String> enHead = new ArrayList<String>();
		enHead.add("_id");
		enHead.addAll(Arrays.asList(enTitleArr));
		// 通过表名获取数据列表
		List<Map<String, Object>> list = unValidDataDetailService.findTableErrorList(batchId, tableName);
		Map<String, List<Map<String, Object>>> map = new HashMap<>();
		map.put(name, list);
		try {
			Workbook workbook = MyExcelUtil.exportExecl(map, enHead, chHead, true);
            MyExcelUtil.setColumnHidden( workbook, 0, 0);
			MyExcelUtil.writeToResponse(fileName, request.getHeader("User-Agent"), response, workbook);
			return CommonUtils.getSucResultMap();
		} catch (Exception e) {
			e.printStackTrace();
			return CommonUtils.getErrorResultMap("导出失败");
		}
	}
}