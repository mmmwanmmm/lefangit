package com.lefanfs.apicenter.enums;

/**
 * Created by fanshuai on 16/12/1.
 */
public enum UserIntegralParentTypeEnum {
    //（0：完善信息，1：活动，2：事件，3：参与户外活动，4：邀请注册，5：购买商品，6：转发、分享信息，7：捐赠，8：其他）
    COMPLATE_USERINFO(1,"完善基本信息"),
    WORK5000(2,"计步5000"),
    WORK10000(8,"计步10000"),
    ACTIVITY(3,"运动"),
    EVENT(4,"事件"),
    INVITE(5,"邀请"),
    SEND_BUY_PRODUCT(6,"购买商品赠送"),
    AD(7,"联合推广"),
    REDUCE_BUY_PRODUCT(9,"积分购买商品"),
    ;

    private UserIntegralParentTypeEnum(Integer type, String name){
        this.type=type;
        this.name=name;
    }
    private Integer type;
    private String name;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
