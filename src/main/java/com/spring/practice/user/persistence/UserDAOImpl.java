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
import com.spring.practice.user.domain.LoginDTO;
import com.spring.practice.user.domain.QuestionVo;
import com.spring.practice.user.domain.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

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

    //  로그인
    @Override
    @LogException
    public UserVo selectByIdAndPw(LoginDTO dto) {
        return sqlSession.selectOne(NAMESPACE + ".selectByIdAndPw", dto);
    }

    //  아이디 찾기
    @Override
    @LogException
    public HashMap<String, Object> getUserIdByNickNameAndEmail(UserVo param) {
        return sqlSession.selectOne(NAMESPACE + ".getUserIdByNickNameAndEmail", param);
    }

    //  비밀번호 찾기 질문
    @Override
    @LogException
    public List<QuestionVo> getJoinQuestionList() {
        return sqlSession.selectList(NAMESPACE + ".getJoinQuestionList");
    }

    // 비밀번호 찾기 질문 조회
    @Override
    @LogException
    public HashMap<String, Object> getUserQuestionById(UserVo param) {
        return sqlSession.selectOne(NAMESPACE + ".getUserQuestionById", param);
    }

    //  비밀번호 찾기 질문 답변
    public UserVo getUserPwByfindAnswer(UserVo param) {
        return sqlSession.selectOne(NAMESPACE + ".getUserPwByfindAnswer", param);
    }

    //  임시 비밀번호 발급
    @Override
    @LogException
    public void getUserUpdatePw(UserVo param) {
        sqlSession.update(NAMESPACE + ".getUserUpdatePw", param);
    }

    //  회원정보 조회
    public UserVo getUser(String user_id) {
        return sqlSession.selectOne(NAMESPACE + ".getUser", user_id);
    }

    //  회원정보 수정
    @Override
    @LogException
    public void updateUserInfo(UserVo param) {
        sqlSession.update(NAMESPACE + ".updateUserInfo", param);
    }

    //  회원정보 탈퇴
    @Override
    @LogException
    public void deleteUserInfoByUserNo(UserVo param) {
        sqlSession.update(NAMESPACE + ".deleteUserInfoByUserNo", param);
    }
}