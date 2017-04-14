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
    <form id="editForm" role="form" action="${ctx}/information/informationDoAddOrUpdate" method="post">
        <input type="hidden" name="id" value="${infoDetail.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>资讯标题</th>
                    <td width="80%"><input required value="${infoDetail.informationName}" name="informationName"></td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>资讯类型</th>
                    <td width="80%">
                        <select name="informationType" required>
                            <option value="0" <c:if test="${infoDetail.informationType == 0}">selected="" </c:if>>医疗</option>
                            <option value="1" <c:if test="${infoDetail.informationType == 1}">selected="" </c:if>>保险</option>
                            <option value="2" <c:if test="${infoDetail.informationType == 2}">selected="" </c:if>>运动</option>
                            <option value="3" <c:if test="${infoDetail.informationType == 3}">selected="" </c:if>>养生</option>
                            <option value="4" <c:if test="${infoDetail.informationType == 4}">selected="" </c:if>>其它</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>资讯头图</th>
                    <td width="80%"><input required id="informationIcon" type="hidden" value="${infoDetail.informationIcon}" name="informationIcon">
                        <img id="infImg" src="${infoDetail.informationIcon}" width="80" height="80">
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/upload">
                    </td>
                </tr>
                <tr>
                    <th class="active"><strong class="necessary"> </strong>资讯内容</th>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea name="informationContext" id="informationContext" >${infoDetail.informationContext}</textarea>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认修改</button>
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
        $("#informationContext").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>