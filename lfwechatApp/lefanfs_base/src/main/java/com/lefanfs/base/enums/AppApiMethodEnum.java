package com.lefanfs.base.enums;

import com.lefanfs.base.interfaces.Api;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * APP API列表
 * 
 * @author Daniel
 */
public enum AppApiMethodEnum implements Api {
	/**
	 * 获取配置
	 */
	COMMON_GETCONFIG("common-getConfig", "获取配置", ApiServerEnum.apicenter, null, false) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			return paramMap;
		}
	},
	COMMON_UPLOADIMG("common-uploadImg", "上传图片", ApiServerEnum.apicenter, null, false) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("business", "业务(*)");
			paramMap.put("fileData", "上传图片base64编码(*)");
			return paramMap;
		}
	},
	/**
	 * 微信登录
	 */
	USER_LOGINBYWECHAT("user-loginByWechat", "微信登录", ApiServerEnum.apicenter, null, false) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("authCode", "授权CODE(*)");
			return paramMap;
		}
	},
	/**
	 * 用户退出
	 */
	USER_LOGOUT("user-logout", "用户退出", ApiServerEnum.apicenter, null, true) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("user_token", "当前用户token(*)");
			return paramMap;
		}
	},
	AREA_PROVINCE_LIST("area-province-list", "省份列表", ApiServerEnum.apicenter, null, false) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			return paramMap;
		}
	},
	AREA_ALL("area-all", "全部城市", ApiServerEnum.apicenter, null, false) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			return paramMap;
		}
	},

	AREA_CHILDREN("area-children", "获取下级区域列表", ApiServerEnum.apicenter, null, false) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("areaParentId", "上级区域id(*)");
			return paramMap;
		}
	},
    USER_SEND_SMS("user-sendSMS", "发送验证码", ApiServerEnum.apicenter, null, false) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("telphone", "手机号码");
            return paramMap;
        }
    },
    USER_LOGIN("user-login", "用户登录", ApiServerEnum.apicenter, null, false) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("phone", "手机号码");
            paramMap.put("validateCode", "验证码");
            paramMap.put("nickName", "昵称");
            paramMap.put("img", "头像");
            paramMap.put("gender", "性别");
            paramMap.put("openId", "用户openId");
            paramMap.put("promotedId", "用户promotedId");
            return paramMap;
        }
    },
    SCENARIOS_LIST("scenarios-list", "用户案例列表", ApiServerEnum.apicenter, null, false) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            return paramMap;
        }
    },
    SCENARIOS_GETSCENARIOSBYID("scenarios-getScenariosById", "用户案例详情", ApiServerEnum.apicenter, null, false) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "用户案例ID(*)");
            return paramMap;
        }
    },
    INSERT_LOAN_APPLICATION("insert-loan-application", "申请贷款", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            paramMap.put("userName", "用户姓名(*)");
            paramMap.put("userPhone", "手机号码(*)");
            paramMap.put("isTrafficAccident", "是否为交通事故(*)");
            paramMap.put("loanMoney", "贷款金额(*)");
            paramMap.put("loanPurpose", "贷款用途(*)");
            paramMap.put("accidentProvince", "事故发生省(*)");
            paramMap.put("accidentCity", "事故发生市(*)");
            paramMap.put("accidentDistrict", "事故发生区(*)");
            paramMap.put("accidentAddress", "事故发生详细地址(*)");
            return paramMap;
        }
    },
    PROMOTEDINFO_LIST("promotedInfo-list", "我的推广人列表", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            return paramMap;
        }
    },
    SELECT_ADINFO_LIST("select-adinfo-list", "根据广告code查询广告信息", ApiServerEnum.apicenter, null, false) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("adCode", "广告位code(*)");
            return paramMap;
        }
    },
    SELECT_QAINFO_LIST("select-qainfo-list", "根据类别查询QA问答信息", ApiServerEnum.apicenter, null, false) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("type", "QA问答类型(*)");
            return paramMap;
        }
    },
    SELECT_COMPANYINFO_BYID("select-companyInfo-byId", "根据ID查询公司服务信息", ApiServerEnum.apicenter, null, false) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "公司服务信息标识ID(*)");
            return paramMap;
        }
    },
    USER_COMMENT_ADD("user-comment-add", "留言", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            paramMap.put("comment", "留言内容(*)");
            paramMap.put("parentCommentid", "回复的留言id可选(*)");
            paramMap.put("type", "留言类型(*)");
            paramMap.put("commentTo", "留言来源（1:小程序，2：其他）(*)");
            return paramMap;
        }
    },
    USER_COMMENT_LIST("user-comment-myList", "查询留言", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            paramMap.put("page", "分页页码1开始");
            paramMap.put("pageSize", "1页多少条");
            return paramMap;
        }
    },
    SELECT_USER_BYOPENID("select-user-byCode", "根据用户微信客户端code查询用户信息", ApiServerEnum.apicenter, null, false) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("jsCode", "微信客户端code(*)");
            return paramMap;
        }
    },
    LOANAPPLICATION_LIST("loanApplication-list", "我的贷款申请列表", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            return paramMap;
        }
    },
    LOAN_APPLICATION_INDETAILS("loan-application-indetails", "贷款详情", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            paramMap.put("id", "贷款申请id(*)");
            return paramMap;
        }
    },
    PROMOTEDINFO_INDETAILS("promotedInfo-indetails", "推广详情", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            paramMap.put("id", "推广记录标识ID(*)");
            return paramMap;
        }
    },
    MESSAGE_LIST("messageInfo-list", "消息列表", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            return paramMap;
        }
    },
    MESSAGE_INDETAILS("messageInfo-indetails", "消息详情", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            paramMap.put("id", "推广记录标识ID(*)");
            return paramMap;
        }
    },
    PROMOTEDINFO_UPDATE("promotedInfo-update", "修改推广人备注相关信息", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            paramMap.put("id", "推广记录标识ID(*)");
            paramMap.put("customerDesc", "客户备注");
            paramMap.put("customerText", "客户描述");
            paramMap.put("customerBusinessCard", "客户名片");
            paramMap.put("customerWechatid", "客户微信号");
            return paramMap;
        }
    }
    ,
    USER_PROMOTED_INDETAILS("user-promoted-indetails", "用户推广认证信息查询", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            return paramMap;
        }
    },
    INSERT_USER_PROMOTED("insert-user-promoted", "提交推广认证信息", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            paramMap.put("realName", "真实姓名(*)");
            paramMap.put("phone", "手机号码(*)");
            paramMap.put("occupation", "职业(*)");
            paramMap.put("province", "省(*)");
            paramMap.put("city", "市(*)");
            paramMap.put("district", "区域(*)");
            paramMap.put("wechatId", "微信号");
            paramMap.put("formId", "提交表单信息formId");
            return paramMap;
        }
    },
    UPDATE_USER_PROMOTED("update-user-promoted", "修改推广认证信息", ApiServerEnum.apicenter, null, true) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("user_token", "当前用户token(*)");
            paramMap.put("id", "主键标识ID(*)");
            paramMap.put("realName", "真实姓名");
            paramMap.put("phone", "手机号码");
            paramMap.put("occupation", "职业");
            paramMap.put("province", "省");
            paramMap.put("city", "市");
            paramMap.put("district", "区域");
            paramMap.put("wechatId", "微信号");
            paramMap.put("formId", "提交表单信息formId");
            return paramMap;
        }
    }
    ,
    SELECT_COMPANYiNFO_BYID("select-companyInfo-byId", "根据ID查询公司服务信息", ApiServerEnum.apicenter, null, false) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "公司介绍标识ID(*)");
            return paramMap;
        }
    }
	;

	private String code;
	private String msg;
	private ApiServerEnum apiServer;
	private ContentTypeEnum contentType;
	private boolean needLogin;

	/**
	 * @param code
	 *            编码
	 * @param msg
	 *            描述
	 * @param apiServer
	 *            所属服务器
	 * @param contentType
	 *            所属业务
	 * @param needLogin
	 *            是否需要登录
	 * @author Daniel
	 */
	AppApiMethodEnum(String code, String msg, ApiServerEnum apiServer, ContentTypeEnum contentType, boolean needLogin) {
		this.code = code;
		this.msg = msg;
		this.apiServer = apiServer;
		this.contentType = contentType;
		this.needLogin = needLogin;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public ApiServerEnum getApiServer() {
		return apiServer;
	}

	public ContentTypeEnum getContentType() {
		return contentType;
	}

	public boolean isNeedLogin() {
		return needLogin;
	}

	/**
	 * 获取所有API方法枚举
	 * 
	 * @return
	 * @author Daniel
	 */
	public static Map<String, AppApiMethodEnum> getApiMethodEnumMap() {
		Map<String, AppApiMethodEnum> retMap = new LinkedHashMap<String, AppApiMethodEnum>();
		AppApiMethodEnum[] enumArr = AppApiMethodEnum.values();
		for (AppApiMethodEnum aEnum : enumArr) {
			retMap.put(aEnum.getCode(), aEnum);
		}
		return retMap;
	}

	/**
	 * 根据服务器获取API方法（供对外的API测试工具使用）
	 * 
	 * @param apiServer
	 * @return
	 * @author Daniel
	 */
	public static Map<String, String> getApiMethodMapByServer(String apiServer) {
		ApiServerEnum sEnum = ApiServerEnum.getApiServerEnum(apiServer);
		Map<String, String> retMap = new LinkedHashMap<String, String>();
		if (sEnum != null) {
			AppApiMethodEnum[] enumArr = AppApiMethodEnum.values();
			for (AppApiMethodEnum aEnum : enumArr) {
				if (sEnum.name().equals(aEnum.getApiServer().name())) {
					retMap.put(aEnum.getCode(), aEnum.getMsg());
				}
			}
		}
		return retMap;
	}

	/**
	 * 根据API方法编号获取参数列表（供API测试工具使用）
	 * 
	 * @param methodCode
	 * @return
	 * @author Daniel
	 */
	public static Map<String, String> getApiParamMapByMethod(String methodCode) {
		AppApiMethodEnum mEnum = AppApiMethodEnum.getApiMethodEnum(methodCode);
		if (mEnum != null) {
			return mEnum.getApiParams();
		}
		return null;
	}

	public static AppApiMethodEnum getApiMethodEnum(String methodCode) {
		return getApiMethodEnumMap().get(methodCode);
	}

	/**
	 * 根据API方法编号获取所属服务器（供API总控制中心分发使用）
	 * 
	 * @param methodCode
	 * @return
	 * @author Daniel
	 */
	public static ApiServerEnum getApiServerEnum(String methodCode) {
		AppApiMethodEnum aEnum = getApiMethodEnumMap().get(methodCode);
		if (aEnum != null) {
			return aEnum.getApiServer();
		}
		return null;
	}

}
