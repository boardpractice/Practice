<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 3월 7일 EdenDEV All rights reserved.          │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--
Date Created : 2023-03-07
Code Author : EdenDev
Creation Time : 오후 3:12
Purpose : Web Posting List View Page
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../include/head.jsp" %>
<%@ include file="../include/user_menu.jsp" %>

<html>
<body class="hold-transition skin-green-light sidebar-mini" oncopy="return false" oncut="return false"
      onpaste="return false">
<div class="wrapper">

    <%@ include file="../include/top_menu.jsp" %>

    <%@ include file="../include/left_menu.jsp" %>

    <div class="content-wrapper">
        <section class="content container-fluid">
            <div class="box-header with-border">

            </div>
            <div class="box-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th class="col-xs-1">글 번호</th>
                        <th class="col-xs-1">카테고리</th>
                        <th class="col-xs-2">제목</th>
                        <th class="col-xs-2">작성자</th>
                        <th class="col-xs-2">작성일</th>
                        <th class="col-xs-1">조회수</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${dataList }" var="data">
                        <tr>
                            <td>${data.boardVo.board_no}</td>
                            <td>${data.categoryVo.category_name}</td>
                            <td><a
                                    href="javascript:goPage(${data.boardVo.board_no});">${data.boardVo.board_title }</a>
                                <span class="badge bg-teal"><i class="fa fa-comment-o"></i> + ${data.totalCommentCount}</span>
                            </td>
                            <td>${data.userVo.user_nickname}</td>
                            <td><fmt:formatDate value="${data.boardVo.board_write_date }" pattern="yyyy:MM:dd: HH:mm:ss" /></td>
                            <td><span class="badge bg-red">${data.boardVo.board_view_count}</span></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <a class="btn btn-success btn-flat" href="javascript:writePage(${category_no});">
                        <i class="fa fa-pencil"></i> 글쓰기
                    </a>
                </div>
            </div>
        </section>
    </div>

    <%@ include file="../include/footer.jsp" %>

</div>

<%@ include file="../include/plugin_js.jsp" %>
</body>
</html>