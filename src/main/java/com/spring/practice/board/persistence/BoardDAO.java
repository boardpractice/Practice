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

import com.spring.practice.board.domain.*;

import java.util.List;

public interface BoardDAO {

    //  게시글 목록
    public List<BoardVo> getBoardList(int search_category_no, String keyword);

    //  게시글 목록 (카테고리별 정렬)
    public List<BoardVo> getBoardByCategoryList(int category_no, int search_category_no, String keyword);

    //  게시글 카테고리 정보
    public CategoryVo getCategoryByNo(int category_no);

    //  게시글 카테고리 목록
    public List<CategoryVo> getCategoryList();

    //  게시글 작성
    public void insertPosting(BoardVo param);

    //  게시글 상세보기
    public BoardVo getBoardByNo(int board_no);

    //  게시글 조회수 증가 중복 방지
    public void insertViewPage(ViewPageVo viewPageVo);

    //  게시글 조회수 증가 중복 방지 조회
    public List<ViewPageVo> getViewPageList(int boardNo);

    //  게시글 조회한 아이피 조회 쿼리
    public int selectByLockupIp(String lockup_ip);

    //  게시글 중복 증가 방지 게시글 조회
    public int selectByViewByBoardNo(int boardNo);

    //  게시글 조회 중복 증가 방지 조회 (게시글번호, 아이피로 조회)
    public int selectByViewPage(ViewPageVo viewPageVo);

    //  게시글 조회수 증가 쿼리
    public void increaseReadCount(int boardNo);

    public void updateViewPage(ViewPageVo param);

    //  게시글 조회수 중복 증가 삭제
    public void deleteViewPage(int boardNo);

    //  게시글 수정
    public void modifyBoard(BoardVo param);

    //  게시글 삭제
    public void deletePosting(int board_no);

    //  게시글 좋아요
    public void doLike(BoardLikeVo likeVo);

    //  게시글 좋아요 상태
    public int getMyLikeCount(BoardLikeVo likeVo);

    //  게시글 좋아요 취소
    public void deleteLike(BoardLikeVo likeVo);

    //  게시글 좋아요 총 갯수
    public int getTotalLikeCount(int board_no);

    //  게시글 검색 카테고리 목록
    public List<SearchCategoryVo> getBoardSearchCategoryList();
}
