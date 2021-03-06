CREATE USER IF NOT EXISTS testUser IDENTIFIED BY 'testPass';

DROP DATABASE IF EXISTS ucinci_test;
CREATE DATABASE ucinci_test DEFAULT CHARACTER SET utf8;

USE ucinci_test;

GRANT ALL ON ucinci_test.* TO 'testUser'@'%';

FLUSH PRIVILEGES;