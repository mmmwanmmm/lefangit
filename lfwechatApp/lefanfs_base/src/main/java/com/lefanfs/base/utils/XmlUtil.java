package com.lefanfs.base.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现xml字符串与java对象之间的互相转换等功能
 * 
 * @author daniel
 * 
 */
public class XmlUtil {
	public static void main(String[] args) {
		// String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><MailDto><mailTo>aaa@tom.com</mailTo><mailSubject>aaaaaaa</mailSubject><mailContent>bbbbbbbbbb</mailContent></MailDto>";
		// // xmlToObject(xmlStr, MailDto.class);
		//
		// List<Url> urlList = new ArrayList<Url>();
		// xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><urlList><url key=\"aaaa\" value=\"aaaa\"/><url key=\"bbb\" value=\"bbb\"/></urlList>";
		// Object obj = getUrlMap();

		// MailDto mailDto=new MailDto();
		// mailDto.setMailTo("aaaaa@163.com");
		// mailDto.setMailSubject("aaaaaa");
		// mailDto.setMailContent("bbbbbb");
		// objectToXml(mailDto);
		// String s="rrwerqq3434343qqasfafsfafqq34534543sdfaafqq44444safafa";
		// Pattern p=Pattern.compile("qq(.*?)qq");
		// Matcher m=p.matcher(s);
		// while(m.find()){
		// System.out.println(m.group());
		// }

		String s1 = "http://localhost:8080/task/file/downloadFile/fileId=59/http://localhost:8080/task/file/downloadFile/fileId=09";
		Pattern p1 = Pattern.compile("/file/downloadFile(.?)fileId=([0-9]+)");
		Matcher m1 = p1.matcher(s1);
		while (m1.find()) {
			int count = m1.groupCount();
			for (int i = 1; i <= count; i++)
				System.out.println(m1.group(i));
		}
	}

	/**
	 * 将xml字符串转换成java对象
	 * 
	 * @param xmlStr
	 *            xml字符串
	 * @param clazz
	 *            对象类型
	 * @return
	 */
	public static Object xmlToObject(String xmlStr, Class<?> clazz) {
		Object obj = null;
		InputStream input = null;
		try {
			input = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller um = jaxbContext.createUnmarshaller();
			obj = um.unmarshal(input);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 将java对象转换成xml字符串
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
	public static String objectToXml(Object obj) {
		String retStr = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
			Marshaller m = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			m.marshal(obj, sw);
			retStr = sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return retStr;
	}

	public static InputStream getResourceAsStream(String resource) {
		String realSource = resource.startsWith("/") ? resource.substring(1) : resource;
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(realSource);
	}

	// public static Document xmlDocument(InputStream in) {
	//
	// Document document = null;
	// try {
	// SAXReader reader = new SAXReader();
	// document = reader.read(in);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return document;
	// }
	//
	// public static Map<String, String> getUrlMap() {
	// Map<String, String> urlMap = new HashMap<String, String>();
	// Document dom = null;
	// try {
	// dom = xmlDocument(getResourceAsStream("url.xml"));
	// Element root = dom.getRootElement();
	// Iterator i = root.elementIterator();
	// while (i.hasNext()) {
	// Element column = (Element) i.next();
	// String key = column.attribute("key").getValue().trim();
	// String value = column.attribute("value").getValue().trim();
	// urlMap.put(key, value);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return urlMap;
	// }
}
