-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-02-2026 a las 18:59:53
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
-- Base de datos: `sushi-inka`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_alertas_stock_bajo` (IN `p_id_sucursal` INT)   BEGIN
    SELECT 
        i.nombre, i.stock_actual, i.stock_minimo, i.unidad_medida,
        ROUND((i.stock_actual / NULLIF(i.stock_minimo, 1)) * 100, 2) AS porcentaje_stock
    FROM insumos i
    WHERE i.id_sucursal = p_id_sucursal
      AND i.stock_actual <= i.stock_minimo
      AND i.estado = 1
    ORDER BY porcentaje_stock ASC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reporte_ventas` (IN `p_id_sucursal` INT, IN `p_fecha_inicio` DATE, IN `p_fecha_fin` DATE)   BEGIN
    SELECT 
        DATE(v.fecha_venta) AS fecha, COUNT(v.id_venta) AS cantidad_ventas,
        SUM(v.monto_total) AS total_ventas, SUM(v.impuestos) AS total_impuestos,
        SUM(CASE WHEN v.metodo_pago = 'Efectivo' THEN v.monto_total ELSE 0 END) AS efectivo,
        SUM(CASE WHEN v.metodo_pago = 'Tarjeta' THEN v.monto_total ELSE 0 END) AS tarjeta
    FROM ventas v
    WHERE v.id_sucursal = p_id_sucursal
      AND DATE(v.fecha_venta) BETWEEN p_fecha_inicio AND p_fecha_fin
      AND v.estado = 1
    GROUP BY DATE(v.fecha_venta)
    ORDER BY fecha DESC;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acceso`
--

CREATE TABLE `acceso` (
  `id_acceso` int(11) NOT NULL,
  `id_perfil` int(11) NOT NULL,
  `id_modulo` int(11) NOT NULL,
  `puede_leer` tinyint(1) DEFAULT 0,
  `puede_escribir` tinyint(1) DEFAULT 0,
  `puede_eliminar` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anulacion_comprobantes`
--

