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
                            <li><a href="#" class="link-black text-lg" id="likeCount">좋아요 수(${data.totalLikeCount})</a>
                            </li>
                            <li><a href="#" class="link-black text-lg"><i class="fa fa-eye"></i>조회수
                                (${data.boardVo.board_view_count})</a></li>
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
                                             src="${path}/resources/dist/img/profile/${data.userVo.user_image}"
                                             alt="user image">
                                        <span>${data.userVo.user_nickname}</span>
                                        <span class="description"><fmt:formatDate pattern="yyyy-MM-dd a HH:mm"
                                                                                  value="${data.boardVo.board_write_date}"/></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">작성 글보기</a></li>
                                        <li><a href="#">작성 댓글보기</a></li>
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
                        <c:if test="${!empty sessionUser}">
                            <button type="button" class="btn btn-info text-lg-center" id="boardLike"> 좋아요</button>
                            <button type="button" class="btn btn-info text-lg-center" id="postingBookMark"> 북마크</button>
                        </c:if>
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

                <%--댓글 입력 영역--%>
                <c:if test="${!empty sessionUser}">
                    <div class="box box-warning">
                        <div class="box-header with-border">
                            <a class="link-black text-lg"><i class="fa fa-pencil"></i> 댓글작성</a>
                        </div>
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group margin-bottom-none">
                                    <div class="col-sm-10">
                                        <textarea class="form-control" id="newCommentText" rows="3"
                                                  placeholder="댓글을 입력해주세요..." style="resize: none"></textarea>
                                    </div>
                                    <div class="col-sm-2">
                                        <input class="form-control" id="newCommentWriter" type="text"
                                               value="${sessionUser.user_nickname}"
                                               readonly="readonly">
                                    </div>
                                    <hr/>
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-primary btn-block commentAddBtn"><i
                                                class="fa fa-save"></i> 저장
                                        </button>
                                    </div>
                                    <!-- 댓글 작성자 정보 -->
                                    <input type="hidden" id="writer" value="${data.userVo.user_id}">
                                    <input type="hidden" id="commentWriter" value="${sessionUser.user_id}">
                                    <input type="hidden" id="title" value="${data.boardVo.board_title}">
                                </div>
                            </form>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty sessionUser}">
                    <div class="box box-warning">
                        <div class="box-header with-border">
                            <p><i class="fa fa-pencil"></i> 댓글 작성을 위해 <a href="${path}/user/login"
                                                                         class="link-black text-lg">로그인</a>해주세요</p>
                        </div>
                    </div>
                </c:if>


                <%--댓글 목록 영역--%>
                <div class="box box-success collapsed-box">
                    <%--댓글 유무 / 댓글 갯수 / 댓글 펼치기, 접기--%>
                    <div class="box-header with-border">
                        <a href="" class="link-black text-lg"><i class="fa fa-comments-o margin-r-5 commentCount"></i>
                        </a>
                        <div class="box-tools">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse">
                                <i class="fa fa-plus"></i>
                            </button>
                        </div>
                    </div>
                    <%--댓글 목록--%>
                    <div class="box-body commentDiv">
                        <c:forEach items="${dataList}" var="comment">
                            <div class="user-block">
                                <img class="img-circle img-bordered-sm"
                                     src="/resources/dist/img/profile/${comment.userVo.user_image}">
                                <span class="username">
                                    <a href="#" id="comment_write">${comment.userVo.user_nickname}</a>
                                </span>
                                <c:if test="${!empty sessionUser && sessionUser.user_no == comment.userVo.user_no}">
                                    <a class="pull-right link-black text-sm"
                                       onclick="modifyComment(${comment.commentVo.comment_no}, ${comment.commentVo.user_no});"><i
                                            class="fa fa-edit">수정</i></a>
                                    <a class="pull-right link-black text-sm"
                                       onclick="deleteComment(${comment.commentVo.comment_no});"><i
                                            class="fa fa-times">삭제</i></a>
                                </c:if>

                                <span class="description"><fmt:formatDate pattern="yyyy-MM-dd a HH:mm"
                                                                          value="${comment.commentVo.comment_write_date}"/></span>
                            </div>
                            <div class="oldReplytext">${comment.commentVo.comment_content}</div>
                            <br>

                            <hr style="border : 0px; border-top: 5px #2e383c;"/>
                        </c:forEach>
                    </div>
                    <%--댓글 페이징--%>
                    <div class="box-footer">
                        <div class="text-center">
                            <ul class="pagination pagination-sm no-margin">

                            </ul>
                        </div>
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