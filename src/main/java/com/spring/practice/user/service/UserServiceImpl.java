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
import com.spring.practice.user.domain.QuestionVo;
import com.spring.practice.user.domain.UserVo;
import com.spring.practice.user.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
        userDAO.updateLoginDate(dto.getUser_id());
        return userDAO.selectByIdAndPw(dto);
    }

    //  아이디 찾기
    @Override
    @LogException
    public HashMap<String, Object> getUserIdByNickNameAndEmail(UserVo param) {
        return userDAO.getUserIdByNickNameAndEmail(param);
    }

    // 비밀번호 찾기 질문
    @Override
    @LogException
    public List<QuestionVo> getJoinQuestionList() {
        return userDAO.getJoinQuestionList();
    }

    //  비밀번호 찾기 질문 조회
    @Override
    @LogException
    public HashMap<String, Object> getUserQuestionById(UserVo param) {
        return userDAO.getUserQuestionById(param);
    }

    //  비밀번호 질문 답변
    public UserVo getUserPwByfindAnswer(UserVo param) {
        return userDAO.getUserPwByfindAnswer(param);
    }

    //  임시 비밀번호 발급
    public void getUserUpdatePw(UserVo param) {
        userDAO.getUserUpdatePw(param);
    }

    // 회원정보 조회
    public UserVo getUser(String user_id) {
        return userDAO.getUser(user_id);
    }

    //  회원정보 수정
    @Override
    @LogException
    public void updateUserInfo(UserVo param) {
        userDAO.updateUserInfo(param);
    }

    //  회원정보 탈퇴
    @Override
    @LogException
    public void deleteUserInfoByUserNo(UserVo param) {
        userDAO.deleteUserInfoByUserNo(param);
    }

    //  아이디 체크
    @Override
    @LogException
    public boolean isCheckId(String user_id) {
        return userDAO.isCheckId(user_id) > 0;
    }

    //  닉네임 체크
    @Override
    @LogException
    public boolean isCheckNickName(UserVo param) {
        return userDAO.isCheckNickName(param) > 0;
    }

    //  이메일 체크
    @Override
    @LogException
    public boolean isCheckEmail(UserVo param) {
        return userDAO.isCheckEmail(param) > 0;
    }

    //  계정 복구 정보 조회
    @Override
    @LogException
    public int checkUser(UserVo param) {
        return userDAO.checkUser(param);
    }

    //  계정 활성화
    @Override
    @LogException
    public void recoveryUserByInfo(UserVo param) {
        userDAO.recoveryUserByInfo(param);
    }
}
