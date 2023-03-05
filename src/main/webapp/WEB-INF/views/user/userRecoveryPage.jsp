<%--
  ~ ┌───────────────────────────────────────────────────────────────────┐
  ~ │ Copyright (c) 2023년 3월 5일 EdenDEV All rights reserved.         │
  ~ └───────────────────────────────────────────────────────────────────┘
  --%>

<%--
Date Created : 2023-03-05
Code Author : EdenDev
Creation Time : 오전 10:40
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
            <div class="row mt-3" id="submitOptionBox"> <!-- 옵션박스 -->
                <div class="col">
                    <div class="row mt-3">
                        <div class="col"><label for="userId">아이디</label></div>
                    </div>
                </div>

                <div class="row mt-1">
                    <div class="col-md-4">
                        <input class="form-control" id="userId" type="text" placeholder="아이디를 입력해 주세요." aria-label="default input example">
                    </div>
                    <div class="col-md-2"><button class="btn btn-info btn-sm" id="checkingId">아이디 체크</button></div>
                    <div class="col" id="checkId"></div>
                </div>

                <div class="row mt-3">
                    <div class="col">
                        <label for="userNick">
                            닉네임
                        </label>
                    </div>
                </div>

                <div class="row mt-1">
                    <div class="col-md-4">
                        <input class="form-control" id="userNick"  type="text" placeholder="닉네임을 입력해 주세요." aria-label="default input example" disabled="disabled">
                    </div>
                    <div class="col-md-2"><button class="btn btn-info btn-sm" id="checkingNickName" disabled="disabled">닉네임 체크</button></div>
                    <div class="col" id="checkNickName"></div>
                </div>

                <div class="row mt-3">
                    <div class="col">
                        <label for="userpw">비밀번호</label>
                    </div>
                </div>

                <div class="row mt-1">
                    <div class="col-md-4">
                        <input class="form-control" id="userpw"  type="password" placeholder="비밀번호를 입력해 주세요." aria-label="default input example" disabled="disabled">
                    </div>
                    <div class="col-md-2"><button class="btn btn-info btn-sm" id="checkingPw" disabled="disabled">비밀번호 체크</button></div>
                    <div class="col" id="checkPassword"></div>
                </div>

                <div class="row mt-3">
                    <div class="col">
                        <label for="Email">이메일주소</label>
                    </div>
                </div>

                <div class="row mt-1">
                    <div class="col-md-4">
                        <input type="text" id="Email" class="form-control" placeholder="이메일을 입력해주세요" disabled="disabled">
                    </div>
                    <div class="col-md-2">
                        <button type="button" id="checkButton"
                                class="btn btn-info btn-sm" disabled="disabled">인증번호 발송
                        </button>
                    </div>
                    <div class="col" id="checkEmail"></div>
                </div>

                <div class="row mt-3">
                    <div class="col">
                        <label for="authentication">인증번호</label>
                    </div>
                </div>

                <div class="row mt-1">
                    <div class="col-md-4">
                        <input type="text" id="authentication" class="form-control" placeholder="인증번호를 입력해주세요" disabled="disabled">
                    </div>
                    <div class="col-md-2">
                        <button type="button" id="authenticationButton"
                                class="btn btn-info btn-sm" disabled='disabled'>인증번호 확인
                        </button>
                    </div>
                    <div class="col" id="checkAuthentication"></div>
                </div>


                <div class="row mt-3">
                    <div class="col bi bi-exclamation-square-fill deepblue">&nbsp;돌아오신 것을 환영합니다! 정보를 정확히 기입하셔야 계정활성화가 가능합니다.</div>
                </div>

                <div class="row mt-3">
                    <div class="col">
                        <button type="button" id="recoveryButton" class="btn-info btn-sm pull-right" disabled='disabled'>&nbsp;계정활성화</button>
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