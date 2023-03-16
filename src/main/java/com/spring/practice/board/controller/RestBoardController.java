/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 9일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-09
작성자 : EdenDev
작성시간 : 오후 4:26
용도 : board web page application data Json conversion requests Handling
*/

package com.spring.practice.board.controller;

import com.spring.practice.board.domain.BoardLikeVo;
import com.spring.practice.board.domain.BoardVo;
import com.spring.practice.board.service.BoardService;
import com.spring.practice.commons.annotation.LogException;
import com.spring.practice.user.domain.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/board/*")
public class RestBoardController {

    private static final HashMap<String, Object> data = new HashMap<String, Object>();

    @Autowired
    BoardService boardService;

    //  게시글 작성 프로시저
    @PostMapping(value = "writePostingProcess")
    @LogException
    public HashMap<String, Object> writePostingProcess(@Valid BoardVo param, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            data.put("result", "error");
        }

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
        param.setUser_no(sessionUser.getUser_no());
        boardService.insertPosting(param);

        data.put("result", "success");

        return data;
    }

    //  게시글 수정 프로시져
    @PostMapping(value = "modifyPostingProcess")
    @LogException
    public HashMap<String, Object> modifyPostingProcess(@Valid BoardVo param, BindingResult result) {

        if (result.hasErrors()) {
            data.put("result", "error");
        }

        boardService.modifyBoard(param);
        data.put("result", "success");

        return data;
    }

    //  게시글 삭제 프로시저
    @PostMapping(value = "deletePosting")
    @LogException
    public HashMap<String, Object> deletePosting(int boardNo) {

        data.put("result", "success");
        boardService.deletePosting(boardNo);

        return data;
    }

    //  게시글 좋아요 및 좋아요 취소
    @PostMapping(value = "doLike")
    @LogException
    public HashMap<String, Object> doLike(BoardLikeVo param, HttpSession session) {
        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            data.put("result", "error");
            data.put("reason", "로그인이 필요합니다.");
            return data;
        }

        int myLikeCount = boardService.getMyLikeCount(param);

        data.put("result", "success");

        if (myLikeCount > 0) {
            data.put("status", "like");
        } else {
            data.put("status", "unlike");
        }

        int userNo = sessionUser.getUser_no();
        param.setUser_no(userNo);

        boardService.doLike(param);

        data.put("result", "success");

        return data;
    }

    //  게시글 좋아요 상태
    @PostMapping("getMyLikeStatus")
    @LogException
    public HashMap<String, Object> getMyLikeStatus(BoardLikeVo param, HttpSession session) {
        HashMap<String, Object> data = new HashMap<>();

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            data.put("result", "error");
            data.put("reason", "로그인이 필요합니다.");
            return data;
        }

        param.setUser_no(sessionUser.getUser_no());

        int myLikeCount = boardService.getMyLikeCount(param);

        data.put("result", "success");

        if (myLikeCount > 0) {
            data.put("status", "like");
        } else {
            data.put("status", "unlike");
        }

        return data;
    }

    //  게시글 좋아요 총 갯수
    @PostMapping(value = "getTotalLikeCount")
    @LogException
    public HashMap<String, Object> getTotalLikeCount(int board_no){
        HashMap<String, Object> data = new HashMap<String, Object>();

        int totalLikeCount = boardService.getTotalLikeCount(board_no);
        data.put("totalLikeCount", totalLikeCount);
        return data;
    }
}
