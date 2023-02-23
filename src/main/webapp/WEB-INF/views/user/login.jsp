<%--
┌───────────────────────────────────────────────────────────────────┐
│ Copyright (c) 2023년 2월 23일 EdenDEV All rights reserved.         │
└───────────────────────────────────────────────────────────────────┘
--%>

<%--
Date Created : 2023-02-23
Code Author : EdenDev
Creation Time : 오후 8:30
Purpose : Web User Login View Page
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../include/head.jsp" %>
<html>
<body class="hold-transition skin-green-light sidebar-mini" oncopy="return false" oncut="return false"
      onpaste="return false">
<div class="wrapper">

    <%@ include file="../include/top_menu.jsp" %>

    <%@ include file="../include/left_menu.jsp" %>

    <div class="content-wrapper">

    </div>

    <%@ include file="../include/footer.jsp" %>

</div>

<div class="modal fade" id="userLoginModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">로그인 페이지</h4>
            </div>
            <div class="modal-body" data-rno>
                <div class="row mt-1">
                    <div class="col">
                        <div class="row mt-1">
                            <div class="col">
                                <label for="inputId">아이디</label>
                            </div>
                        </div>
                        <div class="row mt-1">
                            <div class="col">
                                <input type="text" class="form-control" id="inputId" name="user_id"
                                       placeholder="아이디를 입력해주세요">
                            </div>
                        </div>
                        <div class="row mt-1">
                            <div class="col">
                                <div class="col">
                                    <input type="password" class="form-control" id="inputPw" name="user_pw"
                                           placeholder="비밀번호를 입력해주세요">
                                </div>
                            </div>
                        </div>
                        <div class="row mt-1">
                            <div class="col">
                                <input type="checkbox" class="form-check-input" id="saveIdBox">ID저장
                                <input type="checkbox" class="form-check-input" id="userCookie"> 자동로그인
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="row mt-1">
                    <div class="col">
                        <button type="button" class="btn btn-danger  btn-xs pull-left" data-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-primary btn-xs pull-right infoModBtn" id="loginButton"><i
                                class="glyphicon glyphicon-log-in"></i>로그인
                        </button>
                    </div>
                </div>
                <div class="row mt-1">
                    <div class="col pull-right">
                        <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                           data-target="#userIdFindModel"><b>아이디 찾기</b></a>
                        <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                           data-target="#userPwFindModel"><b>비밀번호 찾기</b></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../include/plugin_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#userLoginModal").modal('show');
    });
</script>
</body>
</html>

