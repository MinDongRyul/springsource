create table spring_member(
	userid varchar2(50) not null primary key,
	userpw varchar2(100) not null,
	username varchar2(100) not null,
	regdate date default sysdate,
	updatedate date default sysdate,
	enabled char(1) default '1'
);

create table spring_member_auth(
	userid varchar2(50) not null,
	auth varchar2(50) not null,
	constraint fk_member_auth foreign key(userid) references spring_member(userid)
);

select * from SPRING_MEMBER;
select * from SPRING_MEMBER_AUTH;

drop table spring_member_auth;
drop table spring_member;

delete from spring_member;

insert into spring_member(userid, userpw, username) values('user10', 'asd', '일반회원10');