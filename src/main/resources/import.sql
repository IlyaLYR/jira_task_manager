-- Тестовый пользователь: login=testuser, password=password
INSERT INTO users (id, login, password_hash, created_at, updated_at)
VALUES (
    'a0000000-0000-0000-0000-000000000001',
    'testuser',
    '$2a$10$a99EY7yN463XON/HlpPxD.rG9qT6bjOMY8MuSBkkrNrxzsshdHXJy',
    now(), now()
);

-- Тестовый проект
INSERT INTO projects (id, name, description, owner_id, created_at, updated_at)
VALUES (
    'b0000000-0000-0000-0000-000000000001',
    'Test Project',
    'Seed project for dev testing',
    'a0000000-0000-0000-0000-000000000001',
    now(), now()
);
