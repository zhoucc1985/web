package com.cloud.service.datacollect.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.entity.datacollect.CollectBatchids;
import com.cloud.mapper.master.datacollect.CollectBatchidsMapper;
import com.cloud.service.datacollect.CollectBatchidsService;

@Service
public class CollectBatchidsServiceImpl implements CollectBatchidsService {
	@Resource
	private CollectBatchidsMapper collectBatchidsMapper;
	@Transactional
	@Override
	public synchronized String obtainBatchid(String name) {
		String batchid = "";
		// 查询当天的有没有num值 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(new Date()).toString();
		CollectBatchids collectBatchids = collectBatchidsMapper.findCollectBatchids(date);
		String numAddZero = "";
		if (collectBatchids == null) {
			//添加当前的num值
			CollectBatchids collectBatchidss = new CollectBatchids();
			collectBatchidss.setName(name);
			collectBatchidss.setDate(df.format(new Date()));
			collectBatchidss.setIncreatNum(1);
			collectBatchidsMapper.insertCollectBatchids(collectBatchidss);
			numAddZero = addZero(collectBatchidss.getIncreatNum(),3);
			batchid = dfs.format(new Date()).toString().concat("-").concat(numAddZero);
		} else {
			//修改增长值加1
			collectBatchidsMapper.updateCollectBatchids(name, date);
			CollectBatchids collectBatchidsss = collectBatchidsMapper.findCollectBatchids(date);
			numAddZero = addZero(collectBatchidsss.getIncreatNum(),3);
			batchid = dfs.format(new Date()).toString().concat("-").concat(numAddZero);
		}
		return batchid;
	}
	//补零
	public static String addZero(Integer num,Integer length){
		if(num==null) return null;
		String resultStr=num+"";
		String numStr=num+"";
		int needaddLength=length-(numStr.length()+1);
		for(int i=0;i<=needaddLength;i++){
			resultStr="0"+resultStr;
		}
		return resultStr;
	}	
}
