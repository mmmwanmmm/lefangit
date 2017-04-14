package com.lefanfs.backend.web.fileupload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lefanfs.backend.dto.ServletUploadFileItem;
import com.lefanfs.base.dto.ApiFinalResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import com.lefanfs.base.utils.RandomIDUtil;
import com.lefanfs.base.web.WebHelper;
import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.web.BackendBaseController;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/1/3.
 */
@Controller
public class FileUploadController extends BackendBaseController {

    @RequestMapping(value = "/upload")
    public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletResponse res,HttpServletRequest req){
        JSONObject json = new JSONObject();
        try {
            String base64 = Base64Utils.encodeToString(file.getBytes());
            Map paramMap = new HashMap();
            paramMap.put("business", "upload");
            paramMap.put("fileData", base64);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<HashMap>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.UPLOADIMG, paramMap, req);
            if (apiFinalResponse.getCode().equals(ApiMsgEnum.SUCCESS.getCode()) ){
                HashMap data = (HashMap)apiFinalResponse.getResults();
                json.putAll(data);
                json.put("code",200);
            }
        } catch (IOException e) {
            e.printStackTrace();
            json.put("errorMsg",e.getMessage());
        }
        return WebHelper.outputJson(json.toJSONString(),res);
    }
    public String makeFilePath(String moduleName, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String uploadPath = null;
        if (type.equals("resume")) {
            uploadPath = File.separator + moduleName + File.separator + type + File.separator;
        } else if (type.equals("apk")) {
            uploadPath = File.separator + moduleName.replace("/", File.separator) + File.separator;
        } else {
            uploadPath = File.separator + moduleName + File.separator + ymd + File.separator + type + File.separator;
        }
        return uploadPath;
    }

    @RequestMapping(value = "/uploadForKindedit")
    public String uploadForKindedit(@RequestParam(value = "imgFile", required = false) MultipartFile file,HttpServletResponse res,HttpServletRequest req){
        JSONObject json = new JSONObject();
        try {
            String base64 = Base64Utils.encodeToString(file.getBytes());
            Map paramMap = new HashMap();
            paramMap.put("business", "upload");
            paramMap.put("fileData", base64);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<HashMap>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.UPLOADIMG, paramMap, req);
            if (apiFinalResponse.getCode().equals(ApiMsgEnum.SUCCESS.getCode()) ){
                HashMap data = (HashMap)apiFinalResponse.getResults();
                json.putAll(data);
                json.put("error",0);
                json.put("url",data.get("imgUrl"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            json.put("error",1);
            json.put("message",e.getMessage());
        }
        return WebHelper.outputJson(json.toJSONString(),res);
    }
}
