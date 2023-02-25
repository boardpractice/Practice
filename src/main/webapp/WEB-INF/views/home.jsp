<%--
┌──────────────────────────────────────────────────────────────────┐
│ Copyright (c) 2023년 2월 22일 EdenDev All rights reserved.        │
└──────────────────────────────────────────────────────────────────┘
--%>

<%--
Date Created : 2023-02-22
Code Author : Eden
Creation Time : 오후 12:52
Purpose : plugin java script include  jsp file
--%>

<!DOCTYPE html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="include/head.jsp" %>
<%@ include file="include/user_menu.jsp" %>

<html>

<body class="hold-transition skin-green-light sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <%@ include file="include/top_menu.jsp" %>

    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="include/left_menu.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                메인페이지
            </h1>
            <ol class="breadcrumb">
                <li><a href="${path}/"><i class="fa fa-dashboard"></i> home</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <p>메인페이지입니다....</p>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <%@ include file="include/footer.jsp" %>

</div>
<!-- ./wrapper -->

<%@ include file="include/plugin_js.jsp" %>
<script type="text/javascript">
    let url = location.pathname;
    $(document).ready(function () {
        if (url.includes('login')) {
            alert("현재 페이지는 로그인을 해야만 로그인 하실 수 있습니다.");
            showLoginModal();
        }
    });
</script>

</body>
</html>