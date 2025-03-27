CREATE SCHEMA `reto2_db` ;

USE mysql;

CREATE USER 'reto2_user'@'%' IDENTIFIED BY 'reto2';

GRANT ALL PRIVILEGES ON reto2_db.* TO 'reto2_user'@'%';
FLUSH PRIVILEGES;