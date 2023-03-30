/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 16일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-16
작성자 : EdenDev
작성시간 : 오전 3:14
용도 : BookMark Business Logic interface
*/

package com.spring.practice.bookmark.service;

import com.spring.practice.bookmark.domain.BookMarkVo;

import java.util.ArrayList;
import java.util.HashMap;

public interface BookMarkService {

    //  게시글 북마크 등록
    public void doBookMark(BookMarkVo param);

    //  게시글 북마크 상태
    public int getMyBookMarkStatus(BookMarkVo param);

    //  내가 북마크한 게시글
    public ArrayList<HashMap<String, Object>> getBookMarkList(int user_no);
}
