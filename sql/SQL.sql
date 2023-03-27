/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 2월 24일 EdenDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

-- 회원가입 테이블
drop table eden_user;
create table eden_user
(
    user_no                   number primary key,
    question_no               number        not null,
    user_id                   varchar2(15)  not null unique,
    user_pw                   varchar2(200) not null,
    user_nickname             varchar2(30)  not null unique,
    user_image                varchar2(200) default 'default-user-image.jpg',
    user_gender               varchar2(1)   not null,
    user_birth                date          not null,
    user_phone                varchar2(30)  not null unique,
    user_email                varchar2(200) not null unique,
    user_findAnswer           varchar2(200) not null,
    user_status               varchar2(200) default 'active',
    user_join_date            date          default sysdate,
    user_last_connection_date date          default sysdate,
    sessionkey                varchar2(50)  default 'none',
    sessionlimit              date          default sysdate,
    constraint user_find_question_no foreign key (question_no) references eden_find_question (question_no)
);

-- 회워 테이블 시퀸스
drop sequence eden_user_seq;
create sequence eden_user_seq;

-- 회원 테이블 (비밀번호 질문, 답변 추가, foreign key 추가 )
alter table eden_user
    add question_no number;
alter table eden_user
    add user_findAnswer varchar2(200);
alter table eden_user
    add constraint user_find_question_no foreign key (question_no) references eden_find_question (question_no);

--  회원 비밀번호 찾기 질문 테이블
drop table eden_find_question;
create table eden_find_question
(
    question_no      number primary key,
    question_content varchar2(200) not null
);

-- 회원 비밀번호 찾기 질문 테이블 시퀸스
drop sequence eden_find_question_seq;
create sequence eden_find_question_seq;

-- 회원 비밀번호 찾기 질문 테이블 데이터 생성
INSERT INTO eden_find_question(question_no, question_content)
VALUES (eden_find_question_seq.nextval, '졸업한 초등학교의 이름은 무엇 입니까?');
INSERT INTO eden_find_question(question_no, question_content)
VALUES (eden_find_question_seq.nextval, '태어난 고향은 어디 입니까?');
INSERT INTO eden_find_question(question_no, question_content)
VALUES (eden_find_question_seq.nextval, '가장 친한 사촌의 이름은 무엇 입니까?');
INSERT INTO eden_find_question(question_no, question_content)
VALUES (eden_find_question_seq.nextval, '어렷을적의 별명은 무엇 입니까?');
INSERT INTO eden_find_question(question_no, question_content)
VALUES (eden_find_question_seq.nextval, '부모님의 고향은 어디 입니까?');
INSERT INTO eden_find_question(question_no, question_content)
VALUES (eden_find_question_seq.nextval, '가장 좋아하는 색깔은 무엇 입니까?');
INSERT INTO eden_find_question(question_no, question_content)
VALUES (eden_find_question_seq.nextval, '가장 좋아하는 음식은 무엇 입니까?');

-- 자동 로그인 (세션아이디 / 세션리미트)
alter table eden_user
    add sessionkey varchar2(50) default 'none';
alter table eden_user
    add sessionlimit date default sysdate;

commit;

-- 게시판 테이블
drop table eden_board;
create table eden_board
(
    board_no         number primary key,
    user_no          number,
    category_no      number,
    board_title      varchar2(2000) not null,
    board_content    varchar2(4000) not null,
    board_view_count number default 0,
    board_write_date date   default sysdate,
    constraint board_user_no foreign key (user_no) references eden_user (user_no),
    constraint board_category_no foreign key (category_no) references eden_board_category (category_no)
);

-- 게시판 시퀸스
drop sequence eden_board_seq;
create sequence eden_board_seq;

--  게시판 카테고리
drop table eden_board_category;
create table eden_board_category
(
    category_no   number primary key,
    category_name varchar2(200) not null
);

--  게시판 카테고리 시퀸스
drop sequence eden_board_category_seq;
create sequence eden_board_category_seq;

--  게시판 카테고리 생성
insert into eden_board_category (category_no, category_name)
values (eden_board_category_seq.nextval, '자유게시판');
insert into eden_board_category (category_no, category_name)
values (eden_board_category_seq.nextval, '사진게시판');
insert into eden_board_category (category_no, category_name)
values (eden_board_category_seq.nextval, '동영상게시판');

