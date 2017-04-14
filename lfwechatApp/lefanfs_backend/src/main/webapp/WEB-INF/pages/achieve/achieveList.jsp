<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>成就管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>成就管理</h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <table class="table table-hover">
            <thead>
            <tr>
                <th class="th-checkbox"><input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选"></th>
                <th class="th-name">名称</th>
                <th>描述</th>
                <th width="200">达到的数值</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${achieveList}" var="item">
                <tr>
                    <td><input type="checkbox" name="list-checkbox" value="${item.id}"></td>
                    <td><strong>${item.achieveName}</strong></td>
                    <td><strong>${item.description}</strong></td>
                    <td>${item.achieveKpiValue}</td>
                    <td><a href="javascript:void(0)" onclick="updateAchieve(${item.id})">修改</a></td>
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
    function updateAchieve(id){
        openDialog({
            frame:true,
            title:"修改成就信息",
            height:300,
            width:400,
            url:"${ctx}/achieve/toupdateachieve/?id="+id
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
