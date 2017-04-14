package com.lefanfs.base.dto;

import java.io.Serializable;

public class MyCellFormat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3502848018418920680L;

	private final int width;

	public MyCellFormat(int _width) {
		this.width = _width;
	}

	public int getWidth() {
		return width;
	}
}
