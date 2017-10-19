CONNECT 'jdbc:derby://localhost/testDB;create=true;user=testUser;password=testPass';

CREATE TABLE users (
	id INT PRIMARY KEY generated always as identity,
	login VARCHAR(15) NOT NULL UNIQUE,	
	password VARCHAR(15) NOT NULL,
	name VARCHAR(25) NOT NULL
);

INSERT INTO users VALUES (DEFAULT, 'admin', 'admin', 'ivanov');
INSERT INTO users VALUES (DEFAULT, 'client', 'client', 'sidorov');	
INSERT INTO users (login, password, name) VALUES ('client2', 'client2', 'petrov');

-- --------------------
-- show tables
-- --------------------

SELECT * FROM users;

