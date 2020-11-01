CREATE DATABASE P0002;

USE P0002;

CREATE TABLE `User` (
  id int AUTO_INCREMENT PRIMARY KEY,
  email varchar(255),
  password varchar(255),
  firstname varchar(255),
  lastname varchar(255),
  address varchar(255),
  phone varchar(10),
  sex int,
  age int,
  card_number varchar(255)
);


CREATE TABLE Flight (
  id int AUTO_INCREMENT PRIMARY KEY,
  `from` varchar(255),
  `to` varchar(255),
  departure_time datetime,
  flight_detail float,
  price float
);

CREATE TABLE Booking (
  id int AUTO_INCREMENT PRIMARY KEY,
  reservation_code varchar(255),
  ticket_issue_date datetime,
  flight_id int,
  `user_id` int,
  FOREIGN KEY (flight_id) REFERENCES `Flight`(id),
  FOREIGN KEY (user_id) REFERENCES `User`(id)
);

INSERT INTO `Flight` (`from`, `to`, departure_time, flight_detail, price) VALUES
('HN', 'HCM', '2020-09-24 19:52:06.910', 1.5, 115), 
('HN', 'HCM', '2020-09-24 22:52:06.910', 1.2, 123),
('DN', 'HN', '2020-09-24 12:52:06.910', 1, 103),
('HN', 'DN', '2020-09-24 20:52:06.910', 0.8, 86);
INSERT INTO `User` (email, password, firstname, lastname, address, phone, sex, age, card_number) VALUES
('nguyenvanan@gmail.com', '123', 'An', 'Nguyen', 'HN, VN', '09673900XX', 0, 22, 1644646);
