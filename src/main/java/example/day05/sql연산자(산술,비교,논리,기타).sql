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
    bamout smallint not null ,         # 구매수량   정수 
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

#--------------------------------------------------------------------------------------#\
# 검색
-- select 필드명1. 필드명2 from 테이블명;
select * from member;
select mid from member;

# 1. 별칭 as // [1. 가독성, 2. 필드명/테이블명 연산시 간소화 등등]
select mid as 회원아이디 from member;
select mid as 회원아이디, mname as 그룹명 from member;
select mid 회원아이디, mname 그룹명 from member;

select * from member as m;
select * from member m;

# 2. 조건절 where [ 리터럴 : 문자열처리 ' ' ]
select * from member where mname =  '블랙핑크';
select * from member where mnumber = 4;
select * from member where mnumber != 4;
select * from member where mnumber > 5;
select * from member where mnumber < 5;
select * from member where mnumber >= 5;
select * from member where mnumber <= 5;
select * from member where mheight >=165 and mheight <=170;		#165~170 키 그룹
select * from member where mheight between 165 and 170; 		#165~170 얘도 그 값 포함임(이상 , 이하)
select * from member where mheight >=165 or mnumber >4;			#165이상 키거나 멤버가 6명 초과이면
select * from member where maddr = '경기' or maddr = '전남' or maddr = '경남'; #주소가 경기 or 전남 or 경남
select * from member where maddr in('경기','전남','경남');  # 위의것과 같다
select * from member where not maddr in('경기','전남','경남');  # 위의것의 반대(차집합) not은 반대

select * from member where mname like '에이%';					# '에이'로 시작하는 그룹명
select * from member where mname like '%핑크';					# '핑크'로 끝나는 그룹명
select * from member where mname like '%마%';					# '마'가 포함된 그룹명

select * from member where mname like '에이_';					# '에이'로 시작하는 세글자 그룹명을 찾음
select * from member where mname like '에이__';					# '에이'로 시작하는 네글자 그룹명을 찾음
select * from member where mname like '__핑크';					# '핑크'로 끝나는 네글자 그룹명을 찾음
select * from member where mname like '_마_';					# 2번째 글자가 '마' 세글자 그룹명을 찾음

select mnumber +10 from member;
select mnumber -10 from member;
select mnumber *10 from member;
select mnumber /10 from member;
select mnumber %10 from member;

select  mnumber +10,  mnumber -10,  mnumber *10,  mnumber /10,  mnumber %3, mnumber div 3, mnumber mod 3 from member;
# select 이니까 원본은 그대로고 안바뀜 바꿀려면 update해야함 (당연)

select * from member where mphone1 = '';
select * from member where mphone1 = null;
select * from member where mphone1 is null;
select * from member where mphone1 is not null;



/*
	연산자
		1. 산술연산자
				+ 더하기 -빼기 *곱하기 /나누기 div 몫 mod(% mysql) 나머지
        2. 비교연산자
				= 같다. != 같지않다. > 초과 < 미만 >=이상 <=이하 
        3. 논리연산자
				and 이면서 or 이거나 not 부정
                &&		 || 되긴하나 oracle이랑 호환 관련 해서 안되는 경우가 있으므로.
        4. 기타연산자
				필드명 between 시작값 and 끝값 : 시작값부터 끝값까지 사이 true
                필드명 in ( '값', '값', '값') : 여러 값중 하나라도 포함하면 포함(or or or) 하면 true
				패턴 비교 검색
					필드명 like 패턴
                    1. % : 모든 문자수 대응 ( 0글자도 포함. )
                    2. _ : _ 개수만큼의 문자 대응
                    3. null 은 is null로 해야함 = null로는 검색이 안됨.
						- null 연산
							필드명 is null;
							필드명 is not null;

*/

select 5+3 더하기, 5-3 as 빼기, 5*2 곱하기, 5/2 as 나누기, 5 div 3 몫, 5 mod 3 as 나머지, 5=5, 5=3, 5>3, 5<=3 from dual; #dual : 임시 테이블. // as 는 생략 가능 // 5=5 true가 아닌 1로 나옴, 5=3 false가 아닌 0으로 나옴

# 1. as 별칭 : 필드 나 테이블에 별칭
# 2. where 조건절
# 3. order by 필드명(asc): 정렬 (주어진 필드로 오름차순(기본값))
# 3. order by 필드명 desc: 정렬 (주어진 필드로 오름차순(기본값))
	# 오름차순 : 1 2 3 4 , 날짜/ 과거 -> 최선 , a b c 순, 가나다 순
    # 2개 이상의 정렬 : order by 필드명 정렬기준, 필드명 정렬기준
		# 앞 정렬된 데이터에서 동일한 데이터(같은 값)끼리의 후 정렬
        
# 4. limit : 검색 레코드 수 제한, ex 페이지 전환
	# 1. limit 레코드 수
    # 2. limit 시작 레코드수(0부터시작), 개수

select * from member;
select * from member order by mdebut;
select * from member order by mdebut asc;
select * from member order by mdebut desc;
select * from member order by mheight desc, mdebut asc; 

select * from member;
select * from member limit 2;
select * from member limit 0,3;
# 5개씩 출력하려면
select * from member limit 0,5;
select * from member limit 5,5;
select * from member order by mheight desc limit 0,5;
select * from member order by mheight desc limit 5,5;
	# url : localhost:8080/board/(1-1)*5, 1페이지 첫 수 계산 식 
	# url : localhost:8080/board/(2-1)*5, 2페이지 첫 수 계산 식
	# where , order by, limit 같이 사용시 순서 정해져 있음.
# select * from member limit 3 where mheight >=165; 오류
# select * from member limit 3 order by mheight >=165; 오류
# 작성 순서 :
# select 필드명 from 테이블명 where 조건절 order by 정렬기준 asc/desc limit 제한

# 5. distinct : 중복제거
select maddr from member;
select * from member;
select distinct maddr from member;
select distinct maddr, mname from member;
select distinct mheight, mnumber from member;
select distinct maddr, mphone1 from member;

# RDBMS : 관계형 데이터베이스
	# 열과 행으로 구성된 테이블 : 검색 결과도 테이블이다. 레코드 단위다.
    
/*

	select : 검색/조회/색인
		select 필드명 from 테이블명
        select * from 테이블명
			
            2. where				: 조건절
            3. order by asc/desc	: 정렬
            4. limit				: 레코드 제한수
				- 작성 순서
					select 필드명 from 테이블명 where 조건절 order by 정렬기준 asc/desc limit 시작번호(0~) , 개수;
            1. as					: 별칭
            5. distinct				: 필드 값 중복 제거

*/



		







