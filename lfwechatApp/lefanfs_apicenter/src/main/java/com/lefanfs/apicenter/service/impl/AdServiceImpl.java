package com.lefanfs.apicenter.service.impl;

import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.apicenter.dao.SysAdInfoMapper;
import com.lefanfs.apicenter.dto.AdListDto;
import com.lefanfs.apicenter.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl extends BaseServiceImpl implements AdService {
	@Autowired
	private SysAdInfoMapper sysAdInfoMapper;

	@Override
	public List<AdListDto> selectList(String placeCode, int limit) {
		ApiRequest apiReq = new ApiRequest();
		this.setPageIndex(apiReq);
		apiReq.put("adCode", placeCode);
		List<AdListDto> list = sysAdInfoMapper.selectList(apiReq);
		if (list != null) {
			for (AdListDto dto : list) {
				dto.setAdPic(this.getCdnUrl(null, dto.getAdPic(), null));
			}
		}
		return list;
	}

}
