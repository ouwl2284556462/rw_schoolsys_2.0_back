# 创建数据库
create database school_manage;

#切换数据库
use school_manage;

#改变数据库编码
alter database school_manage character set utf8;

#创建账号表
create table account (
  id int not null auto_increment,
  account_name varchar(64) not null,
  password varchar(64) not null,
  create_time datetime not null,
  primary key (id)
);

ALTER TABLE account ADD unique(account_name);

#创建用户表详细信息表
create table user_dtl(
	acct_id int not null,
	name varchar(64) not null,
	sex varchar(2) not null,
	tel varchar(20),
	identity_Num varchar(32) not null,
	identity_type varchar(2) not null,
	primary key (acct_id)
);


#角色表
create table sys_role(
	id int not null auto_increment,
	name varchar(32) not null,
	mark varchar(64),
	primary key (id)
);
ALTER TABLE sys_role ADD unique(name);

#角色权限
create table sys_role_privilege(
	role_id int not null,
	res_id varchar(16) not null,
	permission varchar(16) not null,
	mark varchar(32),
	primary key (role_id, res_id)
);


#用户-角色关系表
create table sys_user_role(
	user_id int not null,
	role_id int not null,
	primary key (user_id)
);

#系统参数表
create table sys_param(
	param_id varchar(32) not null,
	param_val varchar(64) not null,
	mark varchar(64),
	primary key (param_id)
);

#字典表
create table sys_dict_item(
	group_id varchar(32) not null,
	item_id varchar(32) not null,
	item_name varchar(64) not null,
	sort int not null,
	description varchar(128),
	mark varchar(64)
);
ALTER TABLE sys_dict_item ADD PRIMARY KEY (group_id, item_id);





#课程表
create table course (
 id int not null auto_increment,
 name varchar(32) not null,
 description varchar(128),
 create_time datetime not null,
 primary key (id)
);
ALTER TABLE course ADD unique(name);

#教师-课程信息关系表
create table teacher_course(
	id int not null auto_increment,
	account_name varchar(64) not null,
	course_id int not null,
	year int not null,
	semester varchar(2) not null,
	create_time datetime not null,
	mark varchar(128),
	status varchar(2) not null,
	primary key (id)
);
ALTER TABLE teacher_course ADD INDEX idx_acct (account_name);
ALTER TABLE teacher_course ADD INDEX idx_crs (course_id);

#学生-课程信息关系表
create table student_course(
	account_name varchar(64) not null,
	teacher_course_id int not null,
	create_time datetime not null,
	score int,
	primary key (account_name, teacher_course_id)
);


##################-插入初始化数据

#角色
insert into sys_dict_item(group_id, item_id, item_name, sort) values('sys_role', 'admin', '管理员', 1);

#管理员角色
insert into sys_role(id, name, mark) values(1, 'admin', '管理员角色');
#角色权限
insert into sys_role_privilege(role_id, res_id, permission, mark) values(1, 'userAdmin', 'all', '用户管理菜单');
insert into sys_role_privilege(role_id, res_id, permission, mark) values(1, 'courseAdmin', 'all', '课程管理菜单');
insert into sys_role_privilege(role_id, res_id, permission, mark) values(1, 'roleAdmin', 'all', '角色管理菜单');


#-管理员帐号
update sys_user_role set role_id = 1 where user_id = (select id from account where account_name = '邮箱账号');

#插入证件类型信息
insert into sys_dict_item(group_id, item_id, item_name, sort) values('identity_type', '1', '中华人民共和国居民身份证', 1);
insert into sys_dict_item(group_id, item_id, item_name, sort) values('identity_type', '2', '护照', 2);
insert into sys_dict_item(group_id, item_id, item_name, sort) values('identity_type', '3', '香港身份证', 3);
#性别
insert into sys_dict_item(group_id, item_id, item_name, sort) values('sex', '1', '男', 1);
insert into sys_dict_item(group_id, item_id, item_name, sort) values('sex', '2', '女', 2);
#学期
insert into sys_dict_item(group_id, item_id, item_name, sort) values('semester', '0', '上学期', 0);
insert into sys_dict_item(group_id, item_id, item_name, sort) values('semester', '1', '下学期', 1);
#状态
insert into sys_dict_item(group_id, item_id, item_name, sort) values('course_status', '0', '正常', 0);
insert into sys_dict_item(group_id, item_id, item_name, sort) values('course_status', '1', '已结束', 1);
#是否已评分
insert into sys_dict_item(group_id, item_id, item_name, sort) values('score_status', '0', '未评分', 0);
insert into sys_dict_item(group_id, item_id, item_name, sort) values('score_status', '1', '已评分', 1);


