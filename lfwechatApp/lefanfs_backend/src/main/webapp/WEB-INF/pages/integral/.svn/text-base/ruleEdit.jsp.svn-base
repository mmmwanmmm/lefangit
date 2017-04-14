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
    <form id="editForm" role="form" action="${ctx}/integral/doedit" method="post">
        <input type="hidden" name="id" value="${ruleDetail.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>积分规则名称</th>
                    <td width="80%">${ruleDetail.integralName}</td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>原来赠送积分</th>
                    <td width="80%">${ruleDetail.integralValue}</td>
                </tr>
                <tr>
                    <th class="active"><strong class="necessary"> </strong>新赠送积分</th>
                    <td><input type="number" class="form-control" id="integralNum" name="integralNum" value="${ruleDetail.integralValue}"  ></td>
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