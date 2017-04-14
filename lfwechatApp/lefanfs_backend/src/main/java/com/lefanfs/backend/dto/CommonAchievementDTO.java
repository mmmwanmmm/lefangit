package com.lefanfs.backend.dto;

import java.io.Serializable;
import java.util.Date;

public class CommonAchievementDTO implements Serializable{
    private Long id;

    private String achieveName;

    private String achieveCode;

    private Integer achieveType;

    private String achievePic;

    private Long achieveReward;

    private String achieveKpi;

    private Integer achieveKpiValue;

    private String kpiUnit;

    private String description;

    private Date createTime;

    private Date modifyTime;

    private Integer deleteFlag;
    private Integer level;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAchieveName() {
        return achieveName;
    }

    public void setAchieveName(String achieveName) {
        this.achieveName = achieveName;
    }

    public String getAchieveCode() {
        return achieveCode;
    }

    public void setAchieveCode(String achieveCode) {
        this.achieveCode = achieveCode;
    }

    public Integer getAchieveType() {
        return achieveType;
    }

    public void setAchieveType(Integer achieveType) {
        this.achieveType = achieveType;
    }

    public String getAchievePic() {
        return achievePic;
    }

    public void setAchievePic(String achievePic) {
        this.achievePic = achievePic;
    }

    public Long getAchieveReward() {
        return achieveReward;
    }

    public void setAchieveReward(Long achieveReward) {
        this.achieveReward = achieveReward;
    }

    public String getAchieveKpi() {
        return achieveKpi;
    }

    public void setAchieveKpi(String achieveKpi) {
        this.achieveKpi = achieveKpi;
    }

    public Integer getAchieveKpiValue() {
        return achieveKpiValue;
    }

    public void setAchieveKpiValue(Integer achieveKpiValue) {
        this.achieveKpiValue = achieveKpiValue;
    }

    public String getKpiUnit() {
        return kpiUnit;
    }

    public void setKpiUnit(String kpiUnit) {
        this.kpiUnit = kpiUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}