package com.lefanfs.apicenter.dto;

import java.io.Serializable;
import java.util.Date;

public class UserFileDto implements Serializable {

	private static final long serialVersionUID = -6101039710953655137L;

	private Long userFileId;

	private Long userId;

	private String userFileDescription;

	private String userFileType;

	private String userFilePath;

	private Long userFileSize;

	private String fileName;

	private String finalName;
	private String ContentType;
	private String imageSize;
	private String userName;
	private String icon;
	private Date createTime;
	private Long circleInfoId;

	private Integer deleteFlag;

	private String videoPicPath;
	private String mp4Path;
	private String oggPath;
	private String flashPath;

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getUserFileId() {
		return userFileId;
	}

	public void setUserFileId(Long userFileId) {
		this.userFileId = userFileId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserFileDescription() {
		return userFileDescription;
	}

	public void setUserFileDescription(String userFileDescription) {
		this.userFileDescription = userFileDescription;
	}

	public String getUserFileType() {
		return userFileType;
	}

	public void setUserFileType(String userFileType) {
		this.userFileType = userFileType;
	}

	public String getUserFilePath() {
		return userFilePath;
	}

	public void setUserFilePath(String userFilePath) {
		this.userFilePath = userFilePath;
	}

	public Long getUserFileSize() {
		return userFileSize;
	}

	public void setUserFileSize(Long userFileSize) {
		this.userFileSize = userFileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFinalName() {
		return finalName;
	}

	public void setFinalName(String finalName) {
		this.finalName = finalName;
	}

	public String getContentType() {
		return ContentType;
	}

	public void setContentType(String contentType) {
		ContentType = contentType;
	}

	public String getImageSize() {
		return imageSize;
	}

	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCircleInfoId() {
		return circleInfoId;
	}

	public void setCircleInfoId(Long circleInfoId) {
		this.circleInfoId = circleInfoId;
	}

	public String getMp4Path() {
		return mp4Path;
	}

	public void setMp4Path(String mp4Path) {
		this.mp4Path = mp4Path;
	}

	public String getOggPath() {
		return oggPath;
	}

	public void setOggPath(String oggPath) {
		this.oggPath = oggPath;
	}

	public String getFlashPath() {
		return flashPath;
	}

	public void setFlashPath(String flashPath) {
		this.flashPath = flashPath;
	}

	public String getVideoPicPath() {
		return videoPicPath;
	}

	public void setVideoPicPath(String videoPicPath) {
		this.videoPicPath = videoPicPath;
	}
	//
	// @Override
	// protected String defaultImage() {
	// if (this.icon == null || "".equals(this.icon)) {
	// return this.getDefaultImage();
	// }
	// return this.getIcon();
	// }
}