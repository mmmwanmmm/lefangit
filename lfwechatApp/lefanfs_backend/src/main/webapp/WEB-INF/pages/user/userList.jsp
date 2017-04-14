<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title> 用户列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
	<div class="main-top">    
            <h3> 用户列表 <small>共<span>${apiRsp.count}</span>个</small></h3>              
    </div><!--main-top-->

	<div class="panel panel-info">
          <table class="table table-hover">
            <thead>
              <tr>
              	<th>头像</th>
                <th>昵称</th>
                <th>手机号</th>
                <th>性别</th>
                <th>是否是推广人</th>
                <th>状态</th>
              </tr>
            </thead>
			  <c:set var="pagingUrl" value="${ctx}/user/userList?"/>
			  <c:set var="nextUrl" value=""/>
			  <c:if test="${not empty apiRsp and apiRsp.curPage!=apiRsp.totalPages}">
			  <c:set var="nextUrl" value="${pagingUrl}&page=${apiRsp.curPage+1}"/>
			  </c:if>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
              <tr>
                <td><a href="${item.img}" target="_blank"><img src="${item.img}" width="75px" height="75px"></a></td>
                <td>${item.nickName}
                   	<span class="row-actions">
                		<c:if test="${item.userState!=0}"><a href="javascript:enableUser('${item.userId}');"><span class="glyphicon glyphicon-ok"></span>启用</a></c:if>
                		<c:if test="${item.userState==0}"><a href="javascript:lockUser('${item.userId}');"><span class="glyphicon glyphicon-remove"></span>冻结</a></c:if>
                	</span>
                </td>
                <td>${item.userTel}</td>
                <td>
                    <c:if test="${item.sex!=1}">男</c:if>
                    <c:if test="${item.sex!=0}">女</c:if>
                </td>
                <td>
                    <c:if test="${item.isPromoter!=0}">否</c:if>
                    <c:if test="${item.isPromoter!=1}">是</c:if>
                </td>
                <td>
                	<c:choose>
                		<c:when test="${item.userState==0}"><span class="glyphicon glyphicon-ok-sign text-success" title="正常"></span></c:when>
                		<c:when test="${item.userState==1}"><span class="glyphicon glyphicon-remove-sign text-danger" title="冻结"></span></c:when>
                	</c:choose>
                </td>
              </tr>
            </c:forEach>  
            </tbody>
          </table>
	</div><!--panel-info-->
    
    <div class="main-bottom">
		<jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
			<jsp:param name="paginationObjectName" value="apiRsp" />
			<jsp:param name="pageNoName" value="" />
			<jsp:param name="requestUrl" value="${pagingUrl}" />
			<jsp:param name="refreshDiv" value="" />
		</jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript">
function enableUser(id){
	ajaxSubmit("${ctx}/user/changeStatus",{"userId":id, "userState":0},reload,"启用成功","确认启用？");
}
function lockUser(id){
	ajaxSubmit("${ctx}/user/changeStatus",{"userId":id, "userState":1},reload,"冻结成功","确认冻结？");
}
$(function(){
});
</script>
</body>
</html>
