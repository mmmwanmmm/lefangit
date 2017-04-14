<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>运动商品</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
	<div class="main-top">    
            <h3>运动商品 <small>共<span>${apiRsp.count}</span>个</small></h3>              
    </div><!--main-top-->

	<div class="panel panel-info">
		  <div class="panel-heading">
		  		<div class="pin">
		          <button id="createProductBtn" type="button" onclick="javascript:createProduct();" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>添加商品</button>
		        </div>
		  </div>
          <table class="table table-hover">
            <thead>
              <tr>
              	<th>封面</th>
                <th>ID</th>
                <th>编号</th>
                <th>名称</th>
                <th>价格</th>
                <th>库存</th>
                <th>销量</th>
                <th>状态</th>
                <th class="th-time">创建时间</th>
                <th class="th-time">更新时间</th>
              </tr>
            </thead>
			  <c:set var="pagingUrl" value="${ctx}/product/productList?"/>
			  <c:set var="nextUrl" value=""/>
			  <c:if test="${not empty apiRsp and apiRsp.curPage!=apiRsp.totalPages}">
			  <c:set var="nextUrl" value="${pagingUrl}&page=${apiRsp.curPage+1}"/>
			  </c:if>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
              <tr>
                <td><img src="${item.productIcon}" width="75px" height="75px"></td>
                <td>${item.id}</td>
                <td>${item.productCode}</td>
                <td>${item.productName}
                   	<span class="row-actions">
                		<a href="javascript:editProduct('${item.id}');"><span class="glyphicon glyphicon-edit"></span>编辑</a>
                		<c:if test="${item.proudctState!=1}"><a href="javascript:publishProduct('${item.id}');"><span class="glyphicon glyphicon-ok"></span>上架</a></c:if>
                		<c:if test="${item.proudctState==1}"><a href="javascript:unpublishProduct('${item.id}');"><span class="glyphicon glyphicon-remove"></span>下架</a></c:if>
                	</span>
                </td>
                <td><fmt:formatNumber value="${item.productPrice}" pattern="#0.00#"/></td>
                <td>${item.stock}</td>
                <td>${item.saleNum}</td>
                <td>
                	<c:choose>
                		<c:when test="${item.proudctState==0}"><span class="glyphicon glyphicon-question-sign text-warning" title="草稿"></span></c:when>
                		<c:when test="${item.proudctState==1}"><span class="glyphicon glyphicon-ok-sign text-success" title="已上架"></span></c:when>
                		<c:when test="${item.proudctState==2}"><span class="glyphicon glyphicon-remove-sign text-danger" title="已下架"></span></c:when>
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
	parent.addTab("添加商品","${ctx}/product/createProduct");
}
function editProduct(id){
	parent.addTab("编辑商品"+id,"${ctx}/product/editProduct/"+id);
}
function publishProduct(id){
	ajaxSubmit("${ctx}/product/changeStatus",{"productId":id, "proudctState":1},reload,"上架成功","确认上架？");
}
function unpublishProduct(id){
	ajaxSubmit("${ctx}/product/changeStatus",{"productId":id, "proudctState":2},reload,"下架成功","确认下架？");
}
$(function(){
});
</script>
</body>
</html>
