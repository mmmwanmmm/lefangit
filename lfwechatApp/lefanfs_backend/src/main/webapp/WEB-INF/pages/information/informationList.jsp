<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>积分规则管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>积分规则管理</h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/information/informationList" method="post">
                    <div class="form-group">
                        <input name="name" type="text">
                        <select name="type">
                            <option value="">全部</option>
                            <option value="0">医疗</option>
                            <option value="1">保险</option>
                            <option value="2">运动</option>
                            <option value="3">养生</option>
                            <option value="4">其它</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>


                        <button onclick="addinfo()" type="button" class="btn btn-default">添加资讯</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th class="th-checkbox"><input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选"></th>
                <th class="th-name">资讯图标</th>
                <th>名称</th>
                <th width="200">类型</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${infoList}" var="item">
                <tr>
                    <td><input type="checkbox" name="list-checkbox" value="${item.id}"></td>
                    <td><img src="${item.informationIcon}" width="80" height="80" /></td>
                    <td><strong>${item.informationName}</strong></td>
                    <td>
                        <c:if test="${item.informationType == 0}">医疗</c:if>
                        <c:if test="${item.informationType == 1}">保险</c:if>
                        <c:if test="${item.informationType == 2}">运动</c:if>
                        <c:if test="${item.informationType == 3}">养生</c:if>
                        <c:if test="${item.informationType == 4}">其它</c:if>
                    </td>
                    <td><a href="javascript:void(0)" onclick="updateInfo(${item.id})">修改</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/information/informationList?name=${name}&type=${type}&page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/information/informationList?name=${name}&type=${type}&page=${page+1}">下一页</a>
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
<script type="text/javascript">
    function updateRule(id){
        openDialog({
            frame:true,
            title:"修改赠送积分值",
            height:300,
            width:400,
            url:"${ctx}/integral/toedit/?id="+id
        });
    }
    function batchOperate(batchOperateType,idArr){
        var operateTitle="";
        var url="";
        if(batchOperateType=="delete"){
            operateTitle="批量删除";
            url="${ctx}/system/admin/delete";
        }else if(batchOperateType=="disable"){
            operateTitle="批量禁用";
            url="${ctx}/system/admin/disable";
        }else if(batchOperateType=="enable"){
            operateTitle="批量启用";
            url="${ctx}/system/admin/enable";
        }
        ajaxSubmit(url,{ids:idArr.join(",")},reload,operateTitle+"成功","确认"+operateTitle+"？");
    }
    function editAdmin(id){
        openDialog({
            frame:true,
            title:"修改账号",
            height:300,
            width:400,
            url:"${ctx}/system/admin/edit/"+id
        });
    }

    function editAdminRole(id){
        openDialog({
            frame:true,
            title:"编辑用户角色",
            height:380,
            width:600,
            url:"${ctx}/system/admin/roleEdit/"+id
        });
    }


    $(function(){
        /*全选 取消全选*/
        bindCheckAll();
        $("#createAdminBtn").on("click",function(){
            openDialog({
                frame:true,
                title:"添加账号",
                height:300,
                width:400,
                url:"${ctx}/system/admin/create"
            });
        });
        $("#batchOperateBtn").click(function(){
            var batchOperateType=$("#batchOperateType").val();
            var check_name = document.getElementsByName("list-checkbox");
            var idArr=new Array();
            for(var i=0;i<check_name.length;i++){
                if(check_name[i].checked){
                    idArr.push(check_name[i].value);
                }
            }
            if(batchOperateType!="" && idArr.length>0){
                batchOperate(batchOperateType,idArr);
            }
        });
    });
</script>
<script>
    var addinfo = function(){
        openDialog({
            frame:true,
            title:"添加资讯信息",
            height:1000,
            width:600,
            url:"${ctx}/information/informationToAdd"
        });
    }
    var updateInfo = function(infoid){
        openDialog({
            frame:true,
            title:"修改资讯信息",
            height:1000,
            width:600,
            url:"${ctx}/information/informationToUpdate?id="+infoid
        });
    }
    var viewInfo = function(infoid){

    }
</script>
</body>
</html>
