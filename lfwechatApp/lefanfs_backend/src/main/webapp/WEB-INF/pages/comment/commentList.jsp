<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>留言管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>留言管理</h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/comment/search" method="post">
                    <div class="form-group">
                        <input name="comment" type="text">
                        <select name="commentTo">
                            <option value="">全部</option>
                            <option value="0">保险</option>
                            <option value="1">资讯</option>
                            <option value="2">订单</option>
                            <option value="3">APP</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th class="th-checkbox"><input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选"></th>
                <th class="th-name">留言用户</th>
                <th>留言内容</th>
                <th width="200">类型</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${commentList}" var="item">
                <tr>
                    <td><input type="checkbox" name="list-checkbox" value="${item.id}"></td>
                    <td>${item.nickName}</td>
                    <td><strong>${item.comment}</strong></td>
                    <td>
                        <c:if test="${item.commentTo == 0}">保险</c:if>
                        <c:if test="${item.commentTo == 1}">资讯</c:if>
                        <c:if test="${item.commentTo == 2}">订单</c:if>
                        <c:if test="${item.commentTo == 3}">APP</c:if>
                    </td>
                    <td>
                        <c:if test="${item.replyParentId != null}">
                            <a href="javascript:void(0)" onclick="commentDetail(${item.replyParentId})">详情回复</a>
                        </c:if>
                        <c:if test="${item.replyParentId == null}">
                            <a href="javascript:void(0)" onclick="commentDetail(${item.id})">详情回复</a>
                        </c:if>

                        <a href="javascript:void(0)" onclick="commentDelete(${item.id})">删除留言</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/comment/search?comment=${comment}&commentTo=${commentTo}&page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/comment/search?comment=${comment}&commentTo=${commentTo}&page=${page+1}">下一页</a>
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
    var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }
    var commentDetail = function(commentid){
        openDialog({
            frame:true,
            title:"查看留言信息",
            height:600,
            width:500,
            url:"${ctx}/comment/detail?id="+commentid
        });
    }
</script>
</body>
</html>
