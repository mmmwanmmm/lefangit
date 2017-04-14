package com.lefanfs.apicenter.model;

import java.util.Date;

public class LoanApplication {
    private Long id;

    private Long userId;

    private String userName;

    private String userPhone;

    private Integer isTrafficAccident;

    private Double loanMoney;

    private Integer loanPurpose;

    private String accidentProvince;

    private String accidentCity;

    private Integer state;

    private String accidentDistrict;

    private String accidentAddress;

    private Date createTime;

    private Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getIsTrafficAccident() {
        return isTrafficAccident;
    }

    public void setIsTrafficAccident(Integer isTrafficAccident) {
        this.isTrafficAccident = isTrafficAccident;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Integer getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(Integer loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getAccidentProvince() {
        return accidentProvince;
    }

    public void setAccidentProvince(String accidentProvince) {
        this.accidentProvince = accidentProvince;
    }

    public String getAccidentCity() {
        return accidentCity;
    }

    public void setAccidentCity(String accidentCity) {
        this.accidentCity = accidentCity;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAccidentDistrict() {
        return accidentDistrict;
    }

    public void setAccidentDistrict(String accidentDistrict) {
        this.accidentDistrict = accidentDistrict;
    }

    public String getAccidentAddress() {
        return accidentAddress;
    }

    public void setAccidentAddress(String accidentAddress) {
        this.accidentAddress = accidentAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}