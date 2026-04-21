-- ============================================
-- SISTEMA DE GESTIÓN DE INVENTARIO
-- Base de Datos MySQL para Spring Boot
-- ============================================

DROP DATABASE IF EXISTS inventario_db;
CREATE DATABASE inventario_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE inventario_db;

-- ============================================
-- TABLA USUARIOS
-- ============================================

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role ENUM('ADMINISTRADOR','ENCARGADO_BODEGA','EMPLEADO') NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================
-- TABLA CATEGORIAS
-- ============================================

CREATE TABLE categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT,
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- TABLA PROVEEDORES
-- ============================================

CREATE TABLE proveedores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    contacto VARCHAR(100),
    email VARCHAR(100),
    telefono VARCHAR(20),
    direccion TEXT,
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- TABLA PRODUCTS (IMPORTANTE PARA SPRING)
-- ============================================

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(200) NOT NULL,
    descripcion TEXT,
    categoria_id BIGINT,
    proveedor_id BIGINT,
    precio DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    stock_minimo INT DEFAULT 0,
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (categoria_id) REFERENCES categorias(id),
    FOREIGN KEY (proveedor_id) REFERENCES proveedores(id)
);

-- ============================================
-- TABLA MOVIMIENTOS
-- ============================================

CREATE TABLE movimientos_inventario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    tipo ENUM('ENTRADA','SALIDA') NOT NULL,
    cantidad INT NOT NULL,
    stock_anterior INT NOT NULL,
    stock_nuevo INT NOT NULL,
    motivo VARCHAR(255),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- ============================================
-- DATOS DE PRUEBA
-- ============================================

INSERT INTO usuarios(username,password,email,role) VALUES
('admin','admin123','admin@inventario.com','ADMINISTRADOR'),
('bodeguero','bodega123','bodega@inventario.com','ENCARGADO_BODEGA'),
('empleado1','empleado123','empleado@inventario.com','EMPLEADO');

INSERT INTO categorias(nombre,descripcion) VALUES
('Electrónica','Dispositivos electrónicos'),
('Oficina','Papelería'),
('Alimentos','Productos alimenticios'),
('Limpieza','Productos limpieza'),
('Herramientas','Equipos trabajo');

INSERT INTO proveedores(nombre,contacto,email,telefono,direccion) VALUES
('Distribuidora Tech SA','Juan Perez','ventas@techsa.com','5550101','Ciudad'),
('Suministros Office','Maria Garcia','info@office.com','5550102','Ciudad'),
('Alimentos del Valle','Carlos Rodriguez','contacto@valle.com','5550103','Ciudad');

INSERT INTO products(codigo,nombre,descripcion,categoria_id,proveedor_id,precio,stock,stock_minimo) VALUES

('ELEC001','Laptop Dell Inspiron','Laptop i5 8GB RAM',1,1,899.99,5,3),
('ELEC002','Mouse Logitech','Mouse inalámbrico USB',1,1,24.99,45,20),
('ELEC003','Teclado Mecánico RGB','Teclado gaming',1,1,79.99,15,5),

('OFI001','Resma Papel A4','500 hojas',2,2,4.99,2,10),
('OFI002','Bolígrafos Azules','Caja 50',2,2,12.99,18,5),

('ALI001','Café en grano','1kg premium',3,3,18.50,1,8),

('LIM001','Detergente 5L','Industrial',4,2,15.99,12,5),

('HER001','Taladro eléctrico','750W',5,1,125.00,8,3);

-- ============================================
-- MOVIMIENTOS
-- ============================================

INSERT INTO movimientos_inventario(product_id,usuario_id,tipo,cantidad,stock_anterior,stock_nuevo,motivo) VALUES
(1,2,'ENTRADA',10,0,10,'Compra inicial'),
(1,2,'SALIDA',5,10,5,'Venta'),
(2,2,'ENTRADA',50,0,50,'Compra'),
(2,3,'SALIDA',5,50,45,'Venta');

-- ============================================
-- VISTAS
-- ============================================

CREATE VIEW v_productos_stock_bajo AS
SELECT
p.id,
p.codigo,
p.nombre,
p.stock,
p.stock_minimo,
c.nombre categoria
FROM products p
JOIN categorias c ON p.categoria_id=c.id
WHERE p.stock <= p.stock_minimo;

CREATE VIEW v_valor_inventario AS
SELECT
p.codigo,
p.nombre,
p.stock,
p.precio,
(p.stock*p.precio) valor_total
FROM products p;

-- ============================================
-- INDICES
-- ============================================

CREATE INDEX idx_product_nombre ON products(nombre);
CREATE INDEX idx_product_stock ON products(stock);

SELECT 'BASE DE DATOS INVENTARIO CREADA CORRECTAMENTE' AS STATUS;