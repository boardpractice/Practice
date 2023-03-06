<%--
┌───────────────────────────────────────────────────────────────────┐
│ Copyright (c) 2023-2023년 2월 22일 EdenDEV All rights reserved.    │
└───────────────────────────────────────────────────────────────────┘
--%>

<%--
Date Created : 2023-02-22
Code Author : Eden
Creation Time : 오후 12:50
Purpose :  head tag include jsp file
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--head.jsp--%>
<head>
    <link type="image/png" sizes="96x96" rel="icon" href="/resources/dist/img/favicon.png">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SpringMVC Examples</title>
    <%-- Tell the browser to be responsive to screen width --%>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/resources/bower_components/bootstrap/dist/css/bootstrap.css">
    <%-- Font Awesome --%>
    <link rel="stylesheet" href="/resources/bower_components/font-awesome/css/font-awesome.min.css">
    <%-- Ionicons --%>
    <link rel="stylesheet" href="/resources/bower_components/Ionicons/css/ionicons.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/resources/plugins/iCheck/square/blue.css">
    <%-- Theme style --%>
    <link rel="stylesheet" href="/resources/dist/css/AdminLTE.css">
    <%-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. --%>
    <link rel="stylesheet" href="/resources/dist/css/skins/_all-skins.min.css">

    <%--fileupload--%>
    <link rel="stylesheet" media="screen" href="/resources/bower_components/fileupload/css/jasny-bootstrap.min.css">
    <%--lightbox--%>
    <link rel="stylesheet" href="/resources/bower_components/lightbox/css/lightbox.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="/resources/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">

    <%-- my css --%>
    <link rel="stylesheet" href="/resources/dist/css/frameCss.css">
    <link rel="stylesheet" href="/resources/dist/css/mainBody.css">

    <%-- Google Font --%>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

    <!-- fontawesome -->
    <script src="https://kit.fontawesome.com/576b673818.js" crossorigin="anonymous"></script>

    <!-- JQuery Java Script -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>


<div id="loading">
    <div id="loading_bar">
        <!-- 로딩바의 경로를 img 태그안에 지정해준다. -->
        <img src="/resources/dist/img/loading.gif">
        <p style="font-size: x-large; font-weight: bold;">로딩 중 입니다 ...</p>
    </div>
</div>