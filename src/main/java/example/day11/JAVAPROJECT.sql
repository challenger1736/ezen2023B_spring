drop database if exists phoenix;
create database phoenix;

use phoenix;

create table grade( -- 직위
   gradeno int not null ,
    gradename varchar(10),
    
    primary key(gradeno)
);

insert into grade values(0,'전체');
insert into grade values(1,'사원');
insert into grade values(2,'대리');
insert into grade values(3,'팀장');
insert into grade values(4,'부장');
insert into grade values(5,'임원');


create table part( -- 부서
   partno int not null,
    partname varchar(30) unique,
    
    primary key(partno)
);

insert into part values(1,'인사');
insert into part values(2,'회계');
insert into part values(3,'개발');

create table employee( -- 사원
   eno int auto_increment,
    gradeno int not null default 0,
   ename varchar(30) not null,
    ephone varchar(15) not null unique,
   eemail varchar(50) not null unique,
    eid varchar(30) not null unique,
    epw varchar(30) not null,
    edate datetime default now(),
    partno int,
    
    primary key(eno),
    foreign key(gradeno) references grade(gradeno), 
    foreign key(partno) references part(partno) on update cascade on delete set null
);

insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(1,'일사원','010-0000-0000','a@co.kr','a','a',1);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(2,'일대리','010-0000-0001','b@co.kr','b','b',1);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(3,'일팀장','010-0000-0002','c@co.kr','c','c',1);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(4,'일부장','010-0000-0003','d@co.kr','d','d',1);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(5,'일임원','010-0000-0004','e@co.kr','e','e',1);

insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(1,'이사원','010-0000-0005','f@co.kr','f','f',2);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(2,'이대리','010-0000-0006','g@co.kr','g','g',2);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(3,'이팀장','010-0000-0007','h@co.kr','h','h',2);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(4,'이부장','010-0000-0008','i@co.kr','i','i',2);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(5,'이임원','010-0000-0009','j@co.kr','j','j',2);

insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(1,'삼사원','010-0000-0010','k@co.kr','k','k',3);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(2,'삼대리','010-0000-0011','l@co.kr','l','l',3);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(3,'삼팀장','010-0000-0012','m@co.kr','m','m',3);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(4,'삼부장','010-0000-0013','n@co.kr','n','n',3);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(5,'삼임원','010-0000-0014','o@co.kr','o','o',3);

/*
직급 샘플 데이터       마스터 : 임원 // 부장 팀장 대리 사원 
*/

/*
부서 샘플 데이터       1회계 2인사 3개발 
*/


create table board(
	boardno int auto_increment,
    boardtitle varchar(30) not null,
    boardcontent longtext not null,
    eno int,
    boarddate datetime default now(),
   
    primary key(boardno),
    foreign key(eno) references employee(eno) on delete set null
);

insert into board(boardtitle,boardcontent,eno) values('1번 게시글','게시글 내용',1);
insert into board(boardtitle,boardcontent,eno) values('2번 게시글','게시글 내용',2);
insert into board(boardtitle,boardcontent,eno) values('3번 게시글','게시글 내용',3);
insert into board(boardtitle,boardcontent,eno) values('4번 게시글','게시글 내용',4);
insert into board(boardtitle,boardcontent,eno) values('5번 게시글','게시글 내용',5);
insert into board(boardtitle,boardcontent,eno) values('6번 게시글','게시글 내용',6);
insert into board(boardtitle,boardcontent,eno) values('7번 게시글','게시글 내용',7);
insert into board(boardtitle,boardcontent,eno) values('8번 게시글','게시글 내용',8);
insert into board(boardtitle,boardcontent,eno) values('9번 게시글','게시글 내용',9);
insert into board(boardtitle,boardcontent,eno) values('10번 게시글','게시글 내용',10);
insert into board(boardtitle,boardcontent,eno) values('11번 게시글','게시글 내용',11);
insert into board(boardtitle,boardcontent,eno) values('12번 게시글','게시글 내용',12);
insert into board(boardtitle,boardcontent,eno) values('13번 게시글','게시글 내용',13);
insert into board(boardtitle,boardcontent,eno) values('14번 게시글','게시글 내용',14);
insert into board(boardtitle,boardcontent,eno) values('15번 게시글','게시글 내용',15);

