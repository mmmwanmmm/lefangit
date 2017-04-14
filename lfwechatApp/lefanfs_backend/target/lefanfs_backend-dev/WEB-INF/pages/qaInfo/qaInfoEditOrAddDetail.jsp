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
    <form id="editForm" role="form" action="${ctx}/qaInfo/qaInfoAddOrUpdate" method="post">
        <input type="hidden" name="id" value="${infoDetail.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">问题</th>
                    <td width="80%">
                        <textarea  name="question" id="question"  style="width: 400px;">${infoDetail.question}</textarea>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary"> </strong>答案</th>
                    <td width="80%"> <textarea name="answer" id="answer" style="width: 400px;" >${infoDetail.answer}</textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                $("#infImg").attr("src","http://openapi.shlefan.com/pic/images/"+pathImg);
                $("#adPic").val(r.imgUrl);
            }else {
                alert("上传失败，请重试111");
            }
        }
    });
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>