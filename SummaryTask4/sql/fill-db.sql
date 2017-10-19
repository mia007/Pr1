INSERT INTO users (id, email, password, f_name, l_name, role_id) VALUES (3,  'anna@gmail.com',  '51426', NULL, NULL, 1);

INSERT INTO  train  ( id,  tag,  price,  type_id ) VALUES (1,  '110 O', '130.00', NULL);
INSERT INTO  train  ( id,  tag,  price,  type_id ) VALUES (2,  '115 O', '120.00', NULL);
INSERT INTO  train  ( id,  tag,  price,  type_id ) VALUES (3,  '082 �', '75.00', NULL);
INSERT INTO  train  ( id,  tag,  price,  type_id ) VALUES (4,  '726 �', '90.00', NULL);

INSERT INTO  station (name)  VALUES ('��������' );
INSERT INTO  station (name) VALUES ('�������' );
INSERT INTO  station (name) VALUES ('�������' );
INSERT INTO  station (name) VALUES ('���������' );
INSERT INTO  station (name) VALUES ('���������' );
INSERT INTO  station (name) VALUES ('���� ����' );
INSERT INTO  station (name) VALUES ( '�����' );
INSERT INTO  station (name) VALUES ( '����� ����' );
INSERT INTO  station (name) VALUES ( '������ �������' );
INSERT INTO  station (name) VALUES ('�������' );
INSERT INTO  station (name) VALUES ('������� ����' );
INSERT INTO  station (name) VALUES ('������' );

INSERT INTO  route  ( id,  date,  train_id ) VALUES (4,  '2017-03-05', 1);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (13,  '2017-03-05', 2);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (3,  '2017-03-06', 1);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (12,  '2017-03-06', 2);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (2,  '2017-03-07', 1);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (10,  '2017-03-07', 2);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (1,  '2017-03-08', 1);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (14,  '2017-03-08', 2);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (15,  '2017-03-09', 2);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (11,  '2017-03-10', 2);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (41,  '2017-03-05', 3);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (17,  '2017-03-05', 4);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (30,  '2017-03-06', 3);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (19,  '2017-03-06', 4);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (20,  '2017-03-07', 3);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (21,  '2017-03-07', 4);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (18,  '2017-03-08', 3);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (23,  '2017-03-08', 4);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (35,  '2017-03-09', 3);
INSERT INTO  route  ( id,  date,  train_id ) VALUES (22,  '2017-03-10', 4);

INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (1, NULL,  '07:23:00', 0, 1, 1);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (2,  '08:58:00',  '09:00:00', 1, 1, 3);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (3,  '09:50:00',  '09:52:00', 2, 1, 4);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (4,  '11:46:00',  '11:48:00', 3, 1, 15);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (5,  '12:02:00', NULL, 4, 1, 2);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (6, NULL,  '18:48:00', 0, 2, 1);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (7,  '21:04:00',  '21:06:00', 1, 2, 3);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (8,  '00:47:00',  '01:07:00', 2, 2, 2);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (9,  '02:54:00',  '02:56:00', 3, 2, 12);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (10,  '04:54:00',  '04:56:00', 4, 2, 13);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (11,  '05:44:00',  '06:04:00', 5, 2, 14);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (12,  '07:15:00',  '07:17:00', 6, 2, 16);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (13,  '08:25:00',  '08:48:00', 7, 2, 6);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (14,  '10:58:00', NULL, 8, 2, 18);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (15, NULL,  '21:37:00', 0, 3, 21);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (16,  '23:09:00',  '23:31:00', 1, 3, 11);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (17,  '01:07:00',  '01:12:00', 2, 3, 5);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (18,  '02:47:00',  '02:49:00', 3, 3, 20);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (19,  '03:48:00',  '03:58:00', 4, 3, 19);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (20,  '04:47:00',  '04:52:00', 5, 3, 22);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (21,  '06:01:00', NULL, 6, 3, 1);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (22, NULL,  '18:03:00', 0, 4, 2);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (23,  '18:17:00',  '18:19:00', 1, 4, 15);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (24,  '20:11:00',  '20:13:00', 2, 4, 4);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (25,  '21:04:00',  '21:06:00', 3, 4, 3);
INSERT INTO  route_item  ( id,  arr_time,  dep_time,  ordinal,  train_id,  station_id ) VALUES (26,  '22:40:00', NULL, 4, 4, 1);

insert into  carriage_type  values(1, 'couchette3', 65);
insert into  carriage_type  values(2,  'corridor2', 35);
insert into  carriage_type  values(3,  'suite1', 25);
insert into  carriage_type  values(4,  'sitting2', 70);
insert into  carriage_type  values(5,  'sitting1', 60); 

INSERT INTO  carriage  ( id,  tag,  price,  type_id,  train_id ) VALUES (1,  '1', '25.00', 5, 1);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (2,  '2', '25.00', 5, 1);
INSERT INTO  carriage  ( id,  tag,   price,  type_id,  train_id ) VALUES (3,  '3','25.00', 5, 1);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (4,  '4a', '15.00', 4, 1);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (5, '4', '15.00', 4, 1);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (6,  '5', '15.00', 4, 1);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (7,  '1', '15.00', 1, 2);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (8,  '2', '16.00', 1, 2);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (9,  '12', '20.00', 2, 2);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (10, '4', '16.00', 1, 2);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (11,  '10', '20.00', 2, 2);
INSERT INTO  carriage  ( id,  tag,  price,  type_id,  train_id ) VALUES (12,  '3', '15.00', 1, 2);
INSERT INTO  carriage  ( id,  tag,   price,  type_id,  train_id ) VALUES (14,  '2', '13.00', 1, 3);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (15,  '3', '13.00', 1, 3);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (16,  '7', '13.00', 1, 3);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (17,  '4', '25.00', 2, 3);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (18,  '5', '25.00', 2, 3);
INSERT INTO  carriage  ( id,  tag,   price,  type_id,  train_id ) VALUES (19,  '6', '30.00', 3, 3);
INSERT INTO  carriage  ( id,  tag,   price,  type_id,  train_id ) VALUES (21,  '3', '23.00', 5, 4);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (22,  '5', '23.00', 5, 4);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (23,  '6', '23.00', 5, 4);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (24, '1a', '16.00', 4, 4);
INSERT INTO  carriage  ( id,  tag,   price,  type_id,  train_id ) VALUES (25,  '1', '16.00', 4, 4);
INSERT INTO  carriage  ( id,  tag,  price,   type_id,  train_id ) VALUES (26,  '2', '16.00', 4, 4);

insert into seat_type values(1,  'upper' );
insert into seat_type values(2,  'lower' );
insert into seat_type values(3,  'sitting' );

insert into  facility_type(name, price)  values( 'bed_linen', 20.00);
insert into  facility_type(name, price)  values( 'tea', 7.00);

