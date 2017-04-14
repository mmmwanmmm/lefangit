package com.lefanfs.apicenter.domain;

import com.lefanfs.base.enums.ContentTypeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by fanshuai on 16/11/26.
 */
@Component
public class CDNDomain {
    private static String resourcePrefix;
    public static String getCdnUrl(String resourcePath) {
        return getCdnUrl(null,resourcePath,null);
    }
    public static String getCdnUrl(ContentTypeEnum typeEnum, String resourcePath, String sizeFlag) {
        if (resourcePath!=null&&resourcePath.startsWith("http")){
            return resourcePath;
        }
        return getStaticUrl(resourcePrefix, resourcePath, sizeFlag);
    }
    private static String getStaticUrl(String serverPrefix, String resourcePath, String sizeFlag) {
        if (resourcePath == null || "".equals(resourcePath)) {
            return null;
        }
        if (sizeFlag != null && !"".equals(sizeFlag)) {
            resourcePath = resourcePath + sizeFlag;
        }
        if (serverPrefix.endsWith("/")){
            if (resourcePath.startsWith("/")){
                return serverPrefix.substring(0,serverPrefix.length()-1) + resourcePath;
            }else {
                return serverPrefix + resourcePath;
            }
        }else {
            if (resourcePath.startsWith("/")){
                return serverPrefix + resourcePath;
            }else {
                return serverPrefix +"/"+ resourcePath;
            }
        }
    }

    @Value("${resource.prefix}")
    public  void setResourcePrefix(String resourcePrefix) {
        CDNDomain.resourcePrefix = resourcePrefix;
    }
}
