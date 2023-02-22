/*
 * ┌───────────────────────────────────────────────────────────────────┐
 * │ Copyright (c) 2023년 2월 22일 EdenDEV All rights reserved.         │
 * └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-02-22
작성자 : EdenDev
작성시간 : 오후 1:13
용도 : 
*/

package com.spring.practice.commons.exception;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomExceptionReSolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception/exception_page");

        if (handler != null) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            modelAndView.addObject("errorMethod", handlerMethod.getMethod().getName());
        }
        modelAndView.addObject("errorCause", ex.getCause());
        modelAndView.addObject("errorClass", ex.getClass().getSimpleName());
        modelAndView.addObject("errorMessage", ex.getMessage());

        return modelAndView;
    }
}