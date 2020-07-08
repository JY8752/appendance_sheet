insert into user(password, user_name, email, role, password_last_changed, password_last_reset, updated_at, created_at)
values('$2a$10$qh0Z0u5XYLwWZCmDY6qtR.z8MDFm3sMCPxt6qU.DdoLcUviOQ2sDq', 'テストユーザー', 'test@example.com', 'ROLE_TEST', null, null, null, now());
