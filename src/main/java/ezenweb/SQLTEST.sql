drop database if exists springweb;
create database springweb;
use springweb;

drop table if exists member;
create table member(
	no bigint auto_increment,			-- 회원 번호
    id varchar(30) not null unique,		-- 회원 아이디
    pw varchar(30) not null,			-- 회원 비밀번호
    name varchar(20) not null,			-- 회원 이름
	email varchar(50),					-- 회원 이메일
    phone varchar(13) not null unique,	-- 회원 핸드폰 번호
    img text , 							-- 프로필 사진의 경로( 사진 데이터가 아닌 사진이 저장된 서버 경로 )
    
    constraint member_no_pk primary key(no) -- 회원번호 pk
);

select * from member;