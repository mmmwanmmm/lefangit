package com.lefanfs.apicenter.backendapi.impl;

import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.apicenter.backendapi.BackendUserApi;
import com.lefanfs.apicenter.dao.UserInfoMapper;
import com.lefanfs.apicenter.model.UserInfo;
import com.lefanfs.apicenter.service.RedisService;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ApiService(descript = "后台用户管理API")
public class BackendUserApiImpl extends BaseServiceImpl implements BackendUserApi {
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private RedisService redisService;

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "后台用户列表", value = "backend-user-list")
	public ApiResponse list(ApiRequest apiReq) {
        Integer page = apiReq.getInt("page");
        int pageSize = 10;
        if (page==null || page<1){
            page = 1;
        }
        page =(page - 1) * pageSize;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageIndex",page);
        paramMap.put("pageSize",pageSize);
        List<UserInfo> userInfoList=userInfoMapper.selectUserInfoByParam(paramMap);
        return new ApiResponse(ApiMsgEnum.SUCCESS,userInfoList==null?0:userInfoList.size(),userInfoList);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "后台用户获取", value = "backend-user-getById")
	public ApiResponse getById(ApiRequest apiReq) {
		Long userId = apiReq.getLong("userId");
		/*if (StringUtils.isEmpty(userId)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		UserInfo record = this.userInfoMapper.selectByPrimaryKey(userId);
		if (record != null) {
			record.setUserIcon(this.getCdnUrl(null, record.getUserIcon(), null));
		}*//*
		return new ApiResponse<UserInfo>(ApiMsgEnum.SUCCESS, (record == null ? 0 : 1), record);*/
        return null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "后台用户变更状态", value = "backend-user-changeStatus")
	public ApiResponse changeStatus(ApiRequest apiReq) {
		Long userId = apiReq.getLong("userId");
		Integer userState = apiReq.getInt("userState");
		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userState)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		UserInfo record = new UserInfo();
		record.setUserId(userId);
		record.setUserState(userState);
		record.setModifyTime(new Date());
		this.userInfoMapper.updateByPrimaryKeySelective(record);
		if (userState == 1) {
			this.redisService.removeUserSession(userId);
		}
		return new ApiResponse(ApiMsgEnum.SUCCESS);
	}
}
