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
import com.spring.practice.board.domain.ViewPageVo;
import com.spring.practice.board.service.BoardService;
import com.spring.practice.comment.service.CommentService;
import com.spring.practice.commons.annotation.LogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    CommentService commentService;


    //  게시글 목록 페이지
    @PostMapping(value = "postingList")
    @LogException
    public String postingList(@RequestParam(value = "category_no", defaultValue = "0") int category_no, Model model, @RequestParam(value = "search_category_no", defaultValue = "0") int search_category_no, @RequestParam(value = "keyword", defaultValue = "") String keyword, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

        ArrayList<HashMap<String, Object>> dataList = boardService.getBoardList(category_no, search_category_no, keyword, pageNum);
        int count = boardService.getBoardCount(category_no, search_category_no, keyword);

        int totalPageCount = (int) Math.ceil(count / 10.0);

        // 1 2 3 4 5 , 6 7 8 9 10
        int startPage = ((pageNum - 1) / 5) * 5 + 1;
        int endPage = ((pageNum - 1) / 5 + 1) * (5);
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        model.addAttribute("count", count);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("category_no", category_no);
        model.addAttribute("dataList", dataList);
        model.addAttribute("list", boardService.getBoardSearchCategoryList());
        model.addAttribute("search_category_no", search_category_no);
        model.addAttribute("keyword", keyword);

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

    //  게시글 상세보기
    @PostMapping(value = "detailsPosting")
    @LogException
    public String detailsPosting(@RequestParam(value = "board_no", defaultValue = "0") int board_no, Model model, HttpServletRequest request) {

        List<ViewPageVo> viewPageVo = boardService.getViewPageList(board_no);
        if (boardService.isSelectByViewByBoardNo(board_no)) {
            if (!boardService.isSelectByLockupIp(request.getRemoteAddr())) {
                ViewPageVo param = new ViewPageVo();
                param.setBoard_no(board_no);
                param.setLockup_ip(request.getRemoteAddr());

                boardService.insertViewPage(param);
                boardService.increaseReadCount(board_no);
            }
        } else {
            ViewPageVo param = new ViewPageVo();
            param.setBoard_no(board_no);
            param.setLockup_ip(request.getRemoteAddr());

            boardService.insertViewPage(param);
            boardService.increaseReadCount(board_no);
        }

        for (ViewPageVo param : viewPageVo) {
            if (param != null) {
                if (boardService.isSelectByLockupIp(request.getRemoteAddr())) {
                    if (param.getLockup_ip().equals(request.getRemoteAddr())) {
                        Date writeDate = new Date(System.currentTimeMillis()); // 현재 서버 시간
                        Date tagetDate = new Date(param.getView_inquiry_time().getTime() + 1000 * 60 * 60 * 24); // 조회한 조회 일자

                        if (writeDate.after(tagetDate)) {
                            boardService.increaseReadCount(board_no);
                            boardService.updateViewPage(param);
                        }
                    }
                }
            }
        }

        model.addAttribute("data", boardService.getBoard(board_no));
        model.addAttribute("dataList", commentService.getCommentList(board_no));
        return "board/detailsPosting";

    }

    //  게시글 수정 페이지
    @PostMapping(value = "modifyPosting")
    public String modifyPosting(@RequestParam(value = "board_no", defaultValue = "0") int boardNo, Model model, @ModelAttribute("boardVo") BoardVo boardVo) {

        model.addAttribute("data", boardService.getBoard(boardNo));
        model.addAttribute("list", boardService.getCategoryList());

        return "board/modifyPosting";
    }
}
