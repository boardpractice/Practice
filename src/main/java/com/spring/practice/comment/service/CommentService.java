/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 22일 EdenDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-22
작성자 : EdenDev
작성시간 : 오후 11:26
용도 : 
*/

package com.spring.practice.comment.service;

import com.spring.practice.comment.domain.CommentVo;

import java.util.ArrayList;
import java.util.HashMap;

public interface CommentService {

    //  댓글 목록
    public ArrayList<HashMap<String, Object>> getCommentList(int board_no);

    //  댓글 총 갯수
    public int getTotalCommentCount(int board_no);

    public void writeComment(CommentVo param);
}
