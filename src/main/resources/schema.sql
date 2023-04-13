CREATE DATABASE IF NOT EXISTS northwind2;

USE northwind2;

CREATE TABLE IF NOT EXISTS fruits_products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    standard_price DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(3, 1) DEFAULT 1.0
);

INSERT INTO fruits_products (name, standard_price, discount) VALUES
   ('Apple', 0.90, 1.0),
   ('Banana', 1.99, 1.0),
   ('Orange', 1.49, 1.0),
   ('Pear', 0.90, 1.0),
   ('Pineapple', 3.99, 1.0),
   ('Grapes', 1.25, 1.0),
   ('Watermelon', 3.50, 1.0),
   ('Strawberry', 2.00, 1.0),
   ('Blueberry', 2.30, 1.0),
   ('Kiwi', 1.80, 1.0);

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