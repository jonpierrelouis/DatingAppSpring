DROP TABLE login;
DROP TABLE profile;

CREATE TABLE login (
	user_id SERIAL PRIMARY KEY,
	user_name varchar(20) NOT NULL,
	user_email varchar(30) NOT NULL UNIQUE,
	user_password varchar(30) NOT NULL 
); 

INSERT INTO login VALUES (DEFAULT, 'test', 'test@gmail.com', 'password');

CREATE TABLE profile (
	profile_id SERIAL PRIMARY KEY,
	login_user_id int,
	profile_name varchar(20),
	birth_day int,
	birth_month int,
	birth_year int,
	about_yourself varchar(300),
	city varchar(20),
	state varchar(20),
	image bytea,
	CONSTRAINT ref_name FOREIGN KEY (login_user_id) REFERENCES login (user_id)
);

INSERT INTO profile VALUES (DEFAULT, 3, 'Jonny', 11, 12, 1996, 'i am a person', 'Boston', 'MA', NULL);

SELECT * FROM login;
SELECT * FROM profile;
