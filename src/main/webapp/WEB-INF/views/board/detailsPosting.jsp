<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 3월 9일 EdenDEV All rights reserved.          │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--
Date Created : 2023-03-09
Code Author : EdenDev
Creation Time : 오후 6:14
Purpose : Web Details Posting View Page
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

            <div class="col-lg-12">


                <%--게시글 내용 영역--%>
                <div class="box box-primary">

                    <%--게시글 제목 영역--%>
                    <div class="box-header with-border">
                        <h3 class="box-title">글제목 : ${data.boardVo.board_title}</h3>
                        <ul class="list-inline pull-right">
                            <li><span><i class="fa fa-eye"></i>조회수
                                (${data.boardVo.board_view_count})</span></li>
                        </ul>
                    </div>

                    <%--게시글 내용 영역--%>
                    <div class="box-body" style="height: 700px">
                        ${fn:replace(fn:replace(fn:escapeXml(data.boardVo.board_content), newLine, "<br/>") , " ", "&nbsp;")}
                    </div>

                    <%--작성자 정보 영역--%>
                    <div class="box-footer">
                        <div class="user-block">
                            <ul class="navbar-custom-menu">
                                <li class="dropdown messages-menu">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <img class="img-circle img-bordered-sm"
                                             src="${path}/resources/dist/img/profile/${data.userVo.user_image}" alt="user image">
                                        <span>${data.userVo.user_nickname}</span>
                                        <span class="description"><fmt:formatDate pattern="yyyy-MM-dd a HH:mm"
                                                                                  value="${data.boardVo.board_write_date}"/></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">작성 글보기</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="box-footer">
                        <form role="form" method="post">
                            <input type="hidden" id="boardNo" name="board_no" value="${data.boardVo.board_no}">
                            <input type="hidden" id="userNo" name="user_no" value="${sessionUser.user_no}">
                            <input type="hidden" id="categoryNo" name="category_no" value="${data.boardVo.category_no}">
                        </form>
                        <button class="btn btn-primary" onclick="postingList(${data.boardVo.category_no})"><i
                                class="fa fa-list"></i> 목록
                        </button>
                        <c:if test="${sessionUser.user_no == data.boardVo.user_no}">
                            <div class="pull-right">
                                <button type="button" class="btn btn-warning modBtn"><i class="fa fa-edit"></i> 수정
                                </button>
                                <button type="button" class="btn btn-danger delBtn"><i class="fa fa-trash"></i> 삭제
                                </button>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <%@ include file="../include/footer.jsp" %>

</div>

<%@ include file="../include/plugin_js.jsp" %>
</body>
</html>