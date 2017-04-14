<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>角色列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>

<div class="main administrator">

	<div class="main-top">    
            <h3>角色列表 <small>共<span>${apiRsp.count}</span>个</small></h3>              
    </div><!--main-top-->

<div class="panel panel-info">
  <div class="panel-heading">
  		<div class="pin">
          <form class="form-inline" role="form">
            <div class="form-group">
                  <select id="batchOperateType" class="form-control">
                            <option value="">批量操作</option>
                            <option value="delete">删除</option>
                  </select>
            </div>
                  <div class="btn-group">
                    <button id="batchOperateBtn" type="button" class="btn btn-default">确认</button>
                  </div>
          </form>
          <button id="createAdminBtn" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>添加角色</button>
        </div>
  </div>
          <table class="table table-hover">
            <thead>
              <tr>
                <th class="th-checkbox"><input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选"></th>
                <th class="th-name">角色名称</th>
                <th>角色描述</th>
                <th></th>
              </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
              <tr>
                <td><input type="checkbox" name="list-checkbox" value="${item.id}"></td>
                <td><strong>${item.roleName}</strong>
<%--                 <span class="row-actions"><a href="javascript:editRole('${item.id}');"><span class="glyphicon glyphicon-edit"></span>编辑</a></span>
 --%>                <span class="row-actions"><a href="javascript:editUserMenu('${item.id}');"><span class="glyphicon glyphicon-th-list"></span>编辑角色</a></span>
                </td>
                <td>${item.roleDesc}</td>
                <td></td>
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
function batchOperate(batchOperateType,idArr){
	var operateTitle="";
	var url="";
	if(batchOperateType=="delete"){
		operateTitle="批量删除";
		url="${ctx}/system/role/delete";
	}
	ajaxSubmit(url,{ids:idArr.join(",")},reload,operateTitle+"成功","确认"+operateTitle+"？");
}

function editRole(id){
	openDialog({
		frame:true,
		title:"编辑角色",
	    height:700,
	    width:700,
		url:"${ctx}/system/role/edit?roleId="+id
	});	
}

function editUserMenu(id){
	openDialog({
		frame:true,
		title:"编辑角色", 
	    height:700,
	    width:700,
		url:"${ctx}/system/role/edit?roleId="+id
	});	
}


$(function(){
	
	$("#createAdminBtn").on("click",function(){
		openDialog({
			frame:true,
			title:"添加角色",
		    height:700,
		    width:700,
			url:"${ctx}/system/role/edit"
		});
	});
	
	/*全选 取消全选*/	
	var check_btn = document.getElementById("check-btn");
	var check_name = document.getElementsByName("list-checkbox");
	check_btn.onclick = function(){
		for(var i=1; i<=check_name.length; i+=1){
			if(check_name[i-1].checked){
				check_name[i-1].checked = false;
			}else{
				check_name[i-1].checked = true;
			}
		}
	};
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
