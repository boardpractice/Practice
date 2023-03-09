/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 7일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-07
작성자 : EdenDev
작성시간 : 오후 3:11
용도 : Handles requests for application bulletin pages
*/

package com.spring.practice.board.controller;

import com.spring.practice.board.domain.BoardVo;
import com.spring.practice.board.service.BoardService;
import com.spring.practice.commons.annotation.LogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    @Autowired
    BoardService boardService;


    //  게시글 목록 페이지
    @PostMapping(value = "postingList")
    @LogException
    public String postingList(@RequestParam(value = "category_no", defaultValue = "0") int category_no, Model model) {

        ArrayList<HashMap<String, Object>> dataList = boardService.getBoardList(category_no);
        model.addAttribute("category_no", category_no);
        model.addAttribute("dataList", dataList);

        return "board/postingList";
    }

    //  게시글 작성 페이지
    @PostMapping(value = "writePosting")
    @LogException
    public String writePosting(@RequestParam(value = "category_no", defaultValue = "0") int category_no, @ModelAttribute("boardVo") BoardVo param, Model model) {
        model.addAttribute("category_no", category_no);
        model.addAttribute("list", boardService.getCategoryList());
        return "board/writePosting";
    }
}
