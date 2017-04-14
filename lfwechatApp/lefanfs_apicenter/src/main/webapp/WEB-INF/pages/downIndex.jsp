<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%
    String defaultApiServer = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<c:set var="ctx" value="${defaultApiServer}"/>
<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        body {
            margin: 0px;padding: 0px;
        }
    </style>
</head>
<body>
<img src="${ctx}/image/downloadBackage.png">
<a href="${ctx}/d/android">
    <img src="${ctx}/image/androiddown.png" style="position: absolute;
    bottom: 45px;
    left: 200px;">
</a>
<a href="${ctx}/d/ios">
    <img src="${ctx}/image/appstoredown.png" style="position: absolute;
    bottom: -170px;
    left: 200px;">
</a>
</body>
</html>