create table boardpermit(
   boardno int not null,
    gradeno int default 0,
    partno int default 0,
    
    
    foreign key(boardno) references board(boardno) on delete cascade,
    foreign key(gradeno) references grade(gradeno),
	foreign key(partno) references part(partno) on update cascade on delete set null
    
);

insert into boardpermit(boardno,gradeno,partno) values(1,1,1);
insert into boardpermit(boardno,gradeno,partno) values(2,2,1);
insert into boardpermit(boardno,gradeno,partno) values(3,3,1);
insert into boardpermit(boardno,gradeno,partno) values(4,4,1);
insert into boardpermit(boardno,gradeno,partno) values(5,5,1);

insert into boardpermit(boardno,gradeno,partno) values(6,1,2);
insert into boardpermit(boardno,gradeno,partno) values(7,2,2);
insert into boardpermit(boardno,gradeno,partno) values(8,3,2);
insert into boardpermit(boardno,gradeno,partno) values(9,4,2);
insert into boardpermit(boardno,gradeno,partno) values(10,5,2);

insert into boardpermit(boardno,gradeno,partno) values(11,1,3);
insert into boardpermit(boardno,gradeno,partno) values(12,2,3);
insert into boardpermit(boardno,gradeno,partno) values(13,3,3);
insert into boardpermit(boardno,gradeno,partno) values(14,4,3);
insert into boardpermit(boardno,gradeno,partno) values(15,5,3);

-- gradno 별 3개, partno 별 5개

create table reply(
	eno int,
    boardno int not null,
    replyno int auto_increment,
    replycontent text not null,
    replydate datetime default now(),
    
    primary key (replyno),
    foreign key (eno) references employee(eno) on delete set null,
    foreign key (boardno) references board(boardno) on delete cascade
);

insert into reply(eno,boardno,replycontent) values(2,1,"댓글내용1");
insert into reply(eno,boardno,replycontent) values(3,1,"댓글내용2");
insert into reply(eno,boardno,replycontent) values(2,2,"댓글내용3");

create table report(
	eno int,
	reportno int auto_increment,
	reporttitle varchar(50) not null,
    reportcontent longtext not null,
    reportdate datetime default now(),
    
    primary key(reportno),
    foreign key(eno) references employee(eno) on delete set null
);

create table reportlog(
	reportno int not null,
    eno int,
    confirm bool default false,
    
    foreign key(reportno) references report(reportno) on delete cascade,
    foreign key(eno) references employee(eno) on delete set null
);

select * from employee;
select * from board;
select * from boardpermit;
select * from reply;
select * from part;

select * from report;
select * from reportlog;

