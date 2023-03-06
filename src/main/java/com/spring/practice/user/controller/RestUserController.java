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

import com.spring.practice.commons.annotation.LogException;
import com.spring.practice.user.domain.LoginDTO;
import com.spring.practice.user.domain.QuestionVo;
import com.spring.practice.user.domain.UserVo;
import com.spring.practice.user.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

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
            mailHelper.setFrom(from, "관리자");    // 보내는 사람 셋팅
            mailHelper.setTo(user_email);        // 받는 사람 셋팅
            mailHelper.setSubject(subject);    // 제목 셋팅
            mailHelper.setText(content);    // 내용 셋팅

            // 메일 전송
            javaMailSender.send(mail);

        } catch (Exception e) {
            e.printStackTrace();
        }

        data.put("code", checkNum);

        return data;
    }

    //  유저로그인
    @PostMapping(value = "userLoginProcess")
    @LogException
    public HashMap<String, Object> userLoginProcess(LoginDTO loginDto, HttpSession session, HttpServletResponse response, HttpServletRequest request) {

        UserVo sessionUser = userService.login(loginDto);

        if (sessionUser != null) {
            String state = sessionUser.getUser_status();
            if (state.equals("Inactive")) {
                data.put("result", "out");
            } else if (!BCrypt.checkpw(loginDto.getUser_pw(), sessionUser.getUser_pw())) {
                System.out.println("비밀번호 불일치 : " + BCrypt.hashpw(loginDto.getUser_pw(), BCrypt.gensalt()));
                data.put("result", "fail");
            } else {
                data.put("result", "success");
                session.setAttribute("sessionUser", sessionUser);
                /* 아이디 저장 */
                if (loginDto.isSaveCookie()) {
                    // 아이디 저장 유효기간 : 30일
                    int amount = 60 * 60 * 24 * 30;
                    // 아이디저장 쿠키 객체 생성
                    Cookie saveIdCooke = new Cookie("setCookieYN", "Y");
                    Cookie userInputId = new Cookie("userInputId", loginDto.getUser_id());
                    // 모든 경로에서 접근 가능하게 처리
                    saveIdCooke.setPath("/");
                    userInputId.setPath("/");
                    // 쿠키 유효 기간
                    saveIdCooke.setMaxAge(amount);
                    userInputId.setMaxAge(amount);
                    // 쿠키 저장
                    response.addCookie(saveIdCooke);
                    response.addCookie(userInputId);
                } else {
                    Cookie saveCookie = WebUtils.getCookie(request, "setCookieYN");
                    Cookie userInputId = WebUtils.getCookie(request, "userInputId");
                    if (saveCookie != null && userInputId != null) {
                        saveCookie.setPath("/");
                        userInputId.setPath("/");
                        // 쿠키 유효기간 0
                        saveCookie.setMaxAge(0);
                        userInputId.setMaxAge(0);
                        // 쿠키 저장
                        response.addCookie(saveCookie);
                        response.addCookie(userInputId);
                    }
                }

                if (loginDto.isUseCookie()) { // 로그인 유지 체크시
                    // 로그인 유지 유효기간 : 일주일
                    int amount = 60 * 60 * 24 * 7;
                    // 로그인 유지 유효 일자
                    Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
                    // 로그인 유지 기간 갱신(아이디, 세션아이디, 유효일자)
                    userService.keepLogin(sessionUser.getUser_id(), session.getId(), sessionLimit);

                    // 로그인 쿠키 객체 생성
                    Cookie loginCookie = new Cookie("loginCookie", session.getId());
                    // 모든 경로에서 접근 가능하게 처리
                    loginCookie.setPath("/");
                    // 쿠키 유효 기간
                    loginCookie.setMaxAge(60 * 60 * 24 * 7);
                    // 쿠키 저장
                    response.addCookie(loginCookie);
                }

                String url = (String) session.getAttribute("destination");
                String redirect = url != null ? url : "/";
                data.put("redirect", redirect);
            }
        }
        return data;
    }

    //  유저로그아웃
    @PostMapping(value = "userLogoutProcess")
    @LogException
    public HashMap<String, Object> userLogoutProcess(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");

        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
        if (loginCookie != null) {
            loginCookie.setPath("/");
            // 쿠키 유효기간 0
            loginCookie.setMaxAge(0);
            // 쿠키 저장
            response.addCookie(loginCookie);
            // 로그인 유지 갱신
            userService.keepLogin(sessionUser.getUser_id(), "none", new Date());
        }

        session.removeAttribute("sessionUser");
        session.invalidate();
        return data;
    }

    //  아이디 찾기
    @PostMapping("getUserIdByNickNameAndEmail")
    @LogException
    public HashMap<String, Object> getUserIdByNickNameAndEmail(UserVo param) {

        HashMap<String, Object> data = new HashMap<>();

        HashMap<String, Object> userInfo = userService.getUserIdByNickNameAndEmail(param);

        if (userInfo == null) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
            data.put("userInfo", userInfo);
        }

        System.out.println(data.get("userInfo"));

        return data;
    }

    //  비밀번호 찾기 질문 조회
    @PostMapping(value = "getUserQuestionById")
    @LogException
    public HashMap<String, Object> getUserQuestionById(UserVo param) {

        HashMap<String, Object> data = new HashMap<>();

        HashMap<String, Object> userInfo = userService.getUserQuestionById(param);

        if (userInfo == null) {
            data.put("result", "fail");
        } else {
            data.put("userInfo", userInfo);
        }

        return data;
    }

    //  비밀번호 질문 답변 조회
    @PostMapping(value = "getUserPwByfindAnswer")
    @LogException
    public HashMap<String, Object> getUserPwByfindAnswer(UserVo param) {
        HashMap<String, Object> data = new HashMap<>();
        UserVo userInfo = userService.getUserPwByfindAnswer(param);

        if (userInfo == null) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }
        return data;
    }

    //  비밀번호 찾기 - 수정
    @PostMapping("getUserUpdatePw")
    @LogException
    public HashMap<String, Object> getUserUpdatePw(UserVo param) {

        HashMap<String, Object> data = new HashMap<String, Object>();
        boolean exist = userService.isExistId(param.getUser_id());
        if (exist) {
            data.put("result", "success");
            String password = BCrypt.hashpw(param.getUser_pw(), BCrypt.gensalt());
            param.setUser_pw(password);
            userService.getUserUpdatePw(param);
        } else {
            data.put("result", "fail");
        }
        return data;
    }

    //  회원정보 수정
    @PostMapping("updateUserInfo")
    public HashMap<String, Object> updateUserInfo(UserVo param, HttpSession session) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        UserVo sessionUser = userService.getUser(param.getUser_id());

        if (sessionUser != null) {
            data.put("result", "success");
            userService.updateUserInfo(param);
        } else {
            data.put("result", "fail");
        }
        return data;
    }

    //  현재 비밀번호 체크
    @PostMapping("checkPw")
    @LogException
    public HashMap<String, Object> checkPw(String current_password, String user_id) {

        HashMap<String, Object> data = new HashMap<>();

        UserVo userVo = userService.getUser(user_id);

        if (userVo != null) {
            if (BCrypt.checkpw(current_password, userVo.getUser_pw())) {
                data.put("result", "success");
            } else {
                data.put("result", "fail");
            }
        }

        return data;
    }

    //  비밀번호 수정
    @PostMapping(value = "modifyPassword")
    @LogException
    public HashMap<String, Object> modifyPassword(UserVo userVo, HttpSession session) {

        HashMap<String, Object> data = new HashMap<>();

        UserVo sessionUser = userService.getUser(userVo.getUser_id());

        if (sessionUser != null) {

            /* 비밀번호 변경 */
            String changePassword = BCrypt.hashpw(userVo.getUser_pw(), BCrypt.gensalt());
            sessionUser.setUser_pw(changePassword);
            userService.getUserUpdatePw(sessionUser);

            /* 비밀번호 변경후 로그아웃 */
            session.invalidate();
        }

        return data;
    }

    //  회원탈퇴
    @PostMapping(value = "deleteUserInfoByUserNo")
    public HashMap<String, Object> deleteUserInfoByUserNo(UserVo vo, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
        String password = vo.getUser_pw();

        if (!BCrypt.checkpw(vo.getUser_pw(), sessionUser.getUser_pw())) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
            userService.deleteUserInfoByUserNo(sessionUser);
            session.invalidate();
        }
        return data;
    }



    //  아이디 체크
    @PostMapping(value = "checkId")
    @LogException
    public HashMap<String, Object> checkId(String user_id) {
        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean isCheckId = userService.isCheckId(user_id);

        if (isCheckId) {
            data.put("result", "success");
        } else {
            data.put("result", "fail");
        }
        return data;
    }

    //  닉네임 체크
    @PostMapping("checkNickName")
    @LogException
    public HashMap<String, Object> checkNickName(UserVo param) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean isCheckNick = userService.isCheckNickName(param);

        if (isCheckNick) {
            data.put("result", "success");
        } else {
            data.put("result", "fail");
        }
        return data;
    }

    //  이메일 체크
    @PostMapping(value = "emailCheck")
    @LogException
    public HashMap<String, Object> emailCheck(UserVo param) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean isCheckNick = userService.isCheckEmail(param);

        if (isCheckNick) {
            data.put("result", "success");
            Random random = new Random();
            int checkNum = random.nextInt(888888) + 111111;

            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                messageHelper.setFrom("hanbyeols333z@gmail.com", "관리자"); // 보내는사람 이메일 여기선 google 메일서버 사용하는 아이디를 작성하면됨
                messageHelper.setTo(param.getUser_email()); // 받는사람 이메일
                messageHelper.setSubject("계정 복구 인증 메일입니다.."); // 메일제목
                messageHelper.setText("홈페이지를 방문해주셔서 감사합니다." +
                        "인증 번호는 " + checkNum + " 입니다." +
                        "\r\n" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요."); // 메일 내용

                javaMailSender.send(mimeMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }

            data.put("code", checkNum);
        } else {
            data.put("result", "fail");
        }
        return data;
    }

    //  계정복구
    @PostMapping(value = "recoveryUserByInfo")
    @LogException
    public HashMap<String, Object> recoveryUserByInfo (UserVo vo){

        HashMap<String, Object> data = new HashMap<String, Object>();


        int checkUser = userService.checkUser(vo);

        if ( checkUser == 1 ) {
            userService.recoveryUserByInfo(vo);
            data.put("result", "success");
        } else {
            data.put("result", "fail");
        }

        return data;
    }
}