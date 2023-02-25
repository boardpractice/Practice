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

@Controller
@RequestMapping(value = "/user/*")
public class UserController {

    @Autowired
    UserService userService;

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

        model.addAttribute("data", userService.getJoinQuestionList());

        return "user/profile";
    }
}
