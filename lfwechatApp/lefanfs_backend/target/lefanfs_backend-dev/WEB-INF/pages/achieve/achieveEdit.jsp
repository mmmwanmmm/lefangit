

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/achieve/doupdateachieve" method="post">
        <input type="hidden" name="achieveId" value="${achieveDto.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>成就名称</th>
                    <td width="80%"><input type="text" class="form-control" id="achieveName" name="achieveName" value="${achieveDto.achieveName}"  ></td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>成就描述</th>
                    <td width="80%"><input type="text" class="form-control" id="description" name="description" value="${achieveDto.achieveName}"  ></td>
                </tr>
                <tr>
                    <th class="active"><strong class="necessary"> </strong>需要数值</th>
                    <td><input type="number" class="form-control" id="kpiValue" name="kpiValue" value="${achieveDto.achieveKpiValue}"  ></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认修改</button>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>