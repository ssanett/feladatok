CREATE table users(
user_id bigint auto_increment,
user_name varchar(255),
primary key(user_id));

CREATE table messages(
id bigint auto_increment,
sender_id bigint,
receiver_id bigint,
message varchar(255),
primary key(id));