<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>查看商品</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
	<div class="main add-game">
		<c:set var="updateFlag" value=""/>
		<form id="editForm" action="${ctx}/product/saveProduct" method="post" role="form">
		<input type="hidden" id="productId" name="productId" value="${product.id}">
		<table class="table">
	        <tbody>
	          <tr>
	            <th class="active" width="15%"><strong class="necessary">*</strong>名称</th>
	            <td width="85%"><input id="productName" name="productName" value="${product.productName}" required="required" type="text" class="form-control"></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>编号</th>
	            <td><input id="productCode" name="productCode" value="${product.productCode}" type="text" class="form-control"></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>分类</th>
	            <td>
	            	<select id="categoryId" name="categoryId" required="required">
	            		<option value="">请选择</option>
	            		<option value="9991">运动装备</option>
	            		<option value="9992">女子鞋服</option>
	            		<option value="9993">男子鞋服</option>
	            		<option value="9994">营养补养</option>
	            	</select>
	            </td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>封面</th>
	            <td>
	            	<input required id="productIcon" type="hidden" value="${product.productIcon}" name="productIcon">
                    <img id="infImg" src="${product.productIcon}" width="80" height="80">
                    <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/upload">
	            </td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>图片</th>
	            <td>
                    <input id="uploadProductImg" type="file"  name="file" multiple  data-url="${ctx}/upload">
                    <div id="productImgDiv">
                    </div>
	            </td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>价格</th>
	            <td><input id="productPrice" name="productPrice" value="${product.productPrice}" type="text" class="form-control"></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>库存</th>
	            <td><input id="stock" name="stock" value="${product.stock}" type="text" class="form-control"></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>介绍</th>
	            <td><textarea id="remark" name="remark" required="required" class="form-control description" onpropertychange="this.style.height = this.scrollHeight + 'px';" oninput="this.style.height = this.scrollHeight + 'px';">${product.remark}</textarea></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>详情</th>
	            <td><textarea id="productDetail" name="productDetail" class="form-control description" rows="10" cols="100">${product.productDetail}</textarea></td>
	          </tr>
	         <tr>
                <td hidden><input id="productType" name="productType" value="${product.productType}" type="text"></td>
             </tr>
	        </tbody>
		</table>	
		<div class="submit">
			<button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 保存</button>
		</div>
		</form>
	</div>
<!--main end-->
	<div id="dialogId"></div>
	<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
	<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/common.js" type="text/javascript"></script>
	<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
	<script type="text/javascript">
		var ctx="${ctx}";
	</script>
	<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
	<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
	<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script type="text/javascript">
var editor1;
KindEditor.ready(function(K) {
     editor1 = K.create('textarea[name="productDetail"]', {
        cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
        uploadJson : '${ctx}/uploadForKindedit'
    });
});
function returnToList(){
	parent.closeThenAddTab("运动商品","${ctx}/product/productList",true);
}
$(function(){
    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            if (r.code == 200){
                $("#infImg").attr("src",r.imgUrl);
                $("#productIcon").val(r.imgUrl);
            }else {
                alert("上传失败，请重试");
            }

        }
    });
    $('#uploadProductImg').fileupload({
        done: function (e, data) {
            var r  = data.result;
            if (r.code == 200){
            	var html='';
            	html+='<input type="hidden" name="productImg" value="'+r.imgUrl+'">';
                html+='<img src="'+r.imgUrl+'" width="80" height="80">';
                $("#productImgDiv").append(html);
            }else {
                alert("上传失败，请重试");
            }

        }
    });
    
    $("#categoryId").val("${product.categoryId}");
    
	$("#editForm").bind('submit', function(event) {
		$("#productDetail").text(editor1.html());
		ajaxFormSubmit(this,returnToList,null,null);
		event.preventDefault();
	});
});
</script>
</body>
</html>