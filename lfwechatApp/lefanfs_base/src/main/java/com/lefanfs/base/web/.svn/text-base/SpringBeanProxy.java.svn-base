package com.lefanfs.base.web;

import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.constants.Constant;
import com.lefanfs.base.enums.AppApiMethodEnum;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpringBeanProxy {

	private static ApplicationContext applicationContext;

	private static Map<String, Object[]> API_METHOD_MAP = new HashMap<String, Object[]>();

	/**
	 * api类目
	 */
	private static Map<String, String> apiCodeCatalogMap = new LinkedHashMap<String, String>();

	/**
	 * api列表
	 */
	private static Map<String, Map<String, String>> apiCodeListMap = new LinkedHashMap<String, Map<String, String>>();

	/**
	 * api参数
	 */
	private static Map<String, Map<String, String>> apiCodeParamMap = new LinkedHashMap<String, Map<String, String>>();

	public synchronized static void setApplicationContext(ApplicationContext arg0) {
		applicationContext = arg0;

		Map<String, Object> tempMap = applicationContext.getBeansWithAnnotation(ApiService.class);
		if (tempMap != null && tempMap.size() > 0) {
			for (Map.Entry<String, Object> entry : tempMap.entrySet()) {
				String beanName = entry.getKey();
				Object bean = entry.getValue();
				ApiService beanFc = bean.getClass().getAnnotation(ApiService.class);
				if (beanFc != null) {
					apiCodeCatalogMap.put(beanName, beanFc.descript());

					Method[] methodArr = bean.getClass().getDeclaredMethods();
					if (methodArr != null && methodArr.length > 0) {
						Map<String, String> methodFunctionCodeMap = new LinkedHashMap<String, String>();
						for (Method method : methodArr) {
							ApiMethod methodFc = method.getAnnotation(ApiMethod.class);
							if (methodFc != null) {
								String methodFunctionCode = methodFc.value();
								API_METHOD_MAP.put(methodFunctionCode, new Object[] { beanName, method, methodFc });

								StringBuffer str = new StringBuffer();
								str.append("/**\n");
								str.append(" * " + methodFc.descript() + "\n");
								str.append(" */\n");
								str.append(methodFunctionCode.toUpperCase().replaceAll("\\-", "_") + "(\"" + methodFunctionCode + "\", \"" + methodFc.descript() + "\", ApiServerEnum.apicenter, null,"
										+ methodFc.needLogin() + ") {");
								str.append("	@Override");
								str.append("	public Map<String, String> getApiParams() {");
								str.append("		Map<String, String> paramMap = new LinkedHashMap<String, String>();");
								Map<String, String> paramMap = new LinkedHashMap<String, String>();
								if (methodFc.needLogin()) {
									paramMap.put(Constant.USER_TOKEN, "当前用户token(*)");
									str.append("		paramMap.put(\"" + Constant.USER_TOKEN + "\", \"" + "当前用户token(*)" + "\");");
								}
								ApiParam[] params = methodFc.apiParams();
								if (params != null) {
									for (ApiParam param : params) {
										// TODO
										// 生成文档时候，如果有needLogin注解，如果有current_user_id则把它转换成USER_TOKEN；如果没有则直接加入USER_TOKEN字段。
										if (methodFc.needLogin() && (Constant.CURRENT_USER_ID.equals(param.name()) || Constant.USER_TOKEN.equals(param.name()))) {
											continue;
										}
										paramMap.put(param.name(), param.descript());
										str.append("		paramMap.put(\"" + param.name() + "\", \"" + param.descript() + "\");");
									}
								}

								str.append("		return paramMap;");
								str.append("	}");
								str.append("},\n");
								if (AppApiMethodEnum.getApiMethodEnum(methodFunctionCode) == null && BackendApiMethodEnum.getApiMethodEnum(methodFunctionCode) == null) {
									System.out.println(str.toString());
								}
								apiCodeParamMap.put(methodFunctionCode, paramMap);

								methodFunctionCodeMap.put(methodFunctionCode, methodFc.descript());
							}
						}
						apiCodeListMap.put(beanName, methodFunctionCodeMap);
					}
				}
			}
		}
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static Object getBeanByApiCode(String apiCode) {
		Object[] objArr = API_METHOD_MAP.get(apiCode);
		if (objArr != null && objArr.length >= 3) {
			String beanName = (String) objArr[0];
			return getBean(beanName);
		}
		return null;
	}

	public static Method getMethodByApiCode(String apiCode) {
		Object[] objArr = API_METHOD_MAP.get(apiCode);
		if (objArr != null && objArr.length >= 3) {
			return (Method) objArr[1];
		}
		return null;
	}

	public static ApiMethod getApiMethodByApiCode(String apiCode) {
		Object[] objArr = API_METHOD_MAP.get(apiCode);
		if (objArr != null && objArr.length >= 3) {
			return (ApiMethod) objArr[2];
		}
		return null;
	}

	public static Map<String, String> getFunctionListByCatalog(String catalog) {
		return apiCodeListMap.get(catalog);
	}

	public static Map<String, String> getParamsByApiCode(String apiCode) {
		return apiCodeParamMap.get(apiCode);
	}

	public static Map<String, String> getFunctionCodeCatalogMap() {
		return apiCodeCatalogMap;
	}

}
