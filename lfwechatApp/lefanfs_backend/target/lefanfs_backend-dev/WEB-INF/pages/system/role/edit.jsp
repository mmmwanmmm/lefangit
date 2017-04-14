<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>编辑角色信息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/zTree_v3/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body>
<div class="container">
	<form id="editForm" role="form" action="${ctx}/system/role/save" method="post">
	<input type="hidden" name="id" value="${roleDto.id}">
	<input type="hidden" name="menuIds" id="menuIds" >
	
	<div class="form-group">
		<label class="control-label" for="roleName"><strong class="necessary">*</strong>角色名称</label>
		<input type="text" class="form-control" id="roleName" name="roleName" value="${roleDto.roleName}"   >
	</div>
	<div class="form-group">
		<label class="control-label" for="roleDesc"><strong class="necessary">*</strong>角色描述</label>
		<input type="text" class="form-control" id="roleDesc" name="roleDesc" value="${roleDto.roleDesc}"  >
	</div>
	<div class="form-group">
		<label class="control-label" for="roleDesc"><strong class="necessary">*</strong>角色菜单</label>
		  <small>
		  [ <a id="toggleBtn" href="#"  onclick="return false;">展开/折叠</a> ]
		  </small>
			<div class="zTreeDemoBackground left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
	</div>
	
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
		<button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>		
	</div>
	</form>
</div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<%-- <script type="text/javascript" src="${ctx}/js/zTree_v3/jquery-1.4.4.min.js"></script> --%>

<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.exedit-3.5.js"></script>

<script type="text/javascript">
function returnCallback(event,param){
	var apiRsp=getApiJson(param.data);
	if(apiRsp && apiRsp.isSuccess){
		alert('角色保存成功');
	}else{
		alert(apiRsp.msg);
	}
	reloadParent();
}

$(function(){
	
	$("#editForm").bind('submit', function(event) {
		
		checkAllNodesId();
		
		ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
		
		event.preventDefault();
		
	});	
	
   ajaxSubmit("${ctx}/system/menu/treeData",{},loadZTree,null,"",loadZTree);
	 
});


function checkAllNodesId(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var checkedNodes = treeObj.getCheckedNodes(true);
	var str = '';
	$(checkedNodes).each(function(index,node){
		str += node.id+",";
	});
	$("#menuIds").val(str);
}


function unCheckAllNodesId(event, param){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	$(param.data).each(function(i,obj){
		var tId = obj.menuId;
		var node = treeObj.getNodeByParam("id", tId, null);
		node.checked  = true;
	}); 
	
	
}

function loadRoleMenu(){
	var roleId = "${roleDto.id}";
	ajaxSubmit("${ctx}/system/menu/byRole?roleId="+roleId,{},unCheckAllNodesId,null,"",unCheckAllNodesId);
}


</script>


<script type="text/javascript">

var setting = {
	view: {
		selectedMulti: true,
		expandSpeed: "normal"
	},
	
/* 	async: {
		enable: true,
		url:"${ctx}/system/menu/treeData",
		autoParam:["id", "name=n", "level=lv"],
		type: "get"
	}, */
	
	check: {
		enable: true,
		chkStyle: "checkbox"
	},
	data: {
		type: "expandAll",
		simpleData: {
			enable: true
		}
	}
};
function loadZTree(event, param){
	$.fn.zTree.init($("#treeDemo"), setting,param.data);
	$("#toggleBtn").bind("click", {type:"expandAll"}, expandNode);
	loadRoleMenu();
}

function expandNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var type = setting.data.type;
	if (type == "expandAll") {
		zTree.expandAll(true);
		setting.data.type="collapseAll";
	}else{
		zTree.expandAll(false);
		setting.data.type="expandAll";
	}
}

</script>
</body>
</html>