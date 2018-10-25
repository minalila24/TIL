-- comments.sql
create table comments
(
	num number(5) primary key,
	id varchar2(10),
	comments varchar2(50)
);
create sequence comments_seq;
insert into comments values(comments_seq.nextval,'test','good!');
commit;
