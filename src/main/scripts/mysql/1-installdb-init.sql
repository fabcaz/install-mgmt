
DROP DATABASE IF EXISTS installdb;
DROP USER IF EXISTS 'user1'@'%';

CREATE DATABASE IF NOT EXISTS installdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'user1'@'%' IDENTIFIED WITH mysql_native_password BY 'aaaa';

-- GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, REFERENCES ON `installdb`.* TO 'user1'@'%';
GRANT ALL ON `installdb`.* TO 'user1'@'%';
FLUSH PRIVILEGES;
