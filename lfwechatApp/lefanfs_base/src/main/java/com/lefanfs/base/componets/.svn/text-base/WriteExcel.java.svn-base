package com.lefanfs.base.componets;

import com.lefanfs.base.dto.MyCellFormat;
import jxl.CellView;
import jxl.JXLException;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.biff.JxlWriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.lang.Number;

@Component("writeExcel")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WriteExcel {

	protected final SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	protected final SimpleDateFormat isoTimeFormat = new SimpleDateFormat("HH:mm:ss.SSSZ");
	protected final SimpleDateFormat isoDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected final SimpleDateFormat dateTimeFormatForFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

	protected Class<?> clazz;
	protected File file;
	protected WritableCellFormat boldText;
	protected WritableCellFormat normalText;

	protected List<String> headers = new ArrayList<String>();
	protected List<MyCellFormat> cellFormats = new ArrayList<MyCellFormat>();
	protected List<Field> ecFields = new ArrayList<Field>();

	private final List<String> fields = new ArrayList<String>();

	/**
	 * 
	 * @param clazz
	 *            类名
	 * @param fieldNames
	 *            字段名
	 * @param columnHeaders
	 *            表头
	 * @param columnFormats
	 *            列格式
	 * @param datas
	 *            数据列表
	 * @throws Exception
	 * @author Daniel
	 */
	public void write(Class<?> clazz, List<String> fieldNames, List<String> columnHeaders, Map<String, MyCellFormat> columnFormats, List<?> datas) throws Exception {
		this.clazz = clazz;
		if (columnHeaders != null && fieldNames.size() != columnHeaders.size()) {
			throw new IllegalArgumentException("columnNames 和 fieldNames长度不一致!");
		}
		initHeadersAndFields(fieldNames, columnHeaders, columnFormats);
		File path = new File("/tmp");
		if (!path.exists()) {
			path.mkdirs();
		}
		String fileName = dateTimeFormatForFileName.format(new Date()) + ".xls";
		file = new File(path, fileName);
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 粗体 11
			WritableFont boldFont = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD);
			boldFont.setPointSize(11);
			// 11
			WritableFont normalFont = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD);
			normalFont.setPointSize(11);
			boldText = new WritableCellFormat(boldFont);
			normalText = new WritableCellFormat(normalFont);
			WritableSheet s = workbook.createSheet("sheet1", 0);
			int row = writeHeader(s);
			writeContent(s, datas, row);
			workbook.write();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (JXLException e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return this.file;
	}

	private void initHeadersAndFields(List<String> fieldNames, List<String> columnNames, Map<String, MyCellFormat> columnFormats) {
		for (int i = 0; i < fieldNames.size(); i++) {
			fields.add(fieldNames.get(i));
			if (columnNames != null) {
				headers.add(columnNames.get(i));
			} else {
				headers.add(fieldNames.get(i));
			}
			String fieldName = fieldNames.get(i);
			if (fieldName != null && columnFormats != null && columnFormats.get(fieldName) != null) {
				cellFormats.add(columnFormats.get(fieldName));
			} else {
				cellFormats.add(null);
			}
		}
	}

	private int writeHeader(WritableSheet sheet) throws RowsExceededException, JXLException {
		sheet.getSettings().setDefaultColumnWidth(20);
		int row = 0;
		for (int i = 0; i < headers.size(); i++) {
			Label l = new Label(i, 0, headers.get(i), boldText);
			sheet.addCell(l);
			if (cellFormats.get(i) != null && cellFormats.get(i).getWidth() > 0) {
				CellView cv = new CellView();
				cv.setSize(20 * cellFormats.get(i).getWidth());
				sheet.setColumnView(i, cv);
			}
		}
		return row + 1;
	}

	@SuppressWarnings("unchecked")
	private int writeContent(WritableSheet s, List<?> datas, int startRow) {
		try {
			Class<?>[] interfaces = clazz.getInterfaces();
			boolean isMap = false;
			for (Class<?> c : interfaces) {
				if (c.getName().equals("java.util.Map")) {
					isMap = true;
					break;
				}
			}
			for (Object obj : datas) {
				int colNumber = 0;
				for (String keyName : fields) {
					Object objValue = null;
					if (isMap) {
						objValue = ((Map<String, Object>) obj).get(keyName);
					} else {
						Method method = clazz.getMethod(generateGetMethod(keyName));
						objValue = method.invoke(obj);
					}
					if (objValue instanceof Number) {
						Number value = null;
						if (objValue != null) {
							value = (Number) objValue;
						}
						jxl.write.Number n = new jxl.write.Number(colNumber, startRow, value.doubleValue());
						s.addCell(n);
					} else {
						Label l = null;
						if (objValue != null && objValue instanceof Date) {
							if (isMap) {
								l = new Label(colNumber, startRow, isoDateFormat.format((Date) objValue), normalText);
							} else {
								Field field = clazz.getField(keyName);
								DateTimeFormat dtf = field.getAnnotation(DateTimeFormat.class);
								if (dtf == null || dtf.iso().equals(DateTimeFormat.ISO.NONE)) {
									l = new Label(colNumber, startRow, isoDateFormat.format((Date) objValue), normalText);
								} else {
									String df = chooseISODateFormat(dtf.iso(), (Date) objValue);
									l = new Label(colNumber, startRow, df, normalText);
								}
							}
						} else {
							l = new Label(colNumber, startRow, objValue == null ? "" : objValue.toString(), normalText);
						}
						s.addCell(l);
					}
					colNumber++;
				}
				startRow++;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (JxlWriteException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return 0;
	}

	protected String generateGetMethod(String fieldName) {
		if (fieldName == null || fieldName.length() < 1) {
			return "";
		}
		return "get" + fieldName.toUpperCase().charAt(0) + fieldName.substring(1);
	}

	protected String chooseISODateFormat(DateTimeFormat.ISO iso, Date date) {
		if (iso.equals(DateTimeFormat.ISO.DATE)) {
			return isoDateFormat.format(date);
		}
		if (iso.equals(DateTimeFormat.ISO.TIME)) {
			return isoTimeFormat.format(date);
		}
		if (iso.equals(DateTimeFormat.ISO.DATE_TIME)) {
			return isoDateTimeFormat.format(date);
		}
		return "yyyy-MM-dd";
	}
}
