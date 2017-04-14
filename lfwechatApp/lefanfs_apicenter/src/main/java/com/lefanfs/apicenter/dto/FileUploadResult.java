package com.lefanfs.apicenter.dto;

import java.io.Serializable;

public class FileUploadResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3058713519652671324L;

	private Boolean isSuccess;
	private String code;
	private String msg;
	private FileDto fileDto;

	public FileUploadResult(Boolean isSuccess, String code, String msg) {
		this.isSuccess = isSuccess;
		this.code = code;
		this.msg = msg;
	}

	public FileUploadResult(Boolean isSuccess, FileDto fileDto) {
		this.isSuccess = isSuccess;
		this.fileDto = fileDto;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public FileDto getFileDto() {
		return fileDto;
	}

	public void setFileDto(FileDto fileDto) {
		this.fileDto = fileDto;
	}

}
