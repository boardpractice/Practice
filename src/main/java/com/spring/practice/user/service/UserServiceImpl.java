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
}
