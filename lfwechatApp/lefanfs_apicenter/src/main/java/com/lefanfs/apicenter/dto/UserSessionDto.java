package com.lefanfs.apicenter.dto;

public class UserSessionDto {

	private String userToken;
	// private String userType;
	private Long userId;
	// private String username;
	// private String nickname;
	private String nickname;
	private String icon;
	private String phone;
	private String pushToken;

    public Integer getIsPromoter() {
        return isPromoter;
    }

    public void setIsPromoter(Integer isPromoter) {
        this.isPromoter = isPromoter;
    }

    private  Integer  isPromoter;
	// private String registerFlag;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// public String getRegisterFlag() {
	// return registerFlag;
	// }
	//
	// public void setRegisterFlag(String registerFlag) {
	// this.registerFlag = registerFlag;
	// }

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	//
	// public String getUserType() {
	// return userType;
	// }
	//
	// public void setUserType(String userType) {
	// this.userType = userType;
	// }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	// public String getUsername() {
	// return username;
	// }
	//
	// public void setUsername(String username) {
	// this.username = username;
	// }
	//
	// public String getNickname() {
	// return nickname;
	// }
	//
	// public void setNickname(String nickname) {
	// this.nickname = nickname;
	// }

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	// public String getDriverStatus() {
	// return driverStatus;
	// }
	//
	// public void setDriverStatus(String driverStatus) {
	// this.driverStatus = driverStatus;
	// }

}
