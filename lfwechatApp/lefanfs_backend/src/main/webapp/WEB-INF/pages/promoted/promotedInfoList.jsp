<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>推广信息列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>推广信息列表</h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/promoted/promotedInfoList" method="post">
                    <div class="form-group">
                        用户名称: <input name="userName" type="text"  value="${userName}">
                    </div>
                    <div class="btn-group">
                      <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                       &nbsp; &nbsp; &nbsp; &nbsp;
                        <%--<button onclick="addinfo()" type="button" class="btn btn-default">添加用户案例信息</button>--%>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th class="th-checkbox"><input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选"></th>
                <th width="100">推广人姓名</th>
                <th width="150">推广客户名称</th>
                <th width="150">推广客户头像</th>
                <th width="200">推广客户备注</th>
                <th width="200">推广客户描述</th>
                <th width="200">推广客户名片</th>
                <th width="200">推广客户手机号</th>
                <th width="200">推广客户状态</th>
                <th width="200">推广客户时间</th>
                <th width="200">结算状态</th>
                <th width="200">结算凭证</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${promotedInfoList}" var="item">
                <tr>
                    <td></td>
                    <td>${item.promoterName}</td>
                    <td>${item.customerName}</td>
                    <td><img src="${item.customerImg}" width="75px" height="75px"></td>
                    <td>${item.customerDesc}</td>
                    <td>${item.customerText}</td>
                    <td><img src="http://openapi.shlefan.com/pic/images${item.customerBusinessCard}" width="75px" height="75px"></td>
                    <td>${item.customerPhone}</td>
                    <td>
                        <c:if test="${item.customerState == 1}">关注乐凡</c:if>
                        <c:if test="${item.customerState == 2}">申请贷款未获得通过</c:if>
                        <c:if test="${item.customerState == 3}">获得贷款</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <c:if test="${item.accountState == 0}">未结算</c:if>
                        <c:if test="${item.accountState == 1}">待结算</c:if>
                        <c:if test="${item.accountState == 2}">已结算</c:if>
                    </td>
                    <td><img src="${item.accountImg}" width="75px" height="75px"></td>
                    <th>
                        <c:if test="${item.accountState == 1}"><a href="javascript:accountPromoted('${item.id}');">
                            结算</a></c:if>
                    </th>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/promoted/promotedInfoList?page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/promoted/promotedInfoList?page=${page+1}">下一页</a>
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
    /*function promotedUpdatea(id,state){
        ajaxSubmit("${ctx}/promoted/userPromotedInfoAddOrUpdate",{"id":id,"state":state},reload,"提交成功","确认提交吗？");
    }*/
   /* var companyDetail = function(companyId){
        openDialog({
            frame:true,
            title:"查看公司服务详情",
            height:600,
            width:500,
            url:"${ctx}/comment/detail?id="+companyId
        });
    }*/
    /*var addinfo = function(){
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
    }*/
   var accountPromoted = function(id) {
       openDialog({
           frame: true,
           title: "结算",
           height: 200,
           width: 800,
           url: "${ctx}/promoted/promotedInfoToAcount?id=" + id
       });
   }
</script>
</body>
</html>
