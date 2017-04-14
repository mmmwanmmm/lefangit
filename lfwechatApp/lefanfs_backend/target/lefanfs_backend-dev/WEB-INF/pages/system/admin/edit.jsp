<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>添加账号</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="container">
	<form id="editForm" role="form" action="${ctx}/system/admin/save" method="post">
	<input type="hidden" name="id" value="${admin.id}">
	<div class="form-group">
		<label class="control-label" for="realname"><strong class="necessary">*</strong>姓名</label>
		<input type="text" class="form-control" id="realname" name="realname" value="${admin.realname}" placeholder="请输入姓名" required="required">
	</div>
	<div class="form-group">
		<label class="control-label" for="username"><strong class="necessary">*</strong>登录账号</label>
		<c:choose>
		<c:when test="${not empty admin.id}">
			<input type="text" class="form-control" id="username" name="username" value="${admin.username}" placeholder="请输入登录账号" readonly="readonly">
		</c:when>
		<c:otherwise>
			<input type="text" class="form-control" id="username" name="username" placeholder="请输入登录账号" required="required">
		</c:otherwise>
		</c:choose>
	</div>
	<div class="form-group">
		<label class="control-label" for="password"><c:if test="${empty admin.id}"><strong class="necessary">*</strong></c:if>登录密码</label>
		<c:choose>
		<c:when test="${not empty admin.id}">
			<input type="text" class="form-control" id="password" name="password" placeholder="请输入登录密码">
		</c:when>
		<c:otherwise>
			<input type="text" class="form-control" id="password" name="password" placeholder="请输入登录密码" required="required">
		</c:otherwise>
		</c:choose>
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
		ajaxFormSubmit(this,reloadParent,null,null);
		event.preventDefault();
	});	
});
</script>
</body>
</html>