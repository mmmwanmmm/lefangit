package com.lefanfs.apicenter.enums;

/**
 * 0：运动结束，1：创建事件，2：事件开始，3：事件结束，4：编辑事件，5：完善信息，6：事件邀请，7：申请联系人，8：系统通知，9：其他消息
 * 
 * @author Daniel
 */
public enum MsgBizTypeEnum {

	/**
	 * 运动邀请
	 */
	CREATE_EVENT(1, "运动邀请", "msgIcon/event.png"),

	/**
	 * 运动开始
	 */
	START_EVENT(2, "运动开始", "msgIcon/event.png"),

	/**
	 * 运动结束
	 */
	COMPLETE_EVENT(3, "运动结束", "msgIcon/event.png"),
	/**
	 * 运动邀请
	 */
	APPLY_EVENT(4, "约跃申请", "msgIcon/event.png"),

	/**
	 * 完善信息
	 */
	COMPLETE_PROFILE(5, "积分消息提醒", "msgIcon/userProfile.png"),


	/**
	 * 申请联系人
	 */
	APPLY_FRIEND(7, "申请联系人", "msgIcon/friend.png"),

	/**
	 * 系统通知
	 */
	SYS_NOTICE(8, "系统通知", "msgIcon/sysNotice.png"),

	/**
	 * 其他消息
	 */
	OTHER(9, "其他消息", "msgIcon/other.png"),

	/**
	 * 系统消息
	 */
	SYS_MSG(9999, "系统消息", "msgIcon/sysMsg.png"),;

	private int typeId;
	private String typeName;
	private String typeIcon;

	private MsgBizTypeEnum(int typeId, String typeName, String typeIcon) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeIcon = typeIcon;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeIcon() {
		return typeIcon;
	}

	public void setTypeIcon(String typeIcon) {
		this.typeIcon = typeIcon;
	}

	public static String getTypeName(int typeId) {
		for (MsgBizTypeEnum typeEnum : MsgBizTypeEnum.values()) {
			if (typeEnum.getTypeId() == typeId) {
				return typeEnum.getTypeName();
			}
		}
		return null;
	}

	public static String getTypeIcon(int typeId) {
		for (MsgBizTypeEnum typeEnum : MsgBizTypeEnum.values()) {
			if (typeEnum.getTypeId() == typeId) {
				return typeEnum.getTypeIcon();
			}
		}
		return null;
	}

	public static MsgBizTypeEnum getMsgBizTypeEnum(int typeId) {
		for (MsgBizTypeEnum typeEnum : MsgBizTypeEnum.values()) {
			if (typeEnum.getTypeId() == typeId) {
				return typeEnum;
			}
		}
		return null;
	}

}
