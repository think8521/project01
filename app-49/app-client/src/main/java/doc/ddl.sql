create table think_board(
  board_no int not null,
  title varchar(255) not null,
  content text null,
  writer varchar(20) not null,
  password varchar(100) null,
  view_count int default 0,
  created_date datetime default now()
);

alter table think_board
  add constraint primary key (board_no),
  modify column board_no int not null auto_increment;
  
create table think_spend(
  spend_no int not null,
  spend varchar(20) not null,
  price varchar(50) not null,
  daily char(1) not null
);

alter table think_spend
  add constraint primary key (spend_no),
  modify column spend_no int not null auto_increment;
  
  
-- 게시판에 카테고리 컬럼 추가
alter table think_board
  add column category int not null;