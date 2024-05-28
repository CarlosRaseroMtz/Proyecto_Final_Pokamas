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

-- Insert  Alumnos
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
(9, '1 DAM'),
(10, '1 DAM'),
(11, '1 DAM'),
(12, '1 DAM'),
(13, '1 DAM'),
(14, '1 DAM'),
(15, '1 DAM'),
(16, '1 DAM'),
(17, '1 DAM'),
(18, '1 DAM'), 
(19, '1 DAM'),
(20, 'Desertor'), 
(21, 'Desertor'),
(22, 'Desertor'),
(23, 'Desertor'); 

-- Insert  Padres
INSERT INTO Jugador (id_jugador, nombre, vida, ataque, defensa) VALUES
(24, 'Juana Isabel', 111, 22, 33),
(25, 'Jose Luis', 111, 22, 33),
(26, 'Maricarmen', 111, 22, 33),
(27, 'Joan Francisco', 111, 22, 33);

INSERT INTO Padre (id_jugador, sexo) VALUES
(24, 'madre'),
(25, 'padre'),
(26, 'madre'),
(27, 'padre');

-- Inserciones para habilidades de profesores
INSERT INTO Habilidad (id_habilidad, nombre, poder, penalizacion_def, probabilidad_crit) VALUES
(1, 'Elocuencia: Se lo camela básicamente', 20, 0, 0.20),
(2, 'Indignacion: Se indigna tanto que le dices vale.', 20, 0, 0.30),
(3, 'Ser Del AMPA: Prácticamente tiene acciones del colegio', 25, 3, 0.60),
(4, 'Justificar a mi hijo: Mi hijo ese dia estaba malo, lo juro.', 15, -3, 0.30);

-- Inserciones para habilidades de padres
INSERT INTO Habilidad (id_habilidad, nombre, poder, penalizacion_def, probabilidad_crit) VALUES
(5, 'Poner Examen Sorpresa: Nadie lo ve venir', 25, -3, 0.20),
(6, 'Enfado: Con razon por no estudiar', 20, -1, 0.30),
(7, 'Usar Moodle: La usa (pero a nadie le gusta*)', 15, 1, 0.50),
(8, 'Salvar: Pasa de un 4 a un 5', 20, 3, 0.40);

-- Inserciones para habilidades de alumnos
INSERT INTO Habilidad (id_habilidad, nombre, poder, penalizacion_def, probabilidad_crit) VALUES
(9, 'Mentir: Falló la habilidad*(Se mintió a sí mismo)', 10, -10, 0.50),
(10, 'Usar ChatGPT: Un gran poder conlleva una gran responsabilidad.', 25, -5, 0.20),
(11, 'Estudiar: Esta habilidad siempre acierta al objetivo', 25, 20, 0.40),
(12, 'Faltar: Hoy me quedo en casita.', 20, 3, 0.30);


-- Habilidades de Padres
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (1,'Beber Cerveza: Además muy fría', 25, 20);
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (2,'Ticket exlusivo del AMPA: Recuperas toda la salud, aumenta tu ataque y defensa mucho, desde ahora todos te respetan.', 50, 30);
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (3,'Tomar una tapita en el bar: Además una cerveza', 25, 30);
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (4,'Usa la chancla: Se pueden hacer muchas cosas con una chancla', 15, 20);

-- Habilidades de Profesores
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (5,'Beber Café: Gracias al cafe, aguanta 3H mas', 25, 20);
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (6,'Ir a la sala de profesores: Lugar Oculto*', 25, 30);
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (7,'Poner parte: En este instituto no toleramos los saltavallas', 15, 15);
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (8,'Tirar Internet: Obliga al jugador a tener que irse, sube mucho la salud', 55, 30);

-- Habilidades de Alumnos
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (9,'Tomar Merienda: El recreo es sagrado', 25, 20);
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (10,'Fumar: Recuperas todas las ganas de volver a clase.', 50, 30);
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (11,'Beber Bebida Energética: Te da un Boost a tus 5h de sueño', 25, 30);
INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (12,'Usar Cascos: La música es poder', 15, 20);
