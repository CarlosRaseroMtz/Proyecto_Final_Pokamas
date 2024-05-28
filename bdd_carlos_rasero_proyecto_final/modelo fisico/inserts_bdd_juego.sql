USE Juego;

-- Insert Profesores
INSERT INTO Jugador (id_jugador, nombre, vida, ataque, defensa) VALUES
(1, 'Angelica', 150, 30, 50),
(2, 'Angelica', 150, 30, 50),
(3, 'Angelica', 150, 30, 50),
(4, 'Gema', 130, 50, 40),
(5, 'Gema', 130, 50, 40),
(6, 'laDeFol', 120, 25, 35);

INSERT INTO Profesor (id_jugador, asignatura) VALUES
(1, 'Prog'),
(2, 'Bdd'),
(3, 'Ed'),
(4, 'Html'),
(5, 'Si'),
(6, 'Fol');

-- Insert predefined Alumnos
INSERT INTO Jugador (id_jugador, nombre, vida, ataque, defensa) VALUES
(7, 'Carlos MañanaDejoDeFumar', 140, 40, 40),
(8, 'Ale Machaca', 50, 100, 30),
(9, 'Ricardo NoFaltes', 100, 30, 30),
(10, 'Pedro Tengo2Asignaturas', 100, 30, 30),
(11, 'Manuel vape', 100, 30, 30),
(12, 'Manuel cigarro', 100,30, 30),
(13, 'Javi Preguntas', 100, 30, 30),
(14, 'Ruben Responsable', 100, 30, 30),
(15, 'Jimmie Teclado', 100, 30, 30),
(16, 'Jaime Calmado', 100, 30, 30),
(17, 'Pablo AhoraFumaMenos', 100, 30, 30),
(18, 'Hugo Sobrao\'', 100, 30, 30),
(19, 'Maria Delegada en Funciones', 100, 30, 30),
(20, 'Caido en combate Pablo', 100, 30, 30),
(21, 'Legendario Francisco Vallas', 100,30, 30),
(22, 'Un tal Alberto', 100, 30, 30),
(23, 'La chica del fondo a la izquierda que se fue', 100, 30, 30);

INSERT INTO Alumno (id_jugador, curso) VALUES
(7, '1 DAM'),
(8, '1 DAM'),
(9, '1 DAM'), -- Replace with actual course if different
(10, '1 DAM'), -- Replace with actual course if different
(11, '1 DAM'), -- Replace with actual course if different
(12, '1 DAM'), -- Replace with actual course if different
(13, '1 DAM'), -- Replace with actual course if different
(14, '1 DAM'), -- Replace with actual course if different
(15, '1 DAM'), -- Replace with actual course if different
(16, '1 DAM'), -- Replace with actual course if different
(17, '1 DAM'), -- Replace with actual course if different
(18, '1 DAM'), -- Replace with actual course if different
(19, '1 DAM'), -- Replace with actual course if different
(20, '1 DAM'), -- Replace with actual course if different
(21, '1 DAM'), -- Replace with actual course if different
(22, '1 DAM'), -- Replace with actual course if different
(23, '1 DAM'); -- Replace with actual course if different

-- Insert predefined Padres
INSERT INTO Jugador (id_jugador, nombre, vida, ataque, defensa) VALUES
(24, 'Isabel', 111, 22, 33),
(25, 'Jose Luis', 111, 22, 33),
(26, 'Maricarmen', 111, 22, 33),
(27, 'Francisco', 111, 22, 33);

INSERT INTO Padre (id_jugador, sexo) VALUES
(24, 'madre'),
(25, 'padre'),
(26, 'madre'),
(27, 'padre');
