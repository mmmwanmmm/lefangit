package com.lefanfs.backend.dto;

import java.util.Date;

public class CommonIntegralBasicDto {
    private Long id;

    private String integralName;

    private String integralCode;

    private Integer integralType;

    private String integralDescription;

    private Integer integralValue;

    private String remark;

    private String integralKpiName;

    private Integer integralKpiType;

    private Integer integralKpiValue;

    private String integralKpiUnit;

    private Date createTime;

    private Long createId;

    private Date modifyTime;

    private Long modifyId;

    private Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntegralName() {
        return integralName;
    }

    public void setIntegralName(String integralName) {
        this.integralName = integralName;
    }

    public String getIntegralCode() {
        return integralCode;
    }

    public void setIntegralCode(String integralCode) {
        this.integralCode = integralCode;
    }

    public Integer getIntegralType() {
        return integralType;
    }

    public void setIntegralType(Integer integralType) {
        this.integralType = integralType;
    }

    public String getIntegralDescription() {
        return integralDescription;
    }

    public void setIntegralDescription(String integralDescription) {
        this.integralDescription = integralDescription;
    }

    public Integer getIntegralValue() {
        return integralValue;
    }

    public void setIntegralValue(Integer integralValue) {
        this.integralValue = integralValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIntegralKpiName() {
        return integralKpiName;
    }

    public void setIntegralKpiName(String integralKpiName) {
        this.integralKpiName = integralKpiName;
    }

    public Integer getIntegralKpiType() {
        return integralKpiType;
    }

    public void setIntegralKpiType(Integer integralKpiType) {
        this.integralKpiType = integralKpiType;
    }

    public Integer getIntegralKpiValue() {
        return integralKpiValue;
    }

    public void setIntegralKpiValue(Integer integralKpiValue) {
        this.integralKpiValue = integralKpiValue;
    }

    public String getIntegralKpiUnit() {
        return integralKpiUnit;
    }

    public void setIntegralKpiUnit(String integralKpiUnit) {
        this.integralKpiUnit = integralKpiUnit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}