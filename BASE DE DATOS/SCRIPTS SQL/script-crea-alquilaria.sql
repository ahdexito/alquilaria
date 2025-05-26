DROP DATABASE IF EXISTS alquilaria;

CREATE DATABASE alquilaria;
USE alquilaria;

-- ---------------------------------------
-- TIPOS DE DATOS PREDEFINIDOS
-- ---------------------------------------
-- NOMBRE: 			VARCHAR(20)
-- APELLIDOS:		VARCHAR(50)
-- DNI:				VARCHAR(10)
-- CORREO:			VARCHAR(100)
-- TELEFONO:		VARCHAR(15)
-- DIRECCION:		VARCHAR(150)
-- DESCRIPCION:		VARCHAR(300)
-- ---------------------------------------

CREATE TABLE propietario (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(10) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellidos VARCHAR(50),
    correo VARCHAR(100),
    telefono VARCHAR(15),
    
    CONSTRAINT uk_dni_propietario
		UNIQUE KEY(dni)
    );

CREATE TABLE tipo_vivienda (
	numero INT PRIMARY KEY,
    nombre VARCHAR(50)
	);

CREATE TABLE vivienda (
	cod VARCHAR(10) PRIMARY KEY,
    id_propietario INT UNSIGNED,
    direccion VARCHAR(150),
    precio FLOAT,
    superficie FLOAT,
    descripcion VARCHAR(300),
    mascotas BOOL,
    tipo INT,
    
    CONSTRAINT fk_id_propietario_vivienda 
		FOREIGN KEY(id_propietario) REFERENCES propietario(id),
    CONSTRAINT fk_tipo_vivienda
		FOREIGN KEY(tipo) REFERENCES tipo_vivienda(numero)
    );

CREATE TABLE inquilino (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(10),
    nombre VARCHAR(20),
    apellidos VARCHAR(50),
    correo VARCHAR(100),
    telefono VARCHAR(15),
    mascota BOOL,
    
    CONSTRAINT uk_dni_inquilino
		UNIQUE KEY(dni)
    );
   
CREATE TABLE contrato (
	id_inquilino INT UNSIGNED,
    cod_vivienda VARCHAR(10),
    fecha_inicio DATE,
    fecha_fin DATE,
    precio FLOAT DEFAULT 0,
    estado ENUM('PENDIENTE', 'ACTIVO', 'VENCIDO') DEFAULT 'PENDIENTE',
    
    PRIMARY KEY(id_inquilino, cod_vivienda, fecha_inicio),
	CONSTRAINT fk_id_inquilino_contrato
		FOREIGN KEY(id_inquilino) REFERENCES inquilino(id),
	CONSTRAINT fk_cod_vivienda_contrato 
		FOREIGN KEY(cod_vivienda) REFERENCES vivienda(cod)
    );