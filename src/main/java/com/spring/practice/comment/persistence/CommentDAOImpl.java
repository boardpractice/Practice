/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 22일 EdenDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-22
작성자 : EdenDev
작성시간 : 오후 11:26
용도 : 
*/

package com.spring.practice.comment.persistence;

import com.spring.practice.comment.domain.CommentVo;
import com.spring.practice.commons.annotation.LogException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    private final String NAMESPACE = "mappers.comment.CommentSQLMapper";

    @Autowired
    SqlSession sqlSession;

    //  댓글 목록
    @Override
    @LogException
    public List<CommentVo> getCommentList(int board_no) {
        return sqlSession.selectList(NAMESPACE + ".getCommentList", board_no);
    }

    //  댓글 총 갯수
    @Override
    @LogException
    public int getTotalCommentCount(int board_no) {
        return sqlSession.selectOne(NAMESPACE + ".getTotalCommentCount", board_no);
    }
}