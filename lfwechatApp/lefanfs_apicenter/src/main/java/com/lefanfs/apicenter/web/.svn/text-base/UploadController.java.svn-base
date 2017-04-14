package com.lefanfs.apicenter.web;

import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.base.utils.DateTimeUtil;
import com.lefanfs.base.utils.RandomIDUtil;
import com.lefanfs.apicenter.dto.FileDto;
import com.lefanfs.apicenter.dto.FileUploadResult;
import com.lefanfs.apicenter.dto.ServletUploadFileItem;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/api/upload")
public class UploadController extends BaseController {
	protected static final Logger loger = Logger.getLogger(UploadController.class);
	@Value("${file.server.url}")
	protected String fileServerUrl;

	@Value("${file.server.tempDir}")
	protected String fileServerTempDir;

	@Value("${file.server.rootDir}")
	protected String fileServerRootDir;

	@Value("${file.upload.imageMaxSize}")
	protected int fileUploadImageMaxSize;

	@Value("${file.upload.imageExt}")
	protected String fileUploadImageExt;

	@Value("${file.upload.imageMimeType}")
	protected String fileUploadImageMimeType;

	@Value("${file.upload.imageDefaultDirName}")
	protected String fileUploadImageDefaultDirName;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = { "/uploadImage", "/uploadImage/" }, method = RequestMethod.POST)
	public String uploadImage(HttpServletRequest req, HttpServletResponse rsp) {
		try {
			ServletUploadFileItem servletUploadFileItem = this.getUploadFileItem(req, fileUploadImageMaxSize);
			List<FileItem> fileList = servletUploadFileItem.getFileItem();
			// Map<String, Object> paramMap = servletUploadFileItem.getFormFieldItems();
			// String bizFlag = null;
			// if (paramMap != null && paramMap.containsKey("biz_flag")) {
			// bizFlag = paramMap.get("biz_flag").toString();
			// }
			FileItem fileItem = null;
			if (fileList != null) {
				fileItem = fileList.get(0);
			}
			if (fileItem == null) {
				return this.outoutApiFinalResponse(new ApiRequest(), new ApiResponse(ApiMsgEnum.FILE_ITEM_EXCEPTION), rsp);
			}
			// 上传图片并提交到CDN
			FileUploadResult uploadResult = this.uploadImage(fileItem, null);
			if (uploadResult.getIsSuccess() == Boolean.TRUE && uploadResult.getFileDto() != null) {
				return this.outoutApiFinalResponse(new ApiRequest(), new ApiResponse<FileDto>(ApiMsgEnum.FILE_UPLOAD_SUCCESS, 1, uploadResult.getFileDto()), rsp);
			} else {
				return this.outoutApiFinalResponse(new ApiRequest(), new ApiResponse(ApiMsgEnum.FORBIDDEN), rsp);
			}
		} catch (FileSizeLimitExceededException e) {
			return this.outoutApiFinalResponse(new ApiRequest(), new ApiResponse(ApiMsgEnum.FILE_SIZE_EXCEPTION), rsp);
		} catch (Exception e) {
			return this.outoutApiFinalResponse(new ApiRequest(), new ApiResponse(ApiMsgEnum.FILE_UPLOAD_EXCEPTION), rsp);
		}
	}

	/**
	 * 上传图片（包括同步CND）
	 * 
	 * @param req
	 * @param fileDir
	 * @return
	 * @throws Exception
	 * @author Daniel
	 */
	protected FileUploadResult uploadImage(FileItem fileItem, String fileDir) throws Exception {
		String retMsg = "图片上传失败";
		int flag = this.checkImageFile(fileItem);
		if (flag == 1) {
			if (StringUtils.isEmpty(fileDir)) {
				String timeStr = DateTimeUtil.formatDateTime(new Date(), "yyyy/MM/dd/HHmm-ss-SSS");
				fileDir = fileUploadImageDefaultDirName + File.separator + timeStr + File.separator;
			}
			FileDto fileDto = this.writeFileToLocalServer(fileItem, fileDir, true);
			if (fileDto != null) {
				// TODO putFileToCdn
				// cdnService.putFileToCdn(fileDto.getPath(), fileDto.getAbsolutePath());
				return new FileUploadResult(true, fileDto);
			}
		} else if (flag == -2 || flag == -3) {
			retMsg = "图片格式只能为" + this.fileUploadImageExt;
		}
		return new FileUploadResult(false, null, retMsg);
	}

	private int checkImageFile(FileItem fileItem) {
		if (fileItem == null) {
			return 0;
		}
		if (!this.fileUploadImageMimeType.contains(fileItem.getContentType())) {
			return -3;
		}
		String ext = fileItem.getName();
		ext = ext.substring(ext.lastIndexOf(".") + 1).toLowerCase();
		if (!this.fileUploadImageExt.contains(ext)) {
			return -2;
		}
		return 1;
	}

	private FileDto writeFileToLocalServer(FileItem fileItem, String fileDir, boolean rename) {
		FileDto fileDto = new FileDto();
		// 文件保存目录路径
		String uploadPath = fileServerRootDir + fileDir;
		// 创建文件夹
		File dirFile = new File(uploadPath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		OutputStream out = null;
		InputStream in = null;
		String fileName = fileItem.getName();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		String fileUUID = RandomIDUtil.getNewUUID();
		String newFileName = fileName;
		if (rename) {
			newFileName = fileUUID + "." + fileExt;
		}
		// 上传
		File uploadedFile = null;
		// String md5 = null;
		try {
			uploadedFile = new File(uploadPath, newFileName);
			out = new FileOutputStream(uploadedFile);
			in = fileItem.getInputStream();
			byte buf[] = new byte[1024];// 可以修改 1024 以提高读取速度
			int length = 0;
			while ((length = in.read(buf)) > 0) {
				out.write(buf, 0, length);
				out.flush();
			}
			// md5 = Md5Util.encodeFile(uploadedFile);
		} catch (Exception e) {
			loger.error("uploadFile Exception:", e);
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				loger.error("uploadFile IOException:", e);
			}
		}
		fileDto.setId(fileUUID);
		fileDto.setSourceName(fileName);
		fileDto.setDestName(newFileName);
		fileDto.setMimeType(fileItem.getContentType());
		fileDto.setFileExt(fileExt);
		fileDto.setPath(fileDir + newFileName);
		fileDto.setSize(fileItem.getSize());
		// fileDto.setMd5(md5);
		fileDto.setPreviewUrl(fileServerUrl + fileDto.getPath());
		fileDto.setAbsolutePath(uploadedFile.getPath());
		fileDto.setSizeStr(this.formatFileSize(fileItem.getSize()));
		return fileDto;
	}

	private String formatFileSize(long size) {
		if (size == 0) {
			return "0MB";
		}
		BigDecimal gb = new BigDecimal(size).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).divide(new BigDecimal("1024"));
		if (gb.doubleValue() < 1) {
			return gb.multiply(new BigDecimal("1024")).setScale(2, RoundingMode.HALF_UP) + "MB";
		} else {
			return gb.setScale(2, RoundingMode.HALF_UP).toString() + "GB";
		}
	}

	/**
	 * 文件上传对象
	 * 
	 * @param request
	 * @param fileSizeMax
	 * @return
	 * @throws Exception
	 */
	protected ServletUploadFileItem getUploadFileItem(final HttpServletRequest request, long fileSizeMax) throws Exception {
		// 创建临时文件夹
		File dirTempFile = new File(this.fileServerTempDir);
		if (!dirTempFile.exists()) {
			dirTempFile.mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10 * 1024 * 1024); // 设定使用内存超过5M时，将产生临时文件并存储于临时目录中。
		factory.setRepository(dirTempFile); // 设定存储临时文件的目录。
		ServletUploadFileItem uploadItem = new ServletUploadFileItem();
		uploadItem.setFileItemFactory(factory);
		uploadItem.setFileSizeMax(fileSizeMax * 1024 * 1024);
		uploadItem.setHeaderEncoding("utf-8");
		// upload.setProgressListener(new ProgressListener() {
		// @Override
		// public void update(long pBytesRead, long pContentLength, int pItems)
		// {
		// Map<String, Long> retMap = new HashMap<String, Long>();
		// retMap.put("loaded", pBytesRead);
		// retMap.put("total", pContentLength);
		// String uploaderLocation = request.getParameter("uploaderLocation");
		// WebUtils.setSessionAttribute(request, "ProgressListener-" +
		// uploaderLocation, retMap);
		// }
		// });
		uploadItem.parse(request);
		return uploadItem;
	}
}
