CREATE DATABASE P0005;

USE P0005;

CREATE TABLE Category (
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(255),
);

CREATE TABLE Article (
	id int IDENTITY(1,1) PRIMARY KEY,
	title nvarchar(255),
	image nvarchar(200),
	intro_content text,
	content text,
	category_id int FOREIGN KEY REFERENCES Category(id),
	published_date datetime
);

INSERT INTO Category(name) VALUES('Art');
INSERT INTO Category(name) VALUES('Balla');

INSERT INTO Article (title, image, intro_content, content, category_id, published_date) VALUES('new title', 'img03.jpg', 'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC', 'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.

The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.', 1, GETDATE());
