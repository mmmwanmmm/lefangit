package com.lefanfs.apicenter.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Jani on 2017/3/8.
 */
public class QrUtils {

    public static String QRfromGoogle(String chl) throws Exception {

        chl = UrlEncode(chl);
        //http://pan.baidu.com/share/qrcode?w=150&h=150&url=http://lanyes.org
        String QRfromGoogle = "http://pan.baidu.com/share/qrcode?w=300&h=300&url="+chl;


        return QRfromGoogle;
    }
    public static String UrlEncode(String src)  throws UnsupportedEncodingException {
        return URLEncoder.encode(src, "UTF-8").replace("+", "%20");
    }

}
