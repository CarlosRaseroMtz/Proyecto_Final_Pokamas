-- Consultas Proyecto Final

-- 5 consultas
-- 1. Media de la vida de los Alumnos
(SELECT AVG(j2.vida) AS 'Media de la vida'
 FROM Alumno a2
 JOIN Jugador j2 ON a2.id_jugador = j2.id_jugador);


-- 2. Profesores que imparten programación o lenguaje de marcas
SELECT 
    p.id_jugador, 
    j.nombre AS nombre_profesor, 
    p.asignatura 
FROM 
    Profesor p
JOIN 
    Jugador j ON p.id_jugador = j.id_jugador
WHERE 
    p.asignatura IN ('prog', 'html');


-- 3. Jugadores 'padre' con más de 25 de ataque
SELECT 
    p.id_jugador, 
    j.nombre AS nombre_padre, 
    p.sexo
FROM 
    Padre p
JOIN 
    Jugador j ON p.id_jugador = j.id_jugador
WHERE 
    j.nombre LIKE 'J%';



-- 4. Alumnos que hayan dejado el curso
SELECT 
    j.id_jugador, 
    j.nombre AS nombre_alumno, 
    a.curso
FROM 
    Alumno a
JOIN 
    Jugador j ON a.id_jugador = j.id_jugador
WHERE 
    a.curso = 'Desertor';


-- 5. Jugadores con vida por encima del promedio:
SELECT j.nombre AS Jugador, j.vida
FROM Jugador j
WHERE j.vida > (SELECT AVG(vida) FROM Jugador);

select * from jugador;

-- Actualizaciones
-- 1. Potenciar Habilidad
UPDATE Habilidad
SET poder = poder * 1.1
WHERE probabilidad_crit > 0.5;

-- 2. Nerfear Item
UPDATE Item
SET poder = poder * 0.9;

-- Borrado
-- 1. Borrar habilidades SIN asignar
DELETE FROM Habilidad
WHERE id_habilidad NOT IN (
    SELECT id_habilidad
    FROM Jugador_Habilidad
);

-- Vistas
-- 1. Vista de jugadores con sus habilidades y poder total:
CREATE VIEW Vista_Jugador_Habilidad AS
SELECT j.id_jugador, j.nombre AS Jugador, SUM(h.poder) AS Total_Poder_Habilidades
FROM Jugador j
JOIN Jugador_Habilidad jh ON j.id_jugador = jh.id_jugador
JOIN Habilidad h ON jh.id_habilidad = h.id_habilidad
GROUP BY j.id_jugador, j.nombre;

-- 2. Vista de los jugadores con sus items y poder total:
CREATE VIEW Vista_Jugador_Item AS
SELECT j.id_jugador, j.nombre AS Jugador, SUM(i.poder) AS Total_Poder_Items
FROM Jugador j
JOIN Jugador_Item ji ON j.id_jugador = ji.id_jugador
JOIN Item i ON ji.id_item = i.id_item
GROUP BY j.id_jugador, j.nombre;

-- Procedimientos y funcionesç
-- 1. Agregar un nuevo jugador
DELIMITER //

CREATE PROCEDURE AgregarJugador (
    IN nombreJugador VARCHAR(255),
    IN vidaJugador INT,
    IN ataqueJugador INT,
    IN defensaJugador INT
)
BEGIN
    INSERT INTO Jugador (nombre, vida, ataque, defensa)
    VALUES (nombreJugador, vidaJugador, ataqueJugador, defensaJugador);
END //

DELIMITER ;

-- 2. Asignar habilidad a jugador
DELIMITER //

CREATE PROCEDURE AsignarHabilidad (
    IN idJugador INT,
    IN idHabilidad INT
)
BEGIN
    INSERT INTO Jugador_Habilidad (id_jugador, id_habilidad)
    VALUES (idJugador, idHabilidad);
END //

DELIMITER ;

-- 3. Calcular poder total
DELIMITER //

CREATE FUNCTION CalcularPoderTotal (idJugador INT) RETURNS INT
BEGIN
    DECLARE poderTotal INT;

    SELECT COALESCE(SUM(h.poder), 0) + COALESCE(SUM(i.poder), 0)
    INTO poderTotal
    FROM Jugador j
    LEFT JOIN Jugador_Habilidad jh ON j.id_jugador = jh.id_jugador
    LEFT JOIN Habilidad h ON jh.id_habilidad = h.id_habilidad
    LEFT JOIN Jugador_Item ji ON j.id_jugador = ji.id_jugador
    LEFT JOIN Item i ON ji.id_item = i.id_item
    WHERE j.id_jugador = idJugador;

    RETURN poderTotal;
END //

DELIMITER ;

-- Disparadores
-- 1. Validar vida del jugador
DELIMITER //

CREATE TRIGGER ValidarVidaJugador
BEFORE UPDATE ON Jugador
FOR EACH ROW
BEGIN
    IF NEW.vida < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La vida del jugador no puede ser negativa';
    END IF;
END;
//

DELIMITER ;

-- 2. trigger de after muestra el nombre
DELIMITER //

CREATE TRIGGER DespuesDeInsertarJugador
AFTER INSERT ON Jugador
FOR EACH ROW
BEGIN
    SELECT 'Nuevo jugador insertado: ' AS Mensaje, NEW.nombre AS NombreJugador;
END;
//

DELIMITER ;
