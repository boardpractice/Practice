<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 2월 24일 EdenDEV All rights reserved.         │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--
Date Created : 2023-02-24
Code Author : EdenDev
Creation Time : 오후 6:00
Purpose : User Profile Web View Page
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
            <div class="row">
                <div class="col-md-5">
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                            <img class="profile-user-img img-responsive img-circle"
                                 src="/resources/dist/img/profile/${sessionUser.user_image}" alt="User profile picture">

                            <h3 class="profile-username text-center">${sessionUser.user_nickname}</h3>
                            <div class="text-center">
                                <a class="btn btn-primary btn-xs" data-toggle="modal"
                                   data-target="#userPhotoModal">
                                    <i class="fa fa-photo"> 프로필사진 수정</i>
                                </a>
                            </div>
                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>아이디</b> <a class="pull-right">${sessionUser.user_id}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>이메일</b> <a class="pull-right">${sessionUser.user_email}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>가입일자</b>
                                    <a class="pull-right">
                                        <fmt:formatDate value="${sessionUser.user_join_date}"
                                                        pattern="yyyy-MM-dd a hh:mm"/>
                                    </a>
                                </li>
                                <li class="list-group-item">
                                    <b>최근 로그인 일자</b>
                                    <a class="pull-right">
                                        <fmt:formatDate value="${sessionUser.user_last_connection_date}"
                                                        pattern="yyyy-MM-dd a hh:mm"/>
                                    </a>
                                </li>
                                <li class="list-group-item">
                                    <b>생년월일</b>
                                    <a class="pull-right">
                                        <fmt:formatDate value="${sessionUser.user_birth}" pattern="yy년MM월dd일"/>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="box-footer text-center">
                            <a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#userInfoModal">
                                <i class="fa fa-info-circle"> 회원정보 수정</i>
                            </a>
                            <a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#userPwModal">
                                <i class="fa fa-question-circle"> 비밀번호 수정</i>
                            </a>
                            <a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#userOutModal">
                                <i class="fa fa-user-times"> 회원 탈퇴</i>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-md-7">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li><a href="#myBookmarks" data-toggle="tab"><i class="fa fa-bookmark-o"></i> 나의 스크랩</a>
                            </li>
                            <li><a href="#myPosts" data-toggle="tab"><i class="fa fa-pencil-square-o"></i>나의 게시글</a>
                            </li>
                            <li><a href="#myComment" data-toggle="tab"><i class="fa fa-comment-o"></i>나의 댓글</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane" id="myBookmarks">
                                <table id="myBookmarksTable" class="table table-bordered table-striped">
                                    <thead>
                                    <th>번호</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${dataList}" var="data">
                                        <tr>
                                            <td>${data.boardVo.board_no}</td>
                                            <td>
                                                <a href="javascript:goPage(${data.boardVo.board_no});">${data.boardVo.board_title}</a>
                                            </td>
                                            <td>${data.userVo.user_nickname}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                            <div class="tab-pane" id="myPosts">
                                <table id="myPostsTable" class="table table-bordered table-striped">
                                    <thead>
                                    <th>제목</th>
                                    <th>작성일시</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${postList}" var="post">
                                        <tr>
                                            <td>
                                                <a href="javascript:goPage(${post.boardVo.board_no});">${post.boardVo.board_title }</a>
                                            </td>
                                            <td><fmt:formatDate value="${post.boardVo.board_write_date }"
                                                                pattern="yyyy:MM:dd: HH:mm:ss"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                            <div class="tab-pane" id="myComment">
                                <table id="myCommentsTable" class="table table-bordered table-striped">
                                    <thead>
                                    <th>번호</th>
                                    <th>게시글제목</th>
                                    <th>댓글내용</th>
                                    <th>작성시간</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${commentList}" var="comment">
                                        <tr>
                                            <td>${comment.boardVo.board_no}</td>
                                            <td>${comment.boardVo.board_title}</td>
                                            <td>${comment.commentVo.comment_content}</td>
                                            <td><fmt:formatDate pattern="yyyy-MM-dd a HH:mm"
                                                                value="${comment.commentVo.comment_write_date}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
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

