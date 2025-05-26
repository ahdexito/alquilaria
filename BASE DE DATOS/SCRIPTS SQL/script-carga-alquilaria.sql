USE alquilaria;


SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM contrato;
DELETE FROM vivienda;
DELETE FROM inquilino;
DELETE FROM propietario;
DELETE FROM tipo_vivienda; 

SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO propietario VALUES
	(1, '12345678A', 'Luis', 'García Pérez', 'luis.garcia@gmail.com', '600123456'),
	(2, '23456789B', 'María', 'López Fernández', 'maria.lopez@outlook.com', '600234567'),
	(3, '34567890C', 'Carlos', 'Martínez Ruiz', 'carlos.martinez@yahoo.com', '600345678'),
	(4, '45678901D', 'Ana', 'Sánchez Gómez', 'ana.sanchez@hotmail.com', '600456789'),
	(5, '56789012E', 'Pedro', 'Gómez Díaz', 'pedro.gomez@gmail.com', '600567890'),
	(6, '67890123F', 'Laura', 'Rodríguez Moreno', 'laura.rodriguez@outlook.com', '600678901'),
	(7, '78901234G', 'Javier', 'Fernández Torres', 'javier.fernandez@yahoo.com', '600789012'),
	(8, '89012345H', 'Sofía', 'Jiménez Ortega', 'sofia.jimenez@hotmail.com', '600890123'),
	(9, '90123456I', 'Miguel', 'Ruiz Vargas', 'miguel.ruiz@gmail.com', '600901234'),
	(10, '01234567J', 'Elena', 'Morales Castro', 'elena.morales@outlook.com', '601012345'),
	(11, '11223344K', 'David', 'Núñez Herrera', 'david.nunez@yahoo.com', '601123456'),
	(12, '22334455L', 'Isabel', 'Díaz Jiménez', 'isabel.diaz@hotmail.com', '601234567'),
	(13, '33445566M', 'Raúl', 'Vargas Sánchez', 'raul.vargas@gmail.com', '601345678'),
	(14, '44556677N', 'Marta', 'Castillo Blanco', 'marta.castillo@outlook.com', '601456789'),
	(15, '55667788O', 'Jorge', 'Blanco Morales', 'jorge.blanco@yahoo.com', '601567890');

INSERT tipo_vivienda VALUES
	(1, 'apartamento'),
    (2, 'ático'),
    (3, 'casa');
 
INSERT INTO vivienda VALUES
	('V001', 1, 'Calle Mayor 12, Madrid', 850.00, 75.5, 'Luminoso y céntrico', 0, 1),
	('V002', 2, 'Av. de la Constitución 45, Sevilla', 920.50, 82.3, 'Ideal para parejas', 0, 2),
	('V003', 3, 'Calle del Sol 3, Valencia', 700.00, 65.0, 'Reformado recientemente', 1, 1),
	('V004', 4, 'Paseo de Gracia 22, Barcelona', 1150.75, 95.0, 'Con terraza amplia', 0, 2),
	('V005', 5, 'Calle Luna 18, Granada', 600.00, 58.2, 'Barrio tranquilo', 1, 1),
	('V006', 6, 'Calle Jardines 7, Málaga', 800.00, 70.0, 'Vistas al mar', 1, 3),
	('V007', 7, 'Calle Real 10, Zaragoza', 950.00, 89.5, 'Con garaje y trastero', 0, 2),
	('V008', 8, 'Av. del Mar 11, Alicante', 740.25, 67.0, 'Amueblado completamente', 0, 1),
	('V009', 9, 'Calle San Juan 8, Bilbao', 680.00, 60.0, 'Muy bien comunicado', 0, 1),
	('V010', 10, 'Av. del Cid 33, Valencia', 820.00, 72.8, 'Cocina equipada', 1, 2),
	('V011', 11, 'Calle Velázquez 55, Madrid', 1100.00, 93.5, 'Zona de lujo', 0, 2),
	('V012', 12, 'Plaza Mayor 6, Salamanca', 650.00, 61.2, 'Ideal estudiantes', 1, 1),
	('V013', 13, 'Calle del Norte 14, León', 720.00, 69.7, 'Muy luminoso', 0, 1),
	('V014', 14, 'Camino de Ronda 29, Granada', 590.00, 55.5, 'Perfecto para familias pequeñas', 1, 3),
	('V015', 15, 'Calle Sevilla 8, Cádiz', 780.00, 71.4, 'Recién pintado', 1, 1);
    
