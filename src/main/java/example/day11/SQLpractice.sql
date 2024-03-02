/* ------------------------------------------------------------------ */
drop database if exists test10;
create database test10;
use test10;

# 1. 회원테이블
drop table if exists member;
create table member(            # 아이돌 그룹
   mid char(8) not null ,         # 식별키       최대 8자리
    mname varchar(10) not null ,   # 그룹명      최대 10자리
    mnumber int not null ,         # 인원수      정수 +-21억정도
    maddr char(2) not null ,       # 지역      최대 2자리
    mphone1 char(3) ,            # 지역번호   최대 2자리 
    mphone2 char(8) ,            # 전화번호    최대 8자리
    mheight smallint ,            # 평균키       정수 +-3만정도
   mdebut date ,               # 데뷔일       yyyy-mm-dd 
    primary key ( mid )            # 제약조건 
);
# 2. 구매테이블
drop table if exists buy;
create table buy(
   bnum int auto_increment ,          # 구매번호   정수    자동번호 부여 
    mid char(8),                  # 구매자      FK 
    bpname char(6) not null ,         # 제품명      최대 6자리 
    bgname char(4) ,                # 분류명       최대   4자리
    bprice int not null ,            # 가격       정수 
    bamount smallint not null ,         # 구매수량   정수 
    primary key(bnum) ,               # 제약조건 
    foreign key ( mid ) references member(mid) # 제약조건 
);

# 샘플데이터 
INSERT INTO member VALUES('TWC', '트와이스', 9, '서울', '02', '11111111', 167, '2015.10.19');
INSERT INTO member VALUES('BLK', '블랙핑크', 4, '경남', '055', '22222222', 163, '2016.08.08');
INSERT INTO member VALUES('WMN', '여자친구', 6, '경기', '031', '33333333', 166, '2015.01.15');
INSERT INTO member VALUES('OMY', '오마이걸', 7, '서울', NULL, NULL, 160, '2015.04.21');
INSERT INTO member VALUES('GRL', '소녀시대', 8, '서울', '02', '44444444', 168, '2007.08.02');
INSERT INTO member VALUES('ITZ', '잇지', 5, '경남', NULL, NULL, 167, '2019.02.12');
INSERT INTO member VALUES('RED', '레드벨벳', 4, '경북', '054', '55555555', 161, '2014.08.01');
INSERT INTO member VALUES('APN', '에이핑크', 6, '경기', '031', '77777777', 164, '2011.02.10');
INSERT INTO member VALUES('SPC', '우주소녀', 13, '서울', '', '88888888', 162, '2016.02.25');
INSERT INTO member VALUES('MMU', '마마무', 4, '전남', '', '99999999', 165, '2014.06.19');

INSERT INTO buy VALUES(NULL, 'BLK', '지갑', NULL, 30, 2);
INSERT INTO buy VALUES(NULL, 'BLK', '맥북프로', '디지털', 1000, 1);
INSERT INTO buy VALUES(NULL, 'APN', '아이폰', '디지털', 200, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '아이폰', '디지털', 200, 5);
INSERT INTO buy VALUES(NULL, 'BLK', '청바지', '패션', 50, 3);
INSERT INTO buy VALUES(NULL, 'MMU', '에어팟', '디지털', 80, 10);
INSERT INTO buy VALUES(NULL, 'GRL', '혼공SQL', '서적', 15, 5);
INSERT INTO buy VALUES(NULL, 'APN', '혼공SQL', '서적', 15, 2);
INSERT INTO buy VALUES(NULL, 'APN', '청바지', '패션', 50, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '지갑', NULL, 30, 1);
INSERT INTO buy VALUES(NULL, 'APN', '혼공SQL', '서적', 15, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '지갑', NULL, 30, 4);


# -
select bamount from buy; #검색
select distinct bamount from buy; #중복제거 검색
select bamount+5 from buy; # 연산

# 1. sum( 필드명 ) : 필드의 총합계
select sum(bamount) from buy;
# 2. avg( 필드명 ) : 필드의 평균
select avg(bamount) from buy;
# 3. max( 필드명 ) : 필드의 최댓값
select max(bamount) from buy;
# 4. min( 필드명 ) : 필드의 최솟값
select min(bamount) from buy;

