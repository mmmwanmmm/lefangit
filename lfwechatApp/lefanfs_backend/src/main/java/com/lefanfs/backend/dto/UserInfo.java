package com.lefanfs.backend.dto;

import java.util.Date;
import java.util.List;

public class UserInfo {
	private Long userId;

	private String nickName;

	private String userIcon;

	private Date birthday;

	private Integer sex;

	private Integer height;

	private Double weight;

	private Double bmi;

	private String hobby;

	private String introduce;

	private String gymnasium;

	private Integer userIntegral;

	private Integer userState;

	private Integer userLevel;

	private Integer userType;

	private Date createTime;

	private Long createId;

	private Date modifyTime;

	private Long modifyId;

	private Integer deleteFlag;

	private Long userFrom;

	private String pushDevice;

	private Integer reduceIntegral;

	private Integer plusIntegral;

	private List<String> hobbyList;

	private String userTelphone;

	private String wechatId;

	private String weiboId;

	public String getUserTelphone() {
		return userTelphone;
	}

	public void setUserTelphone(String userTelphone) {
		this.userTelphone = userTelphone;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId;
	}

	public Integer getReduceIntegral() {
		return reduceIntegral;
	}

	public void setReduceIntegral(Integer reduceIntegral) {
		this.reduceIntegral = reduceIntegral;
	}

	public Integer getPlusIntegral() {
		return plusIntegral;
	}

	public void setPlusIntegral(Integer plusIntegral) {
		this.plusIntegral = plusIntegral;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getGymnasium() {
		return gymnasium;
	}

	public void setGymnasium(String gymnasium) {
		this.gymnasium = gymnasium;
	}

	public Integer getUserIntegral() {
		return userIntegral;
	}

	public void setUserIntegral(Integer userIntegral) {
		this.userIntegral = userIntegral;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getModifyId() {
		return modifyId;
	}

	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Long getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(Long userFrom) {
		this.userFrom = userFrom;
	}

	public String getPushDevice() {
		return pushDevice;
	}

	public void setPushDevice(String pushDevice) {
		this.pushDevice = pushDevice;
	}

	public List<String> getHobbyList() {
		return hobbyList;
	}

	public void setHobbyList(List<String> hobbyList) {
		this.hobbyList = hobbyList;
	}
}