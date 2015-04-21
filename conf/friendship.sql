create table user(
	id int auto_increment primary key,
	username varchar(20),
	password varchar(20),
	phone varchar(11),
	email varchar(20)
) 

create table activity(
	id int auto_increment primary key,
	content varchar(255),
	address varchar(100),
	acttype varchar(20),
	edittime date,
	activitytime date,
	userid int 
)