# 5-1. count(필드명)
# 5-2. count(*)
select count(bgname) from buy; #9
select count(*) from buy; #12

/*
	집계함수
		1. sum(필드명) : 필드 합계
        2. avg(필드명) : 필드 평균
        3. max(필드명) : 필드 최댓값
        4. min(필드명) : 필드 최솟값
        5. count(필드명) : 필드 레코드 수 (null 제외)
			  count(*) : 레코드 수 (null 포함)
              
              
	  특정 범위 집계 : (~~별, ~~ 끼리 등등) : 그룹
		group by

*/

# 1. 총 판매수량 합계
select sum(bamount) from buy;


# 2. 회원 아이디 별로 판매수량 합계 / 회원 마다 판매수량 합계
# select sum(bamount) , mid from buy; # 이렇게 하면 sum은 레코드 하나 mid는 여러개 나와서 표 자체가 깨지므로 이런 식으로 표현이 불가능하다 RDBMS 는 행/열의 테이블로 표현하는 것.

#
select mid from buy;		# mid 필드
select mid from buy group by mid;		# mid 필드 그룹
select distinct mid from buy;			# mid 필드 중복 제거

#2. 
select mid, sum(bamount) from buy group by mid;
select mid as 구매자 , sum(bamount) as 구매수량 from buy group by mid;
select distinct mid, sum(bamount) from buy; # X

# 3. 회원 아이디 별로 총매출액[판매가격*판매수량]
# 3-1.전체 총매출액
select sum(bamount*bprice) from buy;
select mid as 회원아이디, sum(bamount*bprice) 총매출액 from buy; # 오류 
select mid as 회원아이디, sum(bamount*bprice) as 총매출액 from buy group by mid;

# 4. 회원 아이디 별(그룹) 로 판매수량 평균
select avg(bamount) from buy;
select avg(bamount) as 평균구매수량, mid 구매자 from buy group by mid;

# 5-1 구매자 명단
select mid as 구매자 from buy;
# 5-2 구매자 그룹
select mid as 구매자 from buy group by mid;
# 5-3 
select mid as 구매자, sum(bamount)  from buy group by mid;

# 6. 회원아이디 별로 총매출액이 1000이상 검색
select * from buy;
select (bamount * bprice) from buy; # 각 판매별 매출액
select sum(bamount * bprice) from buy; # 총 판매 매출액
select * from buy where (bamount*bprice) >=1000; # 각 판매별 매출액이 1000 이상
select * from buy where bamount*bprice; # where 뒤에는 조건절이 나와야하는데, 뭐 이건 아무것도 아님.  [T/F로 나오는 것들을 해야함]
select * from buy where sum(bamount*bprice); # where 뒤에는 조건절이 나와야하는데, 오류 나옴(sum을 where 절에 쓰면 X)

#
select mid 구매자, sum(bamount*bprice) 총매출액 from buy group by mid having sum(bamount*bprice) >= 1000;

# having 그훕에 해당하는 조건(후) vs where 그룹에 해당하지 않는 조건(이전 조건) >> sum 같은거 할 수 없고 필드 검색만 가능함.

#
select mid 구매자, sum(bamount*bprice) 총매출액 from buy where sum(bamount*bprice) >= 1000 group by mid; # 오류 나옴


# 구매수량이 3개 이상인 회원아이디 별로 총매출액이 1000 이상 검색
select * from buy;
select * from buy where bamount>=3;
select sum(bamount*bprice) from buy where bamount >=3;
select sum(bamount*bprice),mid from buy where bamount >=3 group by mid;
select sum(bamount*bprice),mid from buy where bamount >=3 group by mid having sum(bamount * bprice) >= 1000;

/*
select 필드명, *, 필드명 as 별칭, distinct 필드명 
from 테이블명, 테이블명 as 별칭 , 테이블 A join 테이블B (조인절)>> 조인을 쓰면 다음에 on절 
where 필드(일반) 조건절 
group by 뭘 기준으로 그 필드를 묶어 보여줄거냐 
having 그룹 후 조건 
order by 정렬 필드 asc/ desc 
limit 레코드수
*/ 

