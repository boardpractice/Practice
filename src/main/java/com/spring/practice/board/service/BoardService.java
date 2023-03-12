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
import com.spring.practice.board.domain.ViewPageVo;

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

    //  게시글 상세보기
    public HashMap<String, Object> getBoard(int board_no);

    //  게시글 조회수 증가 중복 방지
    public void insertViewPage(ViewPageVo viewPageVo);

    //  게시글 조회수 증가 중복 방지 조회
    public List<ViewPageVo> getViewPageList(int boardNo);

    //  게시글 조회한 아이피 조회 쿼리
    public boolean isSelectByLockupIp(String lockup_ip);

    //  게시글 중복 증가 방지 게시글 조회
    public boolean isSelectByViewByBoardNo(int boardNo);

    //  게시글 조회 중복 증가 방지 조회 (게시글번호, 아이피로 조회)
    public boolean isSelectByViewPage(ViewPageVo viewPageVo);

    //  게시글 조회수 증가 쿼리
    public void increaseReadCount(int boardNo);

    public void updateViewPage(ViewPageVo param);

    //  게시글 조회수 중복 증가 삭제
    public void deleteViewPage(int boardNo);

    //  게시글 수정
    public void modifyBoard(BoardVo param);
}
