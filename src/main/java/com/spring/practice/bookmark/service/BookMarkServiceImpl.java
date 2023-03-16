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

import com.spring.practice.board.persistence.BoardDAO;
import com.spring.practice.bookmark.domain.BookMarkVo;
import com.spring.practice.bookmark.persistence.BookMarkDAO;
import com.spring.practice.commons.annotation.LogException;
import com.spring.practice.user.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