<div class="modal fade" id="userInfoModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">회원정보 수정</h4>
            </div>
            <div class="model-body" align="center">
                <form id="updateForm" action="../user/updateUserInfo">
                    <div class="row mt-2">
                        <div class="col">

                            <div class="row mt-1">
                                <div class="col">
                                    <label for="inputUserId">아이디</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col">
                                    <input type="text" id="inputUserId" class="form-control" name="user_id"
                                           value="${sessionUser.user_id}" disabled="disabled">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col">
                                    <label for="userNickName">닉네임</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col">
                                    <input type="text" id="userNickName" class="form-control" name="user_nickname"
                                           value="${sessionUser.user_nickname}">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col" id="alertNickName" style="font-size: 12px;"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col">
                                    <label for="userPhone">휴대폰번호</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col">
                                    <input type="text" id="userPhone" class="form-control" name="user_phone"
                                           value="${sessionUser.user_phone}">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col" id="alertPhone" style="font-size:12px"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-md-7">
                                    <input type="text" id="userEmail" class="form-control" name="user_email"
                                           value="${sessionUser.user_email}">
                                </div>
                                <div class="col">
                                    <button type="button" id="checkEmailButton"
                                            class="btn btn-primary pwModBtn" disabled='disabled'>인증번호 발송
                                    </button>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col" id="alertEmail" style="font-size:12px;"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col">
                                    <label for="checkEmail">인증번호</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col" id="alertCertified" style="font-size:12px;"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col">
                                    <input class="form-control" id="checkEmail" type="text"
                                           placeholder="인증번호를 입력해주세요." aria-label="default input example"
                                           disabled="disabled">
                                </div>
                            </div>

                            <div class="row mt-2">
                                <div class="col">
                                    <select class="form-select" name="question_no" id="userQuestion"
                                            aria-label="Default select example">
                                        <c:forEach items="${data }" var="question">
                                            <option value="${question.question_no }">
                                                    ${question.question_content }</option>
                                        </c:forEach>

                                    </select>
                                </div>
                            </div>

                            <div class="row mt-2">
                                <div class="col">
                                    <input type="text" id="userFindAnswer" class="form-control"
                                           value="${sessionUser.user_findAnswer}">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col" id="alertFindAnswer" style="font-size:12px;"></div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary infoModBtn" id="updateInfo" disabled="disabled">수정</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="userPwModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">비밀번호 변경</h4>
            </div>
            <div class="modal-body" data-rno>
                <div class="row mt-1">
                    <div class="col">
                        <div class="row mt-1">
                            <div class="col">
                                <input type="hidden" id="uid" value="${sessionUser.user_id}">
                                <label for="currentPassword">현재 비밀번호</label>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-md-7">
                                <input type="password" class="form-control"
                                       placeholder="현재 사용중인 패스워드를 입력해주세요" id="currentPassword">
                            </div>
                            <div class="col">
                                <button type="button" class="but btn-info pwModBtn" id="checkPassword">체크하기</button>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col" id="alertCurrentPassword" style="font-size:12px;"></div>
                        </div>

                        <div class="row mt-1">
                            <div class="col">
                                <label for="newPassword">새로운 비밀번호 :</label>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col">
                                <input type="password" class="form-control" placeholder="새로운 패스워드를 입력해주세요"
                                       id="newPassword" disabled="disabled">
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col" id="alertNewPassword"></div>
                        </div>

                        <div class="row mt-1">
                            <div class="col">
                                <label for="checkingNewPassword">비밀번호 확인 : </label>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col">
                                <input type="password" class="form-control"
                                       placeholder="새로운 패스워드를 한번 더 입력해주세요" id="checkingNewPassword"
                                       disabled="disabled">
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-md-7" id="alertCheckingPassword"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary pull-right pwModBtn" id="modifyPw" disabled="disabled">
                    저장
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="userOutModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">회원을 탈퇴하시겠습니까?</h4>
            </div>
            <div class="modal-body" data-rno>
                <div class="row mt-1">
                    <div class="col">
                        <div class="row mt-1">
                            <div class="col">
                                <label for="currentPassword2">현재 비밀번호</label>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-md-7">
                                <input type="password" class="form-control"
                                       placeholder="현재 사용중인 패스워드를 입력해주세요" id="currentPassword2">
                            </div>
                            <div class="col">
                                <button type="button" class="but btn-info pwModBtn" id="checkPassword2">체크하기</button>
                            </div>
                        </div>
                        <div class="row mt-1">
                            <div class="col" id="alertCurrentPassword2" style="font-size:12px;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary myInfoModModalBtn" id="deleteUser" disabled="disabled">탈퇴
                </button>
            </div>
        </div>
    </div>
</div>