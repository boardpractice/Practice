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
});