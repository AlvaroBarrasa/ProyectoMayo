-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 18-05-2020 a las 23:52:03
-- Versión del servidor: 5.5.65-MariaDB
-- Versión de PHP: 7.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `abarrasa_US`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`AdminUs`@`%` PROCEDURE `Agregar_cuenta` (IN `vnCuenta` VARCHAR(23), IN `vFecha_creacion` DATE, IN `vSaldo` DOUBLE)  NO SQL
BEGIN
INSERT INTO Cuentas VALUES (vnCuenta, vFecha_creacion, vSaldo);
END$$

CREATE DEFINER=`AdminUs`@`%` PROCEDURE `Agregar_titular` (IN `tNIF` VARCHAR(9), IN `tnCuenta` VARCHAR(23))  NO SQL
BEGIN
IF (SELECT COUNT(*) FROM us_cuentas WHERE DNI = tNIF AND nCuenta=tnCuenta)<1 THEN
INSERT INTO us_cuentas (DNI,nCuenta) VALUES (tNIF, tnCuenta);
ELSE
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Ya existe este titular";
END IF;
END$$

CREATE DEFINER=`AdminUs`@`%` PROCEDURE `Agregar_usuario` (IN `vNIF` VARCHAR(9) CHARSET utf8, IN `vApellidos` VARCHAR(50) CHARSET utf8, IN `vNombre` VARCHAR(50) CHARSET utf8, IN `vAno_nacimiento` INT(4), IN `vDireccion` VARCHAR(100) CHARSET utf8, IN `vEmail` VARCHAR(100) CHARSET utf8, IN `vTelefono` INT(9))  NO SQL
BEGIN
INSERT INTO Usuario VALUES (vNIF,vApellidos,vNombre,vAno_nacimiento,vDireccion,vEmail,vTelefono);
END$$

CREATE DEFINER=`AdminUs`@`%` PROCEDURE `Ingreso` (IN `cuenta` VARCHAR(23), IN `suma` DOUBLE)  NO SQL
BEGIN
UPDATE Cuentas SET Saldo=Saldo+suma WHERE nCuenta LIKE cuenta;
END$$

CREATE DEFINER=`AdminUs`@`%` PROCEDURE `Modifica_usuario` (IN `buscaNIF` VARCHAR(9), IN `mApellidos` VARCHAR(50), IN `mNombre` VARCHAR(50), IN `mAño` INT(4), IN `mDireccion` VARCHAR(100), IN `mEmail` VARCHAR(100), IN `mTelefono` INT(9))  NO SQL
BEGIN
UPDATE Usuario SET Apellidos = mApellidos, Nombre = mNombre, Año_nacimiento = mAño, Dirección = mDireccion, Teléfono = mTelefono WHERE NIF = buscaNIF;
END$$

CREATE DEFINER=`AdminUs`@`%` PROCEDURE `Nueva_operacion` (IN `cod` VARCHAR(40), IN `tipo` VARCHAR(50), IN `fecha` DATE, IN `dinero` FLOAT, IN `nif` VARCHAR(9), IN `cuenta` VARCHAR(23), IN `cuenta_destino` VARCHAR(23))  NO SQL
BEGIN
INSERT INTO Operacione VALUES(cod, tipo, fecha, dinero, nif, cuenta, cuenta_destino);
END$$

CREATE DEFINER=`AdminUs`@`%` PROCEDURE `Quitar_titular` (IN `qNIF` VARCHAR(9), IN `qnCuenta` VARCHAR(23))  NO SQL
BEGIN
IF (SELECT COUNT(*) FROM us_cuentas WHERE nCuenta=qnCuenta)>1 THEN
DELETE FROM us_cuentas WHERE DNI=qNIF AND nCuenta=qnCuenta;
ELSE
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No puede dejar la cuenta sin titulares";
END IF;
END$$

CREATE DEFINER=`AdminUs`@`%` PROCEDURE `Retirada` (IN `cuenta` VARCHAR(23), IN `resta` FLOAT)  NO SQL
BEGIN
UPDATE Cuentas SET Saldo=Saldo-resta WHERE nCuenta=cuenta;
END$$

CREATE DEFINER=`AdminUs`@`%` PROCEDURE `Transaccion` (IN `propia` VARCHAR(23), IN `destino` VARCHAR(23), IN `cantidad` FLOAT)  NO SQL
BEGIN
UPDATE Cuentas SET Saldo=Saldo+cantidad WHERE nCuenta=destino;
UPDATE Cuentas SET Saldo=Saldo-cantidad WHERE nCuenta=propia;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cuentas`
--

CREATE TABLE `Cuentas` (
  `nCuenta` varchar(23) NOT NULL,
  `Fecha_creación` date NOT NULL,
  `Saldo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Cuentas`
--

INSERT INTO `Cuentas` (`nCuenta`, `Fecha_creación`, `Saldo`) VALUES
('0001-0180-40-0000000001', '2020-05-17', 200),
('0001-0180-40-0000000002', '2020-05-17', 90);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Operacione`
--

