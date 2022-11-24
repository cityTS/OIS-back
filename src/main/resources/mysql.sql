create database if not exists ois;

use ois;

# 考试表
create table if not exists examination(
    id int primary key auto_increment comment '考试编号',
    name varchar(255) not null comment '考试名称',
    begin_time bigint not null comment '考试开始时间',
    end_time bigint not null comment '考试结束时间',
    create_time bigint not null comment '创建考试时间',
    delete_time bigint null comment '删除考试时间'
);

# 监考日志表
create table if not exists logs(
    id int primary key auto_increment comment '监考日志编号',
    name varchar(255) not null comment '学生姓名',
    ip varchar(255) not null comment '登录机器IP',
    level varchar(255) not null comment '日志等级[信息][异常]',
    content varchar(255) not null comment '日志内容',
    create_time bigint not null comment '发生时间'
);

# 参加考试学生名单
create table if not exists student(
    id int primary key auto_increment comment '学生编号',
    name varchar(255) not null comment '学生姓名',
    student_number char(10) not null comment '学生学号',
    examination_id int not null comment '考试编号',
    last_login_ip varchar(255) null comment '学生上次登录IP'
);

create table if not exists admin(
    id int primary key auto_increment comment '管理员编号',
    name varchar(255) not null comment '管理员名称',
    password varchar(255) not null comment '管理员密码',
    last_ip varchar(255) null comment '上次登录IP'
);

insert into admin(name, password) value ('root', '123456')