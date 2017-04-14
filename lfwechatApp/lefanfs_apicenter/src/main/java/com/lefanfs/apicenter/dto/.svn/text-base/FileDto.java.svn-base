package com.lefanfs.apicenter.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8037226606233529722L;

	private String id;
	private String sourceName;
	private String destName;
	private String descript;
	private String path;
	private String mimeType;
	private String fileExt;
	private long size;
	private String md5;

	private String previewUrl;
	private String absolutePath;
	private String sizeStr;

	public void setSizeStr(String sizeStr) {
		this.sizeStr = sizeStr;
	}

	public String getSizeStr() {
		if (this.size == 0) {
			this.sizeStr = "0MB";
		} else {
			BigDecimal gb = new BigDecimal(this.size).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).divide(new BigDecimal("1024"));
			if (gb.doubleValue() < 1) {
				this.sizeStr = gb.multiply(new BigDecimal("1024")).setScale(2, RoundingMode.HALF_UP) + "MB";
			} else {
				this.sizeStr = gb.setScale(2, RoundingMode.HALF_UP).toString() + "GB";
			}
		}
		return this.sizeStr;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}
