<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 3월 8일 EdenDEV All rights reserved.          │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--
Date Created : 2023-03-08
Code Author : EdenDev
Creation Time : 오전 2:56
Purpose : Web Write Posting View Page
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
                <form:form action="${path}/board/writePostingProcess" modelAttribute="boardVo" id="writeForm"
                           method="post">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">게시글 작성</h3>
                        </div>
                    </div>
                    <div class="box-body">

                        <div class="row mt-1">
                            <div class="col-lg-5">
                                <label for="categoryList">카테고리</label>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-lg-5">
                                <select name="category_no" class="form-control" id="categoryList">
                                    <c:forEach items="${list}" var="category">
                                        <c:choose>
                                            <c:when test="${category_no == category.category_no}">
                                                <option value="${category.category_no}" selected>
                                                        ${category.category_name}
                                                </option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${category.category_no}">
                                                        ${category.category_name}
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-lg-5">
                                <label for="board_title">게시글 제목</label>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-lg-5">
                                <form:input type="text" class="form-control" path="board_title" id="board_title"
                                            placeholder="제목을 입력해주세요"/>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col my-auto"><form:errors path="board_title" id="error_message"/></div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-lg-5">
                                <label for="board_content">게시글 내용</label>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-lg-5">
                                <form:textarea path="board_content" class="form-control" id="board_content" rows="30"
                                               placeholder="내용을 입력해주세요" style="resize: none;"/>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col my-auto"><form:errors path="board_content" id="error_message"/></div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-lg-5">
                                <label for="write">게시글 작성자</label>
                            </div>
                        </div>

                        <div class="row-mt-1">
                            <div class="col-lg-5">
                                <input type="text" id="write" class="form-control" value="${sessionUser.user_nickname}"
                                       disabled>
                            </div>
                        </div>
                    </div>

                    <div class="box-footer">
                        <div class="pull-right">
                            <a href="#" onclick="postingList(${data.category_no});" class="btn btn-primary"><i
                                    class="fa fa-list"></i> 목록</a>
                            <button type="reset" class="btn btn-warning"><i class="fa fa-reply"></i> 초기화</button>
                            <button type="button" id="writePostingButton" class="btn btn-success"><i
                                    class="fa fa-save"></i> 저장
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </section>
    </div>

    <%@ include file="../include/footer.jsp" %>

</div>

<%@ include file="../include/plugin_js.jsp" %>
</body>
</html>