package com.lefanfs.apicenter.util;

/**
 * 工具类 - 编号生成
 */
public class SerialNumberUtil {
    public static final String ORDER_PREFIX = "DD";
    public static final String RETURN_PREFIX = "TH";
    public static final String PRODUCT_PREFIX = "PR";

    public static void main(String[] args) {
        System.out.println(SerialNumberUtil.nextOrderCode());
        System.out.println(SerialNumberUtil.nextReturnOrderCode());
        System.out.println(SerialNumberUtil.nextProductCode());
    }

    public static synchronized String nextOrderCode() {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-8,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return ORDER_PREFIX +paymentID;
    }

    public static synchronized String nextReturnOrderCode() {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-8,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return RETURN_PREFIX +paymentID;
    }
    public static synchronized String nextProductCode() {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-8,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return PRODUCT_PREFIX +paymentID;
    }

}