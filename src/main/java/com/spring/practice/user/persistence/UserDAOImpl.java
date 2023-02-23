/*
 * ┌───────────────────────────────────────────────────────────────────┐
 * │ Copyright (c) 2023년 2월 22일 EdenDEV All rights reserved.         │
 * └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-02-22
작성자 : EdenDev
작성시간 : 오후 5:43
용도 : User Data Access Object Request Handling
*/

package com.spring.practice.user.persistence;

import com.spring.practice.commons.annotation.LogException;
import com.spring.practice.user.domain.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final String NAMESPACE = "mappers.user.UserSQLMapper";

    @Autowired
    private SqlSession sqlSession;

    //  회원가입
    @Override
    @LogException
    public void insertUser(UserVo param) {
        sqlSession.insert(NAMESPACE + ".insertUser", param);
    }

    //  아이디 중복체크
    @Override
    @LogException
    public int isExistId(String user_id) {
        return sqlSession.selectOne(NAMESPACE + ".isExistId", user_id);
    }

    //  닉네임 중복체크
    @Override
    @LogException
    public int isExistNickName(String user_nickname) {
        return sqlSession.selectOne(NAMESPACE + ".isExistNickName", user_nickname);
    }

    //  휴대폰 중복체크
    @Override
    @LogException
    public int isExistPhone(String user_phone) {
        return sqlSession.selectOne(NAMESPACE + ".isExistPhone", user_phone);
    }

    //  이메일 중복체크
    @Override
    @LogException
    public int isExistEmail(String user_email) {
        return sqlSession.selectOne(NAMESPACE + ".isExistEmail", user_email);
    }
}