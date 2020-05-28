CREATE DATABASE BJE;
USE BJE;
CREATE USER 'gamelandIvan'@'localhost' IDENTIFIED BY 'Ageofempires2';

GRANT INSERT, UPDATE, SELECT, UPDATE ON BJE.* TO 'imcivan'@'localhost';

CREATE TABLE ROL(
ID INT NOT NULL auto_increment,
NOMBRE VARCHAR(10) NOT NULL,
primary key (ID)
);

INSERT INTO ROL(NOMBRE) VALUES ('TRABAJADOR'), ('MANAGER'), ('GERENTE');

CREATE TABLE LOCALIZACION(
ID INT NOT NULL auto_increment,
NOMBRE VARCHAR(50),
primary key (ID)

);


INSERT INTO LOCALIZACION(NOMBRE) VALUES ('SANTA PONSA'), ('MAGALUF'), ('PALMANOVA');

CREATE TABLE RESTAURANTE(
ID INT NOT NULL auto_increment,
LOCALIZACION INT NOT NULL,
NOMBRE VARCHAR(30) NOT NULL,
primary key (ID),
FOREIGN KEY (LOCALIZACION) REFERENCES LOCALIZACION(ID)

);

INSERT INTO RESTAURANTE(LOCALIZACION, NOMBRE) VALUES( 1, 'RES SANTA'), (2, 'RES MAGALUF');


CREATE TABLE USUARIO (
ID INT NOT NULL auto_increment,
CORREO VARCHAR(50) NOT NULL,
NOMBRE VARCHAR(20),
APELLIDO VARCHAR(20),
PASS varchar(20) NOT NULL,
ROL INT,
OBSERVACIONES VARCHAR(300),
RESTAURANTE INT,
ACTIVO INT(1) NOT NULL DEFAULT(0),

primary key (ID),
foreign key (ROL) references ROL(ID),
FOREIGN KEY (RESTAURANTE) REFERENCES RESTAURANTE(ID) ON DELETE CASCADE

);


INSERT INTO USUARIO(CORREO, NOMBRE, APELLIDO, PASS, ROL, OBSERVACIONES, RESTAURANTE, ACTIVO)
 VALUES ('ivanjl4444@hotmail.com', 'IVAN', 'JIMENEZ', '123', 1, 'ES UN FIGURA BEST COCINERO', 1, 1), 
		('epygma@hotmail.com', 'ANDREW', 'BYRNE', '123', 2, 'ES IRLANDES', 1, 0);
        

INSERT INTO USUARIO(CORREO, NOMBRE, APELLIDO, PASS, ROL, RESTAURANTE, ACTIVO)
 VALUES ('miguel@hotmail.com', 'MIGUEL', 'MIGUEL', '123', 3, 1, 1);
 
 CREATE TABLE HORARIO(
ID INT NOT NULL auto_increment,
ACTIVO INT NOT NULL DEFAULT 0,
MES INT NOT NULL,
ANYO INT NOT NULL,
PRIMARY KEY(ID)

);

INSERT INTO HORARIO(ACTIVO, MES, ANYO) VALUES(1,3,2018);

 CREATE TABLE DIA(
ID INT NOT NULL auto_increment,
FECHA DATE NOT NULL,
ENTRADA_1 TIME,
SALIDA_1 TIME,
ENTRADA_2 TIME,
SALIDA_2 TIME,
USUARIO INT NOT NULL,
IDHORARIO int not null,
primary key (ID),
foreign key (USUARIO) REFERENCES USUARIO(ID) ON DELETE CASCADE,
foreign key (IDHORARIO) REFERENCES HORARIO(ID) ON DELETE CASCADE

);


INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-01', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );

INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO , IDHORARIO)
	VALUES( '2018-03-02', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
INSERT INTO DIA (FECHA, USUARIO, IDHORARIO)
	VALUES( '2018-03-03', 1 , 1);
    
    INSERT INTO DIA (FECHA,USUARIO, IDHORARIO)
	VALUES( '2018-03-04', 1, 1 );


INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-05', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1 , 1);

INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-06', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-07', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1 , 1);
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-08', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-09', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );

INSERT INTO DIA (FECHA, USUARIO, IDHORARIO)
	VALUES( '2018-03-10', 1, 1 );
    
    INSERT INTO DIA (FECHA,USUARIO, IDHORARIO)
	VALUES( '2018-03-11', 1, 1 );


INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-12', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );

INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-13', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-14', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-15', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-16', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );

INSERT INTO DIA (FECHA, USUARIO, IDHORARIO)
	VALUES( '2018-03-17', 1, 1 );
    
    INSERT INTO DIA (FECHA,USUARIO, IDHORARIO)
	VALUES( '2018-03-18', 1, 1 );


INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-19', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );

INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-20', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-21', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-22', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-23', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );

INSERT INTO DIA (FECHA, USUARIO, IDHORARIO)
	VALUES( '2018-03-24', 1, 1 );
    
    INSERT INTO DIA (FECHA,USUARIO, IDHORARIO)
	VALUES( '2018-03-25', 1, 1 );


INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-26', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );

INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-27', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-28', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-29', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );
    
    INSERT INTO DIA (FECHA, ENTRADA_1, SALIDA_1, ENTRADA_2, SALIDA_2, USUARIO, IDHORARIO)
	VALUES( '2018-03-30', '08:00:00', '12:00:00', '12:30:00', '16:00:00', 1, 1 );

INSERT INTO DIA (FECHA, USUARIO, IDHORARIO)
	VALUES( '2018-03-31', 1, 1 );
    
    
        CREATE TABLE CATEGORIA(
    ID INT NOT NULL auto_increment,
    NOMBRE VARCHAR(20) NOT NULL,
    
    PRIMARY KEY(ID)
    
    );
    
    INSERT INTO CATEGORIA(NOMBRE) VALUES("PAN"),("VEGETAL"),("CARNE"),("SALSA");
    
    CREATE TABLE ALIMENTO(
    ID INT NOT NULL auto_increment,
    NOMBRE VARCHAR(20) NOT NULL,
    TIEMPO int (3) NOT NULL,
    IDCATEGORIA INT NOT NULL,
    
    primary key (ID),
    foreign key(IDCATEGORIA) REFERENCES CATEGORIA(ID)
    
    
    );

INSERT INTO ALIMENTO(NOMBRE,IDCATEGORIA, TIEMPO) VALUES("PAN CUARTO",1, 100), ("LECHUGA",2, 1),("CARNE CUARTO", 3, 20), ("SALSA QUESO", 4, 4), ("PAN NORMAL",1, 50);

CREATE TABLE TIMER(
ID INT NOT NULL auto_increment,
IDALIMENTO INT NOT NULL,
FECHA DATETIME NOT NULL,
IDRESTAURANTE INT NOT NULL DEFAULT 1,
PRIMARY KEY (ID),
foreign key (IDRESTAURANTE) REFERENCES RESTAURANTE(ID) ON DELETE CASCADE

); 


INSERT INTO TIMER(IDALIMENTO,FECHA) VALUES(3,'2020-05-18 17:25:00');
INSERT INTO TIMER(IDALIMENTO,FECHA) VALUES(3,'2020-05-18 21:25:00');

INSERT INTO TIMER(IDALIMENTO,FECHA) VALUES(3,'2020-05-18 21:51:22');

INSERT INTO TIMER(IDALIMENTO,FECHA) VALUES(1,NOW());

CREATE TABLE FACTURA(
ID INT NOT NULL auto_increment,
FECHA DATE NOT NULL,


primary key(ID)


);

INSERT INTO FACTURA(FECHA) VALUES("2020-05-20");
INSERT INTO FACTURA(FECHA) VALUES("2020-04-21");

CREATE TABLE HORA_VENTA(
VENTA DOUBLE DEFAULT 0,
HORA INT NOT NULL,
IDFACTURA INT NOT NULL,
IDRESTAURANTE INT NOT NULL,

PRIMARY KEY(IDFACTURA,HORA),
FOREIGN KEY(IDFACTURA) REFERENCES FACTURA(ID) ON DELETE CASCADE,
FOREIGN KEY(IDRESTAURANTE) REFERENCES RESTAURANTE(ID) ON DELETE CASCADE


);

INSERT INTO HORA_VENTA(VENTA,HORA, IDFACTURA, IDRESTAURANTE) VALUES(200.0,17, 1,1),(50.55,18,1,1),(150.20,19,1,1);