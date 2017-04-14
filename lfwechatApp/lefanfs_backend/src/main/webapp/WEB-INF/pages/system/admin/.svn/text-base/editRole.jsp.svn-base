<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>编辑用户角色</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="container">
	<form id="editForm" role="form" action="${ctx}/system/admin/roleSave" method="post">
	<input type="hidden" name="userId" value="${admin.id}">
	<input type="hidden" name="roleIds" id="roleIds">
	<div class="form-group">
		<label class="control-label" for="realname"><strong class="necessary">*</strong>姓名</label>
		<input type="text" class="form-control" id="realname" name="realname" value="${admin.realname}"  readonly="readonly" >
	</div>
	<div class="form-group">
		<label class="control-label" for="username"><strong class="necessary">*</strong>登录账号</label>
		<input type="text" class="form-control" id="username" name="username" value="${admin.username}"  readonly="readonly">
	</div>
	<div class="form-group">
		<label class="control-label" for="password"><strong class="necessary">*</strong>所属角色</label>
		<br>
		<c:forEach items="${apiRsp.results }" var="role">
			<input type="checkbox" name="list-checkbox" value="${role.id}"> ${role.roleName } 
		</c:forEach>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
		<button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>		
	</div>
	</form>
</div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript">

 function _checkRole(){
		var check_name = document.getElementsByName("list-checkbox");
		var idArr=new Array();
		for(var i=0;i<check_name.length;i++){
			if(check_name[i].checked){
				idArr.push(check_name[i].value);
			}
		}
		$("#roleIds").val(idArr);
 }
 



$(function(){
	$("#editForm").bind('submit', function(event) {
		_checkRole();
		ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
		event.preventDefault();
	});	
	
	do_list_checkbox();
});


function do_list_checkbox(){
	 var checkValue = "${userRoleIds }";
	 $("input[name=list-checkbox]").each(function (){
		  var indexCode = checkValue.indexOf(","+$(this).val()+",");
		  if(indexCode>-1){
		  	 $(this).attr("checked","checked"); 
		  }
	 });
}

function returnCallback(event,param){
	var apiRsp=getApiJson(param.data);
	if(apiRsp && apiRsp.isSuccess){
		alert('权限设置成功');
	}else{
		alert(apiRsp.msg);
	}
	closeDialog();
}

</script>
</body>
</html>