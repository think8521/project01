 --think_member 테이블 예제 데이터

insert into think_member(member_no, name, id, pwd)
  values(1, '김성주', 'ksj123', sha1('1111'));
insert into think_member(member_no, name, id, pwd)
  values(2, '방현석', 'bhs123', sha1('1111'));
insert into think_member(member_no, name, id, pwd)
  values(3, '최기현', 'cgh123', sha1('1111'));

  
  
 --think_spend 테이블 예제 데이터

insert into think_spend(spend_no, spend, price, daily, who)
  values(1, '아아', 1400, 'Y', 1);
insert into think_spend(spend_no, spend, price, daily, who)
  values(2, '따아', 1400, 'Y', 1);
insert into think_spend(spend_no, spend, price, daily, who)
  values(3, '비누', 1000, 'Y', 2);
insert into think_spend(spend_no, spend, price, daily, who)
  values(4, '쿠키', 2000, 'N', 3);
insert into think_spend(spend_no, spend, price, daily, who)
  values(5, '키보드', 50000, 'Y', 1);
insert into think_spend(spend_no, spend, price, daily, who)
  values(6, '옥수수수염차', 2000, 'N', 3);



--think_board 테이블 예제 데이터
/*
insert into think_board(board_no, title, content, writer, password, category) 
  values(11, '제목1', '내용', 1, '1111', 1);
insert into think_board(board_no, title, content, writer, password, category) 
  values(12, '제목2', '내용', 2, '1111', 1);
insert into think_board(board_no, title, content, writer, password, category) 
  values(13, '제목3', '내용', 2, '1111', 1);
insert into think_board(board_no, title, content, writer, password, category) 
  values(14, '제목4', '내용', 5, '1111', 1);
insert into think_board(board_no, title, content, writer, password, category) 
  values(15, '제목5', '내용', '윤봉길', '1111', 2);
insert into think_board(board_no, title, content, writer, password, category) 
  values(16, '제목6', '내용', '안중근', '1111', 2);
insert into think_board(board_no, title, content, writer, password, category) 
  values(17, '제목7', '내용', '김구', '1111', 2);
  */