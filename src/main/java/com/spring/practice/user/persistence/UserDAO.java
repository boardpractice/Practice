/*
 * ┌───────────────────────────────────────────────────────────────────┐
 * │ Copyright (c) 2023년 2월 22일 EdenDEV All rights reserved.         │
 * └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-02-22
작성자 : EdenDev
작성시간 : 오후 5:43
용도 : 용도 :  User Data Access Object Interface
*/

package com.spring.practice.user.persistence;

import com.spring.practice.user.domain.UserVo;

public interface UserDAO {

    //  회원가입
    public void insertUser(UserVo param);
}