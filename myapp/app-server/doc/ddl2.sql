create table think_member(
  member_no int not null,
  name varchar(20) not null,
  id varchar(20) not null,
  pwd varchar(100) null,
  created_date date default (current_date())
);

alter table think_member
  add constraint primary key (member_no),
  modify column member_no int not null auto_increment;
  
alter table think_member
  add constraint think_member_uk unique (id);
  
  
  
create table think_spend(
  spend_no int not null,
  spend varchar(20) not null,
  price int not null,
  daily char(1) not null,
  who int not null
);

alter table think_spend
  add constraint primary key (spend_no),
  modify column spend_no int not null auto_increment;
  
alter table think_spend
  add constraint think_spend_fk foreign key (who) references think_member (member_no);
  
  
-- 게시판에 카테고리 컬럼 추가
alter table think_board
  add column category int not null;