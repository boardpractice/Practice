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

import com.spring.practice.board.domain.BoardVo;
import com.spring.practice.board.domain.CategoryVo;
import com.spring.practice.board.domain.ViewPageVo;
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
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardDAO boardDAO;

    @Autowired
    UserDAO userDAO;

    //  게시글 목록

    @Override
    @LogException
    public ArrayList<HashMap<String, Object>> getBoardList(int category_no) {

        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        List<BoardVo> boardVoList;

        if (category_no != 0) {
            boardVoList = boardDAO.getBoardByCategoryList(category_no);
        } else {
            boardVoList = boardDAO.getBoardList();
        }

        for (BoardVo boardVo : boardVoList) {
            int userNo = boardVo.getUser_no();
            UserVo userVo = userDAO.getUserByNo(userNo);
            CategoryVo categoryVo = boardDAO.getCategoryByNo(boardVo.getCategory_no());

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("boardVo", boardVo);
            map.put("userVo", userVo);
            map.put("categoryVo", categoryVo);

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

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userVo", userVo);
        map.put("boardVo", boardVo);

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
    public void modifyBoard(BoardVo param) {
        boardDAO.modifyBoard(param);
    }
}
