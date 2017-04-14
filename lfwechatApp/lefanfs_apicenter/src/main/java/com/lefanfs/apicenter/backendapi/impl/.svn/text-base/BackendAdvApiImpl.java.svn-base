package com.lefanfs.apicenter.backendapi.impl;

import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.apicenter.backendapi.BackendAdvApi;
import com.lefanfs.apicenter.dao.SysAdInfoMapper;
import com.lefanfs.apicenter.model.SysAdInfo;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@ApiService(descript = "后台广告管理API")
public class BackendAdvApiImpl extends BaseServiceImpl implements BackendAdvApi {
	@Autowired
	private SysAdInfoMapper sysAdInfoMapper;

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "后台广告列表", value = "backend-adv-list")
	public ApiResponse list(ApiRequest apiReq) {
		this.setBackendPageSize(apiReq);
		int count = sysAdInfoMapper.selectCountForBackend(apiReq);
		List<SysAdInfo> list = sysAdInfoMapper.selectListForBackend(apiReq);
		if (list != null) {
			for (SysAdInfo dto : list) {
				dto.setAdPic(this.getCdnUrl(null, dto.getAdPic(), null));
			}
		}
		return new ApiResponse<List<SysAdInfo>>(ApiMsgEnum.SUCCESS, count, list);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "后台广告保存", value = "backend-adv-save")
	public ApiResponse save(ApiRequest apiReq) {
		String adCode = apiReq.getString("adCode");
		String adTitle = apiReq.getString("adTitle");
		String adHerf = apiReq.getString("adHerf");
		Integer adOrder = apiReq.getInt("adOrder");
		String adPic = apiReq.getString("adPic");
		if (StringUtils.isEmpty(adCode) || StringUtils.isEmpty(adTitle) || StringUtils.isEmpty(adOrder) || StringUtils.isEmpty(adPic)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		SysAdInfo record = new SysAdInfo();
		record.setAdCode(adCode);
		record.setAdTitle(adTitle);
		record.setAdHerf(adHerf);
		record.setAdOrder(adOrder);
		record.setAdPic(adPic);
		record.setDeleteFlag(0);
		record.setCreateTime(new Date());
		this.sysAdInfoMapper.insert(record);
		return new ApiResponse(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "后台广告获取", value = "backend-adv-getById")
	public ApiResponse getById(ApiRequest apiReq) {
		Long advId = apiReq.getLong("advId");
		if (StringUtils.isEmpty(advId)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		SysAdInfo record = this.sysAdInfoMapper.selectByPrimaryKey(advId);
		if (record != null) {
			record.setAdPic(this.getCdnUrl(null, record.getAdPic(), null));
		}
		return new ApiResponse<SysAdInfo>(ApiMsgEnum.SUCCESS, (record == null ? 0 : 1), record);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "后台广告更新", value = "backend-adv-update")
	public ApiResponse update(ApiRequest apiReq) {
		Long advId = apiReq.getLong("advId");
		String adCode = apiReq.getString("adCode");
		String adTitle = apiReq.getString("adTitle");
		String adHerf = apiReq.getString("adHerf");
		Integer adOrder = apiReq.getInt("adOrder");
		String adPic = apiReq.getString("adPic");
		if (StringUtils.isEmpty(advId) || StringUtils.isEmpty(adCode) || StringUtils.isEmpty(adTitle) || StringUtils.isEmpty(adHerf) || StringUtils.isEmpty(adOrder) || StringUtils.isEmpty(adPic)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		SysAdInfo record = new SysAdInfo();
		record.setId(advId);
		record.setAdCode(adCode);
		record.setAdTitle(adTitle);
		record.setAdHerf(adHerf);
		record.setAdOrder(adOrder);
		record.setAdPic(adPic);
		this.sysAdInfoMapper.updateByPrimaryKeySelective(record);
		return new ApiResponse(ApiMsgEnum.SUCCESS);
	}

	@Override
	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "后台广告变更状态", value = "backend-adv-changeStatus")
	public ApiResponse changeStatus(ApiRequest apiReq) {
		Long advId = apiReq.getLong("advId");
		Integer deleteFlag = apiReq.getInt("deleteFlag");
		if (StringUtils.isEmpty(advId) || StringUtils.isEmpty(deleteFlag)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		SysAdInfo record = new SysAdInfo();
		record.setId(advId);
		record.setDeleteFlag(deleteFlag);
		this.sysAdInfoMapper.updateByPrimaryKeySelective(record);
		return new ApiResponse(ApiMsgEnum.SUCCESS);
	}
}
