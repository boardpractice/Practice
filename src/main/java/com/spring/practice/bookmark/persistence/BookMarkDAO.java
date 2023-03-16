/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 16일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-16
작성자 : EdenDev
작성시간 : 오전 12:29
용도 : BookMark Data Access Object Interface
*/

package com.spring.practice.bookmark.persistence;

import com.spring.practice.bookmark.domain.BookMarkVo;

public interface BookMarkDAO {

    //  게시글 북마크 등록
    public void doBookMark(BookMarkVo param);

    //  게시글 북마크 상태
    public int getMyBookMarkStatus(BookMarkVo param);

    //  게시글 북마크 취소
    public void deleteBookMark(int board_no, int user_no);
}
