<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>户外活动</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
	<div class="main-top">    
            <h3>户外活动 <small>共<span>${apiRsp.count}</span>个</small></h3>              
    </div><!--main-top-->

	<div class="panel panel-info">
		  <div class="panel-heading">
		  		<div class="pin">
		          <button id="createProductBtn" type="button" onclick="javascript:createProduct();" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>添加活动</button>
		        </div>
		  </div>
          <table class="table table-hover">
            <thead>
              <tr>
              	<th>封面</th>
                <th>ID</th>
                <th width="200px;">名称</th>
                <th>费用</th>
                <th>积分</th>
               	<th>日期</th>
                <th>状态</th>
                <th class="th-time">创建时间</th>
                <th class="th-time">更新时间</th>
              </tr>
            </thead>
			  <c:set var="pagingUrl" value="${ctx}/activity/productList?"/>
			  <c:set var="nextUrl" value=""/>
			  <c:if test="${not empty apiRsp and apiRsp.curPage!=apiRsp.totalPages}">
			  <c:set var="nextUrl" value="${pagingUrl}&page=${apiRsp.curPage+1}"/>
			  </c:if>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
              <tr>
                <td><img src="${item.productIcon}" width="75px" height="75px"></td>
                <td>${item.id}</td>
                <td>${item.productName}<br>
                	<span class="row-actions">
                		<a href="javascript:editProduct('${item.id}');"><span class="glyphicon glyphicon-edit"></span>编辑</a>
                		<c:if test="${item.proudctState!=1}"><a href="javascript:publishProduct('${item.id}');"><span class="glyphicon glyphicon-ok"></span>发布</a></c:if>
                		<c:if test="${item.proudctState==1}"><a href="javascript:unpublishProduct('${item.id}');"><span class="glyphicon glyphicon-remove"></span>取消</a></c:if>
                	</span>
                </td>
                <td><fmt:formatNumber value="${item.productPrice}" pattern="#0.00#"/></td>
                <td>${item.productIntegral}</td>
                <td><fmt:formatDate value="${item.sportStartTime}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${item.sportEndTime}" pattern="yyyy-MM-dd"/></td>
                <td>
                	<c:choose>
                		<c:when test="${item.proudctState==0}"><span class="glyphicon glyphicon-question-sign text-warning" title="草稿"></span></c:when>
                		<c:when test="${item.proudctState==1}"><span class="glyphicon glyphicon-ok-sign text-success" title="已发布"></span></c:when>
                		<c:when test="${item.proudctState==2}"><span class="glyphicon glyphicon-remove-sign text-danger" title="已取消"></span></c:when>
                	</c:choose>
                </td>
                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${item.modifyTime}" pattern="yyyy-MM-dd HH:mm"/></td>
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
function createProduct(){
	parent.addTab("添加户外活动","${ctx}/activity/createProduct");
}
function editProduct(id){
	parent.addTab("编辑户外活动"+id,"${ctx}/activity/editProduct/"+id);
}
function publishProduct(id){
	ajaxSubmit("${ctx}/activity/changeStatus",{"productId":id, "proudctState":1},reload,"发布成功","确认发布？");
}
function unpublishProduct(id){
	ajaxSubmit("${ctx}/activity/changeStatus",{"productId":id, "proudctState":2},reload,"取消成功","确认取消？");
}
$(function(){
});
</script>
</body>
</html>
