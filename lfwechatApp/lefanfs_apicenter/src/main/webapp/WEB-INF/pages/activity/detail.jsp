<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>${(activity.productName)}</title>
    <style>
        .bodyContent{
            margin-left: 10px;
            margin-right: 10px;
        }
        .title{
            font-weight: 600;font-size: 21px;
            line-height: 25px;


            color: black;
            font-family: 黑体;
            margin-top: 10px;
            margin-bottom: 10px;
        }
        .headImg{
            height:180px;
        }
        .infomationDetail{
            font-size: 18px;
            margin-top: 15px;

        }
    </style>
</head>
<body style="margin:0px;">
<div>
    <div class="bodyContent headImg">
        <img style="width:100%;height:100%;" src="${(activity.productIcon)}">
    </div>
    <div class="bodyContent infomationDetail">${(activity.productDetail)}</div>
</div>
</body>
</html>