package com.lefanfs.base.constants;

public class Constant {

	// //////////// 系统参数分为以下3块：
	// // 1.客户端每次请求API时从header中携带的设备相关的参数，不能影响缓存策略！；
	public static final String DEVICE_NO = "device_no";
	public static final String MAC_ADDRESS = "mac_address";
	public static final String OS_VERSION = "os_version";
	public static final String MODEL_NAME = "model_name";
	public static final String CHANNEL_CODE = "channel_code";
	public static final String CLIENT_BUNDLE_ID = "client_bundle_id";
	public static final String CLIENT_VERSION_NAME = "client_version_name";
	public static final String CLIENT_VERSION_CODE = "client_version_code";
	public static final String PUSH_DEVICE_ID = "push_device_id";
	public static final String USER_TOKEN = "user_token";

	// // 2.客户端每次请求API时与业务参数一起传递的参数；
	public static final String API_CODE = "api_code";
	public static final String API_VERSION = "api_version";
	public static final String API_KEY = "api_key";
	public static final String API_SIGN = "api_sign";

	// // 3.服务端收集整理的参数；
	public static final String REQUEST_IP = "request_ip";
	public static final String REQUEST_TIMESTAMP = "request_timestamp";
	public static final String USER_AGENT = "user_agent";
	public static final String CURRENT_USER_ID = "current_user_id";

	// // 常量
	public static final String INNER_API_KEY = "innerApiKey";
	public static final String INNER_API_SIGN = "innerApiSign";
	public static final String X_CLIENT_EVENT = "X-Client-Event";
	public static final String X_HASH_IP = "X-Hash-Ip";
	public static final String DATA = "data";
	public static final String PAGE = "page";
	public static final String PAGE_SIZE = "pageSize";
	public static final String PAGE_INDEX = "pageIndex";
	public static final int APP_PAGE_SIZE = 10;
	public static final int BACKEND_PAGE_SIZE = 20;

	public static final String COOKIE_KEY_USER_TOKEN = "user_token";
	public static final String SESSION_KEY_USER = "current_user";

	// public static final String JUST_NEED_RESULT = "justNeedResult";
	// public static final String DEFAULT_ACCESS_TOKEN = "innerToken";
	// public static final String ACCESS_TOKEN = "token";

}