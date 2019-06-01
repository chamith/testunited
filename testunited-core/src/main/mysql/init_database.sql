CREATE DATABASE testunited;

CREATE USER 'springuser'@'%' identified by 'testunited_spring';

GRANT ALL on testunited.* to 'springuser'@'%';
