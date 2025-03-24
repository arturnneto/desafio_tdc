CREATE TABLE IF NOT EXISTS roles (
    role_id BIGINT PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL
);

MERGE INTO roles (role_id, role_name)
KEY(role_id)
VALUES (1, 'admin');

MERGE INTO roles (role_id, role_name)
KEY(role_id)
VALUES (2, 'basic');