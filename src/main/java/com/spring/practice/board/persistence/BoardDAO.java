/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 7일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-07
작성자 : EdenDev
작성시간 : 오후 3:21
용도 : Board Data Access Object Interface
*/

package com.spring.practice.board.persistence;

import com.spring.practice.board.domain.BoardVo;
import com.spring.practice.board.domain.CategoryVo;

import java.util.List;

public interface BoardDAO {

    //  게시글 목록
    public List<BoardVo> getBoardList();

    //  게시글 목록 (카테고리별 정렬)
    public List<BoardVo> getBoardByCategoryList(int category_no);

    //  게시글 카테고리 정보
    public CategoryVo getCategoryByNo(int category_no);

    //  게시글 카테고리 목록
    public List<CategoryVo> getCategoryList();

    //  게시글 작성
    public void insertPosting(BoardVo param);

    //  게시글 상세보기
    public BoardVo getBoardByNo(int board_no);
}
