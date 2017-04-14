package com.lefanfs.base.enums;

import com.lefanfs.base.interfaces.Api;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 后台API列表
 * 
 * @author Daniel
 */
public enum BackendApiMethodEnum implements Api {

	/**
	 * 后台管理员登录
	 */
	BACKEND_ADMIN_LOGIN("backend-admin-login", "后台管理员登录", ApiServerEnum.apicenter, ContentTypeEnum.sys_user) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("username", "用户名");
			paramMap.put("password", "密码");
			paramMap.put("login_ip", "登录IP");
			return paramMap;
		}
	},

	/**
	 * 后台管理员修改密码
	 */
	BACKEND_ADMIN_UPDATEPASSWORD("backend-admin-updatePassword", "后台管理员修改密码", ApiServerEnum.apicenter, ContentTypeEnum.sys_user) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "用户ID");
			paramMap.put("new_password", "新密码");
			paramMap.put("old_password", "旧密码");
			return paramMap;
		}
	},

	/**
	 * 管理员列表
	 */
	BACKEND_ADMIN_LIST("backend-admin-list", "管理员列表", ApiServerEnum.apicenter, ContentTypeEnum.sys_user) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			return paramMap;
		}
	},

	/**
	 * 添加账号
	 */
	BACKEND_ADMIN_ADD("backend-admin-add", "添加账号", ApiServerEnum.apicenter, ContentTypeEnum.sys_user) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("realname", "姓名");
			paramMap.put("username", "用户名");
			paramMap.put("password", "密码");
			return paramMap;
		}
	},

	/**
	 * 根据ID获取信息
	 */
	BACKEND_ADMIN_GETBYID("backend-admin-getById", "根据ID获取信息", ApiServerEnum.apicenter, ContentTypeEnum.sys_user) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "ID");
			return paramMap;
		}
	},

	/**
	 * 更新账号
	 */
	BACKEND_ADMIN_UPDATE("backend-admin-update", "更新账号", ApiServerEnum.apicenter, ContentTypeEnum.sys_user) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "ID");
			paramMap.put("realname", "姓名");
			paramMap.put("password", "密码");
			return paramMap;
		}
	},

	/**
	 * 删除账号
	 */
	BACKEND_ADMIN_DELETE("backend-admin-delete", "删除账号", ApiServerEnum.apicenter, ContentTypeEnum.sys_user) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("ids", "管理员ID串");
			return paramMap;
		}
	},

	/**
	 * 禁用账号
	 */
	BACKEND_ADMIN_DISABLE("backend-admin-disable", "禁用账号", ApiServerEnum.apicenter, ContentTypeEnum.sys_user) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("ids", "管理员ID串");
			return paramMap;
		}
	},

	/**
	 * 启用账号
	 */
	BACKEND_ADMIN_ENABLE("backend-admin-enable", "启用账号", ApiServerEnum.apicenter, ContentTypeEnum.sys_user) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("ids", "管理员ID串");
			return paramMap;
		}
	},

	/**
	 * 系统菜单列表
	 */
	BACKEND_SYSMENU_LIST("backend-sysmenu-list", "系统菜单列表", ApiServerEnum.apicenter, ContentTypeEnum.sys_menu) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("page", "页码");
			paramMap.put("page_size", "每页多少条");
			return paramMap;
		}
	},

	/**
	 * 用户菜单列表
	 */
	BACKEND_SYSMENU_LISTBYUSERID("backend-sysmenu-listByUserId", "用户菜单列表", ApiServerEnum.apicenter, ContentTypeEnum.sys_menu) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("userId", "用户ID");
			return paramMap;
		}
	},

	/**
	 * 菜单tree列表
	 */
	BACKEND_SYSMENU_TREELIST("backend-sysmenu-treelist", "菜单tree列表", ApiServerEnum.apicenter, ContentTypeEnum.sys_menu) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			return paramMap;
		}
	},

	/**
	 * 菜单添加
	 */
	BACKEND_SYSMENU_ADD("backend-sysmenu-add", "菜单添加", ApiServerEnum.apicenter, ContentTypeEnum.sys_menu) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("menuName", "菜单名称");
			paramMap.put("menuCode", "功能码");
			paramMap.put("menuUrl", "菜单URL");
			paramMap.put("pid", "父菜单ID");
			paramMap.put("isShow", "是否显示");
			return paramMap;
		}
	},

	/**
	 * 获取一条菜单
	 */
	BACKEND_SYSMENU_GETBYID("backend-sysmenu-getById", "获取一条菜单", ApiServerEnum.apicenter, ContentTypeEnum.sys_menu) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "菜单ID");
			return paramMap;
		}
	},

	/**
	 * 菜单修改
	 */
	BACKEND_SYSMENU_UPDATE("backend-sysmenu-update", "菜单修改", ApiServerEnum.apicenter, ContentTypeEnum.sys_menu) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "菜单ID");
			paramMap.put("menuName", "菜单名称");
			paramMap.put("menuSummary", "描述");
			paramMap.put("menuCode", "功能码");
			paramMap.put("menuUrl", "菜单URL");
			paramMap.put("pid", "父菜单ID");
			paramMap.put("isShow", "是否显示");
			return paramMap;
		}
	},

	/**
	 * 菜单删除
	 */
	BACKEND_SYSMENU_DELETE("backend-sysmenu-delete", "菜单删除", ApiServerEnum.apicenter, ContentTypeEnum.sys_menu) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "菜单ID");
			return paramMap;
		}
	},

	/**
	 * 角色列表
	 */
	BACKEND_SYSROLE_LIST("backend-sysrole-list", "角色列表", ApiServerEnum.apicenter, ContentTypeEnum.sys_role) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("page", "页码");
			paramMap.put("page_size", "每页多少条");
			return paramMap;
		}
	},

	/**
	 * 角色添加
	 */
	BACKEND_SYSROLE_ADD("backend-sysrole-add", "角色添加", ApiServerEnum.apicenter, ContentTypeEnum.sys_role) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("roleName", "角色名称");
			paramMap.put("roleDesc", "描述");
			return paramMap;
		}
	},

	/**
	 * 获取一条角色
	 */
	BACKEND_SYSROLE_GETBYID("backend-sysrole-getById", "获取一条角色", ApiServerEnum.apicenter, ContentTypeEnum.sys_role) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "角色ID");
			return paramMap;
		}
	},

	/**
	 * 角色修改
	 */
	BACKEND_SYSROLE_UPDATE("backend-sysrole-update", "角色修改", ApiServerEnum.apicenter, ContentTypeEnum.sys_role) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "角色ID");
			paramMap.put("roleName", "角色名称");
			paramMap.put("roleDesc", "描述");
			return paramMap;
		}
	},

	/**
	 * 角色删除
	 */
	BACKEND_SYSROLE_DELETE("backend-sysrole-delete", "角色删除", ApiServerEnum.apicenter, ContentTypeEnum.sys_role) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("ids", "角色ID");
			return paramMap;
		}
	},

	/**
	 * 角色菜单添加
	 */
	BACKEND_SYSROLEMENU_ADD("backend-sysrolemenu-add", "角色菜单添加", ApiServerEnum.apicenter, ContentTypeEnum.sys_role_menu) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("roleId", "角色ID");
			paramMap.put("menuIds", "多个菜单ID");
			return paramMap;
		}
	},

	/**
	 * 角色菜单列表
	 */
	BACKEND_SYSROLEMENU_LIST("backend-sysrolemenu-list", "角色菜单列表", ApiServerEnum.apicenter, ContentTypeEnum.sys_role_menu) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("roleId", "角色ID");
			return paramMap;
		}
	},

	/**
	 * 角色菜单修改
	 */
	BACKEND_SYSROLEMENU_UPDATE("backend-sysrolemenu-update", "角色菜单修改", ApiServerEnum.apicenter, ContentTypeEnum.sys_role_menu) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("roleId", "角色ID");
			paramMap.put("menuIds", "多个菜单ID");
			return paramMap;
		}
	},

	/**
	 * 用户角色修改
	 */
	BACKEND_SYSUSERROLE_UPDATE("backend-sysuserrole-update", "用户角色修改", ApiServerEnum.apicenter, ContentTypeEnum.sys_user_role) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("userId", "用户ID");
			paramMap.put("roleIds", "多个角色ID");
			return paramMap;
		}
	},

	/**
	 * 用户角色列表
	 */
	BACKEND_SYSUSERROLE_LIST("backend-sysuserrole-list", "用户角色列表", ApiServerEnum.apicenter, ContentTypeEnum.sys_user_role) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("userId", "用户ID");
			return paramMap;
		}
	},

	/**
	 * 后台广告更新
	 */
	BACKEND_ADV_UPDATE("backend-adv-update", "后台广告更新", ApiServerEnum.apicenter, null) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			return paramMap;
		}
	},

	/**
	 * 后台广告列表
	 */
	BACKEND_ADV_LIST("backend-adv-list", "后台广告列表", ApiServerEnum.apicenter, null) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			return paramMap;
		}
	},

	/**
	 * 后台广告保存
	 */
	BACKEND_ADV_SAVE("backend-adv-save", "后台广告保存", ApiServerEnum.apicenter, null) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			return paramMap;
		}
	},

	/**
	 * 后台广告变更状态
	 */
	BACKEND_ADV_CHANGESTATUS("backend-adv-changeStatus", "后台广告变更状态", ApiServerEnum.apicenter, null) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			return paramMap;
		}
	},

	/**
	 * 后台广告获取
	 */
	BACKEND_ADV_GETBYID("backend-adv-getById", "后台广告获取", ApiServerEnum.apicenter, null) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			return paramMap;
		}
	},

	/**
	 * 后台用户列表
	 */
	BACKEND_USER_LIST("backend-user-list", "后台用户列表", ApiServerEnum.apicenter, null) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("page", "当前第几页");
			return paramMap;
		}
	},

	/**
	 * 后台用户变更状态
	 */
	BACKEND_USER_CHANGESTATUS("backend-user-changeStatus", "后台用户变更状态", ApiServerEnum.apicenter, null) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();

			return paramMap;
		}
	},

	/**
	 * 后台用户获取
	 */
	BACKEND_USER_GETBYID("backend-user-getById", "后台用户获取", ApiServerEnum.apicenter, null) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			return paramMap;
		}
	},
	INFO_SEARCH("information-search", "资讯搜索", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("name", "标题名称");
			paramMap.put("type", "类型");
			paramMap.put("page", "页码");
			return paramMap;
		}
	},

	INFO_DETAIL("information-detail", "资讯详细", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "id");
			return paramMap;
		}
	},

	INFO_ADD("information-add", "资讯添加", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("informationName", "资讯名称");
			paramMap.put("informationIcon", "资讯图片");
			paramMap.put("informationContext", "资讯内容");
			paramMap.put("informationType", "资讯类型");
			return paramMap;
		}
	},

	INFO_UPDATE("information-update", "资讯修改", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "id");
			paramMap.put("informationName", "资讯名称");
			paramMap.put("informationIcon", "资讯图片");
			paramMap.put("informationContext", "资讯内容");
			paramMap.put("informationType", "资讯类型");
			return paramMap;
		}
	},
	UPLOADIMG("common-uploadImg", "上传图片", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("business", "业务(*)");
			paramMap.put("fileData", "上传图片base64编码(*)");
			return paramMap;
		}
	},

	COMMENT_SEARCH("comment-search", "留言查询", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("commentTo", "业务(*)");
			paramMap.put("comment", "上传图片base64编码(*)");
			paramMap.put("page", "上传图片base64编码(*)");
			return paramMap;
		}
	},
	COMMENT_ADDREPLY("comment-addReply", "留言回复", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("replyParentId", "业务(*)");
			paramMap.put("comment", "上传图片base64编码(*)");
			return paramMap;
		}
	},
	COMMENT_DETAIL("comment-detail", "留言详情", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "业务(*)");
			return paramMap;
		}
	},
	COMMENT_DELETE("comment-delete", "留言删除", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
		@Override
		public Map<String, String> getApiParams() {
			Map<String, String> paramMap = new LinkedHashMap<String, String>();
			paramMap.put("id", "业务(*)");
			return paramMap;
		}
	},
    BACKEND_LOAN_APPLICATION_LIST("backend-loan-application-list", "用户贷款申请列表", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("state", "贷款申请状态");
            paramMap.put("userId", "用户ID");
            paramMap.put("userPhone", "用户手机号");
            paramMap.put("isTrafficAccident", "是否为交通事故");
            return paramMap;
        }
    },
    BACKEND_UPDATE_LOAN_APPLICATION("backend-update-loan-application", "更新用户贷款申请状态", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "贷款申请标识ID");
            paramMap.put("state", "贷款申请状态");
            return paramMap;
        }
    },
    BACKEND_DELETE_LOAN_APPLICATION("backend-delete-loan-application", "用户贷款申请删除", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "贷款申请标识ID");
            return paramMap;
        }
    },
    BACKEND_COMPANYINFO_LIST("backend-companyInfo-list", "公司服务列表", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("page", "当前第几页");
            return paramMap;
        }
    },
    BACKEND_ADD_COMPANYINFO("backend-add-companyInfo", "新增公司服务信息", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("title", "标题");
            paramMap.put("content", "服务内容");
            return paramMap;
        }
    },
    BACKEND_UPDATE_COMPANYINFO("backend-update-companyInfo", "修改公司服务信息", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "标识ID");
            paramMap.put("title", "标题");
            paramMap.put("content", "服务内容");
            return paramMap;
        }
    },
    BACKEND_DELETE_COMPANYINFO("backend-delete-companyInfo", "删除公司服务信息", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "标识ID");
            return paramMap;
        }
    },
    BACKEND_INDETAILS_COMPANYINFO("backend-indetails-companyInfo", "公司服务详情", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "标识ID");
            return paramMap;
        }
    },
    BACKEND_USER_SCENARIOS_LIST("backend-user-scenarios-list", "用户案例列表", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("page", "当前第几页");
            return paramMap;
        }
    },
    BACKEND_UPDATE_USER_SCENARIOS("backend-update-user-scenarios", "用户案例修改", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "标识ID");
            paramMap.put("title", "标题");
            paramMap.put("content", "案例内容");
            paramMap.put("img", "案例图");
            return paramMap;
        }
    },
    BACKEND_DELETE_USER_SCENARIOS("backend-delete-user-scenarios", "用户案例删除", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "标识ID");
            return paramMap;
        }
    },
    BACKEND_SELECT_USER_SCENARIOS("backend-select-user-scenarios", "用户案例详情", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "标识ID");
            return paramMap;
        }
    },
    BACKEND_ADD_USER_SCENARIOS("backend-add-userScenarios", "新增用户案例信息", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("title", "标题");
            paramMap.put("content", "案例内容");
            paramMap.put("img", "案例图");
            return paramMap;
        }
    },
    BACKEND_USER_PROMOTED_LIST("backend-user-promoted-list", "推广认证用户列表", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("page", "当前第几页");
            return paramMap;
        }
    },
    BACKEND_UPDATE_USER_PROMOTED("backend-update-user-promoted", "推广认证用户状态修改", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "推广认证标识ID");
            paramMap.put("state", "状态");
            paramMap.put("reason", "审核不通过原因");
            return paramMap;
        }
    },
    BACKEND__PROMOTED_INFO_LIST("backend-promoted-info-list", "推广记录列表查询", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("page", "当前第几页");
            paramMap.put("userId", "用户名");
            paramMap.put("userName", "用户名称");
            return paramMap;
        }
    } ,
    BACKEND_SELECT_QAINFO_LIST("backend-select-qainfo-list", "QA问答列表", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("page", "当前第几页");
            return paramMap;
        }
    } ,
    BACKEND_ADD_QAINFO("backend-add-qaInfo", "新增QA问答信息", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("question", "问题（*）");
            paramMap.put("answer", "答案（*）");
            paramMap.put("questionId", "提问者ID（*）");
            paramMap.put("questionName", "提问者（*）");
            paramMap.put("answerId", "回答者ID（*）");
            paramMap.put("answerIdName", "回答者（*）");
            return paramMap;
        }
    } ,
    BACKEND_UPDATE_QAINFO("backend-update-qaInfo", "更新QA问答信息", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "主键标识ID（*）");
            paramMap.put("question", "问题");
            paramMap.put("answer", "答案");
            paramMap.put("questionId", "提问者ID");
            paramMap.put("questionName", "提问者");
            paramMap.put("answerId", "回答者ID");
            paramMap.put("answerIdName", "回答者");
            return paramMap;
        }
    } ,
    BACKEND_DELETE_QAINFO("backend-delete-qaInfo", "删除QA问答信息", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "主键标识ID（*）");
            return paramMap;
        }
    } ,
    BACKEND_INDETAILS_QAINFO("backend-indetails-qaInfo", "查询QA问题详情", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "主键标识ID（*）");
            return paramMap;
        }
    },
    BACKEND_UPDATE_PROMOTED_INFO("backend-update-promoted-info", "推广记录结算列表修改", ApiServerEnum.apicenter, ContentTypeEnum.agent_info) {
        @Override
        public Map<String, String> getApiParams() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("id", "主键标识ID（*）");
            paramMap.put("state","结算状态");
            paramMap.put("accountImg", "结算凭证");
            return paramMap;
        }
    }


    ;
	private String code;
	private String msg;
	private ApiServerEnum apiServer;
	private ContentTypeEnum contentType;

	/**
	 *
	 * @param code
	 *            编码
	 * @param msg
	 *            描述
	 * @param apiServer
	 *            所属服务器
	 * @param contentType
	 *            所属业务
	 * @param privateApi
	 *            是否内部私有API
	 * @param backendApi
	 *            是否后台API
	 * @param webApi
	 *            是否WEB API
	 * @author Daniel
	 */
	BackendApiMethodEnum(String code, String msg, ApiServerEnum apiServer, ContentTypeEnum contentType) {
		this.code = code;
		this.msg = msg;
		this.apiServer = apiServer;
		this.contentType = contentType;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public ApiServerEnum getApiServer() {
		return apiServer;
	}

	public ContentTypeEnum getContentType() {
		return contentType;
	}

	/**
	 * 获取所有API方法枚举
	 * 
	 * @return
	 * @author Daniel
	 */
	public static Map<String, BackendApiMethodEnum> getApiMethodEnumMap() {
		Map<String, BackendApiMethodEnum> retMap = new LinkedHashMap<String, BackendApiMethodEnum>();
		BackendApiMethodEnum[] enumArr = BackendApiMethodEnum.values();
		for (BackendApiMethodEnum aEnum : enumArr) {
			retMap.put(aEnum.getCode(), aEnum);
		}
		return retMap;
	}

	/**
	 * 根据服务器获取API方法（供对外的API测试工具使用）
	 * 
	 * @param apiServer
	 * @return
	 * @author Daniel
	 */
	public static Map<String, String> getApiMethodMapByServer(String apiServer) {
		ApiServerEnum sEnum = ApiServerEnum.getApiServerEnum(apiServer);
		Map<String, String> retMap = new LinkedHashMap<String, String>();
		if (sEnum != null) {
			BackendApiMethodEnum[] enumArr = BackendApiMethodEnum.values();
			for (BackendApiMethodEnum aEnum : enumArr) {
				if (sEnum.name().equals(aEnum.getApiServer().name())) {
					retMap.put(aEnum.getCode(), aEnum.getMsg());
				}
			}
		}
		return retMap;
	}

	/**
	 * 根据API方法编号获取参数列表（供API测试工具使用）
	 * 
	 * @param methodCode
	 * @return
	 * @author Daniel
	 */
	public static Map<String, String> getApiParamMapByMethod(String methodCode) {
		BackendApiMethodEnum mEnum = BackendApiMethodEnum.getApiMethodEnum(methodCode);
		if (mEnum != null) {
			return mEnum.getApiParams();
		}
		return null;
	}

	public static BackendApiMethodEnum getApiMethodEnum(String methodCode) {
		return getApiMethodEnumMap().get(methodCode);
	}

	/**
	 * 根据API方法编号获取所属服务器（供API总控制中心分发使用）
	 * 
	 * @param methodCode
	 * @return
	 * @author Daniel
	 */
	public static ApiServerEnum getApiServerEnum(String methodCode) {
		BackendApiMethodEnum aEnum = getApiMethodEnumMap().get(methodCode);
		if (aEnum != null) {
			return aEnum.getApiServer();
		}
		return null;
	}
}
