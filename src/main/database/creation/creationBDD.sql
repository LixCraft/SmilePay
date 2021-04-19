CREATE TABLE merchant (
	id INT PRIMARY KEY,
	create_date TIMESTAMP,
	name VARCHAR ( 250 ) NOT NULL,
	lastname VARCHAR ( 250 ) NOT NULL,
	birthdate date NOT NULL
);

CREATE TABLE product (
	id INT PRIMARY KEY,
	create_date TIMESTAMP,
	label VARCHAR ( 500 ),
	unit_price NUMERIC,
	currency VARCHAR ( 150 ),
	weight NUMERIC,
	height NUMERIC
);

CREATE TABLE merchant_product (
    id INT PRIMARY KEY,
    affiliation_id VARCHAR (100) NOT NULL UNIQUE,
    merchant_id INT NOT NULL,
    product_id INT NOT NULL,
    affiliation_date date,
    FOREIGN KEY (merchant_id)
        REFERENCES merchant (id),
    FOREIGN KEY (product_id)
        REFERENCES product (id)
);

CREATE TABLE address (
    id INT PRIMARY KEY,
    number INT,
    street VARCHAR (500),
    zipcode VARCHAR (50),
    merchant_id INT NOT NULL,
    FOREIGN KEY (merchant_id)
        REFERENCES merchant(id)
);

CREATE SEQUENCE merchant_id_seq INCREMENT 1 START 1;
CREATE SEQUENCE product_id_seq INCREMENT 1 START 1;
CREATE SEQUENCE address_id_seq INCREMENT 1 START 1;
CREATE SEQUENCE merchant_product_id_seq INCREMENT 1 START 1;