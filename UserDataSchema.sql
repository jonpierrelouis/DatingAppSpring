DROP TABLE login;

CREATE TABLE login (
	user_id SERIAL PRIMARY KEY,
	user_name varchar(20) NOT NULL,
	user_email varchar(30) NOT NULL UNIQUE,
	user_password varchar(30) NOT NULL 
); 

INSERT INTO login VALUES (DEFAULT, 'test', 'test@gmail.com', 'password');

SELECT * FROM login;

