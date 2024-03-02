drop database if exists testmvc1;
create database testmvc1;
use testmvc1;

create table board(
	bno int auto_increment,
    btitle varchar(30) not null,
    bcontent longtext not null,
    bwriter varchar(10) not null,
    bview int default 0,
    bpw varchar(15) not null,
    
    primary key(bno)
);

insert into board(btitle, bcontent, bwriter, bpw) values ('1번 제목', '1번 내용', '1번글쓴이', '123');
insert into board(btitle, bcontent, bwriter, bpw) values ('2번 제목', '2번 내용', '2번글쓴이', '123');
insert into board(btitle, bcontent, bwriter, bpw) values ('3번 제목', '3번 내용', '3번글쓴이', '123');
insert into board(btitle, bcontent, bwriter, bpw) values ('4번 제목', '4번 내용', '4번글쓴이', '123');
insert into board(btitle, bcontent, bwriter, bpw) values ('5번 제목', '5번 내용', '5번글쓴이', '123');

select * from board;