/*
 * ┌───────────────────────────────────────────────────────────────────┐
 * │ Copyright (c) 2023년 2월 22일 EdenDEV All rights reserved.         │
 * └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-02-22
작성자 : EdenDev
작성시간 : 오후 1:21
용도 : 
*/

package com.spring.practice.commons.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAdvice {

    /*
     * private : 외부에서 로그를 가로채지 못하도록 하기 위해
     * static final : 로그 내용이 변동 되지 않으므로
     * 로깅툴을 사용하는 이유 : system.out.println 명령어는 IO 리소스를 많이 사용하여 시스템이 느려질 수 있다. 로그를 파일로 저장하여 분석 할 필요가 있다.
     */

    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    @AfterThrowing(value = "@annotation(com.spring.practice.commons.annotation.LogException)", throwing = "exception")
    public void exception(JoinPoint joinPoint, Exception exception) {

        logger.error("error class : " + exception.getClass().getSimpleName());
        logger.error("error method name : " + joinPoint.getSignature().getName());
        logger.error("error cause : " + exception.getCause());
        logger.error("errorMessage : " + exception.getMessage());

    }

    @AfterReturning(value = "@annotation(com.spring.practice.commons.annotation.LogException)", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        //logger.info("실행된 클래스 : " + joinPoint.getTarget().getClass().getSimpleName());
        //logger.info("실행된 메서드 : " + joinPoint.getSignature().getName());
        //logger.info("전달된 파라미터 : " + Arrays.toString(joinPoint.getArgs()));
    }
}