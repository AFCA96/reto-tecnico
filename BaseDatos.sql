DROP DATABASE IF EXISTS cliente_persona_db;
CREATE DATABASE cliente_persona_db;
  
  CREATE TABLE `cliente_persona_db`.`clientes` (
  `cliente_id` int NOT NULL AUTO_INCREMENT,
  `contrasenia` varchar(45) NOT NULL,
  `estado` tinyint DEFAULT NULL,
  `identificacion` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cliente_id`),
  UNIQUE KEY `identificacion_UNIQUE` (`identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `cliente_persona_db`.`clientes` 
(`contrasenia`, `direccion`, `edad`, `estado`, `genero`, `identificacion`, `nombre`, `telefono`) 
values
('56151', 'Villaflora', 28, true, 'Masculino', 87923, 'Aldo Carrillo', '0965874523'),
('1234', 'Otavalo sn y principal', 30, true, 'Masculino', 10001, 'Jose Lema', '098254785'),
('5678', 'Amazonas y NNUU', 28, true, 'Femenino', 10002, 'Marianela Montalvo', '097548965'),
('1245', '13 junio y Equinoccial', 35, true, 'Masculino', 10003, 'Juan Osorio', '098874587');


DROP DATABASE IF EXISTS cuenta_movimiento_db;
CREATE DATABASE cuenta_movimiento_db;


CREATE TABLE `cuenta_movimiento_db`.`cuentas` (
  `numero_cuenta` int NOT NULL,
  `tipo_cuenta` varchar(45) NOT NULL,
  `saldo_inicial` double NOT NULL,
  `estado` tinyint DEFAULT '1',
  `cliente` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`numero_cuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `cuenta_movimiento_db`.`cuentas` 
(`numero_cuenta`, `tipo_cuenta`, `saldo_inicial`, `estado`, `cliente`) 
VALUES
('85635', 'Corriente', '2000', '1', 'Aldo Carrillo'),
(478758, 'Ahorro', 2000, 1, 'Jose Lema'),
(225487, 'Corriente', 100, 1, 'Marianela Montalvo'),
(495878, 'Ahorros', 0, 1, 'Juan Osorio'),
(496825, 'Ahorros', 540, 1, 'Marianela Montalvo');

CREATE TABLE `cuenta_movimiento_db`.`movimientos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero_cuenta` int NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `tipo_movimiento` varchar(45) DEFAULT NULL,
  `valor` double NOT NULL,
  `saldo` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cuentas_movimiento_idx` (`numero_cuenta`),
  CONSTRAINT `cuentas_movimiento` FOREIGN KEY (`numero_cuenta`) REFERENCES `cuentas` (`numero_cuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


