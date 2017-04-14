<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>后台管理员</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
	<div class="main-top">    
            <h3>后台管理员 <small>共<span>${apiRsp.count}</span>个</small></h3>              
    </div><!--main-top-->

	<div class="panel panel-info">
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
          <table class="table table-hover">
            <thead>
              <tr>
                <th class="th-checkbox"><input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选"></th>
                <th class="th-name">姓名</th>
                <th>登录账号</th>
                <th width="200">创建时间</th>
                <th width="200">最后登录时间</th>
                <th width="200">登录IP</th>
                <th width="100">登录次数</th>
                <th width="50">状态</th>
              </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
              <tr>
                <td><input type="checkbox" name="list-checkbox" value="${item.id}"></td>
                <td><strong>${item.realname}</strong>
                	<span class="row-actions"><a href="javascript:editAdmin('${item.id}');"><span class="glyphicon glyphicon-edit"></span>编辑</a></span>
                	<span class="row-actions"><a href="javascript:editAdminRole('${item.id}');"><span class="glyphicon glyphicon-user"></span>角色设置</a></span>
                </td>
                <td>${item.username}</td>
                <td><fmt:formatDate value="${item.dateJoined}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${item.lastLogin}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>${item.lastLoginIp}</td>
                <td>${item.loginCount}</td>
                <td>
                	<c:choose>
                	<c:when test="${item.status eq 'activity'}">
                		<span class="glyphicon glyphicon-ok-sign text-success" title="启用"></span>
                	</c:when>
                	<c:otherwise>
                		<span class="glyphicon glyphicon-remove-sign text-danger" title="禁用"></span>
                	</c:otherwise>
                	</c:choose>
                </td>
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
