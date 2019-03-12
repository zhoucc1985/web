package com.cloud.common.vo.datacollection;

import lombok.Data;

/**
 * 导入数据异步消息信息
 *
 * @program cloudIAS
 * @author: Pan Jianneng
 * @create: 2018/11/17 11:07
 */
@Data
public class ImportDataSyncMsgVO {

    /**
     * 批次ID
     */
    private Long batchId;
    /**
     * 本次导入文档数据总数
     */
    private Integer currentImportDataTotal;
    /**
     * 本次导入文档数据有效数据
     */
    private Integer currentValidTotal;
    /**
     * 本次导入文档数据无效数据
     */
    private Integer currentInvalidTotal;

    /**
     * 通知前端是否关闭连接
     */
    private Boolean offConnection;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 入原始库后发送消息，只需要这两个数值
     * @author Pan Jianneng
     * @date 2018/11/17 11:19 AM
     * @params [batchId, currentImportDataTotal,offConnection]
     * @return
     */
    public ImportDataSyncMsgVO(Long batchId,String batchNo,int currentImportDataTotal,Boolean offConnection){
        this.batchId = batchId;
        this.currentImportDataTotal = currentImportDataTotal;
        this.offConnection = offConnection;
        this.batchNo = batchNo;
    }

    /**
     * 完成数据采集发送消息，包括数据校验与数据入库
     * @author Pan Jianneng
     * @date 2018/11/17 11:20 AM
     * @params [batchId, currentImportDataTotal, currentValidTotal, currentInvalidTotal,offConnection]
     * @return
     */
    public ImportDataSyncMsgVO(Long batchId,String batchNo,int currentImportDataTotal,
                               int currentValidTotal,int currentInvalidTotal,
                               Boolean offConnection){
        this.batchId = batchId;
        this.currentImportDataTotal = currentImportDataTotal;
        this.currentValidTotal = currentValidTotal;
        this.currentInvalidTotal = currentInvalidTotal;
        this.offConnection = offConnection;
        this.batchNo = batchNo;
    }

}

