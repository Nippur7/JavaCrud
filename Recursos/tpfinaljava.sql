-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-12-2023 a las 18:57:34
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tpfinaljava`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `idequipo` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `titulares` int(11) DEFAULT NULL,
  `suplentes` int(11) DEFAULT NULL,
  `directorTecnico` varchar(45) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `puntos` int(11) DEFAULT NULL,
  `partidosJugados` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`idequipo`, `nombre`, `titulares`, `suplentes`, `directorTecnico`, `puntos`, `partidosJugados`) VALUES
(6, 'Boca Junios', 11, 5, 'Juan Riquelme', 4, 2),
(7, 'River Plate', 11, 5, 'Guillermo Barros', 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido`
--

CREATE TABLE `partido` (
  `idpartido` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `idequipo1` int(11) DEFAULT NULL,
  `idequipo2` int(11) DEFAULT NULL,
  `golesEq1` int(11) DEFAULT NULL,
  `golesEq2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `partido`
--

INSERT INTO `partido` (`idpartido`, `fecha`, `idequipo1`, `idequipo2`, `golesEq1`, `golesEq2`) VALUES
(10, '2023-12-10 00:00:00', 6, 7, 5, 5),
(11, '2023-12-20 00:00:00', 6, 7, 6, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`idequipo`);

--
-- Indices de la tabla `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`idpartido`),
  ADD UNIQUE KEY `idpartido_3` (`idpartido`),
  ADD KEY `FK_idEq1` (`idequipo1`),
  ADD KEY `FK_idEq2` (`idequipo2`),
  ADD KEY `idpartido` (`idpartido`),
  ADD KEY `idpartido_2` (`idpartido`),
  ADD KEY `idpartido_4` (`idpartido`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `idequipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `partido`
--
ALTER TABLE `partido`
  MODIFY `idpartido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `partido`
--
ALTER TABLE `partido`
  ADD CONSTRAINT `FK_idEq1` FOREIGN KEY (`idequipo1`) REFERENCES `equipo` (`idequipo`),
  ADD CONSTRAINT `FK_idEq2` FOREIGN KEY (`idequipo2`) REFERENCES `equipo` (`idequipo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
