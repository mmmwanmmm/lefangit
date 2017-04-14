package com.lefanfs.apicenter.backendapi.impl;

import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.base.utils.Md5Util;
import com.lefanfs.apicenter.backendapi.BackendAdminApi;
import com.lefanfs.apicenter.dao.SysUserMapper;
import com.lefanfs.apicenter.model.SysUser;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@ApiService(descript = "后台管理员")
public class BackendAdminApiImpl extends BaseServiceImpl implements BackendAdminApi {
	private static final Logger loger = Logger.getLogger(BackendAdminApiImpl.class);

	@Autowired
	private SysUserMapper sysUserMapper;

	@Resource
	private PlatformTransactionManager platformTransactionManager;

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "登录", value = "backend-admin-login", apiParams = { @ApiParam(descript = "用户名", name = "username"), @ApiParam(descript = "密码", name = "password"),
			@ApiParam(descript = "登录IP", name = "login_ip") })
	@Override
	public ApiResponse login(ApiRequest apiReq) {
		Object usernameObj = apiReq.get("username");
		Object passwordObj = apiReq.get("password");
		Object login_ip = apiReq.get("login_ip");
		if (StringUtils.isEmpty(usernameObj) || StringUtils.isEmpty(passwordObj) || StringUtils.isEmpty(login_ip)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		// 登录
		SysUser user = sysUserMapper.selectUser(apiReq);
		if (user == null) {
			return new ApiResponse(ApiMsgEnum.UserDosentExist);
		}
		if (UserStatusEnum.freeze.name().equalsIgnoreCase(user.getStatus())) {
			return new ApiResponse(ApiMsgEnum.UserBeenLookedException);
		}
		if (!Md5Util.encodeString(passwordObj.toString()).equals(user.getNewPassword())) {
			return new ApiResponse(ApiMsgEnum.UsernameOrPasswordException);
		}
		// 更新最后登录时间
		SysUser record = new SysUser();
		record.setId(user.getId());
		record.setLastLogin(new Date());
		record.setLastLoginIp((String) login_ip);
		record.setLoginCount(user.getLoginCount() + 1);
		this.sysUserMapper.updateByPrimaryKeySelective(record);
		// 返回用户信息
		SysUser u = this.sysUserMapper.selectByPrimaryKey(user.getId());
		return new ApiResponse<SysUser>(ApiMsgEnum.SUCCESS, 1, u);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "更改密码", value = "backend-admin-updatePassword", apiParams = { @ApiParam(descript = "用户ID", name = "id"), @ApiParam(descript = "新密码", name = "new_password"),
			@ApiParam(descript = "旧密码", name = "old_password") })
	public ApiResponse updatePassword(ApiRequest apiReq) {
		Object idObj = apiReq.get("id");
		Object newPasswordObj = apiReq.get("new_password");
		Object oldPasswordObj = apiReq.get("old_password");
		if (StringUtils.isEmpty(idObj) || StringUtils.isEmpty(newPasswordObj) || StringUtils.isEmpty(oldPasswordObj)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		SysUser user = sysUserMapper.selectUser(apiReq);
		if (user == null) {
			return new ApiResponse(ApiMsgEnum.UserDosentExist);
		}
		if (!Md5Util.encodeString(oldPasswordObj.toString()).equals(user.getNewPassword())) {
			return new ApiResponse(ApiMsgEnum.CurrentPasswordException);
		}
		String newPassword = Md5Util.encodeString(newPasswordObj.toString());
		SysUser record = new SysUser();
		record.setId(user.getId());
		record.setNewPassword(newPassword);
		this.sysUserMapper.updateByPrimaryKeySelective(record);
		return new ApiResponse(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "管理员列表", value = "backend-admin-list", apiParams = { @ApiParam(descript = "页码", name = "page"), @ApiParam(descript = "每页多少条", name = "page_size") })
	public ApiResponse list(ApiRequest apiReq) {
		this.setBackendPageSize(apiReq);
		int count = this.sysUserMapper.countListForBackend(apiReq);
		List<SysUser> list = this.sysUserMapper.selectListForBackend(apiReq);
		return new ApiResponse<List<SysUser>>(ApiMsgEnum.SUCCESS, count, list);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "添加账号", value = "backend-admin-add", apiParams = { @ApiParam(descript = "姓名", name = "realname"), @ApiParam(descript = "用户名", name = "username"),
			@ApiParam(descript = "密码", name = "password") })
	public ApiResponse add(ApiRequest apiReq) {
		Object realname = apiReq.get("realname");
		Object username = apiReq.get("username");
		Object password = apiReq.get("password");
		if (StringUtils.isEmpty(realname) || StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		int usernameCount = sysUserMapper.countByUsername(username.toString());
		if (usernameCount > 0) {
			return new ApiResponse(ApiMsgEnum.UsernameBeenRegistered);
		}
		Date now = new Date();
		SysUser user = new SysUser();
		user.setNewPassword(Md5Util.encodeString(password.toString()));
		user.setLastLogin(now);
		user.setIsSuperuser(Boolean.TRUE);
		user.setUsername(username.toString());
		user.setRegistType(UserTypeEnum.backend.name());
		user.setStatus(UserStatusEnum.activity.name());
		user.setDateJoined(now);
		user.setType(UserTypeEnum.backend.name());
		user.setRealname(realname.toString());
		user.setLoginCount(0);
		sysUserMapper.insert(user);
		return new ApiResponse(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "根据ID获取信息", value = "backend-admin-getById", apiParams = { @ApiParam(descript = "ID", name = "id") })
	public ApiResponse getById(ApiRequest apiReq) {
		Object id = apiReq.get("id");
		if (StringUtils.isEmpty(id)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		SysUser u = this.sysUserMapper.selectByPrimaryKey(Integer.valueOf(id.toString()));
		return new ApiResponse<SysUser>(ApiMsgEnum.SUCCESS, 1, u);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "更新账号", value = "backend-admin-update", apiParams = { @ApiParam(descript = "ID", name = "id"), @ApiParam(descript = "姓名", name = "realname"),
			@ApiParam(descript = "密码", name = "password") })
	public ApiResponse update(ApiRequest apiReq) {
		Object id = apiReq.get("id");
		Object realname = apiReq.get("realname");
		Object password = apiReq.get("password");
		if (StringUtils.isEmpty(id) || StringUtils.isEmpty(realname)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		SysUser record = new SysUser();
		record.setId(Integer.valueOf(id.toString()));
		record.setRealname(realname.toString());
		if (!StringUtils.isEmpty(password)) {
			record.setNewPassword(Md5Util.encodeString(password.toString()));
		}
		this.sysUserMapper.updateByPrimaryKeySelective(record);
		return new ApiResponse(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "删除账号", value = "backend-admin-delete", apiParams = { @ApiParam(descript = "管理员ID串", name = "ids") })
	public ApiResponse delete(ApiRequest apiReq) {
		return this.updateStatus(apiReq, "delete");
	}

	@SuppressWarnings("rawtypes")
	private ApiResponse updateStatus(ApiRequest apiReq, String status) {
		Object idsObj = apiReq.get("ids");
		if (StringUtils.isEmpty(idsObj)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		String[] idArr = idsObj.toString().split(",");
		if (idArr == null || idArr.length == 0) {
			return new ApiResponse(ApiMsgEnum.BAD_REQUEST);
		}
		SysUser record = new SysUser();
		record.setStatus(status);
		TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			for (String idStr : idArr) {
				if (!StringUtils.isEmpty(idStr)) {
					if ("delete".equals(status)) {
						this.sysUserMapper.deleteByPrimaryKey(Integer.valueOf(idStr));
					} else {
						record.setId(Integer.valueOf(idStr));
						this.sysUserMapper.updateByPrimaryKeySelective(record);
					}
				}
			}
		} catch (RuntimeException e) {
			platformTransactionManager.rollback(transactionStatus);
			loger.error(e);
		} finally {
			platformTransactionManager.commit(transactionStatus);
		}
		return new ApiResponse(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "禁用账号", value = "backend-admin-disable", apiParams = { @ApiParam(descript = "管理员ID串", name = "ids") })
	public ApiResponse disable(ApiRequest apiReq) {
		return this.updateStatus(apiReq, UserStatusEnum.freeze.name());
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "启用账号", value = "backend-admin-enable", apiParams = { @ApiParam(descript = "管理员ID串", name = "ids") })
	public ApiResponse enable(ApiRequest apiReq) {
		return this.updateStatus(apiReq, UserStatusEnum.activity.name());
	}

	public enum UserTypeEnum {
		www, backend
	}

	public enum UserStatusEnum {
		activity, freeze
	}
}
