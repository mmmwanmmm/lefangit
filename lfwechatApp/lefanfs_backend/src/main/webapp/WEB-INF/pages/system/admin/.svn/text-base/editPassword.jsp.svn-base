<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>修改密码</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>

<div class="main change-password">



	<div class="main-top">    
            <h3>修改密码</h3>    
    </div><!--main-top-->
    

          <div class="panel panel-info">
            <div class="panel-heading">* 密码长度6~14位，区分大小写</div>
            <div class="panel-body">
              
              
          <form id="editForm" action="${ctx}/system/admin/updatePassword" method="post" class="form-horizontal" role="form">
            <div class="form-group">
              <label for="old_password" class="col-sm-2 control-label"><strong class="necessary">*</strong>旧密码</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="old_password" name="old_password" required="required">
              </div>
            </div>          
            <div class="form-group">
              <label for="new_password" class="col-sm-2 control-label"><strong class="necessary">*</strong>新密码</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="new_password" name="new_password" required="required">
              </div>
            </div>
            <div class="form-group">
              <label for="confirm_new_password" class="col-sm-2 control-label"><strong class="necessary">*</strong>确认新密码</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="confirm_new_password" name="confirm_new_password" required="required">
              </div>
            </div>
          
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-success loading-btn">提交修改</button>
              </div>
            </div>
          </form>
          
          
          
          <!-- <a id="message_trigger_ok" href="#">成功</a> <a id="message_trigger_err" href="#">失败</a> -->
          
          
          
            </div>
          </div><!--panel-info end-->





</div><!--main end-->
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript">
function logout(){
	parent.location.href="${ctx}/logout";
}
$(function(){
	$("#editForm").bind('submit', function(event) {
		ajaxFormSubmit(this,logout,"修改成功，请重新登录！",null);
		/* $(this).ajaxForm({
			success:function(event,param){
				var apiRsp=getApiJson(param.data);
				if(apiRsp){
					if(apiRsp.isSuccess){
						alert("修改成功，请重新登录！");
						parent.location.href="${ctx}/logout";
					}else{
						alert(apiRsp.msg);
					}
				}else{
					alert("返回异常");
				}
			}
		}); */
		event.preventDefault();
	});	
});
</script>
</body>
</html>