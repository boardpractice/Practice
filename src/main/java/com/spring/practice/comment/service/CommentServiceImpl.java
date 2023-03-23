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

package com.spring.practice.comment.service;

import com.spring.practice.comment.domain.CommentVo;
import com.spring.practice.comment.persistence.CommentDAO;
import com.spring.practice.commons.annotation.LogException;
import com.spring.practice.user.domain.UserVo;
import com.spring.practice.user.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    UserDAO userDAO;

    //  댓글 목록
    @Override
    @LogException
    public ArrayList<HashMap<String, Object>> getCommentList(int board_no) {
        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        List<CommentVo> commentVoList = commentDAO.getCommentList(board_no);

        for (CommentVo commentVo : commentVoList) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");

            UserVo userVo = userDAO.getUserByNo(commentVo.getUser_no());
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("commentVo", commentVo);
            map.put("userVo", userVo);

            dataList.add(map);
        }
        return dataList;
    }

    //  댓글 총 갯수
    @Override
    @LogException
    public int getTotalCommentCount(int board_no) {
        return commentDAO.getTotalCommentCount(board_no);
    }
}
