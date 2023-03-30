/*
 * ┌───────────────────────────────────────────────────────────────────┐
 * │ Copyright (c) 2023년 2월 22일 EdenDEV All rights reserved.         │
 * └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-02-22
작성자 : EdenDev
작성시간 : 오후 5:10
용도 : Handles requests for the application user page
*/

package com.spring.practice.user.controller;

import com.spring.practice.board.service.BoardService;
import com.spring.practice.bookmark.service.BookMarkService;
import com.spring.practice.comment.service.CommentService;
import com.spring.practice.commons.annotation.LogException;
import com.spring.practice.user.domain.UserVo;
import com.spring.practice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/user/*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;

    @Autowired
    BookMarkService bookMarkService;

    @Autowired
    CommentService commentService;

    //  회원가입 페이지
    @GetMapping(value = "register")
    @LogException
    public String register(Model model, @ModelAttribute("userVo") UserVo param) {

        model.addAttribute("data", userService.getJoinQuestionList());

        return "user/register";
    }

    //  회원가입 프로세스
    @PostMapping(value = "insertUserProcess")
    @LogException
    public String insertUserProcess(Model model, @Valid UserVo param, BindingResult result) {

        if (result.hasErrors()) {

            model.addAttribute("data", userService.getJoinQuestionList());

            return "user/register";
        }

        userService.insertUser(param);
        return "redirect:../";
    }

    // 내정보 페이지
    @GetMapping("profile")
    public String profile(Model model, HttpSession session) {
        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");

        /* 내가 북마크한 게시글 */
        ArrayList<HashMap<String, Object>> dataList = bookMarkService.getBookMarkList(sessionUser.getUser_no());

        /* 내가 작성한 게시글 */
        ArrayList<HashMap<String, Object>> myPostList = boardService.getMyPostList(sessionUser.getUser_no());

        /* 내가 작성한 댓글 */
        ArrayList<HashMap<String, Object>> myCommentList = commentService.getMyCommentList(sessionUser.getUser_no());

        model.addAttribute("data", userService.getJoinQuestionList());
        model.addAttribute("dataList", dataList);
        model.addAttribute("postList", myPostList);
        model.addAttribute("commentList", myCommentList);

        return "user/profile";
    }

    //  계정복구 페이지
    @GetMapping(value = "userRecoveryPage")
    public String userRecoveryPage() {
        return "user/userRecoveryPage";
    }
}
