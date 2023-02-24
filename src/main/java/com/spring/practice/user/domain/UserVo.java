/*
 * ┌───────────────────────────────────────────────────────────────────┐
 * │ Copyright (c) 2023년 2월 22일 EdenDEV All rights reserved.         │
 * └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-02-22
작성자 : EdenDev
작성시간 : 오후 5:39
용도 : 
*/

package com.spring.practice.user.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;


public class UserVo {
    private int user_no; // 유저 번호
    private int question_no; // 비밀번호 찾기 질문 번호
    @Pattern(regexp = "^(?=.*[a-zA-z])(?=.*[0-9])(?!.*[^a-zA-z0-9]).{5,10}")
    private String user_id; // 유저 아아디

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$")
    private String user_pw; // 유저 비밀번호

    @NotNull
    @Pattern(regexp = "^[가-힣].{1,10}$")
    private String user_nickname; // 유저 닉네임

    @NotNull
    private String user_image; // 프로필 이미지

    @NotNull
    private String user_gender; // 유저 성별

    @Past
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date user_birth; // 유저 생년월일

    @NotNull
    @Length(max = 13)
    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$")
    private String user_phone; // 유저 휴대폰 번호

    @NotNull
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
    private String user_email; // 유저 이메일

    @NotNull
    @Length(min = 1, max = 100)
    private String user_findAnswer; // 비밀번호찾기 답변

    private String user_status; // 유저 상태
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date user_join_date; // 유저 가입 일자
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date user_last_connection_date; // 최종 로그인 일자

    public UserVo() {
        super();
    }

    public UserVo(int user_no, int question_no, String user_id, String user_pw, String user_nickname, String user_image, String user_gender, Date user_birth, String user_phone, String user_email, String user_findAnswer, String user_status, Date user_join_date, Date user_last_connection_date) {
        this.user_no = user_no;
        this.question_no = question_no;
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_nickname = user_nickname;
        this.user_image = user_image;
        this.user_gender = user_gender;
        this.user_birth = user_birth;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.user_findAnswer = user_findAnswer;
        this.user_status = user_status;
        this.user_join_date = user_join_date;
        this.user_last_connection_date = user_last_connection_date;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getQuestion_no() {
        return question_no;
    }

    public void setQuestion_no(int question_no) {
        this.question_no = question_no;
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

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public Date getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(Date user_birth) {
        this.user_birth = user_birth;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_findAnswer() {
        return user_findAnswer;
    }

    public void setUser_findAnswer(String user_findAnswer) {
        this.user_findAnswer = user_findAnswer;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public Date getUser_join_date() {
        return user_join_date;
    }

    public void setUser_join_date(Date user_join_date) {
        this.user_join_date = user_join_date;
    }

    public Date getUser_last_connection_date() {
        return user_last_connection_date;
    }

    public void setUser_last_connection_date(Date user_last_connection_date) {
        this.user_last_connection_date = user_last_connection_date;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "user_no=" + user_no +
                ", question_no=" + question_no +
                ", user_id='" + user_id + '\'' +
                ", user_pw='" + user_pw + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_image='" + user_image + '\'' +
                ", user_gender='" + user_gender + '\'' +
                ", user_birth=" + user_birth +
                ", user_phone='" + user_phone + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_findAnswer='" + user_findAnswer + '\'' +
                ", user_status='" + user_status + '\'' +
                ", user_join_date=" + user_join_date +
                ", user_last_connection_date=" + user_last_connection_date +
                '}';
    }
}