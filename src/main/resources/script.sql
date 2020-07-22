
create table User (id bigint not null auto_increment, age integer, first_name varchar(255), last_name varchar(255), password varchar(255), salary bigint, username varchar(255), primary key (id)) engine=MyISAM;
INSERT INTO User (age, first_name, last_name,password,salary,username) values (23, 'admin', 'admin','$2a$04$EZzbSqieYfe/nFWfBWt2KeCdyq0UuDEM1ycFF8HzmlVR6sbsOnw7u',12345,'admin');
INSERT INTO User (age, first_name, last_name,password,salary,username) values (24, 'Alex123', 'Alex123','$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 3456, 'Alex123');
INSERT INTO User (age, first_name, last_name,password,salary,username) values (25, 'admin', 'daniel','$2y$12$IWq1kNs8g8xBACd60dw4WOKBRtBv/dJxBjeclmG1nLiTrQzTnYY6G', 12345,'daniel');

INSERT INTO User (id, username, password, salary, age) VALUES (200, 'Adam', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 4234, 'Adam');

create table board (
	id bigint not null auto_increment, 
	user_id bigint, 
	rows_number integer, 
	columns_number integer, 	
	cells text,
	mines integer,
	status tinytext,
    primary key (id)
) engine=MyISAM;

create table saved_game (
	id bigint not null auto_increment, 
	user_id bigint, 
	saved_game_id tinytext, 
	started_date_time timestamp, 	
    primary key (id)
) engine=MyISAM;


