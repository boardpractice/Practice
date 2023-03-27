/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 22일 EdenDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-22
작성자 : EdenDev
작성시간 : 오후 11:15
용도 : comment web page application data Json conversion requests Handling
*/

package com.spring.practice.comment.controller;

import com.spring.practice.comment.domain.CommentLikeVo;
import com.spring.practice.comment.domain.CommentVo;
import com.spring.practice.comment.service.CommentService;
import com.spring.practice.commons.annotation.LogException;
import com.spring.practice.user.domain.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/comment/*")
public class RestCommentController {

    HashMap<String, Object> data = new HashMap<>();

    @Autowired
    CommentService commentService;

    //  댓글 총 갯수
    @PostMapping(value = "getCommentList")
    @LogException
    public HashMap<String, Object> getCommentList(int board_no) {

        data.put("commentInfo", commentService.getCommentList(board_no));
        data.put("totalCommentCount", commentService.getTotalCommentCount(board_no));

        return data;
    }

    //  댓글 작성
    @PostMapping(value = "writeComment")
    @LogException
    public HashMap<String, Object> writeComment(CommentVo param, HttpSession session) {

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            data.put("result", "로그인 이후 댓글 작성이 가능합니다. ");
        }

        if (sessionUser != null) {
            int userNo = sessionUser.getUser_no();
            param.setUser_no(userNo);
            commentService.writeComment(param);
            data.put("result", "success");
        }
        return data;
    }

    //  댓글 수정
    @PostMapping(value = "commentModify")
    @LogException
    public HashMap<String, Object> commentModify(CommentVo commentVo) {

        commentService.updateComment(commentVo);

        data.put("result", "success");

        return data;
    }

    //  댓글 삭제
    @PostMapping(value = "deleteComment")
    @LogException
    public HashMap<String, Object> deleteComment(int comment_no) {

        commentService.deleteComment(comment_no);

        return data;
    }

    //  댓글 좋아요
    @PostMapping(value = "doCommentLike")
    @LogException
    public HashMap<String, Object> doCommentLike(CommentLikeVo param, HttpSession session) {

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            data.put("result", "error");
            data.put("reason", "로그인이 필요합니다.");
            return data;
        }

        int myLikeCount = commentService.getMyCommentLikeCount(param);

        data.put("result", "success");

        if (myLikeCount < 1) {
            data.put("status", "like");
        } else {
            data.put("status", "unlike");
        }

        int userNo = sessionUser.getUser_no();
        param.setUser_no(userNo);

        commentService.doCommentLike(param);

        return data;
    }

    //  댓글 추천 총 갯수
    @PostMapping(value = "getTotalCommentLikeCount")
    @LogException
    public HashMap<String, Object> getTotalCommentLikeCount(int comment_no) {

        int totalCommentLikeCount = commentService.getTotalCommentLikeCount(comment_no);
        data.put("totalCommentLikeCount", totalCommentLikeCount);

        return data;
    }
}
