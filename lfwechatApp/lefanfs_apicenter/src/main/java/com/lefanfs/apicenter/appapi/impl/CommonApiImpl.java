package com.lefanfs.apicenter.appapi.impl;

import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.base.utils.RandomIDUtil;
import com.lefanfs.apicenter.appapi.CommonApi;
import com.lefanfs.apicenter.dto.AdListDto;
import com.lefanfs.apicenter.service.AdService;
import com.lefanfs.apicenter.service.SmsService;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import com.lefanfs.apicenter.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ApiService(descript = "通用API")
public class CommonApiImpl extends BaseServiceImpl implements CommonApi {
	@Autowired
	private SmsService smsService;
	@Autowired
	private AdService adService;

    @Value("${file.server.url}")
    private String fileServerUrl;



    @ApiMethod(descript = "获取配置", value = "common-getConfig", apiParams = {})
	@SuppressWarnings("rawtypes")
	@Override
	public ApiResponse getConfig(ApiRequest apiReq) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("driverReportInterval", 30);
		retMap.put("mustUpdateFlag", 1);
		// TODO
		return new ApiResponse<Map<String, Object>>(ApiMsgEnum.SUCCESS, retMap.size(), retMap);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "发送短信", value = "common-sendSms", apiParams = { @ApiParam(descript = "手机号(*)", name = "phone") })
	@Override
	public ApiResponse sendSms(ApiRequest apiReq) {
		String phone = apiReq.getString("phone");
		if (StringUtils.isEmpty(phone)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		String randomNum = RandomIDUtil.getNumber(6);
		smsService.sendSms(null, phone, randomNum);
		return new ApiResponse(ApiMsgEnum.ValidateCodeSendSuccess);
	}

	@Override
	// @ApiMethod(needLogin = true, descript = "获取推送token", value =
	// "common-getPushToken", apiParams = {})
	@SuppressWarnings("rawtypes")
	public ApiResponse getPushToken(ApiRequest apiReq) {
		String pushToken = null;
		return new ApiResponse<String>(ApiMsgEnum.SUCCESS, (pushToken == null ? 0 : 1), pushToken);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@ApiMethod(descript = "获取广告列表", value = "common-getAdList", apiParams = { @ApiParam(descript = "广告位编号(*)", name = "placeCode"), @ApiParam(descript = "需要广告个数", name = "pageSize") })
	public ApiResponse<List<AdListDto>> getAdList(ApiRequest apiReq) {
		String placeCode = apiReq.getString("placeCode");
		if (StringUtils.isEmpty(placeCode)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		this.setPageIndex(apiReq);
		List<AdListDto> list = adService.selectList(placeCode, this.getAppPageSize(apiReq));
		return new ApiResponse<List<AdListDto>>(ApiMsgEnum.SUCCESS, (list == null ? 0 : list.size()), list);
	}

	@ApiMethod(descript = "上传图片", value = "common-uploadImg",apiParams = {@ApiParam(descript = "上传图片base64编码", name = "fileData"),@ApiParam(descript = "业务", name = "business")})
	@Override
	public ApiResponse uploadImg(ApiRequest apiReq) {
		String business = apiReq.getString("business");
		String fileData = apiReq.getString("fileData");
		if (business==null){
			business="default";
		}
		String filePath = FileUtils.saveBytesToFile(business, fileData);
		Map<String,String> retMap = new HashMap<>();
		retMap.put("imgKey",filePath);
		retMap.put("imgUrl",getCdnUrl(null,filePath,null));
		return new ApiResponse(ApiMsgEnum.SUCCESS,1,retMap);
	}
}
