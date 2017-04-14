package com.lefanfs.base.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 对象转化工具
 * 
 * @author kevin
 * 
 */
public class ObjectUtil {
	private static final Logger log = Logger.getLogger(ObjectUtil.class);

	public static void copyProperties(Object dest, Object orig) {
		if (orig == null) {
			return;
		}
		try {
			PropertyUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			log.error("copyProperties Exception :", e);
		}
	}

	public Object ByteToObject(byte[] bytes) {
		Object obj = null;
		try {
			// bytearray to object
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);
			obj = oi.readObject();
			bi.close();
			oi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public byte[] ObjectToByte(Object obj) {
		byte[] bytes = null;
		try {
			// object to bytearray
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);

			bytes = bo.toByteArray();

			bo.close();
			oo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (bytes);
	}

	public static <T> String arrayToString(T[] idArr) {
		if (idArr == null || idArr.length == 0) {
			return null;
		}
		String ids = "";
		for (int i = 0; i < idArr.length; i++) {
			ids += idArr[i].toString();
			if (i != idArr.length - 1) {
				ids += ",";
			}
		}
		return ids;
	}

	public static <T> String listToString(List<T> idList) {
		if (idList == null || idList.size() == 0) {
			return null;
		}
		String ids = "";
		for (int i = 0; i < idList.size(); i++) {
			ids += idList.get(i).toString();
			if (i != idList.size() - 1) {
				ids += ",";
			}
		}
		return ids;
	}

	public static <T> String setToString(Set<T> idList) {
		if (idList == null || idList.size() == 0) {
			return null;
		}
		String ids = "";
		Iterator<T> it = idList.iterator();
		int i = 0;
		while (it.hasNext()) {
			ids += it.next().toString();
			if (i != idList.size() - 1) {
				ids += ",";
			}
			i++;
		}
		return ids;
	}
}
