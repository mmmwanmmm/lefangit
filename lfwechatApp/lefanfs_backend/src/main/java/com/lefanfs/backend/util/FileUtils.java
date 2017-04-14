package com.lefanfs.backend.util;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by fanshuai on 16/10/28.
 */
public class FileUtils {

    private static final String baseDir = "/data/img";
    public static String saveBytesToFile(String busket, byte[] fileDataStream)  {
        FileOutputStream out =null;
        try {
            File dir = new File("/"+busket+"/");
            File file = new File(dir,System.currentTimeMillis()+".jpg");
            if (!dir.exists()){
                dir.mkdirs();
            }
            if (!file.exists()){
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            out.write(fileDataStream);
            out.flush();
            return "/"+busket+"/"+file.getName();
        }catch (Exception e){
            return null;
        }finally {
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    out=null;
                }
            }
        }
    }

    public static String saveBytesToFile(String busket,String base64FileValue)  {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            return saveBytesToFile(busket, base64Decoder.decodeBuffer(base64FileValue));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
