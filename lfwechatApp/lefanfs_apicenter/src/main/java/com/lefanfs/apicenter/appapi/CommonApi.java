package com.lefanfs.apicenter.appapi;

import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.apicenter.dto.AdListDto;

import java.util.List;

/**
 * 通用API
 * 
 * @author Daniel
 */
public interface CommonApi {
	/**
	 * 
	 * 获取配置
	 * 
	 * @param apiReq
	 * @return
	 * @author Daniel
	 */
	@SuppressWarnings("rawtypes")
	ApiResponse getConfig(ApiRequest apiReq);

	/**
	 * 发送短信验证码
	 * 
	 * @param apiReq
	 * @return
	 * @author Daniel
	 */
	@SuppressWarnings("rawtypes")
	ApiResponse sendSms(ApiRequest apiReq);

	/**
	 * 获取推送token
	 * 
	 * @param apiReq
	 * @return
	 * @author Daniel
	 */
	@SuppressWarnings("rawtypes")
	ApiResponse getPushToken(ApiRequest apiReq);

	/**
	 * 获取广告列表
	 * 
	 * @param apiReq
	 * @return
	 * @author Daniel
	 */
	ApiResponse<List<AdListDto>> getAdList(ApiRequest apiReq);

	/**
	 * 上传图片
	 * @param apiReq
	 * @return
     */
	ApiResponse uploadImg(ApiRequest apiReq);
}
