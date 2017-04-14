<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>广告列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
	<div class="main-top">    
            <h3>广告列表 <small>共<span>${apiRsp.count}</span>个</small></h3>              
    </div><!--main-top-->

	<div class="panel panel-info">
		  <div class="panel-heading">
		  		<div class="pin">
		          <button id="createAdvBtn" type="button" onclick="javascript:createAdv();" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>添加广告</button>
		        </div>
		  </div>
          <table class="table table-hover">
            <thead>
              <tr>
              	<th>封面</th>
                <th>广告位</th>
                <th>ID</th>
                <th>名称</th>
                <th>链接</th>
                <th>顺序号</th>
                <th class="th-time">创建时间</th>
              </tr>
            </thead>
			  <c:set var="pagingUrl" value="${ctx}/adv/advList?"/>
			  <c:set var="nextUrl" value=""/>
			  <c:if test="${not empty apiRsp and apiRsp.curPage!=apiRsp.totalPages}">
			  <c:set var="nextUrl" value="${pagingUrl}&page=${apiRsp.curPage+1}"/>
			  </c:if>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
              <tr>
                <td><a href="${item.adPic}" target="_blank"><img src="${item.adPic}" width="75px" height="75px"></a></td>
                <td>
                	<c:choose>
                		<c:when test="${item.adCode eq 'mall-home'}">商城首页广告</c:when>
                		<c:when test="${item.adCode eq 'info-ad'}">资讯首页广告</c:when>
                		<c:when test="${item.adCode eq 'outdoorActivity-home'}">户外活动首页广告</c:when>
                		<c:otherwise>${item.adCode}</c:otherwise>
                	</c:choose>
                </td>
                <td>${item.id}</td>
                <td>${item.adTitle}
                   	<span class="row-actions">
                		<a href="javascript:editAdv('${item.id}');"><span class="glyphicon glyphicon-edit"></span>编辑</a>
                		<a href="javascript:unpublishAdv('${item.id}');"><span class="glyphicon glyphicon-remove"></span>下架</a>
                	</span>
                </td>
                <td><a href="${item.adHerf}" target="_blank">${item.adHerf}</a></td>
                <td>${item.adOrder}</td>
                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
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
function createAdv(){
	parent.addTab("添加广告","${ctx}/adv/createAdv");
}
function editAdv(id){
	parent.addTab("编辑广告"+id,"${ctx}/adv/editAdv/"+id);
}
function unpublishAdv(id){
	ajaxSubmit("${ctx}/adv/changeStatus",{"advId":id, "deleteFlag":1},reload,"下架成功","确认下架？");
}
$(function(){
});
</script>
</body>
</html>
