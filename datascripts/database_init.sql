CREATE DATABASE productservice;

CREATE USER productservice IDENTIFIED BY 'password';

USE productservice;

GRANT ALL ON *.* TO  'productservice'@'%';