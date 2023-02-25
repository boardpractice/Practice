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
    user_image                varchar2(200) not null,
    user_gender               varchar2(1)   not null,
    user_birth                date          not null,
    user_phone                varchar2(30)  not null unique,
    user_email                varchar2(200) not null unique,
    user_findAnswer           varchar2(200) not null,
    user_status               varchar2(200) not null,
    user_join_date            date          not null,
    user_last_connection_date date          not null,
    constraint user_find_question_no foreign key (question_no) references eden_find_question (question_no)
);

-- 회워 테이블 시퀸스
drop sequence eden_user_seq;
create sequence eden_user_seq;

-- 회원 테이블 (비밀번호 질문, 답변 추가, foreign key 추가 )
alter table eden_user add question_no number;
alter table eden_user add user_findAnswer varchar2(200);
alter table eden_user add constraint user_find_question_no foreign key (question_no) references eden_find_question (question_no);

--  회원 비밀번호 찾기 질문 테이블
drop table eden_find_question;
create table eden_find_question(
    question_no number primary key,
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

update eden_user set user_status = 'active' where user_no = 24;