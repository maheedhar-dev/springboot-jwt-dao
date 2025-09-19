-- Insert default roles
INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

-- Insert default users with BCrypt-encoded passwords
-- "password" and "admin" pre-encoded
INSERT INTO users (username, password, enabled) VALUES
('maheedhar', '$2a$10$YqJfIeqs1b8gBfE7wFmwrua4Qj0p8WUx0lm.KVJjP5mwb0IUZLcf2', true),
('admin', '$2a$10$Vh3e0oD3h6kQpVb2WydvKuY0OR7U3HhTCyQ88ItDdV7Nq3.6l5mBS', true);

-- Assign roles
INSERT INTO user_roles (user_id, role_id)
VALUES
((SELECT id FROM users WHERE username='maheedhar'), (SELECT id FROM roles WHERE name='ROLE_USER')),
((SELECT id FROM users WHERE username='admin'), (SELECT id FROM roles WHERE name='ROLE_ADMIN'));