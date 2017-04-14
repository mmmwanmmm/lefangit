<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
   <title>登录-后台控制中心</title>
   <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>

<div class="container">
  <form id="loginForm" class="form-signin" role="form" action="${ctx}/doLogin" method="post">
    <h2 class="form-signin-heading">管理员登录</h2> 
    <label><span class="label label-danger">${msg}</span></label>
    <input name="username" value="${username}" type="text" class="form-control" placeholder="登录账号" required autofocus>
    <input name="password" type="password" class="form-control" placeholder="登录密码" required style="margin-top:15px;">
    <!-- <div class="checkbox">
      <label>
        <input type="checkbox" value="remember-me"> 下次自动登录
      </label>
    </div> -->
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>    
  </form>
  
</div>

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
</body>
</html>