/*
 * ┌───────────────────────────────────────────────────────────────────┐
 * │ Copyright (c) 2023년 2월 22일 EdenDEV All rights reserved.         │
 * └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-02-22
작성자 : EdenDev
작성시간 : 오후 5:46
용도 : Performing User Business Logic
*/

package com.spring.practice.user.service;

import com.spring.practice.commons.annotation.LogException;
import com.spring.practice.user.domain.LoginDTO;
import com.spring.practice.user.domain.UserVo;
import com.spring.practice.user.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    //  회원가입
    @Override
    @LogException
    public void insertUser(UserVo param) {
        userDAO.insertUser(param);
    }

    //  아이디 중복체크
    @Override
    @LogException
    public boolean isExistId(String user_id) {
        return userDAO.isExistId(user_id) > 0;
    }

    //  닉네임 중복체크
    @Override
    @LogException
    public boolean isExistNickName(String user_nickname) {
        return userDAO.isExistNickName(user_nickname) > 0;
    }

    //  휴대폰 중복체크
    @Override
    @LogException
    public boolean isExistPhone(String user_phone) {
        return userDAO.isExistPhone(user_phone) > 0;
    }

    //  이메일 중복체크
    @Override
    @LogException
    public boolean isExistEmail(String user_email) {
        return userDAO.isExistEmail(user_email) > 0;
    }

    //  로그인
    @Override
    @LogException
    public UserVo login(LoginDTO dto) {
        return userDAO.selectByIdAndPw(dto);
    }
}
