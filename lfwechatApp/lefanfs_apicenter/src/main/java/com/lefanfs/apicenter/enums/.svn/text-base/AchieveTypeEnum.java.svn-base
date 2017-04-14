package com.lefanfs.apicenter.enums;

/**
 * Created by Thinkpad on 2016/11/17.
 */
public enum AchieveTypeEnum {
    RUN(0,"走路"),
    CREATE_EVENT(1,"发起事件"),
    JOIN_EVENT(2,"参与事件"),
    YUNDONG_DAREN(3,"运动达人"),
    HUWAI_DAREN(4,"户外达人");


    private int typeId;
    private String typeName;
    private AchieveTypeEnum(int typeId,String typeName){
        this.typeId=typeId;
        this.typeName=typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static String getTypeName(int typeId){
        for (AchieveTypeEnum typeEnum: AchieveTypeEnum.values()){
            if (typeId==typeEnum.getTypeId()){
                return typeEnum.getTypeName();
            }
        }
        return null;
    }
}