CREATE TABLE `anulacion_comprobantes` (
  `id_anulacion` int(11) NOT NULL,
  `id_comprobante_electronico` int(11) NOT NULL,
  `motivo` varchar(200) NOT NULL,
  `fecha_anulacion` datetime DEFAULT current_timestamp(),
  `id_usuario_anulo` int(11) NOT NULL,
  `xml_baja` longtext DEFAULT NULL,
  `ticket_sunat` varchar(50) DEFAULT NULL,
  `estado_baja` varchar(50) DEFAULT 'Pendiente'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bitacora_accion`
--

CREATE TABLE `bitacora_accion` (
  `id_bitacora` bigint(20) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `id_superadmin` int(11) DEFAULT NULL,
  `modulo` varchar(50) NOT NULL,
  `accion` varchar(50) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `ip_address` varchar(45) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `fecha_hora` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias_gastos`
--

CREATE TABLE `categorias_gastos` (
  `id_categoria_gasto` int(11) NOT NULL,
  `id_restaurante` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `tipo` enum('Fijo','Variable') DEFAULT 'Variable',
  `color` varchar(7) DEFAULT '#FF6B6B',
  `icono` varchar(50) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT 1,
  `fecha_creacion` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias_menu`
--

CREATE TABLE `categorias_menu` (
  `id_categoria` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `icono` varchar(50) DEFAULT NULL,
  `orden` int(11) DEFAULT 0,
  `estado` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `id_restaurante` int(11) NOT NULL,
  `tipo_cliente` enum('Persona','Empresa') DEFAULT 'Persona',
  `nombre_razon_social` varchar(200) NOT NULL,
  `documento` varchar(20) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `estado` tinyint(4) DEFAULT 1,
  `fecha_registro` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobantes_electronicos`
--

CREATE TABLE `comprobantes_electronicos` (
  `id_comprobante_electronico` int(11) NOT NULL,
  `id_venta` int(11) NOT NULL,
  `tipo_documento` varchar(20) NOT NULL,
  `serie_documento` varchar(10) NOT NULL,
  `numero_documento` varchar(20) NOT NULL,
  `razon_social_cliente` varchar(200) DEFAULT NULL,
  `documento_cliente` varchar(20) DEFAULT NULL,
  `direccion_cliente` varchar(200) DEFAULT NULL,
  `xml_sin_firmar` longtext DEFAULT NULL,
  `xml_firmado` longtext DEFAULT NULL,
  `xml_cdr` longtext DEFAULT NULL,
  `codigo_hash` varchar(255) DEFAULT NULL,
  `codigo_qr` text DEFAULT NULL,
  `estado_sunat` varchar(50) DEFAULT 'Pendiente',
  `codigo_respuesta_sunat` varchar(10) DEFAULT NULL,
  `mensaje_sunat` text DEFAULT NULL,
  `fecha_emision` datetime DEFAULT current_timestamp(),
  `fecha_envio` datetime DEFAULT NULL,
  `fecha_respuesta` datetime DEFAULT NULL,
  `ruta_pdf` varchar(255) DEFAULT NULL,
  `ruta_xml` varchar(255) DEFAULT NULL,
  `id_usuario_emisor` int(11) DEFAULT NULL,
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_pedido`
--

CREATE TABLE `detalle_pedido` (
  `id_detalle` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `id_plato` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL DEFAULT 1,
  `precio_unitario` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumos`
--

CREATE TABLE `insumos` (
  `id_insumo` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `stock_actual` decimal(10,2) DEFAULT 0.00,
  `stock_minimo` decimal(10,2) DEFAULT 0.00,
  `unidad_medida` varchar(20) DEFAULT NULL,
  `precio_compra` decimal(10,2) DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `codigo_barra` varchar(50) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT 1,
  `fecha_creacion` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesas`
--

CREATE TABLE `mesas` (
  `id_mesa` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `numero_mesa` varchar(10) NOT NULL,
  `capacidad` int(11) DEFAULT 4,
  `estado_mesa` enum('Disponible','Ocupada','Reservada') DEFAULT 'Disponible',
  `estado` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulo`
--

CREATE TABLE `modulo` (
  `id_modulo` int(11) NOT NULL,
  `nombre_modulo` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `icono` varchar(50) DEFAULT NULL,
  `ruta` varchar(100) DEFAULT NULL,
  `orden` int(11) DEFAULT 0,
  `estado` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos_caja`
--

CREATE TABLE `movimientos_caja` (
  `id_movimiento_caja` int(11) NOT NULL,
  `id_sesion` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `tipo_movimiento` enum('Ingreso','Egreso') NOT NULL,
  `concepto` varchar(200) NOT NULL,
  `id_categoria_gasto` int(11) DEFAULT NULL,
  `monto` decimal(10,2) NOT NULL,
  `comprobante` varchar(100) DEFAULT NULL,
  `fecha_movimiento` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos_inventario`
--

CREATE TABLE `movimientos_inventario` (
  `id_movimiento` int(11) NOT NULL,
  `id_insumo` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_proveedor` int(11) DEFAULT NULL,
  `tipo_movimiento` enum('Entrada','Salida','Ajuste') NOT NULL,
  `cantidad` decimal(10,2) NOT NULL,
  `motivo` varchar(200) NOT NULL,
  `numero_documento` varchar(50) DEFAULT NULL,
  `costo_unitario` decimal(10,2) DEFAULT NULL,
  `costo_total` decimal(10,2) DEFAULT NULL,
  `fecha_movimiento` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `movimientos_inventario`
--
DELIMITER $$
CREATE TRIGGER `trg_actualizar_stock_inventario` AFTER INSERT ON `movimientos_inventario` FOR EACH ROW BEGIN
    IF NEW.tipo_movimiento = 'Entrada' THEN
        UPDATE insumos SET stock_actual = stock_actual + NEW.cantidad 
        WHERE id_insumo = NEW.id_insumo;
    ELSEIF NEW.tipo_movimiento = 'Salida' THEN
        UPDATE insumos SET stock_actual = stock_actual - NEW.cantidad 
        WHERE id_insumo = NEW.id_insumo;
    ELSEIF NEW.tipo_movimiento = 'Ajuste' THEN
        UPDATE insumos SET stock_actual = NEW.cantidad 
        WHERE id_insumo = NEW.id_insumo;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago_suscripcion`
--

CREATE TABLE `pago_suscripcion` (
  `id_pago` int(11) NOT NULL,
  `id_restaurante` int(11) NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `fecha_pago` datetime DEFAULT current_timestamp(),
  `metodo_pago` varchar(50) DEFAULT NULL,
  `numero_operacion` varchar(100) DEFAULT NULL,
  `periodo_inicio` date NOT NULL,
  `periodo_fin` date NOT NULL,
  `estado_pago` varchar(20) DEFAULT 'Confirmado',
  `comprobante_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id_pedido` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `id_mesa` int(11) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `nombre_cliente` varchar(200) DEFAULT NULL,
  `telefono_cliente` varchar(20) DEFAULT NULL,
  `tipo_pedido` varchar(20) DEFAULT 'Mesa',
  `direccion_entrega` varchar(200) DEFAULT NULL,
  `fecha_hora` datetime DEFAULT current_timestamp(),
  `estado_pedido` varchar(20) DEFAULT 'Pendiente',
  `monto_total` decimal(10,2) DEFAULT 0.00,
  `observaciones` text DEFAULT NULL,
  `fecha_update` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `pedidos`
--
DELIMITER $$
CREATE TRIGGER `trg_ocupar_mesa_pedido` AFTER INSERT ON `pedidos` FOR EACH ROW BEGIN
    IF NEW.id_mesa IS NOT NULL AND NEW.tipo_pedido = 'Mesa' THEN
        UPDATE mesas SET estado_mesa = 'Ocupada' 
        WHERE id_mesa = NEW.id_mesa;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE `perfil` (
  `id_perfil` int(11) NOT NULL,
  `nombre_perfil` varchar(50) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `platos`
--

CREATE TABLE `platos` (
  `id_plato` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `tiempo_preparacion` int(11) DEFAULT NULL,
  `disponible` tinyint(1) DEFAULT 1,
  `estado` tinyint(4) DEFAULT 1,
  `fecha_creacion` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id_proveedor` int(11) NOT NULL,
  `id_restaurante` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `ruc` varchar(20) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `contacto_nombre` varchar(100) DEFAULT NULL,
  `contacto_telefono` varchar(20) DEFAULT NULL,
  `contacto_email` varchar(100) DEFAULT NULL,
  `banco` varchar(100) DEFAULT NULL,
  `numero_cuenta` varchar(50) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `estado` tinyint(4) DEFAULT 1,
  `fecha_registro` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `restaurantes`
--

CREATE TABLE `restaurantes` (
  `id_restaurante` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `ruc` varchar(20) NOT NULL,
  `direccion_principal` varchar(200) DEFAULT NULL,
  `telefono_principal` varchar(20) DEFAULT NULL,
  `email_contacto` varchar(100) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `simbolo_moneda` varchar(5) DEFAULT 'S/',
  `tasa_igv` decimal(5,2) DEFAULT 18.00,
  `estado` tinyint(4) DEFAULT 1 COMMENT '1=Activo, 0=Inactivo',
  `fecha_creacion` datetime DEFAULT current_timestamp(),
  `fecha_vencimiento` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesiones_caja`
--

CREATE TABLE `sesiones_caja` (
  `id_sesion` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `monto_inicial` decimal(10,2) NOT NULL,
  `fecha_apertura` datetime DEFAULT current_timestamp(),
  `id_usuario_cierre` int(11) DEFAULT NULL,
  `monto_final_calculado` decimal(10,2) DEFAULT NULL,
  `monto_final_real` decimal(10,2) DEFAULT NULL,
  `diferencia` decimal(10,2) DEFAULT NULL,
  `fecha_cierre` datetime DEFAULT NULL,
  `observaciones_cierre` text DEFAULT NULL,
  `estado` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

CREATE TABLE `sucursales` (
  `id_sucursal` int(11) NOT NULL,
  `id_restaurante` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `horario_atencion` varchar(100) DEFAULT NULL,
  `latitud` decimal(10,8) DEFAULT NULL,
  `longitud` decimal(11,8) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT 1,
  `fecha_creacion` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `super_admin`
--

CREATE TABLE `super_admin` (
  `id_superadmin` int(11) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `dni` varchar(20) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `nombre_usuario_login` varchar(50) NOT NULL,
  `contrasena` varchar(255) NOT NULL COMMENT 'SHA-256 hash',
  `token_login` varchar(255) DEFAULT NULL,
  `foto_url` varchar(255) DEFAULT NULL,
  `rol` varchar(20) DEFAULT 'MASTER',
  `ultimo_login` datetime DEFAULT NULL,
  `ip_ultimo_acceso` varchar(45) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT 1,
  `fecha_creacion` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `id_perfil` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `nombre_usuario` varchar(100) NOT NULL,
  `apellido_usuario` varchar(100) DEFAULT NULL,
  `dni_usuario` varchar(20) NOT NULL,
  `telefono_usuario` varchar(20) DEFAULT NULL,
  `email_usuario` varchar(100) DEFAULT NULL,
  `nombre_usuario_login` varchar(50) NOT NULL,
  `contrasena_usuario` varchar(255) NOT NULL,
  `foto_url` varchar(255) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT 1,
  `fecha_creacion` datetime DEFAULT current_timestamp(),
  `ultimo_login` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id_venta` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `id_sesion` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `tipo_comprobante` varchar(20) NOT NULL,
  `serie_comprobante` varchar(5) DEFAULT NULL,
  `numero_comprobante` int(11) DEFAULT NULL,
  `monto_total` decimal(10,2) NOT NULL,
  `impuestos` decimal(10,2) DEFAULT NULL,
  `metodo_pago` varchar(20) NOT NULL,
  `monto_efectivo` decimal(10,2) DEFAULT NULL,
  `monto_tarjeta` decimal(10,2) DEFAULT NULL,
  `fecha_venta` datetime DEFAULT current_timestamp(),
  `observaciones` text DEFAULT NULL,
  `estado` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `ventas`
--
DELIMITER $$
CREATE TRIGGER `trg_liberar_mesa_venta` AFTER INSERT ON `ventas` FOR EACH ROW BEGIN
    DECLARE v_id_mesa INT;
    SELECT id_mesa INTO v_id_mesa 
    FROM pedidos WHERE id_pedido = NEW.id_pedido;
    IF v_id_mesa IS NOT NULL THEN
        UPDATE mesas SET estado_mesa = 'Disponible' 
        WHERE id_mesa = v_id_mesa;
        UPDATE pedidos SET estado_pedido = 'Entregado' 
        WHERE id_pedido = NEW.id_pedido;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_balance_diario`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_balance_diario` (
`id_sucursal` int(11)
,`sucursal` varchar(150)
,`fecha` date
,`total_ventas` bigint(21)
,`ingresos_ventas` decimal(32,2)
,`otros_ingresos` decimal(32,2)
,`egresos` decimal(32,2)
,`balance_neto` decimal(34,2)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_dashboard_superadmin_ventas`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_dashboard_superadmin_ventas` (
`fecha` date
,`id_sucursal` int(11)
,`sucursal` varchar(150)
,`total_ventas` bigint(21)
,`ingresos_totales` decimal(32,2)
,`ticket_promedio` decimal(14,6)
,`efectivo` decimal(32,2)
,`tarjeta` decimal(32,2)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_estado_inventario`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_estado_inventario` (
`id_insumo` int(11)
,`nombre` varchar(150)
,`stock_actual` decimal(10,2)
,`stock_minimo` decimal(10,2)
,`unidad_medida` varchar(20)
,`sucursal` varchar(150)
,`nivel_alerta` varchar(9)
,`fecha_vencimiento` date
,`dias_para_vencer` int(7)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_productos_mas_vendidos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_productos_mas_vendidos` (
`id_plato` int(11)
,`producto` varchar(150)
,`categoria` varchar(100)
,`sucursal` varchar(150)
,`periodo` varchar(7)
,`unidades_vendidas` decimal(32,0)
,`ingresos_totales` decimal(32,2)
,`precio_promedio` decimal(14,6)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_resumen_gastos_categoria`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_resumen_gastos_categoria` (
`categoria` varchar(100)
,`tipo` enum('Fijo','Variable')
,`sucursal` varchar(150)
,`periodo` varchar(7)
,`cantidad_movimientos` bigint(21)
,`total_gastado` decimal(32,2)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `v_balance_diario`
--
DROP TABLE IF EXISTS `v_balance_diario`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_balance_diario`  AS SELECT `s`.`id_sucursal` AS `id_sucursal`, `s`.`nombre` AS `sucursal`, cast(`v`.`fecha_venta` as date) AS `fecha`, count(distinct `v`.`id_venta`) AS `total_ventas`, coalesce(sum(`v`.`monto_total`),0) AS `ingresos_ventas`, coalesce(sum(case when `mc`.`tipo_movimiento` = 'Ingreso' then `mc`.`monto` else 0 end),0) AS `otros_ingresos`, coalesce(sum(case when `mc`.`tipo_movimiento` = 'Egreso' then `mc`.`monto` else 0 end),0) AS `egresos`, coalesce(sum(`v`.`monto_total`),0) + coalesce(sum(case when `mc`.`tipo_movimiento` = 'Ingreso' then `mc`.`monto` else 0 end),0) - coalesce(sum(case when `mc`.`tipo_movimiento` = 'Egreso' then `mc`.`monto` else 0 end),0) AS `balance_neto` FROM (((`sucursales` `s` left join `ventas` `v` on(`s`.`id_sucursal` = `v`.`id_sucursal` and `v`.`estado` = 1)) left join `sesiones_caja` `sc` on(`s`.`id_sucursal` = `sc`.`id_sucursal` and cast(`sc`.`fecha_apertura` as date) = cast(`v`.`fecha_venta` as date))) left join `movimientos_caja` `mc` on(`sc`.`id_sesion` = `mc`.`id_sesion`)) WHERE cast(`v`.`fecha_venta` as date) is not null GROUP BY `s`.`id_sucursal`, `s`.`nombre`, cast(`v`.`fecha_venta` as date) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_dashboard_superadmin_ventas`
--
DROP TABLE IF EXISTS `v_dashboard_superadmin_ventas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_dashboard_superadmin_ventas`  AS SELECT cast(`v`.`fecha_venta` as date) AS `fecha`, `s`.`id_sucursal` AS `id_sucursal`, `s`.`nombre` AS `sucursal`, count(`v`.`id_venta`) AS `total_ventas`, sum(`v`.`monto_total`) AS `ingresos_totales`, avg(`v`.`monto_total`) AS `ticket_promedio`, sum(case when `v`.`metodo_pago` = 'Efectivo' then `v`.`monto_total` else 0 end) AS `efectivo`, sum(case when `v`.`metodo_pago` = 'Tarjeta' then `v`.`monto_total` else 0 end) AS `tarjeta` FROM (`ventas` `v` join `sucursales` `s` on(`v`.`id_sucursal` = `s`.`id_sucursal`)) WHERE `v`.`estado` = 1 GROUP BY cast(`v`.`fecha_venta` as date), `s`.`id_sucursal`, `s`.`nombre` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_estado_inventario`
--
DROP TABLE IF EXISTS `v_estado_inventario`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_estado_inventario`  AS SELECT `i`.`id_insumo` AS `id_insumo`, `i`.`nombre` AS `nombre`, `i`.`stock_actual` AS `stock_actual`, `i`.`stock_minimo` AS `stock_minimo`, `i`.`unidad_medida` AS `unidad_medida`, `s`.`nombre` AS `sucursal`, CASE WHEN `i`.`stock_actual` <= 0 THEN 'Sin Stock' WHEN `i`.`stock_actual` <= `i`.`stock_minimo` THEN 'Crítico' WHEN `i`.`stock_actual` <= `i`.`stock_minimo` * 1.5 THEN 'Bajo' ELSE 'Normal' END AS `nivel_alerta`, `i`.`fecha_vencimiento` AS `fecha_vencimiento`, to_days(`i`.`fecha_vencimiento`) - to_days(curdate()) AS `dias_para_vencer` FROM (`insumos` `i` join `sucursales` `s` on(`i`.`id_sucursal` = `s`.`id_sucursal`)) WHERE `i`.`estado` = 1 ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_productos_mas_vendidos`
--
DROP TABLE IF EXISTS `v_productos_mas_vendidos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_productos_mas_vendidos`  AS SELECT `p`.`id_plato` AS `id_plato`, `p`.`nombre` AS `producto`, `c`.`nombre` AS `categoria`, `s`.`nombre` AS `sucursal`, date_format(`ped`.`fecha_hora`,'%Y-%m') AS `periodo`, sum(`dp`.`cantidad`) AS `unidades_vendidas`, sum(`dp`.`subtotal`) AS `ingresos_totales`, avg(`dp`.`precio_unitario`) AS `precio_promedio` FROM ((((`platos` `p` join `detalle_pedido` `dp` on(`p`.`id_plato` = `dp`.`id_plato`)) join `pedidos` `ped` on(`dp`.`id_pedido` = `ped`.`id_pedido`)) join `sucursales` `s` on(`p`.`id_sucursal` = `s`.`id_sucursal`)) left join `categorias_menu` `c` on(`p`.`id_categoria` = `c`.`id_categoria`)) WHERE `ped`.`estado_pedido` <> 'Cancelado' GROUP BY `p`.`id_plato`, `p`.`nombre`, `c`.`nombre`, `s`.`nombre`, date_format(`ped`.`fecha_hora`,'%Y-%m') ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_resumen_gastos_categoria`
--
DROP TABLE IF EXISTS `v_resumen_gastos_categoria`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_resumen_gastos_categoria`  AS SELECT `cg`.`nombre` AS `categoria`, `cg`.`tipo` AS `tipo`, `s`.`nombre` AS `sucursal`, date_format(`mc`.`fecha_movimiento`,'%Y-%m') AS `periodo`, count(0) AS `cantidad_movimientos`, sum(`mc`.`monto`) AS `total_gastado` FROM (((`movimientos_caja` `mc` join `categorias_gastos` `cg` on(`mc`.`id_categoria_gasto` = `cg`.`id_categoria_gasto`)) join `sesiones_caja` `sc` on(`mc`.`id_sesion` = `sc`.`id_sesion`)) join `sucursales` `s` on(`sc`.`id_sucursal` = `s`.`id_sucursal`)) WHERE `mc`.`tipo_movimiento` = 'Egreso' GROUP BY `cg`.`nombre`, `cg`.`tipo`, `s`.`nombre`, date_format(`mc`.`fecha_movimiento`,'%Y-%m') ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `acceso`
--
ALTER TABLE `acceso`
  ADD PRIMARY KEY (`id_acceso`),
  ADD UNIQUE KEY `uq_perfil_modulo` (`id_perfil`,`id_modulo`),
  ADD KEY `idx_perfil` (`id_perfil`),
  ADD KEY `idx_modulo` (`id_modulo`);

--
-- Indices de la tabla `anulacion_comprobantes`
--
ALTER TABLE `anulacion_comprobantes`
  ADD PRIMARY KEY (`id_anulacion`),
  ADD KEY `idx_comprobante` (`id_comprobante_electronico`),
  ADD KEY `idx_usuario` (`id_usuario_anulo`),
  ADD KEY `idx_fecha` (`fecha_anulacion`),
  ADD KEY `idx_estado` (`estado_baja`);

--
-- Indices de la tabla `bitacora_accion`
--
ALTER TABLE `bitacora_accion`
  ADD PRIMARY KEY (`id_bitacora`),
  ADD KEY `idx_usuario` (`id_usuario`),
  ADD KEY `idx_superadmin` (`id_superadmin`),
  ADD KEY `idx_modulo` (`modulo`),
  ADD KEY `idx_accion` (`accion`),
  ADD KEY `idx_fecha` (`fecha_hora`);

--
-- Indices de la tabla `categorias_gastos`
--
ALTER TABLE `categorias_gastos`
  ADD PRIMARY KEY (`id_categoria_gasto`),
  ADD KEY `idx_restaurante` (`id_restaurante`),
  ADD KEY `idx_tipo` (`tipo`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `categorias_menu`
--
ALTER TABLE `categorias_menu`
  ADD PRIMARY KEY (`id_categoria`),
  ADD KEY `idx_sucursal` (`id_sucursal`),
  ADD KEY `idx_orden` (`orden`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `idx_restaurante` (`id_restaurante`),
  ADD KEY `idx_documento` (`documento`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `comprobantes_electronicos`
--
ALTER TABLE `comprobantes_electronicos`
  ADD PRIMARY KEY (`id_comprobante_electronico`),
  ADD UNIQUE KEY `uq_serie_numero` (`serie_documento`,`numero_documento`),
  ADD KEY `fk_comp_elec_usuario` (`id_usuario_emisor`),
  ADD KEY `idx_venta` (`id_venta`),
  ADD KEY `idx_estado` (`estado_sunat`),
  ADD KEY `idx_fecha_emision` (`fecha_emision`);

--
-- Indices de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `idx_pedido` (`id_pedido`),
  ADD KEY `idx_plato` (`id_plato`);

--
-- Indices de la tabla `insumos`
--
ALTER TABLE `insumos`
  ADD PRIMARY KEY (`id_insumo`),
  ADD KEY `idx_sucursal` (`id_sucursal`),
  ADD KEY `idx_codigo` (`codigo_barra`),
  ADD KEY `idx_stock` (`stock_actual`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `mesas`
--
ALTER TABLE `mesas`
  ADD PRIMARY KEY (`id_mesa`),
  ADD UNIQUE KEY `uq_sucursal_mesa` (`id_sucursal`,`numero_mesa`),
  ADD KEY `idx_sucursal` (`id_sucursal`),
  ADD KEY `idx_estado_mesa` (`estado_mesa`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `modulo`
--
ALTER TABLE `modulo`
  ADD PRIMARY KEY (`id_modulo`),
  ADD UNIQUE KEY `nombre_modulo` (`nombre_modulo`),
  ADD KEY `idx_orden` (`orden`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `movimientos_caja`
--
ALTER TABLE `movimientos_caja`
  ADD PRIMARY KEY (`id_movimiento_caja`),
  ADD KEY `fk_movimiento_categoria` (`id_categoria_gasto`),
  ADD KEY `idx_sesion` (`id_sesion`),
  ADD KEY `idx_usuario` (`id_usuario`),
  ADD KEY `idx_tipo` (`tipo_movimiento`),
  ADD KEY `idx_fecha` (`fecha_movimiento`);

--
-- Indices de la tabla `movimientos_inventario`
--
ALTER TABLE `movimientos_inventario`
  ADD PRIMARY KEY (`id_movimiento`),
  ADD KEY `fk_mov_inv_proveedor` (`id_proveedor`),
  ADD KEY `idx_insumo` (`id_insumo`),
  ADD KEY `idx_usuario` (`id_usuario`),
  ADD KEY `idx_tipo` (`tipo_movimiento`),
  ADD KEY `idx_fecha` (`fecha_movimiento`);

--
-- Indices de la tabla `pago_suscripcion`
--
ALTER TABLE `pago_suscripcion`
  ADD PRIMARY KEY (`id_pago`),
  ADD KEY `idx_restaurante` (`id_restaurante`),
  ADD KEY `idx_periodo` (`periodo_fin`),
  ADD KEY `idx_estado` (`estado_pago`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `idx_sucursal` (`id_sucursal`),
  ADD KEY `idx_mesa` (`id_mesa`),
  ADD KEY `idx_usuario` (`id_usuario`),
  ADD KEY `idx_estado` (`estado_pedido`),
  ADD KEY `idx_fecha` (`fecha_hora`);

--
-- Indices de la tabla `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`id_perfil`),
  ADD UNIQUE KEY `nombre_perfil` (`nombre_perfil`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `platos`
--
ALTER TABLE `platos`
  ADD PRIMARY KEY (`id_plato`),
  ADD KEY `idx_categoria` (`id_categoria`),
  ADD KEY `idx_sucursal` (`id_sucursal`),
  ADD KEY `idx_disponible` (`disponible`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id_proveedor`),
  ADD KEY `idx_restaurante` (`id_restaurante`),
  ADD KEY `idx_ruc` (`ruc`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `restaurantes`
--
ALTER TABLE `restaurantes`
  ADD PRIMARY KEY (`id_restaurante`),
  ADD UNIQUE KEY `ruc` (`ruc`),
  ADD KEY `idx_ruc` (`ruc`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `sesiones_caja`
--
ALTER TABLE `sesiones_caja`
  ADD PRIMARY KEY (`id_sesion`),
  ADD KEY `fk_sesion_usuario_cierre` (`id_usuario_cierre`),
  ADD KEY `idx_sucursal` (`id_sucursal`),
  ADD KEY `idx_usuario` (`id_usuario`),
  ADD KEY `idx_estado` (`estado`),
  ADD KEY `idx_fecha_apertura` (`fecha_apertura`);

--
-- Indices de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD PRIMARY KEY (`id_sucursal`),
  ADD KEY `idx_restaurante` (`id_restaurante`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `super_admin`
--
ALTER TABLE `super_admin`
  ADD PRIMARY KEY (`id_superadmin`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `nombre_usuario_login` (`nombre_usuario_login`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD KEY `idx_email` (`email`),
  ADD KEY `idx_login` (`nombre_usuario_login`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `dni_usuario` (`dni_usuario`),
  ADD UNIQUE KEY `nombre_usuario_login` (`nombre_usuario_login`),
  ADD KEY `idx_perfil` (`id_perfil`),
  ADD KEY `idx_sucursal` (`id_sucursal`),
  ADD KEY `idx_dni` (`dni_usuario`),
  ADD KEY `idx_login` (`nombre_usuario_login`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id_venta`),
  ADD UNIQUE KEY `uq_comprobante` (`serie_comprobante`,`numero_comprobante`),
  ADD KEY `idx_pedido` (`id_pedido`),
  ADD KEY `idx_sesion` (`id_sesion`),
  ADD KEY `idx_sucursal` (`id_sucursal`),
  ADD KEY `idx_usuario` (`id_usuario`),
  ADD KEY `idx_cliente` (`id_cliente`),
  ADD KEY `idx_fecha` (`fecha_venta`),
  ADD KEY `idx_tipo_comprobante` (`tipo_comprobante`),
  ADD KEY `idx_estado` (`estado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `acceso`
--
ALTER TABLE `acceso`
  MODIFY `id_acceso` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `anulacion_comprobantes`
--
ALTER TABLE `anulacion_comprobantes`
  MODIFY `id_anulacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `bitacora_accion`
--
ALTER TABLE `bitacora_accion`
  MODIFY `id_bitacora` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `categorias_gastos`
--
ALTER TABLE `categorias_gastos`
  MODIFY `id_categoria_gasto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `categorias_menu`
--
ALTER TABLE `categorias_menu`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `comprobantes_electronicos`
--
ALTER TABLE `comprobantes_electronicos`
  MODIFY `id_comprobante_electronico` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `insumos`
--
ALTER TABLE `insumos`
  MODIFY `id_insumo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mesas`
--
ALTER TABLE `mesas`
  MODIFY `id_mesa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `modulo`
--
ALTER TABLE `modulo`
  MODIFY `id_modulo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `movimientos_caja`
--
ALTER TABLE `movimientos_caja`
  MODIFY `id_movimiento_caja` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `movimientos_inventario`
--
ALTER TABLE `movimientos_inventario`
  MODIFY `id_movimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pago_suscripcion`
--
ALTER TABLE `pago_suscripcion`
  MODIFY `id_pago` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id_perfil` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `platos`
--
ALTER TABLE `platos`
  MODIFY `id_plato` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `restaurantes`
--
ALTER TABLE `restaurantes`
  MODIFY `id_restaurante` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sesiones_caja`
--
ALTER TABLE `sesiones_caja`
  MODIFY `id_sesion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  MODIFY `id_sucursal` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `super_admin`
--
ALTER TABLE `super_admin`
  MODIFY `id_superadmin` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `acceso`
--
ALTER TABLE `acceso`
  ADD CONSTRAINT `fk_acceso_modulo` FOREIGN KEY (`id_modulo`) REFERENCES `modulo` (`id_modulo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_acceso_perfil` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id_perfil`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `anulacion_comprobantes`
--
ALTER TABLE `anulacion_comprobantes`
  ADD CONSTRAINT `fk_anulacion_comprobante` FOREIGN KEY (`id_comprobante_electronico`) REFERENCES `comprobantes_electronicos` (`id_comprobante_electronico`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_anulacion_usuario` FOREIGN KEY (`id_usuario_anulo`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `bitacora_accion`
--
ALTER TABLE `bitacora_accion`
  ADD CONSTRAINT `fk_bitacora_superadmin` FOREIGN KEY (`id_superadmin`) REFERENCES `super_admin` (`id_superadmin`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_bitacora_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `categorias_gastos`
--
ALTER TABLE `categorias_gastos`
  ADD CONSTRAINT `fk_categoria_gasto_restaurante` FOREIGN KEY (`id_restaurante`) REFERENCES `restaurantes` (`id_restaurante`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `categorias_menu`
--
ALTER TABLE `categorias_menu`
  ADD CONSTRAINT `fk_categoria_menu_sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `fk_cliente_restaurante` FOREIGN KEY (`id_restaurante`) REFERENCES `restaurantes` (`id_restaurante`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `comprobantes_electronicos`
--
ALTER TABLE `comprobantes_electronicos`
  ADD CONSTRAINT `fk_comp_elec_usuario` FOREIGN KEY (`id_usuario_emisor`) REFERENCES `usuario` (`id_usuario`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_comp_elec_venta` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD CONSTRAINT `fk_detalle_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_detalle_plato` FOREIGN KEY (`id_plato`) REFERENCES `platos` (`id_plato`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `insumos`
--
ALTER TABLE `insumos`
  ADD CONSTRAINT `fk_insumo_sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `mesas`
--
ALTER TABLE `mesas`
  ADD CONSTRAINT `fk_mesa_sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `movimientos_caja`
--
ALTER TABLE `movimientos_caja`
  ADD CONSTRAINT `fk_movimiento_categoria` FOREIGN KEY (`id_categoria_gasto`) REFERENCES `categorias_gastos` (`id_categoria_gasto`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_movimiento_sesion` FOREIGN KEY (`id_sesion`) REFERENCES `sesiones_caja` (`id_sesion`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_movimiento_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `movimientos_inventario`
--
ALTER TABLE `movimientos_inventario`
  ADD CONSTRAINT `fk_mov_inv_insumo` FOREIGN KEY (`id_insumo`) REFERENCES `insumos` (`id_insumo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mov_inv_proveedor` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mov_inv_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `pago_suscripcion`
--
ALTER TABLE `pago_suscripcion`
  ADD CONSTRAINT `fk_pago_restaurante` FOREIGN KEY (`id_restaurante`) REFERENCES `restaurantes` (`id_restaurante`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_pedido_mesa` FOREIGN KEY (`id_mesa`) REFERENCES `mesas` (`id_mesa`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_pedido_sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_pedido_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `platos`
--
ALTER TABLE `platos`
  ADD CONSTRAINT `fk_plato_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categorias_menu` (`id_categoria`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_plato_sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD CONSTRAINT `fk_proveedor_restaurante` FOREIGN KEY (`id_restaurante`) REFERENCES `restaurantes` (`id_restaurante`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `sesiones_caja`
--
ALTER TABLE `sesiones_caja`
  ADD CONSTRAINT `fk_sesion_sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sesion_usuario_apertura` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sesion_usuario_cierre` FOREIGN KEY (`id_usuario_cierre`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD CONSTRAINT `fk_sucursal_restaurante` FOREIGN KEY (`id_restaurante`) REFERENCES `restaurantes` (`id_restaurante`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_perfil` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id_perfil`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_usuario_sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `fk_venta_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_venta_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_venta_sesion` FOREIGN KEY (`id_sesion`) REFERENCES `sesiones_caja` (`id_sesion`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_venta_sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_venta_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
