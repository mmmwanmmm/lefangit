<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>编辑菜单信息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="container">
	<form id="editForm" role="form" action="${ctx}/system/menu/save" method="post">
	<input type="hidden" name="id" value="${menuDto.id}">
	<input type="hidden" name="pid" value="${pid}">
	<input type="hidden" name="depth" value="${depth}">
	<div class="form-group">
		<table class="table">
	        <tbody>
	          <tr>
	            <th width="20%" class="active"><strong class="necessary">*</strong>菜单名称</th>
	            <td width="80%"><input type="text" class="form-control" name="menuName" value="${menuDto.menuName}" placeholder="请输入名称" required="required"></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary"> </strong>功能代码</th>
	            <td><input type="text" class="form-control" id="menuCode" name="menuCode" value="${menuDto.menuCode}"  ></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary"> </strong>菜单URL</th>
	            <td><input type="text" class="form-control" id="menuUrl" name="menuUrl" value="${menuDto.menuUrl}" ></td>
	          </tr>	          
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>是否菜单</th>
	            <td>
	            <select class="form-control"  name="isShow" id="isShow" required="required">
				    <option value="1">是</option>
				    <option value="0">否</option>
				</select>
	            </td>
	          </tr>
	        </tbody>
	      </table>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
		<button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>		
	</div>
	</form>
</div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript">


$(function(){
	$("#editForm").bind('submit', function(event) {
		ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
		event.preventDefault();
	});	
	
	var isShow_val = '${menuDto.isShow}';
	if(isShow_val!=''){
		$("#isShow").val(isShow_val);
	}
});


function returnCallback(event,param){
	var apiRsp=getApiJson(param.data);
	if(apiRsp && apiRsp.isSuccess){
		alert('菜单保存成功');
	}else{
		alert(apiRsp.msg);
	}
	reloadParent();
}

</script>
</body>
</html>