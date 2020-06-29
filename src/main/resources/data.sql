insert into employee(employee_id, employee_name, age)
values(1, '山田太郎', 30);

-- アドミン権限
insert into m_user(
    user_id,
    password,
    user_name,
    birthday,
    age,
    marrige,
    role
)
values(
    'yamada@example.com',
    '$2a$10$7WwzJtw1PUd.CzgXfNw0TOpOs2z5dVeNL0hpJ28HBZs0kzXjmPu5W',
    '山田太郎',
    '1990-01-01',
    28,
    false,
    'ROLE_ADMIN'
);

-- 一般権限
insert into m_user(
    user_id,
    password,
    user_name,
    birthday,
    age,
    marrige,
    role
)
values(
    'tamura@example.com',
    '$2a$10$7WwzJtw1PUd.CzgXfNw0TOpOs2z5dVeNL0hpJ28HBZs0kzXjmPu5W',
    '田村達也',
    '1986-11-015',
    31,
    false,
    'ROLE_GENERAL'
);