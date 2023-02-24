<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 2월 24일 EdenDEV All rights reserved.         │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--
Date Created : 2023-02-24
Code Author : EdenDev
Creation Time : 오후 1:14
Purpose : user menu include jsp file
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                        <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" onclick="searchId();"><b>아이디 찾기</b></a>
                        <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" onclick="searchPw();"><b>비밀번호 찾기</b></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="userIdFindModel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">아이디 찾기</h4>
            </div>
            <div class="modal-body" align="center">
                <form action="./user/findIdProcess">
                    <div class="row">
                        <div class="col">
                            <div class="row mt-2">
                                <div class="col">
                                    <input type="text" class="form-control" name="user_nickname" id="user_nickname"
                                           placeholder="닉네임을 입력해주세요.">
                                </div>
                            </div>

                            <div class="row mt-2">
                                <div class="col">
                                    <input type="text" class="form-control" name="user_email" id="user_email"
                                           placeholder="이메일을 입력해주세요.">
                                </div>
                            </div>

                            <div class="row mt-2">
                                <div class="col" id="answerLine"></div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger pull-right" data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-info pull-right" id="findIdButton">검색</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="userPwFindModel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">비밀번호 찾기</h4>
            </div>
            <div class="modal-body" align="center">
                <form id="findPw" action="../user/findPwProcess">
                    <div class="row">
                        <div class="row mt-1">
                            <div class="col">
                                <label for="findIdInput">아이디</label>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col">
                                <input type="text" class="form-control" name="user_id" placeholder="아이디를 입력해주세요" id="findIdInput">
                                <button type="button" class="btn btn-info pull-right btn-xs" id="findQuestionButton">힌트조회</button>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col">
                                <input type="text" class="form-control" name="user_findAnswer" placeholder="힌트답을 입력해주세요" id="findAnswerInput">
                                <button type="button" class="btn btn-info pull-right btn-xs" id="findButton">답변확인</button>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col" id="question_content"></div>
                            <div class="col" id="answerLine2"></div>
                        </div>

                        <div class="row mt-1">
                            <div class="col">
                                <input type="password" class="form-control" id="findPwInput" name="user_pw" placeholder="사용하실 비밀번호를 입력해주세요" disabled="disabled">
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col" id="answerLine3"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="row">
                    <div class="col-lg-2"><button type="button" class="btn btn-danger pull-left" data-dismiss="modal">닫기</button></div>
                    <div class="col-lg-2"><button type="button" class="btn btn-info pull-right" id="updatePW" disabled="disabled">수정</button></div>
                </div>
            </div>

        </div>
    </div>
</div>