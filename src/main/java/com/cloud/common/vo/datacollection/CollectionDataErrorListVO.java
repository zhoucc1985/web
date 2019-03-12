package com.cloud.common.vo.datacollection;

import lombok.Data;

/**
 * 采集数据错误对象,前端格式类
 *
 * @author: Pan Jianneng
 * @create: 2018/11/19 14:58
 */
@Data
public class CollectionDataErrorListVO {

    /**
     *
     */
    private Long id;

    /**
     * 学生学号
     */
    private String studentNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private CollectionDataErrorObjVO sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 身份证
     */
    private CollectionDataErrorObjVO idcard;

    /**
     * 出生日期
     */
    private CollectionDataErrorObjVO birthDate;

    /**
     * 出生身份
     */
    private CollectionDataErrorObjVO birthProvince;

    /**
     * 出生城市
     */
    private CollectionDataErrorObjVO birthCity;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 专业名称
     */
    private String professionName;

    /**
     * 学校组织机构id
     */
    private Long orgId;

    /**
     * 星座
     */
    private String constellation;

    /**
     * 高考成绩
     */
    private CollectionDataErrorObjVO score;

    /**
     * 报告批次id
     */
    private Long batchId;

    /**
     * 是否存在错误数据
     */
    private Boolean isError;

    /**
     * 当前列错误字段信息对象
     */
    private String errorMsg;

    /**
     * 学校编码
     */
    private String orgCode;

}

