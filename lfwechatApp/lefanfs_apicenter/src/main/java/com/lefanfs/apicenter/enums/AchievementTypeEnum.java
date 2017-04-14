package com.lefanfs.apicenter.enums;

/**
 * Created by fanshuai on 16/12/29.
 */
public enum AchievementTypeEnum {
    WORK(1,"计步成就"),
    CREARE_EVENT(2,"发起事件"),
    JOIN_EVENT(3,"参与事件"),
    Sports(4,"运动达人"),
    OutActivity(5,"户外达人"),
    ;

    private AchievementTypeEnum(int type,String name){
        this.type=type;
        this.name=name;
    }

    public int type;
    public String name;

}
