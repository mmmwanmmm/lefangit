<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%
String defaultApiServer = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<c:set var="ctx" value="${defaultApiServer}"/>
<!DOCTYPE html>
<html>
<head>
    <title>使用帮助</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<body style="margin:0px;">
<div align="center" ><img src="<%=defaultApiServer %>/image/u2.png"></div>
    <div>
        <p align="center">
            本App的定位旨在填补市场空白，将运动、积分、保险、游戏多功能进行有机结合的应用类软件。
            用户可以通过记录监测用户的日常运动，邀请朋友参加运动，购买户外活动或户外装备，
            参与公益类运动项目等途径来获取积分，积分可兑换等值奖励，融入游戏类的成就、等级、
            竞赛等概念和UI 设计，来增强用户粘性。目标通过不断更新功能，带入游戏设计性的内容，
            打造平台型的运动类app软件
        </p>
    </div>
    <div align="center" ><p>北京润升昌盛投资有限公司</p></div>
</body>
</html>