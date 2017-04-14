<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户案例</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>用户案例</h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/scenarios/scenariosInfoList" method="post">
                    <div class="form-group">

                    </div>
                    <div class="btn-group">
                       <%-- <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>--%>
                       &nbsp; &nbsp; &nbsp; &nbsp; <button onclick="addinfo()" type="button" class="btn btn-default">添加用户案例信息</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th class="th-checkbox"><input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选"></th>
                <th width="100">标题</th>
               <%-- <th width="150">内容</th>--%>
                <th width="150">案例图</th>
                <th width="150">创建时间</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${userScenariosDtoList}" var="item">
                <tr>
                    <td></td>
                    <td>${item.title}</td>
                    <td> <img id="infImg"  target="_blank" src="${item.img}" width="80" height="80"></td>
                   <%-- <td><strong>${item.content}</strong></td>--%>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <th><a href="javascript:scenariosDelete('${item.id}');"><span class="glyphicon glyphicon-remove"></span>删除</a>&nbsp;&nbsp;&nbsp;<a  href="javascript:void(0)" onclick="updateInfo(${item.id})">编辑</a></th>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/scenarios/scenariosInfoList?page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/scenarios/scenariosInfoList?page=${page+1}">下一页</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/system/admin/list" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var scenariosDelete = function(id){
        ajaxSubmit("${ctx}/scenarios/scenariosInfoTodelete?id="+id,{},function(){location.reload();},"删除成功","确认此条用户案例删除吗？",null);
    }*/
    function scenariosDelete(id){
        ajaxSubmit("${ctx}/scenarios/scenariosInfoTodelete",{"id":id},reload,"删除成功","确认删除该条用户案例吗？");
    }
   /* var companyDetail = function(companyId){
        openDialog({
            frame:true,
            title:"查看公司服务详情",
            height:600,
            width:500,
            url:"${ctx}/comment/detail?id="+companyId
        });
    }*/
    var addinfo = function(){
        openDialog({
            frame:true,
            title:"添加用户案例信息",
            height:1000,
            width:600,
            url:"${ctx}/scenarios/scenariosInfoToAdd"
        });
    }

    var updateInfo = function(companyId){
        openDialog({
            frame:true,
            title:"修改用户案例信息",
            height:1000,
            width:600,
            url:"${ctx}/scenarios/scenariosInfoToUpdate?id="+companyId
        });
    }
</script>
</body>
</html>
