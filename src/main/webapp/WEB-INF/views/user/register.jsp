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
                <form:form action="${path}/user/insertUserProcess" modelAttribute="userVo" id="insertForm" method="post">
                    <div class="row mt-2">
                        <div class="col">
                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="joinIdInput">아이디</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="text" id="joinIdInput" class="form-control" path="user_id"
                                                placeholder="아이디를 입력하세요"/>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5" id="alertId"><form:errors path="user_id" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="changePassword">비밀번호</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="password" id="changePassword" class="form-control"
                                                placeholder="비밀번호를 입력하세요" path="user_pw"/>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5" id="alterPassword"><form:errors path="user_pw" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="confirmPassword">비밀번호 확인</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <input type="password" id="confirmPassword" class="form-control"
                                           placeholder="비밀번호를 한번 더 입력해주세요">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5" id="alterPassword2"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="userNickName">닉네임</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="text" id="userNickName" class="form-control" path="user_nickname"
                                                placeholder="닉네임을 입력해주세요"/>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5" id="alertNickName"><form:errors path="user_nickname" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="userGender">성별</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:radiobutton id="userGender" path="user_gender" value="M"/>남
                                    <form:radiobutton id="userGender" path="user_gender" value="W"/>여
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5" id="alertGender"><form:errors path="user_gender" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="userBirth">생년월일</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="date" id="userBirth" class="form-control" path="user_birth"
                                                placeholder="생년월일을 선택해주세요"/>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5" id="alertBirth"><form:errors path="user_birth"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="userPhone">휴대폰번호</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="text" id="userPhone" class="form-control" path="user_phone"
                                                placeholder="휴대폰번호를 입력해주세요"/>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5" id="alertPhone"><form:errors path="user_phone" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="userEmail">이메일</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="text" id="userEmail" class="form-control" path="user_email"
                                                placeholder="이메일주소를 입력해주세요"/>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col d-grid">
                                    <button type="button" id="checkEmailButton"
                                            class="btn btn-primary" style="height:36px;" disabled='disabled'>인증번호 발송
                                    </button>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5" id="alertEmail"><form:errors path="user_email" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col bi bi-exclamation-square-fill deepblue">
                                    인증번호 발송은 서버 상황에따라 5초에서 10초정도 시간이 걸릴 수 있습니다.
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <input class="form-control" id="checkEmail" type="text"
                                           placeholder="인증번호를 입력해주세요." aria-label="default input example"
                                           disabled="disabled">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5" id="alertCertified"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <a type="button" class="btn btn-default pull-left" href="../main/main">취소</a>
                                    <button type="button" class="btn btn-primary infoModBtn pull-right" id="joinButton">가입하기
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="user_image" name="user_image" value="default-user-image.jpg">
                </form:form>
            </div>
        </section>
    </div>

    <%@ include file="../include/footer.jsp" %>

</div>

<%@ include file="../include/plugin_js.jsp" %>
</body>
</html>