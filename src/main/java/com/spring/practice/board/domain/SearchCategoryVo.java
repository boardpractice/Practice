/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 3월 17일 EdenDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성일시 : 2023-03-17
작성자 : EdenDev
작성시간 : 오전 6:38
용도 : Posting Search Category Value Object
*/

package com.spring.practice.board.domain;

public class SearchCategoryVo {
    private int search_category_no;
    private String search_type;

    public SearchCategoryVo() {
        super();
    }

    public SearchCategoryVo(int search_category_no, String search_keyword) {
        this.search_category_no = search_category_no;
        this.search_type = search_keyword;
    }

    public int getSearch_category_no() {
        return search_category_no;
    }

    public void setSearch_category_no(int search_category_no) {
        this.search_category_no = search_category_no;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    @Override
    public String toString() {
        return "SearchCategoryVo{" +
                "search_category_no=" + search_category_no +
                ", search_type='" + search_type + '\'' +
                '}';
    }
}
