/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 7일 EdenDEV All rights reserved.          │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-07
작성자 : EdenDev
작성시간 : 오후 3:22
용도 : Board Data Access Object Request Handling
*/

package com.spring.practice.board.persistence;

import com.spring.practice.board.domain.BoardVo;
import com.spring.practice.board.domain.CategoryVo;
import com.spring.practice.commons.annotation.LogException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {

    private static final String NAMESPACE = "mappers.board.BoardSQLMapper";

    @Autowired
    SqlSession sqlSession;

    //  게시글 목록
    @Override
    @LogException
    public List<BoardVo> getBoardList() {
        return sqlSession.selectList(NAMESPACE + ".getBoardList");
    }

    //  게시글 목록 (카테고리별 정렬)
    @Override
    @LogException
    public List<BoardVo> getBoardByCategoryList(int category_no) {
        return sqlSession.selectList(NAMESPACE + ".getBoardByCategoryList", category_no);
    }

    //  카테고리리 정보
    @Override
    @LogException
    public CategoryVo getCategoryByNo(int category_no) {
        return sqlSession.selectOne(NAMESPACE + ".getCategoryByNo", category_no);
    }
}
