CREATE DATABASE IF NOT EXISTS northwind2;

USE northwind2;

CREATE TABLE IF NOT EXISTS fruits_products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    standard_price DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(3, 1) DEFAULT 1.0
);

CREATE TABLE IF NOT EXISTS purchase_order (
    order_id INT AUTO_INCREMENT NOT NULL,
    order_date DATE NOT NULL,
    customer_name VARCHAR(128) NOT NULL,
    ship_address VARCHAR(128),
    notes LONGTEXT,
    tax DECIMAL(2,2) DEFAULT 0.05,
    PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS purchase_order_details (
  id INT AUTO_INCREMENT NOT NULL,
  product VARCHAR(64),
  unit_price DECIMAL(3,2),
  discount DECIMAL(2,1) DEFAULT 1.0,
  quantity INT,
  order_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_order_id
    FOREIGN KEY (order_id) REFERENCES purchase_order(order_id)
);