INSERT INTO inquilino VALUES
	(1, '98765432Z', 'Laura', 'Fernández Ruiz', 'laura.fernandez@gmail.com', '610123456', 0),
	(2, '87654321Y', 'Diego', 'Martínez López', 'diego.martinez@outlook.com', '610234567', 0),
	(3, '76543210X', 'Carla', 'Santos García', 'carla.santos@yahoo.com', '610345678', 1),
	(4, '65432109W', 'Ángel', 'Pérez Sánchez', 'angel.perez@hotmail.com', '610456789', 0),
	(5, '54321098V', 'Nuria', 'Ramírez Díaz', 'nuria.ramirez@gmail.com', '610567890', 1),
	(6, '43210987U', 'Iván', 'Moreno Castillo', 'ivan.moreno@outlook.com', '610678901', 1),
	(7, '32109876T', 'Sara', 'Gil Ortega', 'sara.gil@yahoo.com', '610789012', 0),
	(8, '21098765S', 'Jorge', 'Blanco Herrera', 'jorge.blanco@hotmail.com', '610890123', 0),
	(9, '10987654R', 'Eva', 'Vargas Morales', 'eva.vargas@gmail.com', '610901234', 0),
	(10, '19876543Q', 'Luis', 'Domínguez Ruiz', 'luis.dominguez@outlook.com', '611012345', 1),
	(11, '08765432P', 'Clara', 'Medina Torres', 'clara.medina@yahoo.com', '611123456', 0),
	(12, '97654321O', 'Raúl', 'Fuentes Jiménez', 'raul.fuentes@hotmail.com', '611234567', 1),
	(13, '86543210N', 'Marta', 'Cruz Vargas', 'marta.cruz@gmail.com', '611345678', 0),
	(14, '75432109M', 'Héctor', 'Lara Santos', 'hector.lara@outlook.com', '611456789', 1),
	(15, '64321098L', 'Silvia', 'Castillo Gómez', 'silvia.castillo@yahoo.com', '611567890', 1);

INSERT INTO contrato VALUES
	(1,  'V001', '2024-01-01', '2025-01-01', 850.00, 'ACTIVO'),
	(2,  'V002', '2023-05-15', '2024-05-15', 1120.20, 'VENCIDO'),
	(3,  'V003', '2024-06-01', '2025-06-01', 700.00, 'PENDIENTE'),
	(4,  'V004', '2024-03-20', '2025-03-20', 1150.75, 'ACTIVO'),
	(5,  'V005', '2023-04-01', '2024-04-01', 800.00, 'VENCIDO'),
	(6,  'V006', '2024-02-10', '2025-02-10', 950.00, 'PENDIENTE'),
	(7,  'V007', '2024-01-01', '2025-01-01', 785.50, 'ACTIVO'),
	(8, 'V008', '2023-06-15', '2024-06-15', 820.00, 'VENCIDO'),
	(9, 'V009', '2024-04-01', '2025-04-01', 734.95, 'ACTIVO'),
	(10, 'V010', '2024-02-15', '2025-02-15', 720.00, 'ACTIVO'),
	(11, 'V011', '2024-01-10', '2025-01-10', 780.00, 'PENDIENTE'),
	(12,  'V012', '2023-05-01', '2024-05-01', 810.00, 'VENCIDO'),
	(13,  'V013', '2024-03-05', '2025-03-05', 900.00, 'ACTIVO'),
	(14,  'V014', '2024-02-01', '2025-02-01', 730.00, 'PENDIENTE'),
	(15,  'V015', '2023-04-10', '2024-04-10', 990.00, 'VENCIDO');
    
SELECT * FROM
	(SELECT COUNT(*) AS 'propietarios' FROM propietario) porpietario, 
    (SELECT COUNT(*) AS 'tipos de vivienda' FROM tipo_vivienda) tipo_vivienda,
    (SELECT COUNT(*) AS 'viviendas' FROM vivienda) vivienda,
    (SELECT COUNT(*) AS 'inquilinos' FROM inquilino) inquilino,
	(SELECT COUNT(*) AS 'contratos' FROM contrato) contrato;