CREATE DATABASE IF NOT EXISTS security_spring;

USE security_spring;

CREATE TABLE IF NOT EXISTS roles(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    descripcion VARCHAR(100),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS usuarios(
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(120) NOT NULL,
    nombre VARCHAR(120) NOT NULL,
    apellido VARCHAR(120),
    password VARCHAR(250) NOT NULL,
    fecha_nacimiento DATE,
    enabled TINYINT NULL DEFAULT 1,
    PRIMARY KEY(id),
    UNIQUE(username)
);

CREATE TABLE IF NOT EXISTS roles_usuarios(
    usuarios_id INT NOT NULL,
    roles_id INT NOT NULL,
    PRIMARY KEY (usuarios_id, roles_id),
    FOREIGN KEY (usuarios_id) REFERENCES usuarios(id),
    FOREIGN KEY (roles_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS empleados(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45),
    sueldo DOUBLE,
    usuarios_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (usuarios_id) REFERENCES usuarios(id)
);

-- POPULATIONS

-- roles
INSERT INTO roles(nombre,descripcion)
VALUES ('ROLE_ADMIN', 'administrador del sistema');

INSERT INTO roles(nombre,descripcion)
VALUES ('ROLE_USER', 'usuarios normales');

-- usuarios
INSERT INTO usuarios(username,nombre,apellido,password,
fecha_nacimiento,enabled)
VALUES ('julio.martinez@gmail.com','julio','martinez','$2a$10$CkiCsL6xujtHe7CHtkBCo.Or8fKu5erbQQcY22F/R4Gu3FgA71/Bi',
'1985-08-05', 1);

INSERT INTO usuarios(username,nombre,apellido,password,
fecha_nacimiento,enabled)
VALUES ('duvan.sotero@gmail.com','duvan','sotero','$2a$10$gqnPwEOosdDZN.UMal0imez5RPhv6AO6GtHYOokMXnK43m4M8Gxsq',
'1995-01-03', 1);
-- roles_usuarios
INSERT INTO roles_usuarios (usuarios_id, roles_id)
VALUES (1,1);

INSERT INTO roles_usuarios (usuarios_id, roles_id)
VALUES (1,2);

INSERT INTO roles_usuarios (usuarios_id, roles_id)
VALUES (2,2);

-- empleados
INSERT INTO empleados(nombre,apellido,sueldo,usuarios_id)
VALUES ('diego', 'ardila', 9000, 1);

INSERT INTO empleados(nombre,apellido,sueldo,usuarios_id)
VALUES ('jeisson', 'e', 7000, 1);