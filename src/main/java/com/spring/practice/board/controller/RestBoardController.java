/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 9일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-09
작성자 : EdenDev
작성시간 : 오후 4:26
용도 : 
*/

package com.spring.practice.board.controller;

import com.spring.practice.board.domain.BoardVo;
import com.spring.practice.board.service.BoardService;
import com.spring.practice.commons.annotation.LogException;
import com.spring.practice.user.domain.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
}
