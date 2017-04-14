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
        <!--
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form">
                    <div class="form-group">
                        <select id="batchOperateType" class="form-control">
                            <option value="">批量操作</option>
                            <option value="disable">禁用</option>
                            <option value="enable">启用</option>
                            <option value="delete">删除</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="button" class="btn btn-default">确认</button>
                    </div>
                </form>
                <button id="createAdminBtn" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>添加账号</button>
            </div>
        </div>
        -->
        <table class="table table-hover">
            <thead>
            <tr>
                <th class="th-checkbox"><input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选"></th>
                <th class="th-name">名称</th>
                <th>类型</th>
                <th width="200">赠送积分</th>
                <th width="200">描述</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${ruleList}" var="item">
                <tr>
                    <td><input type="checkbox" name="list-checkbox" value="${item.id}"></td>
                    <td><strong>${item.integralName}</strong></td>
                    <td><strong>${item.integralName}</strong></td>
                    <td>${item.integralValue}</td>
                    <td>${item.remark}</td>
                    <td><a href="javascript:void(0)" onclick="updateRule(${item.id})">修改</a></td>
                </tr>
            </c:forEach>
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
</body>
</html>
