package com.lefanfs.apicenter.web;

import com.lefanfs.apicenter.dto.ServletUploadFileItem;
import com.lefanfs.apicenter.dto.UserFileDto;
import com.lefanfs.apicenter.enums.ImageSizeEnum;
import com.lefanfs.apicenter.inner.FileService;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sh.zj100.common.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传下载
 *
 * @author kevin
 *
 */
@RequestMapping("/file")
@Controller
public class FileController extends BaseController {
    private static final Logger log = Logger.getLogger(FileController.class);
    @Autowired
    private FileService fileService;

    @Value("${file.server}")
    private String imageServer;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            ServletUploadFileItem upload = fileService.getUploadFileItem(request);
            System.out.println( "---------------fileItems----------"+ upload.toString());
            List<FileItem> fileItems = upload.getFileItem();
            System.out.println( "---------------fileItems----------"+ fileItems.toString());
            boolean flag = fileService.validateFile(fileItems);
            if (flag) {
                Map<String, String> filedMap = upload.getFormFieldItem();
                String moduleName = filedMap.get("moduleName");
                String userId = filedMap.get("userId");
                String filePath = this.fileService.makeFilePath(moduleName, "doc");
                for (FileItem item : fileItems) {
                    String fileName = item.getName();
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    System.out.println("---------------fileExt----------"+ fileExt);
//                    if("apk".equals(fileExt)){
//                        filePath = this.fileService.makeFilePath("epeit/appfile", "apk");
//                        userId="9527";
//                    }
                }
                System.out.println( "---------------filePath----------"+ filePath);
                List<UserFileDto> files = this.fileService.uploadFile("", filePath, Long.valueOf(userId), fileItems);
                jsonMap.put("success", "true");
                jsonMap.put("images", files);
            } else {
                jsonMap.put("success", "false");
                jsonMap.put("message", "上传的文件过大,或者文件类型不允许上传");
            }
        } catch (Exception e) {
            log.error("FileController.uploadFile exception:", e);
            jsonMap.put("success", "false");
            jsonMap.put("message", "上传异常");
        }
        String json = JsonUtil.objectToJson(jsonMap);
        this.outputJson(json, response);
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public void uploadImage(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "1") Long fileType, @RequestParam(defaultValue = "1") String userId,
                            @RequestParam(defaultValue = "1") String moduleName) {

       /* request.getHeader().header('content-type:application:json;charset=utf8');  
        header('Access-Control-Allow-Origin:*');  
        header('Access-Control-Allow-Methods:POST');  
        header('Access-Control-Allow-Headers:x-requested-with,content-type');  */
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            ServletUploadFileItem upload = fileService.getUploadFileItem(request);
            List<FileItem> fileItems = upload.getFileItem();
            boolean flag = true;
            // fileType:1:图片，2：视频
            if (fileType != null && fileType.intValue() == 2) {
                flag = fileService.validateVideoFile(fileItems);
            } else {
                flag = fileService.validateImageFile(fileItems);
            }
            if (flag) {
                Map<String, String> filedMap = upload.getFormFieldItem();
                // String moduleName = filedMap.get("moduleName");
                String userFileDescription = filedMap.get("userFileDescription");
                String filePath = this.fileService.makeFilePath(moduleName, ImageSizeEnum.SIZE_DEFAULT.getValue());
                List<UserFileDto> files = new ArrayList<UserFileDto>();
                userId =  "247";
                UserFileDto fileDto = this.fileService.uploadOneFile(fileItems.get(0), fileType, filePath, userFileDescription, Long.valueOf(userId));
                if(null == fileDto){
                    jsonMap.put("success", "false");
                    jsonMap.put("message", "上传文件异常");
                    String json = JsonUtil.objectToJson(jsonMap);
                    this.outputJson(json, response);
                    return;
                }
                files.add(fileDto);
                jsonMap.put("success", true);
                jsonMap.put("images", files);
            } else {
                jsonMap.put("success", false);
                jsonMap.put("message", "上传的文件过大,或者文件类型不允许上传");
            }
        } catch (Exception e) {
            log.error("FileController.uploadFile exception:", e);
            jsonMap.put("success", "false");
            jsonMap.put("message", "上传异常");
        }
        String json = JsonUtil.objectToJson(jsonMap);
        this.outputJson(json, response);
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileId = request.getParameter("fileId");
        UserFileDto userFile = this.fileService.getUserFileByFileId(Long.valueOf(fileId));
        this.fileService.downloadFile(userFile, response);
    }

    @RequestMapping(value = "/uploadFileForKindEditor", method = RequestMethod.POST)
    public void uploadFileForKindEditor(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "1") Long fileType, @RequestParam(defaultValue = "1") String userId,
                                        @RequestParam(defaultValue = "1") String moduleName) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            ServletUploadFileItem upload = fileService.getUploadFileItem(request);
            List<FileItem> fileItems = upload.getFileItem();
            boolean flag = fileService.validateImageFile(fileItems);
            if (flag) {
                Map<String, String> filedMap = upload.getFormFieldItem();
                String filePath = this.fileService.makeFilePath(moduleName, ImageSizeEnum.SIZE_DEFAULT.getValue());
                List<UserFileDto> files = new ArrayList<UserFileDto>();
                UserFileDto fileDto = this.fileService.uploadOneFile(fileItems.get(0), fileType, filePath, null, Long.valueOf(userId));
                files.add(fileDto);

                retMap.put("error", 0);
                if (files != null && files.size() > 0) {
                    log.debug("imageServer=" + imageServer);
                    String retUrl = imageServer + files.get(0).getUserFilePath();
                    retMap.put("url", retUrl);
                    for (Map.Entry<String, String> entry : filedMap.entrySet()) {
                        retMap.put(entry.getKey(), entry.getValue());
                    }
                }
            } else {
                retMap.put("error", 1);
                retMap.put("message", "上传的文件过大,或者文件类型不允许上传");
            }
        } catch (Exception e) {
            log.error("uploadFileForKindEditor exception:", e);
            retMap.put("error", 1);
            retMap.put("message", "上传失败！");
        }
        String json = JsonUtil.objectToJson(retMap);
        this.outputJson(json, response);
    }

    @RequestMapping(value = "/uploadMhtFile", method = RequestMethod.POST)
    public void uploadMhtFile(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            ServletUploadFileItem upload = fileService.getUploadFileItem(request);
            List<FileItem> fileItems = upload.getFileItem();
            boolean flag = fileService.validateImageFile(fileItems);

            if (!flag) { // 若是文件类型
                flag = fileService.validateFile(fileItems);
            }
            if (flag) {
                Map<String, String> filedMap = upload.getFormFieldItem();
                String moduleName = filedMap.get("moduleName");
                String userId = filedMap.get("userId");
                Long fileId = 0L;
                if (null != filedMap.get("fileId") && !filedMap.get("fileId").equals("null")) {
                    fileId = Long.valueOf(filedMap.get("fileId"));
                }
                String filePath = this.fileService.makeFilePath(moduleName, "resume");
                List<UserFileDto> files = this.fileService.uploadFileToMht(filePath, Long.valueOf(userId), fileItems, fileId);
                // this.fileService.makeFixImages(files);
                retMap.put("error", 0);
                if (files != null && files.size() > 0) {
                    log.debug("imageServer=" + imageServer);
                    String retUrl = imageServer + files.get(0).getUserFilePath();
                    fileId = files.get(0).getUserFileId();
                    // retMap.put("url", retUrl);

                    for (Map.Entry<String, String> entry : filedMap.entrySet()) {
                        retMap.put(entry.getKey(), entry.getValue());
                    }
                }
                retMap.put("fileId", fileId);
            } else {
                retMap.put("error", 1);
                retMap.put("message", "上传的文件过大,或者文件类型不允许上传");
            }
        } catch (Exception e) {
            log.error("uploadFileForKindEditor exception:", e);
            retMap.put("error", 1);
            retMap.put("message", "上传失败！");
        }
        String json = JsonUtil.objectToJson(retMap);
        this.outputJson(json, response);
    }

    @RequestMapping(value = "/uploadApkFile", method = RequestMethod.POST)
    public void uploadApkFile(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        String imageUrl = "";
        try {
            ServletUploadFileItem upload = fileService.getUploadFileItem(request);
            List<FileItem> fileItems = upload.getFileItem();
            boolean flag = fileService.validateApkFile(fileItems);
            if (flag) {
                Map<String, String> filedMap = upload.getFormFieldItem();
                String moduleName = filedMap.get("moduleName");
                String userId = filedMap.get("userId");
                // String type = apkPlatform.equals("0") ? "android" : "ios";
                String filePath = this.fileService.makeFilePath(moduleName, "apk");
                List<UserFileDto> files = this.fileService.uploadApkFile(filePath, Long.valueOf(userId), fileItems);
                // 生成app下载二维码图片
                if (null != files && files.size() > 0) {
                    String contentString = imageServer + files.get(0).getUserFilePath();
                    String fileName2 = files.get(0).getUserFileId() + "_2.png";
                   // imageUrl = this.fileService.createQRcode(contentString, fileName2);
                }

                jsonMap.put("success", "true");
                jsonMap.put("apk", files);
                jsonMap.put("imageUrl", imageUrl);
            } else {
                jsonMap.put("success", "false");
                jsonMap.put("message", "上传的文件过大,或者文件类型不允许上传");
            }
        } catch (Exception e) {
            log.error("FileController.uploadFile exception:", e);
            jsonMap.put("success", "false");
            jsonMap.put("message", "上传的异常");
        }
        String json = JsonUtil.objectToJson(jsonMap);
        this.outputJson(json, response);
    }

    @RequestMapping(value = "/uploadWeiZhanImage",method = RequestMethod.POST)
    public void uploadWeiZhanImage(@RequestParam Long userId,@RequestParam Long userFileId,@RequestParam String filePath,@RequestParam Long left,@RequestParam Long top,@RequestParam Long width,@RequestParam Long height,HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try{
            if(StringUtils.isEmpty(userFileId)||StringUtils.isEmpty(filePath)||StringUtils.isEmpty(left)||StringUtils.isEmpty(top)||StringUtils.isEmpty(width)||StringUtils.isEmpty(height))
            {
                jsonMap.put("success", "false");
                jsonMap.put("message", "缺少参数");
            }else{
//                gif,jpg,jpeg,bmp,png
                List list = new ArrayList();
                list.add("gif");
                list.add("jpg");
                list.add("jpeg");
                list.add("bmp");
                list.add("png");
                log.info("===============================开始切图");
                String cutPath = fileService.uploadWeiZhanCutFile(filePath,left.intValue(),top.intValue(),width.intValue(),height.intValue());
                log.info("cutPath====="+cutPath);
                File file = new File(cutPath);
                FileChannel fc = null;
                fc = new RandomAccessFile(file, "r").getChannel();
                Long fileSize = fc.size();
                String fileName = cutPath.substring(cutPath.lastIndexOf(File.separator)+1);
                log.info("cutfileName"+fileName);

                List<UserFileDto> files = new ArrayList<UserFileDto>();
                String finalPath = this.fileService.makeFilePath("weizhan", "default");
                Long fileType = 1L;
                UserFileDto fileDto = this.fileService.uploadWeiZhanOneFile(fileName,cutPath, fileSize,fileType,finalPath,"",userId);
                log.info("fileDto===="+fileDto);
                files.add(fileDto);
                jsonMap.put("success", true);
                jsonMap.put("images", files);
            }
        }catch (Exception e){
            log.error("FileController.uploadFile exception:", e);
            jsonMap.put("success", "false");
            jsonMap.put("message", "上传的异常");
        }
        String json = JsonUtil.objectToJson(jsonMap);
        this.outputJson(json, response);
    }
}
