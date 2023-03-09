/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 9일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-09
작성자 : EdenDev
작성시간 : 오후 11:34
용도 : Posting View Count Value Object
*/

package com.spring.practice.board.domain;

import java.util.Date;

public class ViewPageVo {
    private int view_page_no;
    private int board_no;
    private String lockup_ip;
    private Date view_inquiry_time;

    public ViewPageVo() {
        super();
    }

    public ViewPageVo(int view_page_no, int board_no, String lockup_ip, Date view_inquiry_time) {
        this.view_page_no = view_page_no;
        this.board_no = board_no;
        this.lockup_ip = lockup_ip;
        this.view_inquiry_time = view_inquiry_time;
    }

    public int getView_page_no() {
        return view_page_no;
    }

    public void setView_page_no(int view_page_no) {
        this.view_page_no = view_page_no;
    }

    public int getBoard_no() {
        return board_no;
    }

    public void setBoard_no(int board_no) {
        this.board_no = board_no;
    }

    public String getLockup_ip() {
        return lockup_ip;
    }

    public void setLockup_ip(String lockup_ip) {
        this.lockup_ip = lockup_ip;
    }

    public Date getView_inquiry_time() {
        return view_inquiry_time;
    }

    public void setView_inquiry_time(Date view_inquiry_time) {
        this.view_inquiry_time = view_inquiry_time;
    }

    @Override
    public String toString() {
        return "ViewPageVo{" +
                "view_page_no=" + view_page_no +
                ", board_no=" + board_no +
                ", lockup_ip='" + lockup_ip + '\'' +
                ", view_inquiry_time=" + view_inquiry_time +
                '}';
    }
}
