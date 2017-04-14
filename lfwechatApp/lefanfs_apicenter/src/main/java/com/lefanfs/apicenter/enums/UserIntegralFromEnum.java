package com.lefanfs.apicenter.enums;

/**
 * Created by fanshuai on 16/12/1.
 */
public enum  UserIntegralFromEnum {
    //（0：完善信息，1：活动，2：事件，3：参与户外活动，4：邀请注册，5：购买商品，6：转发、分享信息，7：捐赠，8：其他）
    ADD_COMPLATE_USERINFO(0,"完善基本信息"),
    ADD_ACTIVITY(1,"活动"),
    ADD_COMPLATE_EVENT(2,"完成事件"),
    ADD_JOIN_ACTIVITY(3,"参与户外活动"),
    ADD_INVITE_REGISTER(4,"参与户外活动"),
    REDUCE_BUY_PRODUCT(5,"购买商品"),
    ADD_SHARE(6,"转发、分享信息"),
    ADD_GIFT(7,"捐赠"),
    ADD_OTHER(8,"其它"),
            ;
    private UserIntegralFromEnum(Integer fromType,String name){
        this.fromType = fromType;
        this.name=name;
    }
    private Integer fromType;
    private String name;

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
