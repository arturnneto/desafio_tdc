INSERT INTO roles (role_id, role_name)
VALUES (1, 'admin')
ON CONFLICT (role_id) DO NOTHING;

INSERT INTO roles (role_id, role_name)
VALUES (2, 'normal')
ON CONFLICT (role_id) DO NOTHING;;
