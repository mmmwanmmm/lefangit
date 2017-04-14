<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/comment/reply" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>留言用户</th>
                    <td width="80%">${comment.nickName}</td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>资讯类型</th>
                    <td width="80%">
                        <c:if test="${comment.commentTo == 0}">保险 </c:if>
                        <c:if test="${comment.commentTo == 1}">资讯 </c:if>
                        <c:if test="${comment.commentTo == 2}">订单 </c:if>
                        <c:if test="${comment.commentTo == 3}">APP </c:if>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>留言内容</th>
                    <td width="80%">${comment.comment}</td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>回复详情</th>
                    <td width="80%">
                        <c:forEach items="${comment.replyList}" var="item">
                            ${item.nickName}:${item.comment}<br>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>添加回复</th>
                    <td width="80%">
                        <input name="replyParentId" type="hidden" value="${comment.id}">
                        <input name="comment" required>
                        <br>
                        <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 添加回复</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery.pin.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/js/jquery.tableDnD.js" type="text/javascript"></script>--%>
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
         editor1 = K.create('textarea[name="informationContext"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadForKindedit'
        });
    });

    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            if (r.code == 200){
                $("#infImg").attr("src",r.imgUrl);
                $("#informationIcon").val(r.imgUrl);
            }else {
                alert("上传失败，请重试");
            }

        }
    });
    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>