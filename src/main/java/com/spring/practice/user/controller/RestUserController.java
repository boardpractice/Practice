/*
 * ┌───────────────────────────────────────────────────────────────────┐
 * │ Copyright (c) 2023년 2월 22일 EdenDEV All rights reserved.         │
 * └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-02-22
작성자 : EdenDev
작성시간 : 오후 8:30
용도 : user web page application data Json conversion requests Handling
*/

package com.spring.practice.user.controller;
import com.spring.practice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping(value = "/user/*")
public class RestUserController {

    private final HashMap<String, Object> data = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    //  아이디 중복 체크
    @PostMapping(value = "isExistId")
    public HashMap<String, Object> isExistId(String user_id) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean exist = userService.isExistId(user_id);

        if (exist) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }

        return data;
    }

    //  닉네임 중복 체크
    @PostMapping(value = "isExistNickName")
    public HashMap<String, Object> isExistNickName(String user_nickname) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean exist = userService.isExistNickName(user_nickname);

        if (exist) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }
        return data;
    }

    //  휴대폰번호 중복 체크
    @PostMapping(value = "isExistPhone")
    public HashMap<String, Object> isExistPhone(String user_phone) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean exist = userService.isExistPhone(user_phone);

        if (exist) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }

        return data;
    }

    //  이메일 중복 체크
    @PostMapping(value = "isExistEmail")
    public HashMap<String, Object> isExistEmail(String user_email) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean exist = userService.isExistEmail(user_email);

        if (exist) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }

        return data;
    }

    //  이메일 유효성 검사
    @PostMapping(value = "checkEmail")
    public HashMap<String, Object> checkEmail(String user_email) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;

        // 메일 제목, 내용
        String subject = "회원가입 인증 메일입니다.";
        String content = "홈페이지를 방문해주셔서 감사합니다." +
                "인증 번호는 " + checkNum + " 입니다." +
                "\r\n" +
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

        // 보내는 사람
        String from = "hanbyeols333z@gmail.com";

        try {
            // 메일 내용 넣을 객체와, 이를 도와주는 Helper 객체 생성
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");

            // 메일 내용을 채워줌
            mailHelper.setFrom(from, "관리자");	// 보내는 사람 셋팅
            mailHelper.setTo(user_email);		// 받는 사람 셋팅
            mailHelper.setSubject(subject);	// 제목 셋팅
            mailHelper.setText(content);	// 내용 셋팅

            // 메일 전송
            javaMailSender.send(mail);

        } catch(Exception e) {
            e.printStackTrace();
        }

        data.put("code", checkNum);

        return data;
    }
}