/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 16일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-16
작성자 : EdenDev
작성시간 : 오전 12:30
용도 : BookMark Data Access Object Request Handling
*/

package com.spring.practice.bookmark.persistence;

import com.spring.practice.bookmark.domain.BookMarkVo;
import com.spring.practice.commons.annotation.LogException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BookMarkDAOImpl implements BookMarkDAO {

    private static final String NAMESPACE = "mappers.bookmark.BookMarkMapper";

    @Autowired
    SqlSession sqlSession;

    //  게시글 북마크 등록
    @Override
    @LogException
    public void doBookMark(BookMarkVo param) {
        sqlSession.insert(NAMESPACE + ".doBookMark", param);
    }

    //  게시글 북마크 상태
    @Override
    @LogException
    public int getMyBookMarkStatus(BookMarkVo param) {
        return sqlSession.selectOne(NAMESPACE + ".getMyBookMarkStatus", param);
    }

    //  게시글 북마크 취소
    @Override
    @LogException
    public void deleteBookMark(int board_no, int user_no) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("boardNo", board_no);
        data.put("userNo", user_no);
        sqlSession.delete(NAMESPACE + ".deleteBookMark", data);
    }

    //  내가 북마크한 게시글
    @Override
    @LogException
    public List<BookMarkVo> getBookMarkList(int user_no) {
        return sqlSession.selectList(NAMESPACE + ".getBookMarkList", user_no);
    }
}