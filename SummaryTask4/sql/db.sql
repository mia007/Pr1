drop database  st4 ;
create database  st4 ;
use  st4 ;

create table if not exists  user  (
	 id  serial,
	 email  varchar(60) not null unique,
	 password  varchar(100) not null,
	 f_name  varchar(40),
	 l_name  varchar(40),
	 role_id  tinyint unsigned not null,
	
	primary key (id)
) charset=utf8;

INSERT INTO user (id, email, password, f_name, l_name, role_id) VALUES (3,  'anna@gmail.com',  '866f60562a153875f2d7e510954f84c634f92867', NULL, NULL, 1);

create table if not exists  train (
	 id  serial,
	 tag  varchar(10) not null unique,
	 price  decimal(5,2) unsigned not null,
	
	primary key (id)
) charset=utf8;

INSERT INTO  train  (id, tag,  price) VALUES (1, '110 O', 130.00);
INSERT INTO  train  (tag,  price) VALUES ('115 O', 120.00);
INSERT INTO  train  (tag,  price) VALUES ('082 П', 75.00);
INSERT INTO  train  (tag,  price) VALUES ('726 Р', 90.00);

create table if not exists  station  (
	 id  smallint unsigned auto_increment not null unique,
	 name  varchar(30) not null unique,
	
	primary key (id)
) charset=utf8;

INSERT INTO  station (name)  VALUES ('Бердянск' );
INSERT INTO  station (name) VALUES ('Винница' );
INSERT INTO  station (name) VALUES ('Дарница' );
INSERT INTO  station (name) VALUES ('Запорожье' );
INSERT INTO  station (name) VALUES ('Здолбунов' );
INSERT INTO  station (name) VALUES ('Киев Пасс' );
INSERT INTO  station (name) VALUES ( 'Кременчуг' );
INSERT INTO  station (name) VALUES ( 'Львов Пасс' );
INSERT INTO  station (name) VALUES ( 'Одесса Главная' );
INSERT INTO  station (name) VALUES ('Полтава' );
INSERT INTO  station (name) VALUES ('Харьков Пасс' );
INSERT INTO  station (name) VALUES ('Херсон' );

create table if not exists  route (
	 id  smallint unsigned auto_increment not null unique,
	 date  date not null,
	 train_id  bigint unsigned not null,
	
	primary key (id),
	foreign key (train_id) references  train (id) on delete cascade on update cascade
) charset=utf8;

INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-05', 1);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-05', 2);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-06', 1);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-06', 2);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-07', 1);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-07', 2);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-08', 1);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-08', 2);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-09', 2);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-10', 2);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-05', 3);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-05', 4);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-06', 3);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-06', 4);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-07', 3);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-07', 4);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-08', 3);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-08', 4);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-09', 3);
INSERT INTO  route  (date,  train_id ) VALUES ('2017-10-10', 4);

create table if not exists  route_item (
	 id  serial,
	 arr_time  time,
	 dep_time  time,
	 ordinal  tinyint not null,
	 train_id  bigint unsigned not null,
	 station_id  smallint unsigned not null,
	
	primary key (id),
	unique key  ordinal_train_station  (ordinal ,  train_id ,  station_id),
	foreign key (train_id) references  train (id) on delete cascade on update cascade,
	foreign key (station_id) references  station (id) on delete cascade on update cascade
) charset=utf8;


INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES (NULL,  '07:23:00', 0, 1, 1);
INSERT INTO  route_item  ( arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES ('08:58:00',  '09:00:00', 1, 1, 2);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ('09:50:00',  '09:52:00', 2, 1, 3);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ('12:02:00', NULL, 3, 1, 4);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ( NULL,  '18:48:00', 0, 2, 5);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ('21:04:00',  '21:06:00', 1, 2, 6);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ('00:47:00',  '01:07:00', 2, 2, 7);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ('02:54:00',  '02:56:00', 3, 2, 8);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ( '08:25:00',  NULL, 4, 2, 9);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ( NULL,  '23:31:00', 0, 3, 3);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ( '01:07:00',  '01:12:00', 1, 3, 4);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ('06:01:00', NULL, 2, 3, 8);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES (NULL,  '18:03:00', 0, 4, 2);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ('20:11:00',  '20:13:00', 1, 4, 4);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ('21:04:00',  '21:06:00', 2, 4, 3);
INSERT INTO  route_item  ( arr_time, dep_time,  ordinal,  train_id,  station_id ) VALUES ( '22:40:00', NULL, 3, 4, 7);


create table if not exists  carriage_type  (
	 id  tinyint unsigned auto_increment not null unique,
	 name  varchar(15) not null unique,
	 seat_num  tinyint unsigned not null,
	
	primary key (id)
) charset=utf8;

insert into  carriage_type  values(1, 'berth3', 54);
insert into  carriage_type  values(2,  'compartment2', 36);
insert into  carriage_type  values(3,  'deLuxe1', 16);
insert into  carriage_type  values(4,  'seating2', 56);
insert into  carriage_type  values(5,  'seating1', 56); 

create table if not exists  carriage  (
	 id  serial,
	 tag  varchar(10) not null,
	 price  decimal (5,2) not null,
	 type_id  tinyint unsigned not null,
	 train_id  bigint unsigned not null,
	
	primary key (id),
	unique key  tag_train  (tag ,  train_id),
	foreign key (type_id) references  carriage_type (id) on update cascade,
	foreign key (train_id) references  train (id) on delete cascade on update cascade
) charset=utf8;

INSERT INTO  carriage  (tag,  price,  type_id,  train_id ) VALUES ('1', 25.00, 5, 1);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('2', 25.00, 5, 1);
INSERT INTO  carriage  (tag,   price,  type_id,  train_id ) VALUES ('3',25.00, 5, 1);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('4a', 15.00, 4, 1);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('4', 15.00, 4, 1);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('5', 15.00, 4, 1);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('1', 15.00, 1, 2);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('2',  16.00 , 1, 2);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('12',  20.00, 2, 2);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('4',  16.00, 1, 2);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('10', 20.00, 2, 2);
INSERT INTO  carriage  (tag,  price,  type_id,  train_id ) VALUES ('3', 15.00, 1, 2);
INSERT INTO  carriage  (tag,   price,  type_id,  train_id ) VALUES ( '2', 13.00, 1, 3);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('3', 13.00, 1, 3);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('7', 13.00, 1, 3);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('4', 25.00, 2, 3);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('5', 25.00, 2, 3);
INSERT INTO  carriage  (tag,   price,  type_id,  train_id ) VALUES ('6', 60.00, 3, 3);
INSERT INTO  carriage  (tag,   price,  type_id,  train_id ) VALUES ('3', 23.00, 5, 4);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('5', 23.00, 5, 4);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ( '6', 23.00, 5, 4);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('1a', 16.00, 4, 4);
INSERT INTO  carriage  (tag,   price,  type_id,  train_id ) VALUES ('1', 16.00, 4, 4);
INSERT INTO  carriage  (tag,  price,   type_id,  train_id ) VALUES ('2', 16.00, 4, 4);

create table if not exists  ticket (
	 id serial,
	 f_name  varchar(40) not null,
	 l_name  varchar(40) not null,
	 seat_num  tinyint not null,
	 price  decimal(5,2) not null,
	 dep_date  timestamp not null,
	 arr_date  timestamp not null,
	 user_id  bigint unsigned not null,
	 carriage_id  bigint unsigned not null,
	 route_id  smallint unsigned not null,
	 route_item_dep_id  bigint unsigned not null,
	 route_item_arr_id  bigint unsigned not null,
	
	primary key(id),
	
	unique key  ticket_uniq  (seat_num ,  carriage_id ,  route_id ,  route_item_dep_id ,  route_item_arr_id),
	
	foreign key (user_id) references  user (id) on update cascade on delete cascade,
	foreign key (carriage_id) references  carriage (id) on update cascade on delete cascade,
	foreign key (route_id) references  route (id) on update cascade,
	foreign key (route_item_dep_id) references  route_item (id) on update cascade,
	foreign key (route_item_arr_id) references  route_item (id) on update cascade
) charset=utf8;

