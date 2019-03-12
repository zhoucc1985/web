package com.cloud.entity.cloud;

import java.util.Date;


/**
 * 操作日志
 * @ClassName OperateLog.java
 * @author shrChang.Liu
 * @date 2018年1月11日下午6:05:54
 */
public class OperateLog {
    private Long id;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 学院id
     */
    private Long collegeId;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 执行时间
     */
    private Long operateDuration;

    /**
     * 结束时间
     */
    private Date endLogDate;

    /**
     * ip地址
     */
    private String operateIpv4;

    /**
     * 方法名
     */
    private String operateMethodCode;

    /**
     * 方法类型
     */
    private String operateMethodType;

    /**
     * 方法参数
     */
    private String operateRequestParam;

    /**
     * 执行者id
     */
    private Long operateUserId;

    /**
     * 执行者名称
     */
    private String operateUserName;

    /**
     * 执行模块
     */
    private String operateModule;

    /**
     * 执行名称
     */
    private String operateName;

    /**
     * 执行类型
     */
    private String operateType;
    
    /**
     * 是否执行成功
     */
    private Boolean isSucess = true;

    /**
     * 操作用户账户
     */
    private String operateUserAccount;

    public Boolean getIsSucess() {
		return isSucess;
	}

	public void setIsSucess(Boolean isSucess) {
		this.isSucess = isSucess;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName == null ? null : collegeName.trim();
    }

    public Long getOperateDuration() {
        return operateDuration;
    }

    public void setOperateDuration(Long operateDuration) {
        this.operateDuration = operateDuration;
    }

    public Date getEndLogDate() {
        return endLogDate;
    }

    public void setEndLogDate(Date endLogDate) {
        this.endLogDate = endLogDate;
    }

    public String getOperateIpv4() {
        return operateIpv4;
    }

    public void setOperateIpv4(String operateIpv4) {
        this.operateIpv4 = operateIpv4 == null ? null : operateIpv4.trim();
    }

    public String getOperateMethodCode() {
        return operateMethodCode;
    }

    public void setOperateMethodCode(String operateMethodCode) {
        this.operateMethodCode = operateMethodCode == null ? null : operateMethodCode.trim();
    }

    public String getOperateMethodType() {
        return operateMethodType;
    }

    public void setOperateMethodType(String operateMethodType) {
        this.operateMethodType = operateMethodType == null ? null : operateMethodType.trim();
    }

    public String getOperateRequestParam() {
        return operateRequestParam;
    }

    public void setOperateRequestParam(String operateRequestParam) {
        this.operateRequestParam = operateRequestParam == null ? null : operateRequestParam.trim();
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName == null ? null : operateUserName.trim();
    }

    public String getOperateModule() {
        return operateModule;
    }

    public void setOperateModule(String operateModule) {
        this.operateModule = operateModule == null ? null : operateModule.trim();
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName == null ? null : operateName.trim();
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

	public String getOperateUserAccount() {
		return operateUserAccount;
	}

	public void setOperateUserAccount(String operateUserAccount) {
		this.operateUserAccount = operateUserAccount;
	}
    
    
}