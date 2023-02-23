/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 2월 21일 Eden All rights reserved.            │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-02-21
작성자 : Eden
작성시간 : 오후 10:24
용도 : JavaScript associated with a user
*/

window.addEventListener("DOMContentLoaded", function () {
    $("#joinIdInput").keyup(function () {
        var value = $(event.target).val();
        var num = value.search(/[0-9]/g);
        var eng = value.search(/[a-z]/ig);

        if (value.length < 5 || value.length > 10) {
            $("#alertId").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertId").text("!  아이디는 5자리이상 10자리 이하여야 합니다.")
        } else if (value.replace(/\s|　/gi, "").length == 0) {
            $("#alertId").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertId").text("! 아이디에 공백은 사용할 수 없습니다.")
        } else if (num < 0 || eng < 0) {
            $("#alertId").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertId").text("!  아이디는 영어+숫자로 이루어져야 합니다.")
        } else {
            $.ajax({
                type: "post",
                url: "./isExistId",
                data: {
                    user_id: $("#joinIdInput").val()
                },
                dataType: "json",
                //contentType : "application/x-www-form-urlencoded", // post
                success: function (data) {
                    if (data.result == "fail") {
                        $("#alertId").css({
                            "color": "red"
                        });
                        $("#alertId").text("! 이미 사용중인 아이디입니다.")
                    } else {
                        $("#alertId").css({
                            "color": "black"
                        });
                        $("#alertId").text("✔  사용 가능한 아이디입니다.")
                    }
                }
            });
        }
    });

    $("#changePassword").keyup(function () {
        var value = $(event.target).val();

        var num = value.search(/[0-9]/g);
        var eng = value.search(/[a-z]/ig);
        var spe = value.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

        if (value.length < 8 || value.length > 30) {
            $("#alterPassword").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alterPassword").text("!  비밀번호는 8자리이상 30자리 이하여야 합니다.")
        } else if (value.replace(/\s|　/gi, "").length == 0) {
            $("#alterPassword").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alterPassword").text("!  비밀번호에 공백은 사용할 수 없습니다.")
        } else if (num < 0 || eng < 0 || spe < 0) {
            $("#alterPassword").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alterPassword").text("!  비밀번호는 영어+숫자+특수문자로 이루어져야 합니다.")
        } else {
            $("#alterPassword").css({
                "color": "black",
                "font-size": "10px"
            });
            $("#alterPassword").text("✔  사용가능한 비밀번호입니다.");
        }
    });

    $("#confirmPassword").keyup(function () {
        var value = $("#confirmPassword").val();


        if (value != $("#changePassword").val()) {
            $("#alterPassword2").css({
                "color": "red",
                "font-size": "12px"
            });
            $("#alterPassword2").text("!  비밀번호가 일치하지 않습니다.")
            return;
        }
        ;
        $("#alterPassword2").css({
            "color": "black",
            "font-size": "10px"
        });
        $("#alterPassword2").text("✔  비밀번호가 일치합니다.");
    });

    $("#userNickName").keyup(function () {
        var value = $(event.target).val();
        var txt = value.search(/[가-힣]/g);

        if (value.length < 1 || value.length > 10) {
            $("#alertNickname").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertNickName").text("!  닉네임은 1자리이상 10자리 이하여야 합니다.")
        } else if (value.replace(/\s|　/gi, "").length == 0) {
            $("#alertNickname").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertNickname").text("!  닉네임에 공백은 사용 할 수 없습니다.")
        } else if (txt < 0) {
            $("#alertNickname").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertNickname").text("!  닉네임은 한글만 입력 가능합니다.")
        } else {
            $.ajax({
                type: "post",
                url: "./isExistNickName",
                data: {
                    user_nickname: $("#userNickName").val()
                },
                dataType: "json",
                success: function (data) {
                    if (data.result == "fail") {
                        $("#alertNickName").css({
                            "color": "red",
                            "font-size": "10px"
                        });
                        $("#alertNickName").text("!  이미 사용중인 닉네임 입니다.")
                    } else {
                        $("#alertNickName").css({
                            "color": "black",
                            "font-size": "10px"
                        });
                        $("#alertNickName").text("✔  사용 가능한 닉네임입니다.")
                    }
                }
            });
        }
    });

    $("#userPhone").keyup(function () {
        var value = $(event.target).val();
        var phone = $("#userPhone").val();
        var regex = new RegExp("^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$");
        if (value.length < 13 || value.length > 13) {
            $("#alertPhone").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertPhone").text("!  휴대폰번호는 하이폰포함 13글자여야 됩니다.");
        } else if (!regex.test(phone)) {
            $("#alertPhone").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertPhone").text("!  휴대폰번호 정규식에 맞게끔 작성해주세요");
        } else {
            $.ajax({
                type: "post",
                url: "./isExistPhone",
                data: {
                    user_phone: $("#userPhone").val()
                },
                dataType: "json",
                success: function (data) {
                    if (data.result == "fail") {
                        $("#alertPhone").css({
                            "color": "red",
                            "font-size": "10px"
                        });
                        $("#alertPhone").text("!  이미 사용중인 휴대폰 번호 입니다.")
                    } else {
                        $("#alertPhone").css({
                            "color": "black",
                            "font-size": "10px"
                        });
                        $("#alertPhone").text("✔  사용 가능한 휴대폰번호입니다.");
                    }
                }
            });
        }
    });

    $("#userEmail").keyup(function () {
        var value = $(event.target).val();
        var email = $("#userEmail").val();
        var regex = new RegExp("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
        if (!regex.test(email)) {
            $("#alertEmail").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertEmail").text("!  이메일의 정규식에 맞게끔 작성 부탁드립니다. abc@gmail.com");
        } else {
            $.ajax({
                type: "post",
                url: "./isExistEmail",
                data: {
                    user_email: email
                },
                dataType: "json",
                success: function (data) {
                    if (data.result == "fail") {
                        $("#alertEmail").css({
                            "color": "red",
                            "font-size": "10px"
                        });
                        $("#alertEmail").text("!  이메일이 이미 사용중입니다.");
                    } else {
                        $("#alertEmail").css({
                            "color": "black",
                            "font-size": "10px"
                        });
                        $("#alertEmail").text("✔  사용 가능한 이메일주소입니다.");
                        $("#checkEmailButton").attr("disabled", false);
                    }
                }
            });
        }
    });

    var code = "";
    $("#checkEmailButton").click(function () {
        $.ajax({
            type: "post",
            url: "./checkEmail",
            data: {
                user_email: $("#userEmail").val()
            },
            dataType: "json",
            success: function (data) {
                if (data.result == "error") {
                    alert("서버와 통신중 에러가 발생했습니다.");
                    $("#alertCertified").css({
                        "color": "rad",
                        "font-size": "10px"
                    });
                    $("#alertCertified").text("!  서버 통신중 에러가 발생 하였습니다.");
                } else {
                    alert("인증번호 발송이 완료되었습니다. 입력한 이메일에서 인증번호 확인을 해주세요.");
                    $("#alertCertified").text("! 인증번호를 입력해주세요.")
                    $("#alertCertified").css({
                        "color": "red",
                        "font-size": "10px"
                    });
                    code = data.code;
                    $("#checkEmail").attr("disabled", false);
                    $("#checkEmailButton").attr("disabled", true);
                }
            }
        });
    });

    $("#checkEmail").keyup(function() {
        if ($("#checkEmail").val().length != 6) {
            $("#alertCertified").text("! 인증번호가 일치하지 않습니다. 다시 확인해주시기 바랍니다.")
            $("#alertCertified").css({
                "color": "red",
                "font-size": "10px"
            });
        } else if ($("#checkEmail").val() == code) {
            $("#alertCertified").text("✔ 메일인증이 완료되었습니다.")
            $("#alertCertified").css({
                "color": "green",
                "font-size": "10px"
            });
            $("#checkEmail").attr("disabled", true);
            $("#joinButton").attr("disabled", false);
            $("#userEmail").attr("disabled", false);
            $("#updateInfo").attr("disabled", false);
        }
    });

    $("#joinButton").click(function() {
        if ($("#alertId").text() != "✔  사용 가능한 아이디입니다.") {
            alert("아이디 중복 확인을 해주세요.");
            return;
        }

        if ($("#alertNickName").text() != "✔  사용 가능한 닉네임입니다.") {
            alert("닉네임 중복확인을 해주세요");
            return;
        }

        if ($("#alertPhone").text() != "✔  사용 가능한 휴대폰번호입니다.") {
            alert("휴대폰번호 중복 확인을 해주세요.");
            return;
        }

        if ($("#alertEmail").text() != "✔  사용 가능한 이메일주소입니다.") {
            alert("이메일 중복 확인을 해주세요");
            return;
        }

        if($("#alertCertified").text() != "✔ 메일인증이 완료되었습니다.") {
            alert("메일 인증을 해주세요");
            return;
        }

        $("#insertForm").submit();

        alert("회원가입이 완료 되었습니다.");
    });

    /* 로그인 Ajax 호출 부분 */
    $("#inputId").keypress(function (e) {
        if (e.keyCode == 13) {
            $("#loginButton").click();
        }
    });

    $("#inputPw").keypress(function (e) {
        if (e.keyCode == 13) {
            $("#loginButton").click();
        }
    });

    var userInputId = getCookie("userInputId");
    var setCookieYN = getCookie("setCookieYN");

    if (setCookieYN == 'Y') {
        $("#saveIdBox").prop("checked", true);
    } else {
        $("#saveIdBox").prop("checked", false);
    }

    $("#inputId").val(userInputId);

    $("#loginButton").click(function () {

        var id = $("#inputId").val();
        var pw = $("#inputPw").val();

        if (id.replace(/\s|　/gi, "").length == 0) {
            $("#alertId").css({
                "color": "red"
            });
            $("#alertId").text("!  아이디를 입력해주세요.")
            return;
        }

        if (pw.replace(/\s|　/gi, "").length == 0) {
            $("#alertPw").css({
                "color": "red"
            });
            $("#alertPw").text("!  패스워드를 입력해주세요.")
            return;
        }

        $.ajax({
            type: "post",
            url: "../user/userLoginProcess",
            data: {
                user_id: $("#inputId").val(),
                user_pw: $("#inputPw").val(),
                useCookie: $("#userCookie").prop("checked"),
                saveCookie: $("#saveIdBox").prop("checked")
            },
            dataType: "json",
            // contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                if (data.result == "success") {
                    alert("로그인에 성공 하였습니다.");
                    location.href = "/";
                } else if (data.result == "out") {
                    if (confirm("비활성화된 계정입니다. 계정 활성화 페이지로 이동하시겠습니까?") == true) {
                        location.href = "../user/userLogoutProcess";
                        location.href = "../user/userRecoveryPage";
                    } else {
                        location.href="../main/main";
                    }
                } else {
                    alert("로그인에 실패하였습니다. 아이디와 비밀번호를 확인해 주세요.");
                }
            }
        });
    });

    $("#logoutButton").click(function () {
        if (confirm("로그아웃 하시겠습니까?")) {
            $.ajax({
                type: "post",
                url: "../user/userLogoutProcess",
                // contentType : "application/x-www-form-urlencoded", // post
                dataType: "json",
                success: function (data) {
                    location.href = "/";
                }
            });
        }
    });
});

/* 로그인 부분 */
function showLoginModal() {
    $("#userLoginModal").modal('show');
}

//쿠키값 가져오기
function getCookie(cookie_name) {
    var x, y;
    var val = document.cookie.split(';');

    for (var i = 0; i < val.length; i++) {
        x = val[i].substr(0, val[i].indexOf('='));
        y = val[i].substr(val[i].indexOf('=') + 1);
        x = x.replace(/^\s+|\s+$/g, ''); // 공백 제거

        if (x == cookie_name) {
            return unescape(y);
        }
    }
}