<%--
┌──────────────────────────────────────────────────────────────────┐
│ Copyright (c) 2023년 2월 20일 EdenDev All rights reserved.        │
└──────────────────────────────────────────────────────────────────┘
--%>

<%--
Date Created : 2023-02-22
Code Author : Eden
Creation Time : 오후 12:50
Purpose : left menu bar include jsp file
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--left_column.jsp--%>
<%-- Left side column. contains the logo and sidebar --%>
<aside class="main-sidebar">

    <%-- sidebar: style can be found in sidebar.less --%>
    <section class="sidebar">

        <div class="user-panel">
            <c:choose>
                <c:when test="${empty sessionUser}">
                    <div class="pull-left image">
                        <img src="../dist/img/default-user-image.jpg" class="img-circle" alt="User Image">
                    </div>

                    <div class="pull-left info">
                        <p>Guest</p>
                        <a href="#"><i class="fa fa-circle text-danger"></i>OFFLINE</a>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="pull-left image">
                        <img src="/dist/img/profile/${sessionUser.user_image}" class="img-circle" alt="User Image">
                    </div>

                    <div class="pull-left info">
                        <p>${sessionUser.user_nickname}</p>

                        <a href="#"><i class="fa fa-circle text-success"></i>ONLINE</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <%-- Sidebar Menu --%>
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">메뉴</li>
            <%-- Optionally, you can add icons to the links --%>
            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-clipboard"></i>
                    <span>게시판</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa-solid fa-user"></i>
                    <span>계정관리</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                </ul>
            </li>
        </ul>
        <%-- /.sidebar-menu --%>
    </section>
    <%-- /.sidebar --%>
</aside>