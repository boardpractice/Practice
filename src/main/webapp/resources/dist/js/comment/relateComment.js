/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 23일 EdenDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-23
작성자 : EdenDev
작성시간 : 오전 9:04
용도 : JavaScript associated with a Comment
*/

window.addEventListener("DOMContentLoaded", function () {
    var printReplyCount = function (totalCount) {
        var commentCount = $(".commentCount");
        var collapsedBox = $(".collapsed-box");

        if (totalCount <= 0) {
            commentCount.html(" 댓글이 없습니다. 의견을 남겨주세요");
            collapsedBox.find(".btn-box-tool").remove();
            return;
        }
        commentCount.html(" 댓글목록 (" + totalCount + ")");
        collapsedBox.find(".box-tools").html(
            "<button type='button' class='btn btn-box-tool' data-widget='collapse'>"
            + "<i class='fa fa-plus'></i>"
            + "</button>"
        );
    };

    var commentList = function () {
        $.ajax({
            type: "post",
            url: "../comment/getCommentList",
            data: {
                board_no: $("#boardNo").val()
            },
            dataType: "json",
            success: function (data) {
                printReplyCount(data.totalCommentCount);
            }
        });
    }

    commentList();

    $(".commentAddBtn").click(function () {
        $.ajax({
            type: "post",
            url: "../comment/writeComment",
            data: {
                board_no: $("#boardNo").val(),
                comment_content: $("#newCommentText").val()
            },
            dataType: "json",
            success: function (data) {
                if (data == "error") {
                    alert("서버와 통신중 에러가 발생했습니다. 다시 확인해주세요");
                } else {
                    alert("댓글 작성에 성공 하였습니다.");
                    location.reload();
                }
            }
        });
    });
});