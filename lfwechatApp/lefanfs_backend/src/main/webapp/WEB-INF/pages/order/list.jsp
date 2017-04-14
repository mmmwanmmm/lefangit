<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>订单列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
	<div class="main-top">    
            <h3>订单列表 <small>共<span>${apiRsp.count}</span>个</small></h3>              
    </div><!--main-top-->

	<div class="panel panel-info">
          <table class="table table-hover">
            <thead>
              <tr>
                <th>订单号</th>
                <th>订单内容</th>
                <th>用户ID</th>
                <th>昵称</th>
                <th>收货人</th>
                <th>手机</th>
                <th>应付金额</th>
                <th>订单状态</th>
                <th class="th-time">下单时间</th>
                <th class="th-time">支付时间</th>
                <th class="th-time">发货时间</th>
              </tr>
            </thead>
			  <c:set var="pagingUrl" value="${ctx}/order/list?"/>
			  <c:set var="nextUrl" value=""/>
			  <c:if test="${not empty apiRsp and apiRsp.curPage!=apiRsp.totalPages}">
			  <c:set var="nextUrl" value="${pagingUrl}&page=${apiRsp.curPage+1}"/>
			  </c:if>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
              <tr>
                <td>${item.orderCode}
                	<span class="row-actions">
                	<a href="javascript:editOrder('${item.id}');"><span class="glyphicon glyphicon-edit"></span>查看订单</a>
                	<c:if test="${item.orderState==1}">
                		<a href="javascript:delivery('${item.id}');"><span class="glyphicon glyphicon-edit"></span>订单发货</a>
                	</c:if>
                	</span>
                </td>
                <td></td>
                <td>${item.userId}</td>
                <td>${item.nickName}</td>
                <td>${item.linkMan}</td>
                <td>${item.linkTel}</td>
                <td><fmt:formatNumber pattern="#0.00#" value="${item.finalPrice}"/></td>
                <td>
                	<c:choose>
                		<c:when test="${item.orderState==0}">未付款</c:when>
                		<c:when test="${item.orderState==1}">已付款</c:when>
                		<c:when test="${item.orderState==2}">运输中</c:when>
                		<c:when test="${item.orderState==3}">完成订单</c:when>
                		<c:when test="${item.orderState==4}">发起退货</c:when>
                		<c:when test="${item.orderState==5}">退货中</c:when>
                		<c:when test="${item.orderState==6}">退货完成</c:when>
                		<c:when test="${item.orderState==7}">订单过期</c:when>
                		<c:when test="${item.orderState==8}">已取消</c:when>
                	</c:choose>
                </td>
                <td><fmt:formatDate value="${item.orderTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${item.paidTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${item.deliverTime}" pattern="yyyy-MM-dd HH:mm"/></td>
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
function editOrder(id){
	openDialog({
		frame:true,
		title:"查看订单",
	    height:700,
	    width:800,
		url:"${ctx}/order/edit/"+id
	});	
}
function delivery(id){
	openDialog({
		frame:true,
		title:"订单发货",
	    height:300,
	    width:800,
		url:"${ctx}/order/delivery/"+id
	});	
}


$(function(){
});
</script>
</body>
</html>
