CREATE TABLE gallery(
	num NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	writer VARCHAR2(20) NOT NULL,
	content CLOB,
	createdAt DATE DEFAULT SYSDATE
	);
	
CREATE TABLE gallery_image(
	num NUMBER PRIMARY KEY,
	galleryNum NUMBER REFERENCES gallery(num),
	saveFileName VARCHAR2(100) NOT NULL,
	createdAt DATE DEFAULT SYSDATE
);
CREATE SEQUENCE gallery_image_seq;


CREATE TABLE comments(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(20) NOT NULL,
	content VARCHAR2(100) NOT NULL,
	targetWriter VARCHAR2(20) NOT NULL, --누구에게 작성한 댓글인지
	groupNum NUMBER NOT NULL, --댓글의 그룹번호
	parentNum NUMBER NOT NULL, --부모가 되는 원글의 글 번호
	deleted CHAR(3) DEFAULT 'no', --댓글을 삭제 했는지 여부
	createdAt DATE DEFAULT SYSDATE --작성일
);

CREATE SEQUENCE comments_seq;


CREATE TABLE board(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(20) NOT NULL,
	title VARCHAR2(50) NOT NULL,
	content CLOB,
	viewCount NUMBER DEFAULT 0,
	createdAt DATE DEFAULT SYSDATE
);

CREATE SEQUENCE board_seq;

CREATE TABLE users(
num NUMBER PRIMARY KEY,
userName VARCHAR2(20) UNIQUE,
passWord VARCHAR2(100) NOT NULL,
email VARCHAR2(100) UNIQUE,
profileImage VARCHAR2(100),
role VARCHAR2(10) DEFAULT 'ROLE_USER',
updatedAt DATE,
createdAt DATE
);

CREATE SEQUENCE users_seq;

CREATE TABLE member(
num NUMBER PRIMARY KEY,
name VARCHAR2(20),
addr VARCHAR2(100)
);

CREATE SEQUENCE member_seq;