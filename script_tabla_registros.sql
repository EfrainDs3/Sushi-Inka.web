-- ============================================================================
-- TABLA REGISTROS - Usuarios Externos para Acceso API
-- ============================================================================
-- Almacena registros de usuarios externos (clientes, socios, integraciones)
-- que acceden a través de tokens de acceso

CREATE TABLE IF NOT EXISTS `registros` (
  `id_registro` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `llave_secreta` varchar(255) NOT NULL COMMENT 'Secret key para validar acceso',
  `access_token` varchar(500) DEFAULT NULL COMMENT 'Token JWT generado',
  `estado` tinyint(4) DEFAULT 1 COMMENT '1=Activo, 0=Inactivo',
  `id_usuario` int(11) DEFAULT NULL COMMENT 'FK opcional a usuario interno del sistema',
  `fecha_creacion` datetime DEFAULT current_timestamp(),
  `ultimo_acceso` datetime DEFAULT NULL,
  `ip_ultimo_acceso` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_registro`),
  UNIQUE KEY `uk_email` (`email`),
  UNIQUE KEY `uk_access_token` (`access_token`),
  KEY `idx_estado` (`estado`),
  KEY `idx_id_usuario` (`id_usuario`),
  KEY `fk_registros_usuario` (`id_usuario`),
  CONSTRAINT `fk_registros_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ============================================================================
-- ÍNDICES ADICIONALES para optimización
-- ============================================================================
ALTER TABLE `registros`
  ADD INDEX `idx_fecha_creacion` (`fecha_creacion`),
  ADD INDEX `idx_ultimo_acceso` (`ultimo_acceso`);

-- ============================================================================
-- INSERTS DE EJEMPLO (opcional)
-- ============================================================================
-- INSERT INTO registros (nombres, apellidos, email, llave_secreta, access_token, estado, id_usuario) 
-- VALUES 
-- ('Cliente', 'Externo', 'cliente@example.com', 'secret_key_123', NULL, 1, NULL);
