package com.lefanfs.apicenter.enums;

/**
 * Created by fanshuai on 16/11/16.
 */
public enum InformationTypeEnum {
    YILIAO(0,"医疗"),
    BAOXIAN(1,"保险"),
    YUNDONG(2,"运动"),
    YANGSHEN(3,"养生"),
    QITA(4,"其它");
    private int typeId;
    private String typeName;

    private InformationTypeEnum(int typeId,String typeName){
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
        for (InformationTypeEnum informationTypeEnum: InformationTypeEnum.values()){
            if (informationTypeEnum.getTypeId()==typeId){
                return informationTypeEnum.getTypeName();
            }
        }
        return null;
    }
}
