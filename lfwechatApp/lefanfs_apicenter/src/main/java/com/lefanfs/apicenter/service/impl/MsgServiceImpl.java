package com.lefanfs.apicenter.service.impl;

import com.lefanfs.apicenter.dao.UserInfoMapper;
import com.lefanfs.apicenter.dao.UserMessageMapper;
import com.lefanfs.apicenter.dto.MsgListDto;
import com.lefanfs.apicenter.enums.MsgBizTypeEnum;
import com.lefanfs.apicenter.model.UserInfo;
import com.lefanfs.apicenter.model.UserMessage;
import com.lefanfs.apicenter.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MsgServiceImpl extends BaseServiceImpl implements MsgService {
	/*@Autowired
	private UserMessageMapper userMessageMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public void deleteMsg(Long msgId) {
		UserMessage record = new UserMessage();
		record.setId(msgId);
		record.setDeleteFlag(1);
		record.setModifyTime(new Date());
		this.userMessageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void readMsg(Long msgId) {
		UserMessage record = new UserMessage();
		record.setId(msgId);
		record.setIsRead(1);
		record.setModifyTime(new Date());
		this.userMessageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<MsgListDto> selectMsgList(Map<String, Object> paramMap) {
		List<MsgListDto> list = this.userMessageMapper.selectList(paramMap);
		return list;
	}

	@Override
	public void persistMsg(MsgBizTypeEnum msgBizTypeEnum, Long fromUserId, Long toUserId, String msg, Long businessId) {
		try {
			UserMessage record = new UserMessage();

			record.setFromId(fromUserId);
			record.setUserId(toUserId);
			record.setBusinessType(msgBizTypeEnum.getTypeId());
			record.setMessage(msg);
			record.setBusinessId(businessId);
			record.setIsRead(0);
			record.setBusinessState(0);
			record.setDeleteFlag(0);
			this.userMessageMapper.insert(record);

			this._pushMsg(msgBizTypeEnum, record);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void _pushMsg(MsgBizTypeEnum msgBizTypeEnum, UserMessage record) {
		UserInfo userinfo = userInfoMapper.selectByPrimaryKey(record.getUserId());
		if (userinfo != null && !StringUtils.isEmpty(userinfo.getPushDevice())) {
			String[] registrationIds = new String[] { userinfo.getPushDevice() };
			// MsgListDto pushDto = new MsgListDto();
			// pushDto.setMsgId(record.getId());
			// pushDto.setMsgIcon(this.getCdnUrl(null,
			// msgBizTypeEnum.getTypeIcon(), null));
			// pushDto.setBusinessType(msgBizTypeEnum.getTypeId());
			// pushDto.setMsgTitle(msgBizTypeEnum.getTypeName());
			// pushDto.setMsgContent(record.getMessage());
			// pushDto.setBusinessId(record.getBusinessId());
			// String pushJson = JsonUtil.object2Fastjson(pushDto);
			// String pushJson = JsonUtil.objectToJson(pushDto);
			Map<String, String> extras = new HashMap<String, String>();
			extras.put("msgId", record.getId().toString());
			extras.put("msgIcon", this.getCdnUrl(null, msgBizTypeEnum.getTypeIcon(), null));
			extras.put("msgType", msgBizTypeEnum.name());
			extras.put("businessType", String.valueOf(msgBizTypeEnum.getTypeId()));
			extras.put("msgTitle", msgBizTypeEnum.getTypeName());
			extras.put("msgContent", record.getMessage());
			extras.put("businessId", record.getBusinessId().toString());

			UserMessage record2 = new UserMessage();
			record2.setId(record.getId());
			record2.setPushState(1);
			record2.setPushTime(new Date());
			record2.setModifyTime(new Date());
			this.userMessageMapper.updateByPrimaryKeySelective(record2);
		}
	}*/

}
