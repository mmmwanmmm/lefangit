package com.lefanfs.base.utils;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class MemcachedUtil {
	private static Object LOCK = new Object();
	private final static Log loger = LogFactory.getLog(MemcachedUtil.class);
	private Properties properties;
	private MemCachedClient memCachedClient;
	public final static int SESSION_SECOND = 3600 * 24;
	public final static int TWO_HOUR_SECOND = 7200;
	public final static int FIVE_HOUR_SECOND = 5 * 60 * 60;
	public final static int TEN_HOUR_SECOND = 10 * 60 * 60;
	public final static String memcacheMapKey = "MEMACACHE_MAP_KEY";

	private static MemcachedUtil instance;

	private void init() {
		try {
			properties = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("memcached.properties");
			properties.load(inStream);
			// 数据缓存服务器，“,”表示配置多个memcached服务
			String[] servers = properties.getProperty("memcache.cache.server").split(",");
			SockIOPool pool = SockIOPool.getInstance("dataServer");
			pool.setServers(servers);
			pool.setFailover(true);
			pool.setInitConn(10);
			pool.setMinConn(5);
			pool.setMaxConn(50);
			pool.setMaintSleep(30);
			pool.setNagle(false);
			pool.setSocketTO(30000);
			pool.setBufferSize(1024 * 1024 * 5);
			pool.setAliveCheck(true);
			pool.initialize(); /* 建立MemcachedClient实例 */
			memCachedClient = new MemCachedClient("dataServer");
		} catch (Exception e) {
			loger.error("MemcachedUtil.init Exception:", e);
		}
	}

	private MemcachedUtil() {
		init();
	}

	private boolean isCacheEnabled() {
		boolean useCache = false;
		try {
			useCache = Boolean.valueOf(properties.getProperty("cache.enable"));
		} catch (Exception e) {
			useCache = false;
			loger.info("Please enable memcached");
		}
		return useCache;
	}

	/**
	 * 改用嵌套类静态实始化
	 * 
	 * @return
	 */
	public static MemcachedUtil getInstance() {
		if (instance == null) {
			synchronized (LOCK) {
				if (instance == null) {
					instance = new MemcachedUtil();
				}
			}
		}
		return instance;
	}

	// /**
	// * 替换
	// *
	// * @param key
	// * @param seconds
	// * 过期秒数
	// * @param obj
	// * @return
	// */
	// public boolean replace(String key, int seconds, Object obj) {
	// if (key == null || "".equals(key)) {
	// return false;
	// }
	// if (obj == null) {
	// return true;
	// }
	// try {
	// if (isCacheEnabled()) {
	// Date expDate = getDateAfter(seconds);
	// boolean result = memCachedClient.replace(key, obj, expDate);
	// loger.info("SET A OBJECT: KEY:" + key + ", OBJ:" + obj + ", exp:" +
	// DateTimeUtil.formatDateTime(expDate) + ", result:" + result);
	// return result;
	// }
	// return true;
	// } catch (Exception e) {
	// loger.error("MemcachedUtil.replace exception:", e);
	// }
	// return false;
	// }

	/**
	 * 存储对象到memcache，永不过期
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	public boolean set(String key, Object obj) {
		if (key == null || "".equals(key)) {
			return false;
		}
		if (obj == null) {
			return true;
		}
		try {
			if (isCacheEnabled()) {
				boolean result = memCachedClient.set(key, obj);
				loger.info("SET A OBJECT: KEY:" + key + ", result:" + result);
				return result;
			}
			return true;
		} catch (Exception e) {
			loger.error("MemcachedUtil.set exception:", e);
		}
		return false;
	}

	/**
	 * set 单位秒
	 * 
	 * @param key
	 * @param expSecond
	 * @param obj
	 * @return
	 */
	public boolean set(String key, int expSecond, Object obj) {
		return this.set(key, getDateAfter(expSecond), obj);
	}

	/**
	 * 将KEY保存到memcache中
	 * 
	 * @param key
	 * @param exp
	 * @param obj
	 * @return
	 */
	private boolean set(String key, Date exp, Object obj) {
		if (key == null || "".equals(key)) {
			return false;
		}
		if (obj == null) {
			return true;
		}
		try {
			if (isCacheEnabled()) {
				boolean result = memCachedClient.set(key, obj, exp);
				loger.info("SET A OBJECT: KEY:" + key + ", OBJ:" + obj + ", exp:" + DateTimeUtil.formatDateTime(exp) + ", result:" + result);
				return result;
			}
			return true;
		} catch (Exception e) {
			loger.error("MemcachedUtil.set exception:", e);
		}
		return false;
	}

	// /**
	// * @deprecated 此方法不建议使用
	// *
	// * 保存key和描述信息；
	// *
	// * @param key
	// * @param second
	// * （单位：秒）
	// * @param obj
	// * @return
	// */
	// @Deprecated
	// public boolean setWithDis(String key, int seconds, Object obj, String
	// discript) {
	// if (key == null || "".equals(key)) {
	// return false;
	// }
	// if (obj == null) {
	// return true;
	// }
	// try {
	// if (isCacheEnabled()) {
	// Date expDate = getDateAfter(seconds);
	// this.putKeyDisMap(key, discript);
	// boolean result = memCachedClient.set(key, obj, expDate);
	// loger.debug("SET A OBJECT: KEY:" + key + ", OBJ:" + obj + ", expDate:" +
	// DateTimeUtil.formatDateTime(expDate));
	// return result;
	// }
	// return true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// loger.error(e, e);
	// }
	// return false;
	// }
	//
	// /**
	// * 把相应的Key值和描述存到此方法中；
	// *
	// * @param key
	// * @param discript
	// * @return
	// */
	// @SuppressWarnings("unchecked")
	// private boolean putKeyDisMap(String key, String discript) {
	// Map<String, String> memMap;
	// Object obj = memCachedClient.get(memcacheMapKey);
	// if (obj == null) {
	// memMap = new HashMap<String, String>();
	// } else {
	// memMap = (HashMap<String, String>) obj;
	// }
	// memMap.put(key, discript);
	// memCachedClient.set(memcacheMapKey, memMap, getDateAfter(60 * 60 * 48));
	//
	// return true;
	// }

	/**
	 * 取
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		try {
			if (isCacheEnabled()) {
				Object obj = memCachedClient.get(key);
				loger.info("GET A OBJECT: KEY:" + key + " is null:" + (obj == null));
				return obj;
			}
		} catch (Exception e) {
			loger.error("MemcachedUtil.get exception:", e);
		}
		return null;
	}

	/**
	 * 取
	 * 
	 * @param key
	 * @return
	 */
	public boolean remove(String key) {
		if (key == null || "".equals(key)) {
			return false;
		}
		try {
			if (isCacheEnabled()) {
				loger.info("delete memcached key: " + key);
				return memCachedClient.delete(key);
			}
		} catch (Exception e) {
			loger.error("MemcachedUtil.remove exception:", e);
		}
		return true;
	}

	/**
	 * 获得当前开始活参数秒的时间日期
	 * 
	 * @Title: getDateAfter @Description: @param @return Date 返回类型 @throws
	 */
	private static Date getDateAfter(int second) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, second);
		return cal.getTime();
	}

	public static void main(String[] args) throws Exception {
		// Map<String, String> map = (Map<String, String>)
		// MemcachedUtil.getInstance().get("af");
		// if (map == null) {
		// System.out.println("before keys: " + map);
		// map = new HashMap<String, String>();
		// for (int i = 0; i < 1; i++) {
		// Random r = new Random(System.currentTimeMillis());
		// String key = "KEY_" + r.nextLong();
		// System.out.println("keys: " + key);
		//
		// byte[] bt = new byte[0];
		// String value = new String(bt, "UTF-8");
		// for (int j = 0; j < 10; j++) {
		// value = value + "abc";
		// }
		// map.put(key, value);
		// }
		//
		// boolean a = MemcachedUtil.getInstance().set("af",
		// MemcachedUtil.TWO_HOUR_SECOND, map);
		//
		// boolean b = MemcachedUtil.getInstance().set("ac",
		// MemcachedUtil.TWO_HOUR_SECOND, "2");
		// System.out.println(a + ", " + b);
		// }
		//
		// System.out.println(MemcachedUtil.getInstance().get("ac") +
		// "after keys: " + map.size());

		String json = (String) MemcachedUtil.getInstance().get("PACKAGED_ETAIL_98681");
		System.out.println(json);

	}
}
