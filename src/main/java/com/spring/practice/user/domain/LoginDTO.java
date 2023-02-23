/*
 * ┌───────────────────────────────────────────────────────────────────┐
 * │ Copyright (c) 2023년 2월 23일 EdenDEV All rights reserved.         │
 * └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-02-23
작성자 : EdenDev
작성시간 : 오후 11:05
용도 : 
*/

package com.spring.practice.user.domain;

public class LoginDTO {
    private String user_id; // 아이디
    private String user_pw; // 비밀번호
    private boolean useCookie; // 로그인 유지 여부
    private boolean saveCookie; // 아이디 저장 여부

    // Constructor getter setter toString 생략

    public LoginDTO() {
        super();
    }

    public LoginDTO(String user_id, String user_pw, boolean useCookie, boolean saveCookie) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.useCookie = useCookie;
        this.saveCookie = saveCookie;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public boolean isUseCookie() {
        return useCookie;
    }

    public void setUseCookie(boolean useCookie) {
        this.useCookie = useCookie;
    }

    public boolean isSaveCookie() {
        return saveCookie;
    }

    public void setSaveCookie(boolean saveCookie) {
        this.saveCookie = saveCookie;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "user_id='" + user_id + '\'' +
                ", user_pw='" + user_pw + '\'' +
                ", useCookie=" + useCookie +
                ", saveCookie=" + saveCookie +
                '}';
    }
}
