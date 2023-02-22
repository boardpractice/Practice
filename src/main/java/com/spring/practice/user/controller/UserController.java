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
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user/*")
public class UserController {

    @Autowired
    UserService userService;

    //  회원가입 페이지
    @GetMapping(value = "register")
    @LogException
    public String registerPage() {
        return "user/register";
    }

    //  회원가입 프로세스
    @PostMapping(value = "insertUserProcess")
    public String insertUserProcess(UserVo param) {

        String hashPw = BCrypt.hashpw(param.getUser_pw(), BCrypt.gensalt());
        param.setUser_pw(hashPw);

        userService.insertUser(param);

        return "redirect:../";
    }
}
