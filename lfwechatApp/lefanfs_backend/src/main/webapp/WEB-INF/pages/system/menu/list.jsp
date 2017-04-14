<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>菜单列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
	<link rel="stylesheet" href="${ctx}/js/zTree_v3/zTreeStyle/zTreeStyle.css" type="text/css">
	<style type="text/css">
	.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
</head>
<body>

<div class="main">

	<div class="main-top"><h3>菜单列表&nbsp;
	<small>[ <a id="toggleBtn" href="#"  onclick="return false;">展开/折叠</a> ]</small>
	</h3></div>

<div class="panel panel-info">
	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree">正在加载菜单...</ul>
		</div>
	</div>
</div><!--panel-info-->

</div><!--main end-->
 

<%-- <script src="${ctx}/js/jquery.min.js" type="text/javascript"></script> --%>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.exedit-3.5.js"></script>
	
<script type="text/javascript">
	
	var setting = {
		async: {
			enable: true,
			url:"${ctx}/system/menu/treeData",
			autoParam:["id", "name=n", "level=lv"],
			dataFilter: filter,
			type: "get"
		},
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: true,
			expandSpeed: "normal"
		},
		edit: {
			enable: true,
			editNameSelectAll: false,
			showRemoveBtn: true,
			showRenameBtn: true
		},
		data: {
			type: "expandAll",
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeEditName: beforeEditName,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onRename: onRename
		}
	};
	
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		alert("treeId:"+treeId);
		return false;
	}
	
	function beforeEditName(treeId, treeNode) {
		var params = "id="+treeNode.id;
		alertDialog("修改菜单",params);
		return false;
	}
	
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		
		if(treeNode.depth==0){
			alert("默认菜单,不能删除.");
			return false;
		}
		
		if(treeNode.isParent){
			alert("该菜单有子菜单,不能直接删除.");
			return false;
		}
		
		return confirm("确认删除菜单[" + treeNode.name + "] 吗？");
	}
	
	function onRemove(e, treeId, treeNode) {
		ajaxSubmit("${ctx}/system/menu/delete?id="+treeNode.id,{},null,"删除成功","",null);
		return false;
	}
	
	function beforeRename(treeId, treeNode, newName, isCancel) {

		return false;
	}
	
	function onRename(e, treeId, treeNode, isCancel) {
		//var data = "id="+treeNode.id+"&menuName="+treeNode.name;
		//ajaxSubmit("${ctx}/system/menu/save?"+data,{},null,"修改成功","",null);
		return false;
	}
	function showRemoveBtn(treeId, treeNode) {
		return !treeNode.isFirstNode;
	}
	function showRenameBtn(treeId, treeNode) {
		return !treeNode.isLastNode;
	}
	
	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var childDepth = parseInt(treeNode.depth)+1;
			var params = "pid="+treeNode.id+"&depth="+childDepth;
			alertDialog("添加菜单",params);
			return false;
		});
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	
	
	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
	}
	
	
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
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
	
	
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting);
		$("#toggleBtn").bind("click", {type:"expandAll"}, expandNode);
		
		//setTimeout(initMenuCss, 500);
		
	});
	
	
	function ajaxCallback(event,param){
		alert("操作成功");
	}
	
	function alertDialog(dialogTitle,params){
		openDialog({
			frame:true,
			title:dialogTitle,
		    height:300,
		    width:700,
			url:"${ctx}/system/menu/edit?"+params
		});
	}
	
	
	function initMenuCss(){
		
		$("#toggleBtn").click();
		
	/* 	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var treeArry =  treeObj.transformToArray(treeObj.getNodes());
		$(treeArry).each(function(index,node){
			if(node.id==56){
				//alert(treeObj.setting.font);
			}
		});  */
		
	}

</script>
</body>
</html>
