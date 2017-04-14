<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>查看订单</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="container">
	<form id="editForm" role="form" action="${ctx}/order/save" method="post">
	<input type="hidden" name="orderId" value="${order.id}">
	<table width="95%" border="1">
		<tr>
			<td width="20%">订单号</td>
			<td width="30%">${order.orderCode}</td>
			<td width="20%">订单内容</td>
			<td width="30%"></td>
		</tr>
		<tr>
			<td>用户ID</td>
			<td>${order.userId}</td>
			<td>昵称</td>
			<td>${order.nickName}</td>
		</tr>
		<tr>
			<td>收货人</td>
			<td>${order.linkMan}</td>
			<td>手机</td>
			<td>${order.linkTel}</td>
		</tr>
		<tr>
			<td>收货地址</td>
			<td>${order.orderAddress}</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>订单状态</td>
			<td>
                	<c:choose>
                		<c:when test="${order.orderState==0}">未付款</c:when>
                		<c:when test="${order.orderState==1}">已付款</c:when>
                		<c:when test="${order.orderState==2}">运输中</c:when>
                		<c:when test="${order.orderState==3}">完成订单</c:when>
                		<c:when test="${order.orderState==4}">发起退货</c:when>
                		<c:when test="${order.orderState==5}">退货中</c:when>
                		<c:when test="${order.orderState==6}">退货完成</c:when>
                		<c:when test="${order.orderState==7}">订单过期</c:when>
                		<c:when test="${order.orderState==8}">已取消</c:when>
                	</c:choose>	
			</td>
			<td>下单时间</td>
			<td><fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm"/></td>
		</tr>
		<tr>
			<td>支付时间</td>
			<td><fmt:formatDate value="${order.paidTime}" pattern="yyyy-MM-dd HH:mm"/></td>
			<td>发货时间</td>
			<td><fmt:formatDate value="${order.deliverTime}" pattern="yyyy-MM-dd HH:mm"/></td>
		</tr>
		<tr>
			<td>快递公司</td>
			<td>${order.logisticsCompany}</td>
			<td>物流单号</td>
			<td>${order.logisticsCode}</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">
				<table width="100%" border="1">
					<tr>
						<th>商品ID</th>
						<th>编号</th>
						<th>名称</th>
						<th>图片</th>
						<th>单价</th>
						<th>数量</th>
						<th>金额</th>
						<th>颜色</th>
						<th>规格</th>
					</tr>
					<c:forEach items="${order.itemList}" var="pdt">
					<tr>
						<td>${pdt.productId}</td>
						<td>${pdt.productCode}</td>
						<td>${pdt.productName}</td>
						<td><img src="${pdt.productIcon}" width="75px;" height="75px;"></td>
						<td>${pdt.productPrice}</td>
						<td>${pdt.buyNum}</td>
						<td>${pdt.buyPrice}</td>
						<td>${pdt.productColors}</td>
						<td>${pdt.productRules}</td>
					</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
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