package com.lefanfs.apicenter.enums;

/**
 * Created by fanshuai on 16/12/1.
 */
public enum IntegralTypeEnum {
    //（0：完善信息，1：活动，2：事件，3：参与户外活动，4：邀请注册，5：购买商品，6：转发、分享信息，7：捐赠，8：其他）
    COMPLATE_USERINFO(UserIntegralParentTypeEnum.COMPLATE_USERINFO,11,"完善基本信息"),
    WORK5000(UserIntegralParentTypeEnum.WORK5000,21,"计步达5000"),
    WORK10000(UserIntegralParentTypeEnum.WORK10000,81,"计步达10000"),
    ACTIVITY_TIME(UserIntegralParentTypeEnum.ACTIVITY,31,"计时运动"),
    ACTIVITY_DISTANCE(UserIntegralParentTypeEnum.ACTIVITY,32,"计距离运动"),
    EVENT_TIME(UserIntegralParentTypeEnum.EVENT,41,"计时事件"),
    EVENT_DISTANCE(UserIntegralParentTypeEnum.EVENT,42,"计距离事件"),
    INVITE(UserIntegralParentTypeEnum.INVITE,51,"邀请好友"),
    BUY_ACTIVITY(UserIntegralParentTypeEnum.SEND_BUY_PRODUCT,61,"购买户外活动"),
    BUY_ACTIVITY_THING(UserIntegralParentTypeEnum.SEND_BUY_PRODUCT,62,"购买装备"),
    REDUCE_BUY_PRODUCT(UserIntegralParentTypeEnum.REDUCE_BUY_PRODUCT,91,"消费购买商品"),
    ;
    private IntegralTypeEnum(UserIntegralParentTypeEnum parent, Integer type, String name){
        this.parentType=parent;
        this.type=type;
        this.name=name;
    }
    private UserIntegralParentTypeEnum parentType;
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

    public UserIntegralParentTypeEnum getParentType() {
        return parentType;
    }

    public void setParentType(UserIntegralParentTypeEnum parentType) {
        this.parentType = parentType;
    }
}
