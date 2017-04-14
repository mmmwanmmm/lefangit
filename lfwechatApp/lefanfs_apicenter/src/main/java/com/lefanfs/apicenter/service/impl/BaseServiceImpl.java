package com.lefanfs.apicenter.service.impl;

import com.lefanfs.base.enums.ContentTypeEnum;
import com.lefanfs.base.impl.SuperServiceImpl;
import com.lefanfs.base.utils.RandomIDUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

public class BaseServiceImpl extends SuperServiceImpl {

	protected static final String GRANT_TYPE = "authorization_code";

	@Value("${resource.prefix}")
	protected String resourcePrefix;

	@Value("${user.default.icon}")
	protected String userDefaultIcon;

	@Value("${user.default.cover}")
	protected String userDefaultCover;

	@Value("${leancloud.app.server}")
	protected String leancloudAppServer;

	@Value("${leancloud.app.id}")
	protected String leancloudAppId;

	@Value("${leancloud.app.key}")
	protected String leancloudAppKey;

	@Value("${rongcloud.app.key}")
	protected String rongcloudAppKey;

	@Value("${rongcloud.app.secret}")
	protected String rongcloudAppSecret;

	@Value("${rongcloud.root.id}")
	protected String rongcloudRootId;

	@Value("${weixin.accessToken}")
	protected String weixinAccessToken;

	@Value("${weixin.getUserInfo}")
	protected String weixinGetUserInfo;

	@Value("${weibo.clientId}")
	protected String weiboClientId;

	@Value("${weibo.clientSecret}")
	protected String weiboClientSecret;

	@Value("${weibo.redirectUri}")
	protected String weiboRedirectUri;

	@Value("${jpush.appKey}")
	protected String jpushAppKey;

	@Value("${jpush.masterSecret}")
	protected String jpushMasterSecret;

	@Value("${kuaidi100.viewLogisticsUrl}")
	protected String kuaidi100ViewLogisticsUrl;

	protected String getUserIcon(String icon) {
		if (!StringUtils.isEmpty(icon)) {
			if (icon.startsWith("http")) {
				return icon;
			}
			return this.getCdnUrl(null, icon, null);
		}
		return this.userDefaultIcon;
	}

	protected String getUserCover(String cover) {
		if (!StringUtils.isEmpty(cover)) {
			if (cover.startsWith("http")) {
				return cover;
			}
			return this.getCdnUrl(null, cover, null);
		}
		return this.userDefaultCover;
	}

	protected String getCdnUrl(ContentTypeEnum typeEnum, String resourcePath, String sizeFlag) {
		if (resourcePath != null && resourcePath.startsWith("http")) {
			return resourcePath;
		}
		return this.getStaticUrl(this.resourcePrefix, resourcePath, sizeFlag);
	}

	protected String generateUsername() {
		return "cdd" + RandomIDUtil.getNumber(10);
	}

	protected String getDisplayName(String username, String nickname) {
		if (!StringUtils.isEmpty(nickname)) {
			return nickname;
		}
		return username;
	}
}
