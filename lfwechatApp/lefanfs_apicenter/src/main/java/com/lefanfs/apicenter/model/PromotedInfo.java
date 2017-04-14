package com.lefanfs.apicenter.model;

import java.util.Date;

public class PromotedInfo {
    private Long id;

    private Long promoterId;

    private String promoterName;

    private Integer promoterType;

    private Long customerId;

    private String customerName;

    private Date createTime;

    private String customerImg;

    private String customerDesc;

    private String customerText;

    private String customerBusinessCard;

    private Integer customerState;

    private String customerPhone;

    private Integer accountState;

    private String  accountImg;

    private String customerWechatid;

    public Integer getAccountState() {
        return accountState;
    }

    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
    }

    public String getAccountImg() {
        return accountImg;
    }

    public void setAccountImg(String accountImg) {
        this.accountImg = accountImg;
    }

    public String getCustomerWechatid() {
        return customerWechatid;
    }

    public void setCustomerWechatid(String customerWechatid) {
        this.customerWechatid = customerWechatid;
    }



    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(Long promoterId) {
        this.promoterId = promoterId;
    }

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName;
    }

    public Integer getPromoterType() {
        return promoterType;
    }

    public void setPromoterType(Integer promoterType) {
        this.promoterType = promoterType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    public String getCustomerText() {
        return customerText;
    }

    public void setCustomerText(String customerText) {
        this.customerText = customerText;
    }

    public String getCustomerBusinessCard() {
        return customerBusinessCard;
    }

    public void setCustomerBusinessCard(String customerBusinessCard) {
        this.customerBusinessCard = customerBusinessCard;
    }

    public Integer getCustomerState() {
        return customerState;
    }

    public void setCustomerState(Integer customerState) {
        this.customerState = customerState;
    }
}