<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户推广认证信息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>用户推广认证信息</h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/promoted/userPromotedInfoList" method="post">
                    <div class="form-group">

                    </div>
                    <div class="btn-group">
                     <%--   <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                       &nbsp; &nbsp; &nbsp; &nbsp; <button onclick="addinfo()" type="button" class="btn btn-default">添加用户案例信息</button>--%>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th class="th-checkbox"><input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选"></th>
                <th width="100">真实姓名</th>
                <th width="150">手机号</th>
                <th width="150">职业</th>
                <th width="150">所属地</th>
                <th width="200">状态</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${userPromotedDtoList}" var="item">
                <tr>
                    <td></td>
                    <td>${item.realName}</td>
                    <td>${item.phone}</td>

                    <td>
                        <c:if test="${item.occupation == 1}">护士</c:if>
                        <c:if test="${item.occupation == 0}">医生</c:if>
                        <c:if test="${item.occupation == 1}">护工</c:if>
                        <c:if test="${item.occupation == 0}">调解组织</c:if>
                        <c:if test="${item.occupation == 1}">其他</c:if>
                    </td>
                    <td>${item.province}${item.city}${item.district}</td>
                    <td>
                        <c:if test="${item.state == 1}">待审核</c:if>
                        <c:if test="${item.state == 2}">审核通过</c:if>
                        <c:if test="${item.state == 3}">驳回</c:if>
                    </td>

                    <th>
                        <c:if test="${item.state == 1}">
                            <a href="javascript:promotedUpdatea('${item.id}',2);">审核通过</a>
                            <a href="javascript:bohuiPromoted('${item.id}');">驳回</a>
                        </c:if>
                        <c:if test="${item.state == 3}">
                                  ${item.reason}
                           <%-- <a href="javascript:promotedUpdatea('${item.id}',2);"><span class="glyphicon glyphicon-remove"></span>审核通过</a>--%>
                        </c:if>
                        <c:if test="${item.state == 2}">
                            <a href="javascript:queryPromoted('${item.userId}');">查看推广客户</a>
                        </c:if>

                     </th>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/promoted/userPromotedInfoList?page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/promoted/userPromotedInfoList?page=${page+1}">下一页</a>
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
    function promotedUpdatea(id,state){
        ajaxSubmit("${ctx}/promoted/userPromotedInfoAddOrUpdate",{"id":id,"state":state},reload,"提交成功","确认提交吗？");
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
    var queryPromoted = function(userId){
        openDialog({
            frame:true,
            title:"推广客户列表",
            height:1000,
            width:1200,
            url:"${ctx}/promoted/promotedInfoList?userId="+userId
        });
    }

   var bohuiPromoted = function(id){
       openDialog({
           frame:true,
           title:"驳回",
           height:200,
           width:800,
           url:"${ctx}/promoted/promotedInfoToReject?id="+id
       });
   }
  /* var addinfo = function(){
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
</script>
</body>
</html>
