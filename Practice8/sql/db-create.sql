drop database practice8;

create database practice8;

use practice8;

CREATE TABLE users (
	id INT primary key auto_increment,
	login varchar(25) not null unique
	);
	
CREATE TABLE groups (
	id INT primary key auto_increment,
	name varchar(25) not null unique
	);

CREATE TABLE users_groups (
	user_id INT,
	group_id INT,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
	FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE	
);

insert into users values(default, "ivanov");
insert into groups values(default, "teamA");