CREATE TABLE `Operacione` (
  `Código_operación` varchar(40) NOT NULL,
  `Tipo_operación` varchar(50) NOT NULL,
  `Fecha_realización` date NOT NULL,
  `Cantidad` double NOT NULL,
  `Usuario` varchar(9) NOT NULL,
  `Cuenta_realizante` varchar(23) NOT NULL,
  `Cuenta_destino` varchar(23) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Operacione`
--

INSERT INTO `Operacione` (`Código_operación`, `Tipo_operación`, `Fecha_realización`, `Cantidad`, `Usuario`, `Cuenta_realizante`, `Cuenta_destino`) VALUES
('IN2020518124550', 'Ingreso', '2020-05-18', 30, '21151378A', '0001-0180-40-0000000001', '0001-0180-40-0000000001'),
('IN2020518125134', 'Ingreso', '2020-05-18', 20, '21151378A', '0001-0180-40-0000000001', '0001-0180-40-0000000001'),
('IN2020518125648', 'Ingreso', '2020-05-18', 50, '21151378A', '0001-0180-40-0000000001', '0001-0180-40-0000000001'),
('IN202051822524', 'Ingreso', '2020-05-18', 30, '21151378A', '0001-0180-40-0000000002', '0001-0180-40-0000000002'),
('RE2020518124550', 'Retirada', '2020-05-18', 40, '21151378A', '0001-0180-40-0000000001', '0001-0180-40-0000000001'),
('RE2020518225142', 'Retirada', '2020-05-18', 30, '28838348M', '0001-0180-40-0000000001', '0001-0180-40-0000000001'),
('RE202051822550', 'Retirada', '2020-05-18', 80, '28838348M', '0001-0180-40-0000000001', '0001-0180-40-0000000001');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE `Usuario` (
  `NIF` varchar(9) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Año_nacimiento` int(4) NOT NULL,
  `Dirección` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Teléfono` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Usuario`
--

INSERT INTO `Usuario` (`NIF`, `Apellidos`, `Nombre`, `Año_nacimiento`, `Dirección`, `Email`, `Teléfono`) VALUES
('21151378A', 'Barrasa Perez', 'Alvaro', 2000, 'Calle Isaac Albeniz 22', 'aksdba@gmail.com', 687451892),
('28838348M', 'afa ajf ', 'migue', 1987, 'calle sevilla 34', 'sdkfd@gmail.com', 685471985);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `us_cuentas`
--

CREATE TABLE `us_cuentas` (
  `DNI` varchar(9) NOT NULL,
  `nCuenta` varchar(23) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `us_cuentas`
--

INSERT INTO `us_cuentas` (`DNI`, `nCuenta`) VALUES
('28838348M', '0001-0180-40-0000000001'),
('21151378A', '0001-0180-40-0000000002'),
('21151378A', '0001-0180-40-0000000001');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Cuentas`
--
ALTER TABLE `Cuentas`
  ADD PRIMARY KEY (`nCuenta`);

--
-- Indices de la tabla `Operacione`
--
ALTER TABLE `Operacione`
  ADD PRIMARY KEY (`Código_operación`),
  ADD KEY `iyiu` (`Cuenta_destino`),
  ADD KEY `kuenta` (`Usuario`);

--
-- Indices de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`NIF`);

--
-- Indices de la tabla `us_cuentas`
--
ALTER TABLE `us_cuentas`
  ADD KEY `gh` (`DNI`),
  ADD KEY `fghgj` (`nCuenta`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Operacione`
--
ALTER TABLE `Operacione`
  ADD CONSTRAINT `kuenta` FOREIGN KEY (`Usuario`) REFERENCES `Usuario` (`NIF`),
  ADD CONSTRAINT `iyiu` FOREIGN KEY (`Cuenta_destino`) REFERENCES `Cuentas` (`nCuenta`);

--
-- Filtros para la tabla `us_cuentas`
--
ALTER TABLE `us_cuentas`
  ADD CONSTRAINT `fghgj` FOREIGN KEY (`nCuenta`) REFERENCES `Cuentas` (`nCuenta`),
  ADD CONSTRAINT `gh` FOREIGN KEY (`DNI`) REFERENCES `Usuario` (`NIF`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
