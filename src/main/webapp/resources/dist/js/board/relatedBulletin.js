/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 8일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-08
작성자 : EdenDev
작성시간 : 오전 3:16
용도 : JavaScript associated with a Bulletin
*/

function postingList(category_no) {
    const form = $("form[id='list']");
    $("#category_no").attr("value", category_no);
    form.attr("action", "../board/postingList");
    form.attr("method", "post");
    form.submit();
}

function writePosting(category_no) {
    const form = $("form[name='writeForm']");
    $("#category").attr("value", category_no);
    form.attr("action", "../board/writePosting");
    form.attr("method", "post");
    form.submit();
}

function goPage(board_no) {
    const form= $("form[name='detailsForm']");
    $("#boardNo").attr("value", board_no);
    form.attr("action", "../board/detailsPosting");
    form.attr("method", "post");
    form.submit();
}

window.addEventListener("DOMContentLoaded", function () {

    /* 게시글 작성 */
    $("#writePostingButton").click(function () {
        $.ajax({
            type: "post",
            url: "../board/writePostingProcess",
            data: {
                category_no: $("#categoryList").val(),
                board_title: $("#board_title").val(),
                board_content: $("#board_content").val()
            },
            dataType: "json",
            success: function (data) {
                if (data.result == "error") {
                    location.reload();
                } else {
                    alert("게시글 작성에 성공 하였습니다.");
                    postingList($("#categoryList").val());
                }
            }
        });
    });

    const formObj = $("form[role='form']");

    $(".listBtn").click(function () {
        formObj.attr("action", "../board/postingList");
        formObj.attr("method", "post");
        formObj.submit();
    });

    $(".modBtn").click(function () {
        formObj.attr("action", "../board/modifyPosting");
        formObj.attr("method", "post");
        formObj.submit();
    });

    $(".crystalBtn").click(function () {
        $.ajax({
            type: "post",
            url: "../board/modifyPostingProcess",
            data: {
                board_no: $("#updateBoardNo").val(),
                category_no: $("#categoryList").val(),
                board_title: $("#title").val(),
                board_content: $("#content").val()
            },
            dataType: "json",
            success: function (data) {
                if (data.result == "error") {
                    location.reload();
                } else {
                    alert("게시글 수정에 성공 하였습니다.");
                    goPage($("#updateBoardNo").val());
                }
            }
        })
    });

    $(".cancelBtn").click(function () {
        formObj.attr("action", "../board/detailsPosting");
        formObj.attr("method", "post");
        formObj.submit();
    });

    $(".delBtn").click(function () {
        if (confirm("해당 게시글을 정말로 삭제 하시겠습니까??")) {
            $.ajax({
                type: "post",
                url: "../board/deletePosting",
                data: {
                    boardNo : $("#boardNo").val()
                },
                dataType: "json",
                success: function (data) {
                    if (data.result == "success") {
                        alert("게시글 삭제에 성공 하였습니다.");
                        postingList($("#categoryNo").val());
                    }
                }
            })
        }
    });

    var getTotalLikeCount = function () {
        $.ajax({
            type: "post",
            url: "../board/getTotalLikeCount",
            data: {
                board_no: $("#boardNo").val()
            },
            dataType: "json",
            success: function (data) {
                $("#likeCount").text("좋아요 수(" + data.totalLikeCount + ")");
            }
        });
    }

    var getMyLikeStatus = function () {
        $.ajax({
            type: "post",
            url: "../board/getMyLikeStatus",
            data: {
                user_no: $("#userNo").val(),
                board_no: $("#boardNo").val()
            },
            dataType: "json",
            // contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                if (data.result == 'error') {
                    console.log(data.reason);
                } else if (data.status == 'like') {
                    $(".boardLike").text("좋아요 취소");
                    $("#like").attr("class", "fa-regular fa-thumbs-down");
                } else if (data.status == 'unlike') {
                    $(".boardLike").text("좋아요");
                    $("#like").attr("class", "fa-regular fa-thumbs-up");
                }
            }
        });
    }

    getMyLikeStatus();

    $(".boardLike").click(function () {
        $.ajax({
            type: "post",
            url: "../board/doLike",
            data: {
                board_no: $("#boardNo").val(),
                user_no: $("userNo").val()
            },
            dataType: "json",
            success: function (data) {
                if (data.status == "unlike") {
                    alert("게시글 좋아요를 완료 하였습니다.");
                    location.reload();
                } else {
                    alert("게시글 좋아요를 취소 하였습니다.");
                    location.reload();
                }
            }
        });
    });
})