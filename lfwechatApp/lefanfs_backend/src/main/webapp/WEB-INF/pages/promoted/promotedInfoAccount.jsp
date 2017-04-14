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
    <form id="editForm" role="form" action="${ctx}/promoted/promotedInfoAddOrUpdate" method="post">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="state" value="2">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>

                    <th class="active"><strong class="necessary">*</strong>结算凭证</th>
                    <td>
                        <input required id="accountImg" type="hidden" value="${infoDetail.img}" name="accountImg">
                        <img id="infImg" src="${infoDetail.img}" width="80" height="80">
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage">
                    </td>
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
                $("#accountImg").val("http://openapi.shlefan.com/pic/images/"+pathImg);
            }else {
                alert("上传失败，请重试111");
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