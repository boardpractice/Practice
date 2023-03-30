/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 16일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-16
작성자 : EdenDev
작성시간 : 오전 3:14
용도 : Performing BookMark Business Logic
*/

package com.spring.practice.bookmark.service;

import com.spring.practice.board.domain.BoardVo;
import com.spring.practice.board.persistence.BoardDAO;
import com.spring.practice.bookmark.domain.BookMarkVo;
import com.spring.practice.bookmark.persistence.BookMarkDAO;
import com.spring.practice.commons.annotation.LogException;
import com.spring.practice.user.domain.UserVo;
import com.spring.practice.user.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BookMarkServiceImpl implements BookMarkService {
    @Autowired
    BookMarkDAO bookMarkDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    BoardDAO boardDAO;

    //  게시글 북마크 등록
    @Override
    @LogException
    public void doBookMark(BookMarkVo param) {
        int bookMarkStatus = getMyBookMarkStatus(param);
        if (bookMarkStatus > 0) {
            bookMarkDAO.deleteBookMark(param.getBoard_no(), param.getUser_no());
        } else {
            bookMarkDAO.doBookMark(param);
        }
    }

    //  게시글 북마크 상태 조회
    @Override
    @LogException
    public int getMyBookMarkStatus(BookMarkVo param) {
        return bookMarkDAO.getMyBookMarkStatus(param);
    }

    //  내가 북마크한 글 리스트 불러오기
    @Override
    @LogException
    public ArrayList<HashMap<String, Object>> getBookMarkList(int user_no) {
        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        List<BookMarkVo> bookMarkVoList = bookMarkDAO.getBookMarkList(user_no);
        for (BookMarkVo bookMarkVo : bookMarkVoList) {
            int boardNo = bookMarkVo.getBoard_no();
            BoardVo boardVo = boardDAO.getBoardByNo(boardNo);
            UserVo userVo = userDAO.getUserByNo(bookMarkVo.getUser_no());
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("boardVo", boardVo);
            map.put("userVo", userVo);
            map.put("bookMarkVo", bookMarkVo);

            dataList.add(map);
        }
        return dataList;
    }
}
