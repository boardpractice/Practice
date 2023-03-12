<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 3월 10일 EdenDEV All rights reserved.         │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--
Date Created : 2023-03-10
Code Author : EdenDev
Creation Time : 오전 11:05
Purpose : 
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

                <form name="detailsForm" role="form" method="post">
                    <input type="hidden" id="boardNo" name="board_no" value="">
                </form>

                <form:form id="modifyForm" method="post" action="${path}/board/modifyPostingProcess"
                           modelAttribute="boardVo">
                    <div class="box box-primary">
                        <div class="box box-header with-border">
                            <h3 class="box-title">게시글 수정</h3>
                        </div>

                        <div class="box-body">
                            <input type="hidden" id="updateBoardNo" name="board_no" value="${data.boardVo.board_no}">

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
                                                <c:when test="${data.boardVo.category_no == category.category_no}">
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
                                    <label for="title">제목</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input path="board_title" id="title" value="${data.boardVo.board_title}"/>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col my-auto"><form:errors path="board_title" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <label for="content">내용</label>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <textarea path="board_content" class="form-control" id="content" rows="30"
                                                   placeholder="내용을 입력해주세요"
                                              style="resize: none;" >${data.boardVo.board_content}</textarea>
                                </div>
                            </div>
                        </div>

                        <div class="box-footer">
                            <button type="button" class="btn btn-primary listBtn"><i class="fa fa-list"></i> 목록</button>
                            <div class="pull-right">
                                <button type="button" class="btn btn-warning cancelBtn"><i class="fa fa-trash"></i> 취소
                                </button>
                                <button type="button" class="btn btn-success crystalBtn"><i class="fa fa-save"></i> 수정
                                    저장
                                </button>
                            </div>
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