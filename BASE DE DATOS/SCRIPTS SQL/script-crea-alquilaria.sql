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
    apellidos VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    
    CONSTRAINT uk_dni_propietario
		UNIQUE KEY(dni)
    );

CREATE TABLE tipo_vivienda (
	numero INT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
	);

CREATE TABLE vivienda (
	cod VARCHAR(10) PRIMARY KEY,
    id_propietario INT UNSIGNED,
    direccion VARCHAR(150) NOT NULL,
    precio FLOAT UNSIGNED DEFAULT 0,
    superficie FLOAT UNSIGNED DEFAULT 0,
    descripcion VARCHAR(300),
    mascotas BOOL DEFAULT 1,
    tipo INT,
    
    CONSTRAINT fk_id_propietario_vivienda 
		FOREIGN KEY(id_propietario) REFERENCES propietario(id)
    ON DELETE CASCADE,
    CONSTRAINT fk_tipo_vivienda
		FOREIGN KEY(tipo) REFERENCES tipo_vivienda(numero)
    ON DELETE CASCADE
    );

CREATE TABLE inquilino (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(10) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    mascotas BOOL DEFAULT 0,
    
    CONSTRAINT uk_dni_inquilino
		UNIQUE KEY(dni)
    );
   
CREATE TABLE contrato (
	id_inquilino INT UNSIGNED,
    cod_vivienda VARCHAR(10),
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    precio FLOAT UNSIGNED DEFAULT 0,
    estado ENUM('PENDIENTE', 'ACTIVO', 'VENCIDO') DEFAULT 'PENDIENTE',
    
    PRIMARY KEY(id_inquilino, cod_vivienda),
	CONSTRAINT fk_id_inquilino_contrato
		FOREIGN KEY(id_inquilino) REFERENCES inquilino(id)
    ON DELETE CASCADE,
	CONSTRAINT fk_cod_vivienda_contrato 
		FOREIGN KEY(cod_vivienda) REFERENCES vivienda(cod)
    ON DELETE CASCADE
    );