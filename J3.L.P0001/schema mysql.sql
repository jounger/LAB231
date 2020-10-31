CREATE DATABASE P0001;

USE P0001;

CREATE TABLE `role` (
	id int AUTO_INCREMENT PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE `user` (
	id int AUTO_INCREMENT PRIMARY KEY,
	username varchar(255),
	password varchar(255),
	email varchar(255),
    role_id int,
	FOREIGN KEY (role_id) REFERENCES `role`(id)
);

CREATE TABLE quiz (
	id int AUTO_INCREMENT PRIMARY KEY,
	quantity int,
	date_started datetime,
    user_id int,
	FOREIGN KEY (user_id) REFERENCES `user`(id)
);

CREATE TABLE question (
	id int AUTO_INCREMENT PRIMARY KEY,
	content varchar(255),
	date_created datetime,
    user_id int,
	FOREIGN KEY (user_id) REFERENCES `user`(id)
);

CREATE TABLE `option` (
	id int AUTO_INCREMENT PRIMARY KEY,
	content varchar(255),
	is_correct bit,
    question_id int,
	FOREIGN KEY (question_id) REFERENCES question(id)
);

CREATE TABLE ask (
	id int AUTO_INCREMENT PRIMARY KEY,
	date_answered datetime,
    question_id int,
    quiz_id int,
    FOREIGN KEY (question_id) REFERENCES question(id),
	FOREIGN KEY (quiz_id) REFERENCES quiz(id)
);

CREATE TABLE answer (
  id int AUTO_INCREMENT PRIMARY KEY,
  option_id int,
  ask_id int,
  FOREIGN KEY (option_id) REFERENCES `option`(id),
  FOREIGN KEY (ask_id) REFERENCES ask(id)
);

INSERT INTO `role`(name) VALUES ('TEACHER'), ('STUDENT');
INSERT INTO `user`(username ,password ,email ,role_id) VALUES('teacher', '123', 'teacher@mail.com', 1), ('student', '123', 'student@mail.com', 2);
INSERT INTO `question`(content, date_created, user_id) VALUES ('The name of capital of Vietnam after year of 1975?', NOW(), 1);
