<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>

<body>

<div class="home">


	<div class="list-box">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><strong>游戏</strong></h3>
            </div>
                <ul class="list-group">
                  <li class="list-group-item"><span class="label label-success">${gameMap.todayCount}</span>
今日新增</li>
                  <li class="list-group-item"><span class="label label-info">${gameMap.yestodayCount}</span>昨日新增</li>
                </ul>
          </div>
     </div>
     
     
	<div class="list-box">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><strong>软件</strong></h3>
            </div>
                <ul class="list-group">
                  <li class="list-group-item"><span class="label label-success">${applicationMap.todayCount}</span>
今日新增</li>
                  <li class="list-group-item"><span class="label label-info">${applicationMap.yestodayCount}</span>昨日新增</li>
                </ul>
          </div>
     </div>
     
     
	<div class="list-box">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><strong>广告</strong></h3>
            </div>
                <ul class="list-group">
                  <li class="list-group-item"><span class="label label-success">${advMap.todayCount}</span>
今日发布</li>
                  <li class="list-group-item"><span class="label label-danger">${advMap.willExpireCount}</span>即将到期</li>
                </ul>
          </div>
     </div>
     
     
	<div class="list-box">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><strong>礼包</strong></h3>
            </div>
                <ul class="list-group">
                  <li class="list-group-item"><span class="label label-success">${giftbagMap.todayCount}</span>
今日发布</li>
                  <li class="list-group-item"><span class="label label-danger">${giftbagMap.willExpireCount}</span>即将到期</li>
                </ul>
          </div>
     </div>
     
     
	<div class="list-box">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><strong>评论</strong></h3>
            </div>
                <ul class="list-group">
                  <li class="list-group-item"><span class="label label-success">${commentMap.todayCount}</span>
今日评论</li>
                  <li class="list-group-item"><span class="label label-danger">${commentMap.reportCount}</span>举报</li>
                </ul>
          </div>
     </div>
     
     
	<div class="list-box">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><strong>反馈</strong></h3>
            </div>
                <ul class="list-group">
                  <li class="list-group-item" title="今日反馈：安卓客户端"><span class="label label-success">${feedbackMap.clientappCount}</span>
安卓客户端</li>
                  <li class="list-group-item" title="今日反馈：应用"><span class="label label-success">${feedbackMap.applicationCount}</span>
应用</li>
                </ul>
          </div>
     </div>
     
     
	<div class="list-box">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><strong>用户</strong></h3>
            </div>
                <ul class="list-group">
                  <li class="list-group-item"><span class="label label-success">${userMap.todayCount}</span>
今日注册</li>
                  <li class="list-group-item"><span class="label label-info">${userMap.yestodayCount}</span>
昨日注册</li>
                </ul>
          </div>
     </div>
     
	<div class="list-box">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><strong>下载量</strong></h3>
            </div>
                <ul class="list-group">
                  <li class="list-group-item"><span class="label label-success">${downloadMap.todayCount}</span>
今日下载</li>
                  <li class="list-group-item"><span class="label label-info">${downloadMap.yestodayCount}</span>
昨日下载</li>
                </ul>
          </div>
     </div>
     
</div><!--home end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
</body>
</html>
