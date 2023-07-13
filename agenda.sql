CREATE DATABASE Agenda;

CREATE TABLE contacts(
  id_contact INT NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  phone_number VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_contact`));

INSERT INTO contacts(first_name,last_name, phone_number) VALUE('Jane','Doe','1234567890');