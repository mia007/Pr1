CONNECT 'jdbc:derby://localhost/testDB;create=true;user=testUser;password=testPass';

CREATE TABLE users_groups (
	user_id INT ,
	group_id INT,	
	 FOREIGN KEY (user_id)
     REFERENCES users(id),
     FOREIGN KEY (group_id)
     REFERENCES groups(id)
);

INSERT INTO users VALUES (DEFAULT, 'admin', 'admin', 'ivanov');
INSERT INTO users VALUES (DEFAULT, 'client', 'client', 'sidorov');	
INSERT INTO users (login, password, name) VALUES ('client2', 'client2', 'petrov');

-- --------------------
-- show tables
-- --------------------

SELECT * FROM users;

