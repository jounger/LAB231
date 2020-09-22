CREATE DATABASE P0001;

USE P0001;

CREATE TABLE Roles (
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(255)
);

CREATE TABLE Users (
	id int IDENTITY(1,1) PRIMARY KEY,
	username nvarchar(255),
	password nvarchar(255),
	email nvarchar(255),
	role_id int UNIQUE FOREIGN KEY REFERENCES Roles(id)
);

CREATE TABLE Quizs (
	id int IDENTITY(1,1) PRIMARY KEY,
	quantity int,
	date_started datetime,
	users_id int UNIQUE FOREIGN KEY REFERENCES Users(id)
);

CREATE TABLE Questions (
	id int IDENTITY(1,1) PRIMARY KEY,
	content nvarchar(255),
	date_created datetime,
	users_id int UNIQUE FOREIGN KEY REFERENCES Users(id)
);

CREATE TABLE Answers (
	id int IDENTITY(1,1) PRIMARY KEY,
	content int,
	isCorrect bit,
	questions_id int UNIQUE FOREIGN KEY REFERENCES Questions(id)
);

CREATE TABLE QA (
	id int IDENTITY(1,1) PRIMARY KEY,
	questions_id int UNIQUE FOREIGN KEY REFERENCES Questions(id),
	answers_id int UNIQUE FOREIGN KEY REFERENCES Answers(id),
	date_answered datetime,
	quizs_id int UNIQUE FOREIGN KEY REFERENCES Quizs(id)
);

INSERT INTO Roles VALUES ('TEACHER'), ('STUDENT');
