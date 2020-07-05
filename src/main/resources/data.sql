insert into user(password, user_name, email, role, password_last_changed, password_last_reset, updated_at, created_at)
values( '{bcrypt}$2a$10$yDXbkd7AhstMiUVb98GfVO48vrHAAh/zPzgS06TrL1YNwo61GkNcW', 'テストユーザー', 'test@example.com', 'ROLE_TEST', null, null, null, now());
