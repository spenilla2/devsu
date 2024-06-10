-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2024 a las 18:10:32
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `devsu_challenge`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `account`
--

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `account_balance` bigint(20) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `identification` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `account`
--

INSERT INTO `account` (`id`, `account_balance`, `account_number`, `account_type`, `identification`, `name`, `status`) VALUES
(1, 1425, '478758', 'Ahorros', '123456', 'Jose Lema', b'1'),
(2, 700, '225487', 'Ahorros', '1234567', 'Marianela Montalvo', b'1'),
(3, 150, '495878', 'Ahorros', '12345678', 'Juan Osorio', b'1'),
(4, 0, '496825', 'Ahorros', '1234567', 'Marianela Montalvo', b'1'),
(5, 1000, '585545', 'Corriente', '123456', 'Jose Lema', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

CREATE TABLE `client` (
  `client_id` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`client_id`, `password`, `status`, `id`) VALUES
('jose.lema', '1234', b'1', 1),
('marianela.montalvo', '5678', b'1', 2),
('juan.osorio', '1245', b'1', 3),
(NULL, NULL, b'0', 4),
(NULL, NULL, b'0', 5),
(NULL, NULL, b'0', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movement`
--

CREATE TABLE `movement` (
  `id` bigint(20) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `date_movement` date DEFAULT NULL,
  `final_balance` bigint(20) DEFAULT NULL,
  `initial_balance` bigint(20) DEFAULT NULL,
  `movement_detail` bigint(20) DEFAULT NULL,
  `status` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movement`
--

INSERT INTO `movement` (`id`, `account_number`, `account_type`, `date_movement`, `final_balance`, `initial_balance`, `movement_detail`, `status`) VALUES
(1, '478758', 'Ahorros', '2022-04-12', 1425, 2000, -575, b'1'),
(2, '225487', 'Ahorros', '2022-04-18', 700, 100, 600, b'1'),
(3, '495878', 'Ahorros', '2022-04-27', 150, 0, 150, b'1'),
(4, '496825', 'Ahorros', '2022-04-29', 0, 540, -540, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person`
--

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `identification` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `person`
--

INSERT INTO `person` (`id`, `address`, `age`, `gender`, `identification`, `name`, `phone`) VALUES
(1, 'Otavalo sn y principal', 30, 'Masculino', '123456', 'Jose Lema', '098254785'),
(2, 'Amazonas y NNNU ', 30, 'Femenino', '1234567', 'Marianela Montalvo', '097549856'),
(3, '13 junio y Equinoccial', 30, 'Femenino', '12345678', 'Juan Osorio', '098874587'),
(4, NULL, 0, NULL, '123456789', 'John Doe', NULL),
(5, NULL, 0, NULL, '1234567891', 'John Doe', NULL),
(6, NULL, 0, NULL, '1234567890', 'John Doe', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `movement`
--
ALTER TABLE `movement`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `movement`
--
ALTER TABLE `movement`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `person`
--
ALTER TABLE `person`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FKr1e0j10i9v9i52l6tqfa69nj0` FOREIGN KEY (`id`) REFERENCES `person` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