-- 보고서 샘플
insert into report(eno, reporttitle, reportcontent) values ( 1 , '1번 보고서' , '1번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 2, 1); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.
insert into reportlog(eno, reportno) values ( 3, 1);
insert into report(eno, reporttitle, reportcontent) values ( 1 , '2번 보고서' , '2번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 4, 2); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.
insert into reportlog(eno, reportno) values ( 5, 2);
insert into report(eno, reporttitle, reportcontent) values ( 2 , '3번 보고서' , '3번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 3, 3); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.
insert into reportlog(eno, reportno) values ( 4, 3);
insert into report(eno, reporttitle, reportcontent) values ( 3 , '4번 보고서' , '4번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 4, 4); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.
insert into reportlog(eno, reportno) values ( 5, 4);
insert into report(eno, reporttitle, reportcontent) values ( 4 , '5번 보고서' , '5번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 5, 5); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.

insert into report(eno, reporttitle, reportcontent) values ( 6 , '6번 보고서' , '6번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 7, 6); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.
insert into reportlog(eno, reportno) values ( 8, 6);
insert into report(eno, reporttitle, reportcontent) values ( 7 , '7번 보고서' , '7번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 8, 7); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.
insert into reportlog(eno, reportno) values ( 9, 7);
insert into report(eno, reporttitle, reportcontent) values ( 8 , '8번 보고서' , '8번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 9, 8); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.
insert into reportlog(eno, reportno) values ( 10, 8);
insert into report(eno, reporttitle, reportcontent) values ( 9 , '9번 보고서' , '9번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 10, 9); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.

insert into report(eno, reporttitle, reportcontent) values ( 11 , '10번 보고서' , '10번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 12, 10); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.
insert into reportlog(eno, reportno) values ( 13, 10);
insert into report(eno, reporttitle, reportcontent) values ( 12 , '11번 보고서' , '11번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 13, 11); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.
insert into reportlog(eno, reportno) values ( 14, 11);
insert into report(eno, reporttitle, reportcontent) values ( 13 , '12번 보고서' , '12번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 14, 12); -- reportno 는 자바에서 계산처리 후 넣는건데 샘플 데이터라 직접 넣음.
insert into reportlog(eno, reportno) values ( 15, 12);
insert into report(eno, reporttitle, reportcontent) values ( 14 , '13번 보고서' , '13번 보고서내용'); 
insert into reportlog(eno, reportno) values ( 15, 13);


-- 심재훈
SELECT  report.reportno , COUNT(*) FROM report JOIN reportlog ON report.reportno = reportlog.reportno GROUP BY report.reportno;
-- report.reportno와 count(*)[현재 선택된 그룹내의 레코드 수를 몇개를 묶었는지 알려주는 함수] <<- group by 랑 같이 쓰는 db용 집계 함수 
-- SELECT  report.reportno ,reportlog.confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno where report.eno = 1 and reportlog.confirm = true group by report.reportno, reportlog.confirm;
SELECT  report.reportno ,reportlog.confirm ,count(*) FROM report JOIN reportlog ON report.reportno = reportlog.reportno where report.eno = 1 GROUP BY report.reportno, reportlog.confirm;
-- SELECT  report.reportno ,reportlog.confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno where report.eno = 1;
SELECT report.*,reportlog.eno FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.eno = 1;
SELECT report.*,reportlog.confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.eno = 4;


select * from report;
select * from reportlog;
update reportlog set confirm = true where eno = 2 and reportno= 1; 

SELECT  report.* ,reportlog.confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno;

SELECT  report.* ,reportlog.confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno where reportlog.eno = 2;


SElect report.reportno,Count(*) as count FROM report JOIN reportlog ON report.reportno = reportlog.reportno group by report.reportno;
SELECT report.reportno,Count(*) FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.confirm =true group by report.reportno, reportlog.confirm;

select * from report join reportlog on report.reportno = reportlog.reportno;




DELIMITER //
-- db 명령어 끝마침을 바꿔주는 명령어.

CREATE EVENT IF NOT EXISTS update_report_confirm
ON SCHEDULE EVERY 1 DAY -- 이벤트가 매일 실행되도록 스케줄링함.
DO
BEGIN -- 어떤 이벤트 수행할지 정의
  UPDATE reportlog	-- 업데이트 할 것.
  SET confirm = false
  WHERE reportno IN ( -- 특정 조건(where)이 만족하는 놈(in)에서만 
    SELECT report.reportno
    FROM report
    LEFT JOIN ( -- report와 조인할 table(report와 reportlog를 reportno로 같은 테이블 그룹화해서 reportno와 그 겹치는 수가 있는 테이블)
      SELECT reportno, COUNT(*) FROM report JOIN reportlog ON report.reportno = reportlog.reportno GROUP BY report.reportno
    ) r1 ON report.reportno = r1.reportno -- 그 조인 결과를 r1이라는 별칭에 저장 및 report.reportno = r1이라는 합친 테이블의 reportno가 같을 때만 저장,
    LEFT JOIN (
      SELECT reportno, COUNT(*) FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.confirm = true GROUP BY report.reportno, reportlog.confirm
    ) r2 ON report.reportno = r2.reportno
    WHERE report.reportdate < NOW() - INTERVAL 7 DAY AND COALESCE(r1.count, 0) != COALESCE(r2.count, 0)
  );
END//

DELIMITER ;

SELECT report.reportno FROM report join (select report.reportno, count(*) 
from report join reportlog on report.reportno = reportlog.reportno group by report.reportno)r1 on report.reportno = r1.reportno
join  (SELECT reportno, COUNT(*) FROM report.reportno JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.confirm = true GROUP BY report.reportno, reportlog.confirm
    ) r2 ON report.reportno = r2.reportno;

show events;

