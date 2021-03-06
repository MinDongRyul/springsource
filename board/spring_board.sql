create table spring_board(
	bno number(10,0),
	title varchar2(200) not null,
	content varchar2(2000) not null,
	writer varchar2(50) not null,
	regdate date default sysdate,
	updatedate date default sysdate
);

alter table spring_board add constraint pk_spring_board primary key(bno);

create sequence seq_board;

--댓글 수 컬럼 추가
alter table spring_board add(replycnt number default 0); 

--기존 댓글 업데이트
update spring_board 
set replycnt = (select count(rno) from SPRING_REPLY where SPRING_BOARD.bno = SPRING_REPLY.bno); 

select * from spring_board;

-- oracle 페이지 나누기

-- 더미 데이터
insert into spring_board(bno, title, content, writer) 
(select seq_board.nextval, title, content, writer from spring_board);

-- rownum 사용

select rownum, bno, title from spring_board order by bno desc;

-- rownum은 order by절과 함께 사용시 주의
-- order by절에서 사용하는 컬럼이 index가 아닐때 
-- 임의로 행을 가지고 나온 후 번호를 붙힘 => 인라인 쿼리
select rownum,.... 
from(select * 
	 from board 
	 where bno > 0 
	 order by re_ref desc)

-- 1) rownum 사용 방식
select rownum, bno, title, writer
from (select bno, title, writer
	  from spring_board
	  order by bno desc)
where rownum <= 10;

-- 2) order by 컬럼이 인덱스라면 오라클 힌트 이용 가능
select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum, bno, title, writer
from spring_board
where rownum <= 10;

-- 댓글이 10개씩만 보여주도록 만든 sql문
select bno, title, writer, regdate, updatedate 
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title, writer, regdate, updatedate 
	  from spring_board
	  where rownum <= 10)
where rn > 0;

-- 2페이지
select bno, title, writer, regdate, updatedate 
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title, writer, regdate, updatedate 
	  from spring_board
	  where rownum <= 20)
where rn > 10;

select count(*) 
		from spring_board 
		where bno > 0;
		
-- 가상의 테이블 사용
select seq_board.nextval from dual;

-- 페이지 나누기 + 검색

-- 타이틀 : 자바 인경우
-- pageNum=1&amount=10&type=T&keyword=자바
select bno, title, writer, regdate, updatedate 
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title, writer, regdate, updatedate 
	  from spring_board
	  where bno > 0 and (title like '%자바%') and rownum <= (1 * 10))
where rn > (1 - 1) * 10;

-- pageNum=1&amount=10&type=TC&keyword=자바
select bno, title, writer, regdate, updatedate 
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title, writer, regdate, updatedate 
	  from spring_board
	  where bno > 0 and (title like '%자바%' or content like '%자바%') and rownum <= (1 * 10))
where rn > (1 - 1) * 10;

-- 댓글
create table spring_reply(
	rno number(10, 0),               -- 댓글 번호
	bno number(10, 0) not null,      -- 원본글 번호
	reply varchar2(1000) not null,   -- 댓글 내용
	replyer varchar2(50) not null,   -- 댓글
	replydate date default sysdate,  -- 댓글작성 날짜
	updatedate date default sysdate  -- 댓글수정 날짜
);

drop table spring_reply;

-- 댓글 시퀀스
create sequence seq_reply;

-- 댓글 테이블 pk 설정 후 이름 지정
alter table spring_reply add constraint pk_reply primary key(rno);

-- 리스트 테이블과 1 대 N 설정
-- 외래키 제약
alter table spring_reply add constraint fk_reply_board foreign key(bno) 
references spring_board(bno);

select * from spring_reply;

-- 인덱스 생성
create index idx_reply on spring_reply(bno desc, rno asc);

update spring_reply set replyer = '홍길동' where rno = 2; 

select * from spring_reply where bno = 562; 

select * from spring_board where bno = 563; 

delete from spring_reply where rno > 33; 

-- 첨부파일 테이블 생성
create table spring_attach(
	uuid varchar2(100) not null,
	uploadpath varchar2(200) not null,
	filename varchar2(100) not null,
	filetype char(1) default '1',
	bno number(10,0)
);

alter table spring_attach add constraint pk_attach primary key(uuid);
alter table spring_attach add constraint fk_board_attach
foreign key(bno) references spring_board(bno);

select * from spring_attach;
select * from spring_reply;

select * 
from spring_attach 
where uploadPath = to_char(sysdate - 1,'yyyy\mm\dd');


