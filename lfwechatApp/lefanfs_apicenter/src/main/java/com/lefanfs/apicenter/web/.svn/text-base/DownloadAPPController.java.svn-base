package com.lefanfs.apicenter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by fanshuai on 17/1/19.
 */
@Controller
public class DownloadAPPController {

    @RequestMapping(value = "/d/index")
    public String index(Integer userId){
        return "/downIndex";
    }

    @RequestMapping(value = "/d/android")
    public String android(Integer userId,HttpServletResponse response){
        String parentFolder = "/data/img/appfile/android";
        File newestAndroid = getLeastFile(parentFolder);
        if (newestAndroid==null){
            return null;
        }
        download(response, newestAndroid, newestAndroid.getName());
        return null;
    }

    @RequestMapping(value = "/d/ios")
    public String ios(Integer userId,HttpServletResponse response){
        String parentFolder = "/data/img/appfile/ios";
        File newestAndroid = getLeastFile(parentFolder);
        if (newestAndroid==null){
            return null;
        }
        download(response, newestAndroid, newestAndroid.getName());
        return null;
    }

    private File getLeastFile(String parentFolder) {
        File f=new File(parentFolder);
        if (!f.exists()){
            f.mkdirs();
        }
        File[] files=f.listFiles();
        if (files==null || files.length==0){
            return null;
        }
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                return (int) (file2.lastModified() - file1.lastModified());
            }
        });
        return files[0];
    }





    private void download(HttpServletResponse response, File f,String fileName) {
        FileInputStream inputStream=null;
        OutputStream os = null;
        try {
            inputStream =new FileInputStream(f);
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (os!=null){
                try {
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
