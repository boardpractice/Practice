<%--
  ~ ┌───────────────────────────────────────────────────────────────────┐
  ~ │ Copyright (c) 2023년 2월 22일 EdenDEV All rights reserved.         │
  ~ └───────────────────────────────────────────────────────────────────┘
  --%>

<%--
Date Created : 2023-02-22
Code Author : EdenDev
Creation Time : 오후 5:11
Purpose : Web Register View Page
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../include/head.jsp" %>

<html>
<body class="hold-transition skin-green-light sidebar-mini" oncontextmenu="return false" oncopy="return false"
      oncut="return false" onpaste="return false" onselect="return false">

<div class="wrapper">

    <%@ include file="../include/top_menu.jsp" %>

    <%@ include file="../include/left_menu.jsp" %>

    <div class="content-wrapper">
        <section class="content container-fluid">
            <div class="register-box-body">
                <p class="login-box-msg">회원가입 페이지</p>
                <form action="${path}/user/insertUserProcess" id="insertForm" method="post">
                    <div class="row mt-2">
                        <div class="row mt-2">
                            <div class="col-ls-5">
                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <label for="joinIdInput">아이디</label>
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <input type="text" id="joinIdInput" class="form-control" name="user_id"
                                               placeholder="아이디를 입력하세요요">
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5" id="alertId"></div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <label for="changePassword">비밀번호</label>
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <input type="password" id="changePassword" class="form-control" name="user_pw"
                                               placeholder="비밀번호를 입력하세요">
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <div class="col-ls-5" id="alterPassword"></div>
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <label for="confirmPassword">비밀번호 확인</label>
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <input type="password" id="confirmPassword" class="form-control" name="user_pw"
                                               placeholder="비밀번호를 한번 더 입력해주세요">
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5" id="alertPassword2"></div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <label for="userNickName">닉네임</label>
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <input type="text" id="userNickName" class="form-control" name="user_nickname"
                                           placeholder="닉네임을 입력해주세요">
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5" id="alertNickName"></div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <label for="userGender">성별</label>
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <input type="radio" id="userGender" name="user_gender" value="M">남
                                        <input type="radio" id="userGender" name="user_gender" value="Y">여
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5" id="alertGender"></div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <label for="userBirth">생년월일</label>
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <input type="date" id="userBirth" class="form-control" name="user_birth"
                                               placeholder="생년월일을 선택해주세요">
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5" id="alertBirth"></div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <label for="userPhone">휴대폰번호</label>
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <input type="text" id="userPhone" class="form-control" name="user_phone"
                                               placeholder="휴대폰번호를 입력해주세요">
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5" id="alertPhone"></div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <label for="userEmail">이메일</label>
                                    </div>
                                </div>
                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <input type="text" id="userEmail" class="form-control" name="user_email"
                                               placeholder="이메일주소를 입력해주세요">
                                    </div>
                                    <div class="col d-grid">
                                        <button type="button" id="checkEmailButton"
                                                class="btn btn-primary" style="height:36px;" disabled='disabled'>인증번호 발송
                                        </button>
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col" id="alertEmail"></div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col bi bi-exclamation-square-fill deepblue">
                                        인증번호 발송은 서버 상황에따라 5초에서 10초정도 시간이 걸릴 수 있습니다.
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <input class="form-control" id="checkEmail" type="text"
                                               placeholder="인증번호를 입력해주세요." aria-label="default input example"
                                               disabled="disabled">
                                    </div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5" id="alertCertified"></div>
                                </div>

                                <div class="row mt-1">
                                    <div class="col-ls-5">
                                        <a type="button" class="btn btn-default pull-left" href="../main/main">취소</a>
                                        <button type="button" class="btn btn-primary infoModBtn pull-right" id="joinButton">가입하기
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>

    <%@ include file="../include/footer.jsp" %>

</div>

<%@ include file="../include/plugin_js.jsp" %>
<script type="text/javascript">
    $("#joinButton").click(function () {
        $("#insertForm").submit();
    });
</script>
</body>
</html>