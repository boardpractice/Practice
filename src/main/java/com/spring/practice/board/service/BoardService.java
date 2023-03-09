/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 7일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-07
작성자 : EdenDev
작성시간 : 오후 3:21
용도 : Board Business Logic interface
*/

package com.spring.practice.board.service;

import com.spring.practice.board.domain.BoardVo;
import com.spring.practice.board.domain.CategoryVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface BoardService {

    //  게시글 목록
    public ArrayList<HashMap<String, Object>> getBoardList(int category_no);

    //  게시글 카테고리 목록
    public List<CategoryVo> getCategoryList();

    //  게시글 작성
    public void insertPosting(BoardVo param);
}
