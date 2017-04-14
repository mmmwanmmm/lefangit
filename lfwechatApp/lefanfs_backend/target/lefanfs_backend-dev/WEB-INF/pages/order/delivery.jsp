<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>订单发货</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="container">
	<form id="editForm" role="form" action="${ctx}/order/update" method="post">
	<input type="hidden" name="orderId" value="${order.id}">
	<input type="hidden" name="orderState" value="2">
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
			<td>快递公司</td>
			<td>
				<select name="logisticsCompany" required="required">
					<option value="">请选择</option>
					<option value="顺丰">顺丰</option>
					<option value="申通">申通</option>
					<option value="圆通速递">圆通速递</option>
					<option value="中通速递">中通速递</option>
					<option value="百世快递">百世快递</option>
					<option value="韵达快运">韵达快运</option>
					<option value="天天快递">天天快递</option>
					<option value="中国邮政快递">中国邮政快递</option>
					<option value="ems快递">ems快递</option>
					<option value="宅急送">宅急送</option>
					<option value="德邦物流">德邦物流</option>
					<option value="全峰快递">全峰快递</option>
					<option value="快捷速递">快捷速递</option>
				</select>
			</td>
			<td>物流单号</td>
			<td><input type="text" name="logisticsCode" value="" required="required"></td>
		</tr>
	</table>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
		<button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认发货</button>		
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