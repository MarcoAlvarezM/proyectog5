DROP DATABASE IF EXISTS inventario_db;
CREATE DATABASE inventario_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE inventario_db;


-- TABLAS

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    email VARCHAR(100),
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE user_roles (
    user_id BIGINT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE,
    nombre VARCHAR(200),
    descripcion TEXT,
    categoria VARCHAR(100),
    precio DECIMAL(10,2),
    stock INT,
    stock_minimo INT
);


-- ROLES

INSERT INTO roles (name) VALUES
('ADMIN'),
('EMPLOYEE'),
('USER');


-- USERS (contraseña: admin123)

INSERT INTO users (username, password, active, email, first_name, last_name) VALUES
('admin', '$2a$10$QpvmTrVqs1ZThWJcseU4XOjjuA7xaFFeotzHO.CY1wHTbdrIJ31eC', true, 'admin@test.com', 'Admin', 'Principal'),
('employee', '$2a$10$QpvmTrVqs1ZThWJcseU4XOjjuA7xaFFeotzHO.CY1wHTbdrIJ31eC', true, 'employee@test.com', 'Empleado', 'General'),
('user', '$2a$10$QpvmTrVqs1ZThWJcseU4XOjjuA7xaFFeotzHO.CY1wHTbdrIJ31eC', true, 'user@test.com', 'Usuario', 'Normal');


-- USER_ROLES

INSERT INTO user_roles VALUES 
(1,1),  -- admin -> ADMIN
(2,2),  -- employee -> EMPLOYEE
(3,3);  -- user -> USER


-- PRODUCTS

INSERT INTO products (codigo, nombre, descripcion, categoria, precio, stock, stock_minimo) VALUES
('P001','Laptop Dell','Laptop i5 8GB RAM','Electronics',899.99,5,2),
('P002','Mouse Logitech','Mouse inalámbrico USB','Electronics',24.99,45,20),
('P003','Teclado Mecánico','Teclado gaming RGB','Electronics',79.99,15,5),
('P004','Resma Papel A4','500 hojas','Office',4.99,2,10),
('P005','Caja de Lapiceros','50 lapiceros azules','Office',12.99,18,5),
('P006','Café Premium','1kg','Food',18.50,1,8);


-- INDEXES

CREATE INDEX idx_product_name ON products(nombre);
CREATE INDEX idx_product_stock ON products(stock);
CREATE INDEX idx_product_code ON products(codigo);


-- VERIFICACIÓN


SELECT * FROM users;
SELECT * FROM roles;
SELECT * FROM user_roles;
SELECT * FROM products;