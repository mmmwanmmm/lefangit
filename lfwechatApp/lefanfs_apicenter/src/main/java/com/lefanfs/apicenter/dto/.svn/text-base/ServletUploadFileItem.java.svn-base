package com.lefanfs.apicenter.dto;

import com.lefanfs.base.utils.ObjectUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 扩展上传组件
 * 
 * @author kevin
 * 
 */
@SuppressWarnings("unchecked")
public class ServletUploadFileItem extends ServletFileUpload {
	private List<FileItem> fileItems;

	public ServletUploadFileItem() {

	}

	public ServletUploadFileItem(FileItemFactory fileItemFactory, HttpServletRequest request) throws FileUploadException {
		super(fileItemFactory);
		this.parse(request);
	}

	public void parse(HttpServletRequest request) throws FileUploadException {
		fileItems = this.parseRequest(request);
	}

	/**
	 * 获取文件上传表单对象
	 * 
	 * @return
	 */
	public Map<String, String> getFormFieldItem() {
		Map<String, String> fileMap = new HashMap<String, String>();
		for (FileItem item : fileItems) {
			if (item.isFormField()) {
				try {
					fileMap.put(item.getFieldName(), item.getString("utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return fileMap;
	}

	/**
	 * 获取文件上传表单对象
	 * 
	 * @return
	 */
	public Map<String, Object> getFormFieldItems() {
		Map<String, List<String>> tempMap = new HashMap<String, List<String>>();
		for (FileItem item : fileItems) {
			if (item.isFormField()) {
				try {
					List<String> tempList = tempMap.get(item.getFieldName());
					if (tempList == null) {
						tempList = new ArrayList<String>();
					}
					tempList.add(item.getString("utf-8"));
					tempMap.put(item.getFieldName(), tempList);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		if (tempMap != null && tempMap.size() > 0) {
			for (Map.Entry<String, List<String>> entry : tempMap.entrySet()) {
				fieldMap.put(entry.getKey(), ObjectUtil.listToString(entry.getValue()));
			}
		}
		return fieldMap;
	}

	/**
	 * 获取文件上传文件对象
	 * 
	 * @return
	 */
	public List<FileItem> getFileItem() {
		List<FileItem> fileList = new ArrayList<FileItem>();
		for (FileItem item : fileItems) {
			if (!item.isFormField()) {
				fileList.add(item);
			}
		}
		return fileList;
	}
}
