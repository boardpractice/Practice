/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 13일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-13
작성자 : EdenDev
작성시간 : 오전 2:22
용도 : Posting Like Value Object
*/

package com.spring.practice.board.domain;
import java.util.Date;

public class BoardLikeVo {

    private int like_no; // 좋아요 번호
    private int user_no; // 좋아요 유저 번호
    private int board_no; // 좋아요 게시글 번호
    private Date like_date; // 좋아요 날짜

    public BoardLikeVo() {
        super();
    }

    public BoardLikeVo(int like_no, int user_no, int board_no, Date like_date) {
        this.like_no = like_no;
        this.user_no = user_no;
        this.board_no = board_no;
        this.like_date = like_date;
    }

    public int getLike_no() {
        return like_no;
    }

    public void setLike_no(int like_no) {
        this.like_no = like_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getBoard_no() {
        return board_no;
    }

    public void setBoard_no(int board_no) {
        this.board_no = board_no;
    }

    public Date getLike_date() {
        return like_date;
    }

    public void setLike_date(Date like_date) {
        this.like_date = like_date;
    }

    @Override
    public String toString() {
        return "BoardLikeVo{" +
                "like_no=" + like_no +
                ", user_no=" + user_no +
                ", board_no=" + board_no +
                ", like_date" + like_date +
                '}';
    }
}