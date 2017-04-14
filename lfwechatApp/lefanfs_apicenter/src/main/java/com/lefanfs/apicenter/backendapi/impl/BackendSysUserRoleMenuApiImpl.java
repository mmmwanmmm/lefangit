package com.lefanfs.apicenter.backendapi.impl;

import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.apicenter.backendapi.BackendSysUserRoleMenuApi;
import com.lefanfs.apicenter.dao.SysMenuMapper;
import com.lefanfs.apicenter.dao.SysRoleMapper;
import com.lefanfs.apicenter.dao.SysRoleMenuMapper;
import com.lefanfs.apicenter.dao.SysUserRoleMapper;
import com.lefanfs.apicenter.dto.TreeData;
import com.lefanfs.apicenter.model.SysMenu;
import com.lefanfs.apicenter.model.SysRole;
import com.lefanfs.apicenter.model.SysRoleMenu;
import com.lefanfs.apicenter.model.SysUserRole;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@ApiService(descript = "后台系统菜单管理")
public class BackendSysUserRoleMenuApiImpl extends BaseServiceImpl implements BackendSysUserRoleMenuApi {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Resource
	private PlatformTransactionManager platformTransactionManager;

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "系统菜单列表", value = "backend-sysmenu-list", apiParams = { @ApiParam(descript = "页码", name = "page"), @ApiParam(descript = "每页多少条", name = "page_size") })
	@Override
	public ApiResponse menuList(ApiRequest apiReq) {
		List<SysMenu> _results = this.sysMenuMapper.selectList(apiReq);
		int count = this.sysMenuMapper.selectCount(apiReq);
		return new ApiResponse<List<SysMenu>>(ApiMsgEnum.SUCCESS, count, _results);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "用户菜单列表", value = "backend-sysmenu-listByUserId", apiParams = { @ApiParam(descript = "用户ID", name = "userId") })
	@Override
	public ApiResponse menuListByUserId(ApiRequest apiReq) {
		List<SysMenu> _results = this.sysMenuMapper.selectTreeList(apiReq);
		return new ApiResponse<List<SysMenu>>(ApiMsgEnum.SUCCESS, 1, _results);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "菜单Tree列表", value = "backend-sysmenu-treelist", apiParams = {})
	@Override
	public ApiResponse menuTreeList(ApiRequest apiReq) {
		List<SysMenu> list = this.sysMenuMapper.selectTreeList(apiReq);
		List<SysMenu> menuList = this._createTree(list);
		List<TreeData> _results = new ArrayList<TreeData>();
		if (CollectionUtils.isNotEmpty(menuList)) {
			for (SysMenu menu : menuList) {
				TreeData tree = new TreeData();
				tree.setId(menu.getId());
				tree.setName(menu.getMenuName());
				tree.setpId(menu.getPid());
				tree.setDepth(menu.getDepth());
				if (menu.getPid() != null && menu.getPid() == -1) {
					tree.setOpen(true);
				}
				_results.add(tree);
			}
		}
		return new ApiResponse<List<TreeData>>(ApiMsgEnum.SUCCESS, _results.size(), _results);
	}

	public List<SysMenu> _createTree(List<SysMenu> _results) {
		if (CollectionUtils.isEmpty(_results)) {
			return null;
		}
		List<SysMenu> treeList = new ArrayList<SysMenu>();
		int len = _results.size();
		for (int i = 0; i < len; i++) {
			SysMenu menu = _results.get(i);
			if (!treeList.contains(menu)) {
				treeList.add(menu);
			}
			List<SysMenu> childList = _getChild(_results, menu.getId());
			if (childList.size() > 0) {
				if (!treeList.containsAll(childList)) {
					for (SysMenu child : childList) {
						treeList.add(child);
						childList = _getChild(_results, child.getId());
						if (!treeList.containsAll(childList)) {
							treeList.addAll(childList);
						}
					}
				}
			}
		}
		return treeList;
	}

	public List<SysMenu> _getChild(List<SysMenu> _results, Integer parentId) {
		List<SysMenu> childList = new ArrayList<SysMenu>();
		for (SysMenu m : _results) {
			if (m.getPid() == parentId) {
				childList.add(m);
			}
		}
		return childList;
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "菜单添加", value = "backend-sysmenu-add", apiParams = { @ApiParam(descript = "菜单名称", name = "menuName"), @ApiParam(descript = "功能码", name = "menuCode"),
			@ApiParam(descript = "菜单URL", name = "menuUrl"), @ApiParam(descript = "父菜单ID", name = "pid"), @ApiParam(descript = "是否显示", name = "isShow") })
	@Override
	public ApiResponse addMenu(ApiRequest apiReq) {
		if (StringUtils.isEmpty(apiReq.getString("menuName")) || StringUtils.isEmpty(apiReq.getString("pid")) || StringUtils.isEmpty(apiReq.getString("isShow"))) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		this.sysMenuMapper.insert(apiReq);
		return new ApiResponse<Object>(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "获取一条菜单", value = "backend-sysmenu-getById", apiParams = { @ApiParam(descript = "菜单ID", name = "id") })
	@Override
	public ApiResponse getMenuById(ApiRequest apiReq) {
		if (StringUtils.isEmpty(apiReq.getInt("id"))) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		SysMenu menu = this.sysMenuMapper.selectByPrimaryKey(apiReq.getInt("id"));
		return new ApiResponse<Object>(ApiMsgEnum.SUCCESS, 1, menu);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "菜单修改", value = "backend-sysmenu-update", apiParams = { @ApiParam(descript = "菜单ID", name = "id"), @ApiParam(descript = "菜单名称", name = "menuName"),
			@ApiParam(descript = "描述", name = "menuSummary"), @ApiParam(descript = "功能码", name = "menuCode"), @ApiParam(descript = "菜单URL", name = "menuUrl"),
			@ApiParam(descript = "父菜单ID", name = "pid"), @ApiParam(descript = "是否显示", name = "isShow") })
	@Override
	public ApiResponse updateMenu(ApiRequest apiReq) {
		if (StringUtils.isEmpty(apiReq.getInt("id")) || StringUtils.isEmpty(apiReq.getString("menuName"))) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		this.sysMenuMapper.updateByPrimaryKey(apiReq);
		return new ApiResponse<Object>(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "菜单删除", value = "backend-sysmenu-delete", apiParams = { @ApiParam(descript = "菜单ID", name = "id") })
	@Override
	public ApiResponse deleteMenu(ApiRequest apiReq) {
		if (StringUtils.isEmpty(apiReq.getInt("id"))) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		this.sysMenuMapper.deleteByPrimaryKey(apiReq.getInt("id"));
		return new ApiResponse<Object>(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "角色列表", value = "backend-sysrole-list", apiParams = { @ApiParam(descript = "页码", name = "page"), @ApiParam(descript = "每页多少条", name = "page_size") })
	@Override
	public ApiResponse roleList(ApiRequest apiReq) {
		int _count = this.sysRoleMapper.selectCount(apiReq);
		List<SysRole> _results = this.sysRoleMapper.selectList(apiReq);
		return new ApiResponse<List<SysRole>>(ApiMsgEnum.SUCCESS, _count, _results);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "角色添加", value = "backend-sysrole-add", apiParams = { @ApiParam(descript = "角色名称", name = "roleName"), @ApiParam(descript = "描述", name = "roleDesc") })
	@Override
	public ApiResponse addRole(ApiRequest apiReq) {
		if (StringUtils.isEmpty(apiReq.getString("roleName")) || StringUtils.isEmpty(apiReq.getString("roleDesc"))) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			this.sysRoleMapper.insert(apiReq);
			Integer roleId = apiReq.getInt("id");
			String menuIds = apiReq.getString("menuIds");
			Set<Integer> menuIdList = new HashSet<Integer>();
			if (!StringUtils.isEmpty(menuIds)) {
				String[] tempArr = menuIds.split(",");
				for (String str : tempArr) {
					if (!StringUtils.isEmpty(str)) {
						menuIdList.add(Integer.valueOf(str));
					}
				}
			}
			if (CollectionUtils.isNotEmpty(menuIdList)) {
				ApiRequest roleMenu = new ApiRequest();
				roleMenu.put("roleId", roleId);
				roleMenu.put("idList", menuIdList);
				this.sysRoleMenuMapper.insert(roleMenu);
			}
		} catch (RuntimeException e) {
			platformTransactionManager.rollback(transactionStatus);
		} finally {
			platformTransactionManager.commit(transactionStatus);
		}
		return new ApiResponse<Object>(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "获取一条角色", value = "backend-sysrole-getById", apiParams = { @ApiParam(descript = "角色ID", name = "id") })
	@Override
	public ApiResponse getRoleById(ApiRequest apiReq) {
		SysRole sysRole = this.sysRoleMapper.selectByPrimaryKey(apiReq.getInt("id"));
		return new ApiResponse<SysRole>(ApiMsgEnum.SUCCESS, 1, sysRole);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "角色修改", value = "backend-sysrole-update", apiParams = { @ApiParam(descript = "角色ID", name = "id"), @ApiParam(descript = "角色名称", name = "roleName"),
			@ApiParam(descript = "描述", name = "roleDesc") })
	@Override
	public ApiResponse updateRole(ApiRequest apiReq) {
		Integer roleId = apiReq.getInt("id");
		String menuIds = apiReq.getString("menuIds");
		if (StringUtils.isEmpty(roleId) || StringUtils.isEmpty(apiReq.getString("roleName")) || StringUtils.isEmpty(apiReq.getString("roleDesc"))) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		Set<Integer> menuIdList = new HashSet<Integer>();
		if (!StringUtils.isEmpty(menuIds)) {
			String[] tempArr = menuIds.split(",");
			for (String str : tempArr) {
				if (!StringUtils.isEmpty(str)) {
					menuIdList.add(Integer.valueOf(str));
				}
			}
		}
		TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			this.sysRoleMapper.updateByPrimaryKey(apiReq);
			this.sysRoleMenuMapper.deleteByRoleId(roleId);
			if (CollectionUtils.isNotEmpty(menuIdList)) {
				ApiRequest roleMenu = new ApiRequest();
				roleMenu.put("roleId", roleId);
				roleMenu.put("idList", menuIdList);
				this.sysRoleMenuMapper.insert(roleMenu);
			}
		} catch (RuntimeException e) {
			platformTransactionManager.rollback(transactionStatus);
		} finally {
			platformTransactionManager.commit(transactionStatus);
		}
		return new ApiResponse<Object>(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "角色删除", value = "backend-sysrole-delete", apiParams = { @ApiParam(descript = "角色ID", name = "ids") })
	@Override
	public ApiResponse deleteRole(ApiRequest apiReq) {
		String roleIds = apiReq.getString("ids");
		if (StringUtils.isEmpty(roleIds)) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		Set<Integer> idsList = new HashSet<Integer>();
		String[] tempArr = roleIds.toString().split(",");
		for (String str : tempArr) {
			if (!StringUtils.isEmpty(str)) {
				idsList.add(Integer.valueOf(str));
			}
		}
		if (CollectionUtils.isEmpty(idsList)) {
			return new ApiResponse<Object>(ApiMsgEnum.FORBIDDEN);
		}
		apiReq.put("roleIdList", idsList);
		int count = this.sysUserRoleMapper.selectCount(apiReq);
		if (count > 0) {
			return new ApiResponse<Object>(ApiMsgEnum.REFERENCE_EXISTED);
		}
		TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			for (String str : tempArr) {
				if (!StringUtils.isEmpty(str)) {
					this.sysRoleMapper.deleteByPrimaryKey(Integer.valueOf(str));
					this.sysRoleMenuMapper.deleteByRoleId(Integer.valueOf(str));
				}
			}
		} catch (RuntimeException e) {
			platformTransactionManager.rollback(transactionStatus);
		} finally {
			platformTransactionManager.commit(transactionStatus);
		}
		return new ApiResponse<Object>(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "角色菜单添加", value = "backend-sysrolemenu-add", apiParams = { @ApiParam(descript = "角色ID", name = "roleId"), @ApiParam(descript = "多个菜单ID", name = "menuIds") })
	@Override
	public ApiResponse addRoleRelationMenu(ApiRequest apiReq) {
		String menuIds = apiReq.getString("menuIds");
		if (StringUtils.isEmpty(apiReq.getInt("roleId")) || StringUtils.isEmpty(menuIds)) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		Set<Integer> menuIdList = new HashSet<Integer>();
		String[] tempArr = menuIds.toString().split(",");
		for (String str : tempArr) {
			if (!StringUtils.isEmpty(str)) {
				menuIdList.add(Integer.valueOf(str));
			}
		}
		apiReq.put("idList", menuIdList);
		this.sysRoleMenuMapper.insert(apiReq);
		return new ApiResponse<Object>(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "角色菜单修改", value = "backend-sysrolemenu-update", apiParams = { @ApiParam(descript = "角色ID", name = "roleId"), @ApiParam(descript = "多个菜单ID", name = "menuIds") })
	@Override
	public ApiResponse updateRoleRelationMenu(ApiRequest apiReq) {
		String menuIds = apiReq.getString("menuIds");
		Integer roleId = apiReq.getInt("roleId");
		if (StringUtils.isEmpty(roleId) || StringUtils.isEmpty(menuIds)) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		Set<Integer> menuIdList = new HashSet<Integer>();
		String[] tempArr = menuIds.toString().split(",");
		for (String str : tempArr) {
			if (!StringUtils.isEmpty(str)) {
				menuIdList.add(Integer.valueOf(str));
			}
		}
		apiReq.put("idList", menuIdList);
		TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			this.sysRoleMenuMapper.deleteByRoleId(roleId);
			this.sysRoleMenuMapper.insert(apiReq);
		} catch (RuntimeException e) {
			platformTransactionManager.rollback(transactionStatus);
		} finally {
			platformTransactionManager.commit(transactionStatus);
		}
		return new ApiResponse<Object>(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "角色菜单列表", value = "backend-sysrolemenu-list", apiParams = { @ApiParam(descript = "角色ID", name = "roleId") })
	@Override
	public ApiResponse roleRelationMenuList(ApiRequest apiReq) {
		if (StringUtils.isEmpty(apiReq.getInt("roleId"))) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		List<SysRoleMenu> roleMenuList = this.sysRoleMenuMapper.selectList(apiReq);
		return new ApiResponse<List<SysRoleMenu>>(ApiMsgEnum.SUCCESS, 1, roleMenuList);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "用户角色修改", value = "backend-sysuserrole-update", apiParams = { @ApiParam(descript = "用户ID", name = "userId"), @ApiParam(descript = "多个角色ID", name = "roleIds") })
	@Override
	public ApiResponse updateUserRelationRole(ApiRequest apiReq) {
		Integer userId = apiReq.getInt("userId");
		String roleIds = apiReq.getString("roleIds");
		if (StringUtils.isEmpty(userId)) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			this.sysUserRoleMapper.deleteByUserId(userId);
			if (!StringUtils.isEmpty(roleIds)) {
				String[] tempArr = roleIds.toString().split(",");
				for (String str : tempArr) {
					if (!StringUtils.isEmpty(str)) {
						apiReq = new ApiRequest();
						apiReq.put("roleId", Integer.valueOf(str));
						apiReq.put("userId", userId);
						this.sysUserRoleMapper.insert(apiReq);
					}
				}
			}
		} catch (RuntimeException e) {
			platformTransactionManager.rollback(transactionStatus);
		} finally {
			platformTransactionManager.commit(transactionStatus);
		}
		return new ApiResponse<Object>(ApiMsgEnum.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "用户角色列表", value = "backend-sysuserrole-list", apiParams = { @ApiParam(descript = "用户ID", name = "userId") })
	@Override
	public ApiResponse selectUserRoleList(ApiRequest apiReq) {
		if (StringUtils.isEmpty(apiReq.getInt("userId"))) {
			return new ApiResponse<Object>(ApiMsgEnum.MISS_PARAMETER);
		}
		List<SysUserRole> userRoleList = this.sysUserRoleMapper.selectList(apiReq);
		return new ApiResponse<List<SysUserRole>>(ApiMsgEnum.SUCCESS, 1, userRoleList);
	}

}
