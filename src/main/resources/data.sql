-- DIRÍA QUE ESTE ARCHIVO ES PARA DESARROLLO SOLAMENTE: --

-- Datos iniciales para GenomeBank
-- Este archivo se ejecuta automáticamente al iniciar la aplicación

-- Insertar roles
INSERT INTO Rol (id, name) VALUES (1, 'ADMIN') ON DUPLICATE KEY UPDATE name=name;
INSERT INTO Rol (id, name) VALUES (2, 'USER') ON DUPLICATE KEY UPDATE name=name;

-- Insertar usuario ADMIN
-- Email: admin@genomebank.com
-- Password: password123
INSERT INTO User (id, name, email, password, active) 
VALUES (1, 'Admin User', 'admin@genomebank.com', '$2a$10$MsG6rH6ROBjx2E1fddHP4O1fWLEkh9VX4TA2eHWgzb06UpGRwgGVm', true)
ON DUPLICATE KEY UPDATE name=name;

-- Insertar usuario USER
-- Email: user@genomebank.com
-- Password: password123
INSERT INTO User (id, name, email, password, active) 
VALUES (2, 'Regular User', 'user@genomebank.com', '$2a$10$MsG6rH6ROBjx2E1fddHP4O1fWLEkh9VX4TA2eHWgzb06UpGRwgGVm', true)
ON DUPLICATE KEY UPDATE name=name;

-- Asignar roles a usuarios
INSERT INTO Rol_User (user_id, rol_id) VALUES (1, 1) ON DUPLICATE KEY UPDATE user_id=user_id;
INSERT INTO Rol_User (user_id, rol_id) VALUES (2, 2) ON DUPLICATE KEY UPDATE user_id=user_id;