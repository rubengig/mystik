-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 16-12-2025 a las 08:48:22
-- Versión del servidor: 8.4.4
-- Versión de PHP: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

CREATE TABLE `articulos` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `precio` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`id`, `nombre`, `descripcion`, `precio`) VALUES
(103, 'Cuerno del Ocaso', 'Artículo escondida en ruinas olvidadas', 22.19),
(104, 'Fragmento de Aurora', 'Artículo nacida del fuego estelar', 98.42),
(105, 'Cristal del Alba', 'Artículo usada en ceremonias de equilibrio espiritual', 37.66),
(106, 'Libro de las Marcas Ocultas', 'Artículo conectada a planos invisibles', 152.05),
(107, 'Candelabro de Almas', 'Artículo purificada en las aguas del tiempo', 77.52),
(108, 'Pluma del Guardián', 'Artículo susurrante al tacto', 45.81),
(109, 'Lágrima del Infinito', 'Artículo encantada para revelar lo oculto', 193.79),
(110, 'Pluma del Guardián', 'Artículo silenciosa pero inquietante', 126.91),
(111, 'Taza de los Recuerdos', 'Artículo tejida con hilos del crepúsculo', 51.81),
(112, 'Amuleto de los Cuatro Vientos', 'Artículo imbuida con energía astral', 24.57),
(113, 'Cuenco del Equilibrio', 'Artículo forjada en rituales antiguos', 110.03),
(114, 'Capa de Somnolencia', 'Artículo escondida en ruinas olvidadas', 194.39),
(115, 'Amuleto de los Cuatro Vientos', 'Artículo teñida con pigmentos de sueños', 18.07),
(142, 'Brújula Astral', 'Artículo protegida por sellos arcanos', 166.94),
(143, 'Cetro de Luz Silente', 'Artículo imbuida con energía astral', 63.70),
(144, 'Amuleto de los Cuatro Vientos', 'Artículo custodiada por generaciones de sabios', 69.82),
(145, 'Lámpara del Horizonte', 'Artículo emanando un tenue resplandor etéreo', 19.67),
(146, 'Botella de Niebla', 'Artículo escondida en ruinas olvidadas', 100.21),
(147, 'Cinturón del Eclipse', 'Artículo custodiada por generaciones de sabios', 26.94),
(148, 'Runa del Silencio', 'Artículo resonante con sueños no contados', 122.87),
(149, 'Flauta de los Espíritus', 'Artículo protegida por sellos arcanos', 52.92),
(150, 'Velo de Estrellas', 'Artículo purificada en las aguas del tiempo', 63.66),
(160, 'Libro de las Marcas Ocultas', 'Artículo encantada para revelar lo oculto', 145.28);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `id` bigint NOT NULL,
  `usuario` bigint NOT NULL,
  `articulo` bigint NOT NULL,
  `texto` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `comentarios`
--

INSERT INTO `comentarios` (`id`, `usuario`, `articulo`, `texto`) VALUES
(42, 72, 149, 'Cada frase parece cantada por voces etéreas.'),
(123, 1, 148, 'Este artículo ilumina mi camino, gracias por compartirlo.'),
(130, 1, 143, 'Me hace reflexionar sobre los ciclos de la existencia.'),
(195, 72, 108, 'Inspiración para la mente y el espíritu.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritos`
--

CREATE TABLE `favoritos` (
  `id` bigint NOT NULL,
  `usuario` bigint NOT NULL,
  `articulo` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `favoritos`
--

INSERT INTO `favoritos` (`id`, `usuario`, `articulo`) VALUES
(110, 1, 110),
(137, 1, 104),
(203, 1, 106),
(247, 1, 107),
(297, 72, 109),
(361, 72, 144),
(372, 1, 146),
(488, 1, 160),
(516, 1, 148),
(527, 1, 106);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiposusuario`
--

CREATE TABLE `tiposusuario` (
  `id` bigint NOT NULL,
  `descripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tiposusuario`
--

INSERT INTO `tiposusuario` (`id`, `descripcion`) VALUES
(1, 'admin'),
(2, 'usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido1` varchar(255) NOT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tipousuario` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellido1`, `apellido2`, `email`, `password`, `tipousuario`) VALUES
(1, 'Ruben', 'Gigante', 'Ekame', 'rubengk@gmail.com', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1),
(72, 'Carmen', 'Rodriguez', 'Pérez', 'carmen@gmail.com', '38083c7ee9121e17401883566a148aa5c2e2d55dc53bc4a94a026517dbff3c6b', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_comentarios_usuario` (`usuario`),
  ADD KEY `fk_comentarios_articulo` (`articulo`);

--
-- Indices de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_favoritos_usuario` (`usuario`),
  ADD KEY `fk_favoritos_articulo` (`articulo`);

--
-- Indices de la tabla `tiposusuario`
--
ALTER TABLE `tiposusuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_tipousuario_id` (`tipousuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulos`
--
ALTER TABLE `articulos`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=170;

--
-- AUTO_INCREMENT de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=249;

--
-- AUTO_INCREMENT de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=552;

--
-- AUTO_INCREMENT de la tabla `tiposusuario`
--
ALTER TABLE `tiposusuario`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `fk_comentarios_articulo` FOREIGN KEY (`articulo`) REFERENCES `articulos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_comentarios_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD CONSTRAINT `fk_favoritos_articulo` FOREIGN KEY (`articulo`) REFERENCES `articulos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_favoritos_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_tipousuario_id` FOREIGN KEY (`tipousuario`) REFERENCES `tiposusuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
