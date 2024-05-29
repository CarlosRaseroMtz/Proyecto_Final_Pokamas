DROP DATABASE IF EXISTS Juego;
CREATE DATABASE IF NOT EXISTS Juego;
USE Juego;

CREATE TABLE Habilidad (
    id_habilidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    poder INT,
    penalizacion_def INT,
    probabilidad_crit FLOAT
);

CREATE TABLE Jugador (
    id_jugador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    vida INT,
    ataque INT,
    defensa INT
);

CREATE TABLE Item (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    poder INT,
    mejora_combate INT
);

CREATE TABLE Jugador_Habilidad (
    id_jugador INT,
    id_habilidad INT,
    PRIMARY KEY (id_jugador, id_habilidad),
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id_jugador) ON DELETE CASCADE,
    FOREIGN KEY (id_habilidad) REFERENCES Habilidad(id_habilidad) ON DELETE CASCADE
);

CREATE TABLE Jugador_Item (
    id_jugador INT,
    id_item INT,
    PRIMARY KEY (id_jugador, id_item),
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id_jugador) ON DELETE CASCADE,
    FOREIGN KEY (id_item) REFERENCES Item(id_item) ON DELETE CASCADE
);

CREATE TABLE Padre (
    id_jugador INT PRIMARY KEY,
    sexo VARCHAR(20),
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id_jugador) ON DELETE CASCADE
);

CREATE TABLE Profesor (
    id_jugador INT PRIMARY KEY,
    asignatura VARCHAR(20),
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id_jugador) ON DELETE CASCADE
);

CREATE TABLE Alumno (
    id_jugador INT PRIMARY KEY,
    curso VARCHAR(20),
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id_jugador) ON DELETE CASCADE
);

