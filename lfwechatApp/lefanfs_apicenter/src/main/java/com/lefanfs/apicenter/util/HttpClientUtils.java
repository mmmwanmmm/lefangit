package com.lefanfs.apicenter.util;

import com.lefanfs.base.utils.RandomIDUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class HttpClientUtils {
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    public static String httpPost(String url, Map<String, String> parameter) throws IOException {
		return httpPost(url, parameter, "UTF-8");
	}

	public static String httpPost(String url, Map<String, String> parameter, String charset) throws IOException {
		NameValuePair[] data = new NameValuePair[parameter.size()];
		Iterator<String> keys = parameter.keySet().iterator();
		for (int i = 0; keys.hasNext(); i++) {
			String key = (String) keys.next();
			String value = (String) parameter.get(key);
			data[i] = new NameValuePair(key, value);
		}
		PostMethod postMethod = new PostMethod(url);
		HttpMethodParams params = postMethod.getParams();
		params.setContentCharset(charset);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
		if (data.length > 0) {
			postMethod.setRequestBody(data);
		}
		HttpClient httpClient = new HttpClient();
		String responseMsg = null;
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == 200) {
				responseMsg = getResponseBodyAsString(postMethod);
				return responseMsg;
			} else {
				throw new IOException("服务端内部错误: " + statusCode + " from " + postMethod.getURI());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getResponseBodyAsStringInputStrem(HttpMethod method,String fileServerRootDir) throws IOException {
		String charset = "utf-8";
		InputStream resStream = method.getResponseBodyAsStream();

        //先把流存储成图片
        String uuid = RandomIDUtil.getNewUUID();
        String fileName=uuid+".jpg";
        String filepath=fileServerRootDir+"/"+fileName;
        StringBuffer resBuffer = new StringBuffer();
        String str2 = resBuffer.toString();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = resStream.read(buffer)) > -1 ) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            if(baos.toString().indexOf("errcode")==-1){
                InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
                //存储文件
                File file=new File(filepath);
                OutputStream os = new FileOutputStream(file);
                int bytesRead = 0;
                byte[] buffer1 = new byte[8192];
                while ((bytesRead = stream1.read(buffer1, 0, 8192)) != -1) {
                    os.write(buffer1, 0, bytesRead);
                }
                os.close();
            }

            //转化字符串
            InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());
            BufferedReader br = new BufferedReader(new InputStreamReader(stream2, charset));
                String resTemp = null;
                while ((resTemp = br.readLine()) != null) {
                    resBuffer.append(resTemp);
                }
                str2=resBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(str2.indexOf("errcode")==-1) {
            return fileName;
        }
		return str2;
	}

    public static String getResponseBodyAsString(HttpMethod method) throws IOException {
        String charset = "utf-8";
        InputStream resStream = method.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(resStream, charset));
        StringBuffer resBuffer = new StringBuffer();
        String resTemp = null;
        while ((resTemp = br.readLine()) != null) {
            resBuffer.append(resTemp);
        }
        return resBuffer.toString();
    }

    public static String postJson(String url,String json, String charset,String fileServerRootDir){

        PostMethod postMethod = new PostMethod(url);
        HttpMethodParams params = postMethod.getParams();
        params.setContentCharset(charset);
        postMethod.setRequestHeader("Content-Type", "application/json;charset=" + charset);
        postMethod.setRequestBody(json);
        HttpClient httpClient = new HttpClient();
        String responseMsg = null;
        int statusCode = 0;
        try {
            statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == 200) {
                responseMsg = getResponseBodyAsStringInputStrem(postMethod, fileServerRootDir);
                System.out.println();
                return responseMsg;
            } else {
                throw new IOException("服务端内部错误: " + statusCode + " from " + postMethod.getURI());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String postJson1(String url,String json, String charset){

        PostMethod postMethod = new PostMethod(url);
        HttpMethodParams params = postMethod.getParams();
        params.setContentCharset(charset);
        postMethod.setRequestHeader("Content-Type", "application/json;charset=" + charset);
        postMethod.setRequestBody(json);
        HttpClient httpClient = new HttpClient();
        String responseMsg = null;
        int statusCode = 0;
        try {
            statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == 200) {
                responseMsg = getResponseBodyAsString(postMethod);
                System.out.println();
                return responseMsg;
            } else {
                throw new IOException("服务端内部错误: " + statusCode + " from " + postMethod.getURI());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void base64StringToImage(String base64String) {
        try {
            byte[] bytes1 = decoder.decodeBuffer(base64String);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File("d://QQ.jpg");// 可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
