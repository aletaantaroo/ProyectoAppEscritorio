-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 21-11-2024 a las 12:22:46
-- Versión del servidor: 5.7.35-0ubuntu0.18.04.2
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `GestorFichajes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `EQUIPO`
--

CREATE TABLE `EQUIPO` (
  `id_equipo` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `categoria` varchar(20) DEFAULT NULL,
  `escudo` longtext,
  `localidad` varchar(50) DEFAULT NULL,
  `director_equipo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `EQUIPO`
--

INSERT INTO `EQUIPO` (`id_equipo`, `nombre`, `categoria`, `escudo`, `localidad`, `director_equipo`) VALUES
(1, 'Dragones de Sevilla', 'Senior', '', 'Sevilla', 'Fernando Rivas'),
(2, 'Tiburones de Cádiz', 'Juvenil', '', 'Cádiz', 'Marina López'),
(3, 'Águilas de Granada', 'Senior', '', 'Granada', 'Héctor García'),
(4, 'Leones de Barcelona', 'Juvenil', '', 'Barcelona', 'Lucía Fernández'),
(5, 'Pumas de Madrid', 'Senior', '', 'Madrid', 'Ricardo Sánchez');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FICHA`
--

CREATE TABLE `FICHA` (
  `nif_jugador` varchar(15) NOT NULL,
  `id_equipo` int(11) NOT NULL,
  `fecha_fichaje` date DEFAULT NULL,
  `fecha_fin_fichaje` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `FICHA`
--

INSERT INTO `FICHA` (`nif_jugador`, `id_equipo`, `fecha_fichaje`, `fecha_fin_fichaje`) VALUES
('10101010J', 1, '2024-10-20', NULL),
('10101010J', 5, '2025-06-01', NULL),
('11111111A', 1, '2024-01-15', '2025-01-14'),
('11111111A', 2, '2024-06-15', '2025-06-15'),
('11111112B', 1, '2024-01-10', '2024-12-31'),
('11111112B', 3, '2025-01-01', NULL),
('11111113C', 2, '2024-03-15', '2025-03-15'),
('11111113C', 4, '2025-04-01', '2026-04-01'),
('11111114D', 3, '2024-05-01', '2025-05-01'),
('11111114D', 5, '2025-06-15', '2026-06-15'),
('11111115E', 1, '2025-09-15', NULL),
('11111115E', 4, '2024-07-01', NULL),
('11111116F', 2, '2026-01-01', '2026-12-31'),
('11111116F', 5, '2024-09-01', '2025-09-01'),
('11111117G', 1, '2024-11-01', NULL),
('11111117G', 3, '2026-03-01', '2027-03-01'),
('11111118H', 2, '2025-01-15', '2025-12-15'),
('11111118H', 4, '2026-05-01', NULL),
('11111119I', 3, '2025-03-01', NULL),
('11111119I', 5, '2026-07-01', '2027-07-01'),
('11111120J', 1, '2026-09-01', NULL),
('11111120J', 4, '2025-06-01', '2026-06-01'),
('11111121K', 2, '2026-11-01', '2027-11-01'),
('11111121K', 5, '2025-09-01', NULL),
('22222222B', 2, '2024-03-10', '2024-12-31'),
('22222222B', 3, '2024-09-01', '2025-03-01'),
('33333333C', 3, '2024-07-01', NULL),
('33333333C', 4, '2024-11-01', NULL),
('44444444D', 4, '2024-11-15', NULL),
('44444444D', 5, '2025-01-10', '2025-12-10'),
('55555555E', 1, '2025-07-01', '2026-06-30'),
('55555555E', 5, '2025-01-01', '2026-06-30'),
('66666666F', 1, '2024-02-28', '2025-05-31'),
('66666666F', 2, '2024-01-20', '2024-12-20'),
('77777777G', 2, '2025-03-01', NULL),
('77777777G', 3, '2024-08-15', NULL),
('88888888H', 3, '2024-12-01', '2026-12-01'),
('88888888H', 4, '2025-02-15', '2026-02-15'),
('99999999I', 4, '2024-10-10', NULL),
('99999999I', 5, '2024-05-01', '2024-12-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `JUGADOR`
--

CREATE TABLE `JUGADOR` (
  `nif` varchar(15) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `categoria` varchar(20) DEFAULT NULL,
  `fichado` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `JUGADOR`
--

INSERT INTO `JUGADOR` (`nif`, `nombre`, `apellidos`, `edad`, `categoria`, `fichado`) VALUES
('10101010J', 'Valeria', 'Castro', 23, 'Juvenil', 1),
('11111111A', 'Daniel', 'Martínez', 28, 'Senior', 1),
('11111112B', 'Laura', 'Gómez', 20, 'Juvenil', 1),
('11111113C', 'Andrés', 'Pérez', 25, 'Senior', 1),
('11111114D', 'Sara', 'López', 27, 'Senior', 1),
('11111115E', 'Mario', 'Hernández', 22, 'Juvenil', 1),
('11111116F', 'Lucía', 'Martínez', 28, 'Senior', 1),
('11111117G', 'David', 'Moreno', 30, 'Senior', 1),
('11111118H', 'Clara', 'Ortiz', 19, 'Juvenil', 0),
('11111119I', 'Pablo', 'Romero', 26, 'Senior', 1),
('11111120J', 'Natalia', 'García', 21, 'Juvenil', 1),
('11111121K', 'Adrián', 'Santos', 24, 'Senior', 1),
('22222222B', 'Carla', 'González', 24, 'Juvenil', 1),
('33333333C', 'Javier', 'López', 30, 'Senior', 0),
('44444444D', 'Sofía', 'Hernández', 26, 'Senior', 1),
('55555555E', 'Miguel', 'Ruiz', 29, 'Senior', 0),
('66666666F', 'Ana', 'Morales', 22, 'Juvenil', 1),
('77777777G', 'Lucas', 'Torres', 31, 'Senior', 1),
('88888888H', 'Emma', 'Vargas', 25, 'Juvenil', 1),
('99999999I', 'Diego', 'Jiménez', 27, 'Senior', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `EQUIPO`
--
ALTER TABLE `EQUIPO`
  ADD PRIMARY KEY (`id_equipo`);

--
-- Indices de la tabla `FICHA`
--
ALTER TABLE `FICHA`
  ADD PRIMARY KEY (`nif_jugador`,`id_equipo`),
  ADD KEY `id_equipo` (`id_equipo`);

--
-- Indices de la tabla `JUGADOR`
--
ALTER TABLE `JUGADOR`
  ADD PRIMARY KEY (`nif`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `EQUIPO`
--
ALTER TABLE `EQUIPO`
  MODIFY `id_equipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `FICHA`
--
ALTER TABLE `FICHA`
  ADD CONSTRAINT `FICHA_ibfk_1` FOREIGN KEY (`nif_jugador`) REFERENCES `JUGADOR` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FICHA_ibfk_2` FOREIGN KEY (`id_equipo`) REFERENCES `EQUIPO` (`id_equipo`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
