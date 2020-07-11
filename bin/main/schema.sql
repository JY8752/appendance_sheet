drop table if exists user;

create table lessonsdb.user(
    user_id int primary key auto_increment,
    password varchar(100) not null,
    user_name varchar(50) not null,
    email varchar(100) not null unique,
    role char(1) not null,
    password_last_changed datetime,
    password_last_reset datetime,
    updated_at datetime,
    created_at datetime not null
);