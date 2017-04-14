package com.lefanfs.apicenter.inner;

import com.lefanfs.apicenter.dto.ServletUploadFileItem;
import com.lefanfs.apicenter.dto.UserFileDto;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件服务
 * 
 * @author daniel
 */
public interface FileService {
	/**
	 * 上传单个文件
	 * 
	 * @param fileItem
	 * @param fileType
	 * @param filePath
	 * @param userFileDescription
	 * @param userId
	 * @return
	 * @author Daniel
	 */
	UserFileDto uploadOneFile(FileItem fileItem, long fileType, String filePath, String userFileDescription, Long userId);

	/**
	 * 上传多个文件 模块名称（路径前缀）
	 * 
	 * @param fileItemList
	 *            文件对象列表
	 * @return
	 */
	public List<UserFileDto> uploadFile(String userFileDescription, String filePath, Long userId, List<FileItem> fileItemList);

	/**
	 * 上传mht或二维码文件，因需对数据库单独处理
	 * 
	 * @param filePath
	 * @param userId
	 * @param fileItemList
	 * @param fileId
	 * @return
	 */
	public List<UserFileDto> uploadFileToMht(String filePath, Long userId, List<FileItem> fileItemList, Long fileId);

	/**
	 * 
	 * 
	 * @Description:上传apk文件
	 * 
	 * @param filePath
	 * @param userId
	 * @param fileItemList
	 * @return
	 * 
	 *         List<UserFileDto>
	 * 
	 * @author: zhaozuowen
	 * 
	 * @time:2014-5-9 上午10:05:22
	 */
	public List<UserFileDto> uploadApkFile(String filePath, Long userId, List<FileItem> fileItemList);

	/**
	 * 
	 * 下载文件
	 * 
	 * @author kevin
	 * @param userFile
	 *            文件
	 * @param response
	 */
	public void downloadFile(UserFileDto userFile, HttpServletResponse response);

	/**
	 * 查询上传文件
	 * 
	 * @param fileId
	 * @return
	 */
	public UserFileDto getUserFileByFileId(Long fileId);

	public ServletUploadFileItem getUploadFileItem(HttpServletRequest request) throws Exception;

	public String makeFilePath(String moduleName, String type);

	public void makeFixImages(List<UserFileDto> userFiles, List<String> imgList);

	public boolean validateFile(List<FileItem> fileItems);

	public boolean validateApkFile(List<FileItem> fileItems);

	public boolean validateImageFile(List<FileItem> fileItems);

	public boolean validateVideoFile(List<FileItem> fileItems);

	public String uploadNetImage(String prefixPath, String sourcePath);

	/*public String createQRcode(String content, String fileName);*/


    /**
     * 微站上传单个文件
     *
     * @param
     * @param fileType
     * @param filePath
     * @param userFileDescription
     * @param userId
     * @return
     * @author Daniel
     */
    UserFileDto uploadWeiZhanOneFile(String fileName, String cutPath, long fileSize, long fileType, String filePath, String userFileDescription, Long userId);

    public String uploadWeiZhanCutFile(String sourceSrc, int left, int top, int width, int height);

}
