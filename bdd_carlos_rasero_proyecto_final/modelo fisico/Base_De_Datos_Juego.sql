DROP DATABASE IF EXISTS Juego;
CREATE DATABASE IF NOT EXISTS Juego;
USE Juego;

-- Crear tabla Habilidad
CREATE TABLE Habilidad (
    id_habilidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    poder INT,
    penalizacion_def INT,
    probabilidad_crit FLOAT
);

-- Crear tabla Jugador
CREATE TABLE Jugador (
    id_jugador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    vida INT,
    ataque INT,
    defensa INT
);

-- Crear tabla Item
CREATE TABLE Item (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    poder INT,
    mejora_combate INT
);

-- Crear tabla Jugador_Habilidad
CREATE TABLE Jugador_Habilidad (
    id_jugador INT,
    id_habilidad INT,
    PRIMARY KEY (id_jugador, id_habilidad),
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id_jugador),
    FOREIGN KEY (id_habilidad) REFERENCES Habilidad(id_habilidad)
);

-- Crear tabla Jugador_Item
CREATE TABLE Jugador_Item (
    id_jugador INT,
    id_item INT,
    PRIMARY KEY (id_jugador, id_item),
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id_jugador),
    FOREIGN KEY (id_item) REFERENCES Item(id_item)
);

-- Crear tabla Padre
CREATE TABLE Padre (
    id_jugador INT PRIMARY KEY,
    sexo VARCHAR(20),
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id_jugador)
);

-- Crear tabla Profesor
CREATE TABLE Profesor (
    id_jugador INT PRIMARY KEY,
    asignatura VARCHAR(20),
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id_jugador)
);

-- Crear tabla Alumno
CREATE TABLE Alumno (
    id_jugador INT PRIMARY KEY,
    curso VARCHAR(20),
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id_jugador)
);


