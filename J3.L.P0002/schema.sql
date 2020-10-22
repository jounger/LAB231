CREATE DATABASE P0002;

USE P0002;

CREATE TABLE [User] (
	id int IDENTITY(1,1) PRIMARY KEY,
	email nvarchar(255),
	password nvarchar(255),
	firstname nvarchar(255),
  lastname nvarchar(255),
  address nvarchar(255),
  phone nvarchar(10),
  sex int,
  age int,
  card_number nvarchar(255)
);


CREATE TABLE Flight (
	id int IDENTITY(1,1) PRIMARY KEY,
	[from] nvarchar(255),
  [to] nvarchar(255),
	departure_time datetime,
  flight_detail float,
  price float
);

CREATE TABLE Booking (
  id int IDENTITY(1,1) PRIMARY KEY,
  reservation_code nvarchar(255),
  ticket_issue_date datetime,
  flight_id int FOREIGN KEY REFERENCES [Flight](id),
  [user_id] int FOREIGN KEY REFERENCES [User](id)
);

INSERT INTO [Flight] ([from], [to], departure_time, flight_detail, price) VALUES('HN', 'HCM', '2020-09-24 19:52:06.910', 1.5, 113), ('HN', 'HCM', '2020-09-24 22:52:06.910', 1.5, 113);

INSERT INTO [User] (email, password, firstname, lastname, address, phone, sex, age, card_number) VALUES('nguyenvanan@gmail.com', '123', 'An', 'Nguyen', 'HN, VN', '09673900XX', 0, 22, 1644646);
