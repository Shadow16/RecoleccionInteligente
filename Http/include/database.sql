CREATE DATABASE smarttest;
USE smarttest;

CREATE TABLE usuarios(
	curp VARCHAR(18) UNIQUE NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	apellidos VARCHAR(50) NOT NULL,
	telefono VARCHAR(10) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(10) NOT NULL,
	municipio VARCHAR(50) NOT NULL,
	PRIMARY KEY(curp)
);

INSERT INTO usuarios VALUES("IAZN931118HJCNNS00", "Nestor Eduardo", "Infante Zenteno", "3310947905", "nestor_infante@hotmail.com", "123456", "Tlaquepaque");

