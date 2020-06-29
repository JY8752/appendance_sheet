drop table if exists employee;
drop table if exists m_user;

create table employee(
  employee_id int primary key not null auto_increment,
  employee_name varchar(50),
  age int
);

create table m_user(
    user_id varchar(50) primary key,
    password varchar(100),
    user_name varchar(50),
    birthday date,
    age int,
    marrige boolean,
    role varchar(50)
);