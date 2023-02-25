/*
 * ┌───────────────────────────────────────────────────────────────────┐
 * │ Copyright (c) 2023년 2월 22일 EdenDEV All rights reserved.         │
 * └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-02-22
작성자 : EdenDev
작성시간 : 오전 9:30
용도 : Handles requests for the application home page
*/

package com.spring.practice.main.controller;

import com.spring.practice.commons.annotation.LogException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    @LogException
    public String homePage(HttpSession session) {

        String destination = (String) session.getAttribute("destination");

        if (destination != null) {
            session.removeAttribute("destination");
        }

        return "home";
    }

    @GetMapping(value = "/user/login")
    @LogException
    public String loginPage(HttpSession session) {

        if (session.getAttribute("destination") == null || session.getAttribute("sessionUser") != null) {
            return "redirect:../";
        }

        return "home";
    }
}