--  게시글 목록 출력 테스트
insert into eden_board (board_no, user_no, category_no, board_title, board_content)
values (eden_board_seq.nextval, 1, 1, '게시글 목록 출력 테스트', '테스트입니다.');
insert into eden_board (board_no, user_no, category_no, board_title, board_content)
values (eden_board_seq.nextval, 1, 2, '게시글 목록 출력 테스트', '테스트입니다.');
insert into eden_board (board_no, user_no, category_no, board_title, board_content)
values (eden_board_seq.nextval, 1, 3, '게시글 목록 출력 테스트', '테스트입니다.');

--  게시글 조회수 테이블
drop table eden_view_page;
create table eden_view_page
(
    view_page_no      number primary key,
    board_no          number,
    lockup_ip         varchar(200),
    view_inquiry_time date default sysdate,
    constraint eden_view_board_no foreign key (board_no) references eden_board (board_no)
);

--  게시글 조회수 시퀸스
drop sequence eden_view_page_seq;
create sequence eden_view_page_seq;

--  게시글 좋아요 테이블
drop table eden_board_like;
create table eden_board_like
(
    like_no   number primary key,
    user_no   number,
    board_no  number,
    like_date date default sysdate,
    constraint eden_like_userNo foreign key (user_no) references eden_user (user_no),
    constraint eden_like_boardNo foreign key (board_no) references eden_board (board_no)
);

--  게시글 좋아요 시퀸스
drop sequence eden_board_like_seq;
create sequence eden_board_like_seq;

--  게시글 북마크
drop table eden_book_mark;
create table eden_book_mark
(
    book_mark_no number primary key,
    board_no     number not null,
    user_no      number not null,
    reg_date     date default sysdate,
    constraint bookMark_boardNo foreign key (board_no) references eden_board (board_no),
    constraint bookMark_userNo foreign key (user_no) references eden_user (user_no)
);

--  게시글 북마크 시퀸스
drop sequence eden_book_mark_seq;
create sequence eden_book_mark_seq;

--  게시글 검색 카테고리
drop table eden_board_search_category;
create table eden_board_search_category
(
    search_category_no number primary key,
    search_type        varchar2(200) not null
);

--  게시글 검색 카테고리 시퀸스
drop sequence eden_board_search_category_seq;
create sequence eden_board_search_category_seq;

insert into eden_board_search_category (search_category_no, search_type)
values (eden_board_search_category_seq.nextval, '제목');
insert into eden_board_search_category (search_category_no, search_type)
values (eden_board_search_category_seq.nextval, '내용');
insert into eden_board_search_category (search_category_no, search_type)
values (eden_board_search_category_seq.nextval, '작성자');
insert into eden_board_search_category (search_category_no, search_type)
values (eden_board_search_category_seq.nextval, '제목+내용');
insert into eden_board_search_category (search_category_no, search_type)
values (eden_board_search_category_seq.nextval, '내용+작성자');
insert into eden_board_search_category (search_category_no, search_type)
values (eden_board_search_category_seq.nextval, '전체');

--  게시글 코멘트
drop table eden_board_comment;
create table eden_board_comment(
    comment_no number primary key,
    board_no number,
    user_no number,
    comment_content varchar2(4000) default '',
    comment_write_date date default sysdate,
    constraint comment_boardNo foreign key(board_no) references eden_board(board_no),
    constraint comment_userNo foreign key(user_no) references eden_user(user_no)
);

--  게시글 코멘트 시퀸스
drop sequence eden_board_comment_seq;
create sequence eden_board_comment_seq;

--  게시글 코멘트 좋아요
drop table eden_board_comment_like;
create table eden_board_comment_like(
    comment_like_no number primary key,
    comment_no number,
    user_no number,
    comment_like_date date default sysdate,
    constraint likeCommentNo foreign key(comment_no) references eden_board_comment(comment_no),
    constraint likeUserNo foreign key (user_no) references eden_user(user_no)
);

--  게시글 코멘트 좋아요 시퀸스
drop sequence eden_board_comment_like_seq;
create sequence eden_board_comment_like_seq;

commit;

