/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 7일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-07
작성자 : EdenDev
작성시간 : 오후 3:21
용도 : Performing Board Business Logic
*/

package com.spring.practice.board.service;

import com.spring.practice.board.domain.*;
import com.spring.practice.board.persistence.BoardDAO;
import com.spring.practice.commons.annotation.LogException;
import com.spring.practice.user.domain.UserVo;
import com.spring.practice.user.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDAO boardDAO;

    @Autowired
    UserDAO userDAO;

    //  게시글 목록

    @Override
    @LogException
    public ArrayList<HashMap<String, Object>> getBoardList(int category_no, int search_category_no, String keyword) {

        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        List<BoardVo> boardVoList;

        if (category_no != 0) {
            boardVoList = boardDAO.getBoardByCategoryList(category_no, search_category_no, keyword);
        } else {
            boardVoList = boardDAO.getBoardList(search_category_no, keyword);
        }
        for (BoardVo boardVo : boardVoList) {
            int userNo = boardVo.getUser_no();
            UserVo userVo = userDAO.getUserByNo(userNo);
            CategoryVo categoryVo = boardDAO.getCategoryByNo(boardVo.getCategory_no());
            int totalLikeCount = boardDAO.getTotalLikeCount(boardVo.getBoard_no());

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("boardVo", boardVo);
            map.put("userVo", userVo);
            map.put("categoryVo", categoryVo);
            map.put("totalLikeCount", totalLikeCount);

            data.add(map);
        }
        return data;
    }

    //  게시글 카테고리 목록
    @Override
    @LogException
    public List<CategoryVo> getCategoryList() {
        return boardDAO.getCategoryList();
    }

    //  게시글 작성
    @Override
    @LogException
    public void insertPosting(BoardVo param) {
        boardDAO.insertPosting(param);
    }

    //  게시글 상세보기
    @Override
    @LogException
    public HashMap<String, Object> getBoard(int board_no) {

        BoardVo boardVo = boardDAO.getBoardByNo(board_no);
        UserVo userVo = userDAO.getUserByNo(boardVo.getUser_no());
        int totalLikeCount = boardDAO.getTotalLikeCount(board_no);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userVo", userVo);
        map.put("boardVo", boardVo);
        map.put("totalLikeCount", totalLikeCount);

        return map;
    }

    //  게시글 조회수 증가 중복 방지
    @Override
    @LogException
    public void insertViewPage(ViewPageVo viewPageVo) {
        boardDAO.insertViewPage(viewPageVo);
    }

    //  게시글 조회수 증가 중복 방지 조회
    @Override
    @LogException
    public List<ViewPageVo> getViewPageList(int boardNo) {
        return boardDAO.getViewPageList(boardNo);
    }

    //  게시글 조회한 아이피 조회 쿼리
    @Override
    @LogException
    public boolean isSelectByLockupIp(String lockup_ip) {
        return boardDAO.selectByLockupIp(lockup_ip) > 0;
    }

    //  게시글 중복 증가 방지 게시글 조회
    @Override
    @LogException
    public boolean isSelectByViewByBoardNo(int boardNo) {
        return boardDAO.selectByViewByBoardNo(boardNo) > 0;
    }

    //  게시글 조회 중복 증가 방지 조회 (게시글번호, 아이피로 조회)
    @Override
    @LogException
    public boolean isSelectByViewPage(ViewPageVo viewPageVo) {
        return boardDAO.selectByViewPage(viewPageVo) > 0;
    }

    //  게시글 조회수 증가 쿼리
    @Override
    @LogException
    public void increaseReadCount(int boardNo) {
        boardDAO.increaseReadCount(boardNo);
    }

    @Override
    @LogException
    public void updateViewPage(ViewPageVo param) {
        boardDAO.updateViewPage(param);
    }

    //  게시글 조회수 중복 증가 삭제
    @Override
    @LogException
    public void deleteViewPage(int boardNo) {
        boardDAO.deleteViewPage(boardNo);
    }

    //  게시글 수정
    @Override
    @LogException
    public void modifyBoard(BoardVo param) {
        boardDAO.modifyBoard(param);
    }

    //  게시글 삭제
    @Override
    @LogException
    public void deletePosting(int boardNo) {

        //  게시글 조회수 중복 체크 삭제
        boardDAO.deleteViewPage(boardNo);

        //  게시글 삭제
        boardDAO.deletePosting(boardNo);
    }

    //  게시글 좋아요
    @Override
    @LogException
    public void doLike(BoardLikeVo likeVo) {
        if (getMyLikeCount(likeVo) < 1) {
            boardDAO.doLike(likeVo);
        } else {
            boardDAO.deleteLike(likeVo);
        }
    }

    //  게시글 좋아요 상태
    @Override
    @LogException
    public int getMyLikeCount(BoardLikeVo likeVo) {
        return boardDAO.getMyLikeCount(likeVo);
    }

    //  게시글 좋아요 총 갯수
    @Override
    @LogException
    public int getTotalLikeCount(int board_no) {
        return boardDAO.getTotalLikeCount(board_no);
    }

    //  게시글 검색 카테고리 목록
    @Override
    @LogException
    public List<SearchCategoryVo> getBoardSearchCategoryList() {
        return boardDAO.getBoardSearchCategoryList();
    }
}
