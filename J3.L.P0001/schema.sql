CREATE DATABASE P0001;

USE P0001;

CREATE TABLE [Role] (
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(255)
);

CREATE TABLE [User] (
	id int IDENTITY(1,1) PRIMARY KEY,
	username nvarchar(255),
	password nvarchar(255),
	email nvarchar(255),
	role_id int FOREIGN KEY REFERENCES [Role](id)
);

CREATE TABLE Quiz (
	id int IDENTITY(1,1) PRIMARY KEY,
	quantity int,
	date_started datetime,
	[user_id] int FOREIGN KEY REFERENCES [User](id)
);

CREATE TABLE Question (
	id int IDENTITY(1,1) PRIMARY KEY,
	content nvarchar(255),
	date_created datetime,
	[user_id] int FOREIGN KEY REFERENCES [User](id)
);

CREATE TABLE [Option] (
	id int IDENTITY(1,1) PRIMARY KEY,
	content nvarchar(255),
	is_correct bit,
	question_id int FOREIGN KEY REFERENCES Question(id)
);

CREATE TABLE Ask (
	id int IDENTITY(1,1) PRIMARY KEY,
	question_id int FOREIGN KEY REFERENCES Question(id),
	date_answered datetime,
	quiz_id int FOREIGN KEY REFERENCES Quiz(id)
);

CREATE TABLE Answer (
  id int IDENTITY(1,1) PRIMARY KEY,
  option_id int FOREIGN KEY REFERENCES [Option](id),
  ask_id int FOREIGN KEY REFERENCES Ask(id)
);

INSERT INTO [Role] VALUES ('TEACHER'), ('STUDENT');
INSERT INTO [User] (username ,password ,email ,role_id) VALUES('teacher', '123', 'teacher@mail.com', 1), ('student', '123', 'student@mail.com', 2);
