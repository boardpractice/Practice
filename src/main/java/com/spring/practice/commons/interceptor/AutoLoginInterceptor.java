/*
 * ┌───────────────────────────────────────────────────────────────────┐
 * │ Copyright (c) 2023년 3월 6일 EdenDEV All rights reserved.          │
 * └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-06
작성자 : EdenDev
작성시간 : 오전 11:41
용도 : Interceptor for automatic login processing
*/

package com.spring.practice.commons.interceptor;

import com.spring.practice.user.domain.UserVo;
import com.spring.practice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
        // 로그인 유지를 위한 쿠키가 존재하면
        if (loginCookie != null) {
            UserVo userVO = userService.checkLoginBefore(loginCookie.getValue());
            // session에 로그인 정보 저장
            if (userVO != null) {
                session.setAttribute("sessionUser", userVO);
            }
        }
        return true;
    }
}
