DROP DATABASE IF EXISTS beerservice;
DROP USER IF EXISTS `beer_service`@`%`;
CREATE DATABASE IF NOT EXISTS beerservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `beer_service`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `beerservice`.* TO `beer_service`@`%`;
FLUSH PRIVILEGES;

USE beerservice;

CREATE TABLE `beer` (
                      `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
                      `beer_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                      `beer_style` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                      `created_date` datetime(6) DEFAULT NULL,
                      `last_modified_date` datetime(6) DEFAULT NULL,
                      `min_on_hand` int(11) DEFAULT NULL,
                      `price` decimal(19,2) DEFAULT NULL,
                      `quantity_to_brew` int(11) DEFAULT NULL,
                      `upc` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                      `version` bigint(20) DEFAULT NULL,
                      PRIMARY KEY (`id`),
                      UNIQUE KEY `UK_p9mb364xktkjqmprmg89u2etr` (`upc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;