<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>广告管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
	<div class="main add-game">
		<c:set var="updateFlag" value=""/>
		<form id="editForm" action="${ctx}/adv/saveAdv" method="post" role="form">
		<input type="hidden" id="advId" name="advId" value="${adv.id}">
		<table class="table">
	        <tbody>
	          <tr>
	            <th class="active" width="15%"><strong class="necessary">*</strong>广告位</th>
	            <td width="85%">
	            	<select id="adCode" name="adCode">
	            		<option value="banner">首页banner</option>
	            		<option value="company">公司服务介绍</option>
	            		<option value="scenarios">案例</option>
	            	</select>
	            </td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>标题</th>
	            <td><input id="adTitle" name="adTitle" value="${adv.adTitle}" type="text" class="form-control"></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>图片</th>
	            <td>
	            	<input required id="adPic" type="hidden" value="${adv.adPic}" name="adPic">
                    <img id="infImg" src="${adv.adPic}" width="80" height="80">
                    <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage">
	            </td>
	          </tr>
	          <tr>
	            <th class="active">链接</th>
	            <td><input id="adHerf" name="adHerf" value="${adv.adHerf}" type="text" class="form-control"></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>顺序号</th>
	            <td><input id="adOrder" name="adOrder" value="${adv.adOrder}" type="number" class="form-control"></td>
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
function returnToList(){
	parent.closeThenAddTab("广告列表","${ctx}/adv/advList",true);
}
$(function(){
	if("${adv.adCode}"!=""){
		$("#adCode").val("${adv.adCode}");
	}
    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                $("#infImg").attr("src","http://openapi.shlefan.com/pic/images"+pathImg);
                $("#adPic").val("http://openapi.shlefan.com/pic/images"+pathImg);
            }else {
                alert("上传失败，请重试111");
            }
        }
    });
	$("#editForm").bind('submit', function(event) {
		ajaxFormSubmit(this,returnToList,null,null);
		event.preventDefault();
	});
});
</script>
</body>
</html>