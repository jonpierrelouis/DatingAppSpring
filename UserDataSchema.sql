CREATE TABLE login (
	user_id SERIAL PRIMARY KEY,
	user_name varchar(20) NOT NULL,
	user_password varchar(30) NOT NULL UNIQUE 
); 