/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 24일 EdenDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-24
작성자 : EdenDev
작성시간 : 오후 11:57
용도 : Posting Comment Like Value Object
*/

package com.spring.practice.comment.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CommentLikeVo {
    private int comment_like_no;
    private int comment_no;
    private int user_no;
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:dd")
    private Date comment_like_date;

    public CommentLikeVo() {
        super();
    }

    public CommentLikeVo(int comment_like_no, int comment_no, int user_no, Date comment_like_date) {
        this.comment_like_no = comment_like_no;
        this.comment_no = comment_no;
        this.user_no = user_no;
        this.comment_like_date = comment_like_date;
    }

    public int getComment_like_no() {
        return comment_like_no;
    }

    public void setComment_like_no(int comment_like_no) {
        this.comment_like_no = comment_like_no;
    }

    public int getComment_no() {
        return comment_no;
    }

    public void setComment_no(int comment_no) {
        this.comment_no = comment_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public Date getComment_like_date() {
        return comment_like_date;
    }

    public void setComment_like_date(Date comment_like_date) {
        this.comment_like_date = comment_like_date;
    }

    @Override
    public String toString() {
        return "CommentLikeVo{" +
                "comment_like_no=" + comment_like_no +
                ", comment_no=" + comment_no +
                ", user_no=" + user_no +
                ", comment_like_date=" + comment_like_date +
                '}';
    }
}

