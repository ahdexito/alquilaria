DELIMITER //
CREATE PROCEDURE sp_modifyPropietario(
    IN p_id INT,
    IN p_DNI VARCHAR(10),
	IN p_nombre VARCHAR(20),
	IN p_apellidos VARCHAR(50),
	IN p_email VARCHAR(100),
	IN p_telefono VARCHAR(15)
)
BEGIN
	-- MODIFICAR DATOS DE CLIENTE DADO EL ID, ADAPTATIVO
    UPDATE cliente
    SET 
        DNI = IF(p_DNI = '' OR p_DNI IS NULL, DNI, p_DNI),
        nombre = IF(p_nombre = '' OR p_nombre IS NULL, nombre, p_nombre),
        apellidos = IF(p_apellidos = '' OR p_apellidos IS NULL, apellidos, p_apellidos),
        email = IF(p_email = '' OR p_email IS NULL, email, p_email),
        telefono = IF(p_telefono = '' OR p_telefono IS NULL, telefono, p_telefono)
    WHERE id = p_id;
END //