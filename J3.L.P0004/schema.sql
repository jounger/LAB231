CREATE DATABASE P0004;

USE P0004;

CREATE TABLE Writer (
	id int IDENTITY(1,1) PRIMARY KEY,
	fullname nvarchar(255)
);


CREATE TABLE Article (
	id int IDENTITY(1,1) PRIMARY KEY,
	title nvarchar(255),
  introContent text,
	content text,
  writer_id int,
  published_date datetime
);

INSERT INTO Writer (fullname) VALUES('Nguyen Van An');
INSERT INTO Article (title, introContent, content, writer_id, published_date) VALUES('new title', 'hehe', '<di>helo</div>', 1, GETDATE());
