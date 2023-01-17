DROP TABLE login;
DROP TABLE profile;
DROP TABLE likes;
DROP TABLE user_likes;
DROP TABLE dislikes;
DROP TABLE user_dislikes;

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
	gender varchar(10),
	sex_orientation varchar(10),
	CONSTRAINT ref_name FOREIGN KEY (login_user_id) REFERENCES login (user_id)
);

INSERT INTO profile VALUES (DEFAULT, 3, 'Jonny', 11, 12, 1996, 'i am a person', 'Boston', 'MA', NULL);

CREATE TABLE likes(
	like_id SERIAL PRIMARY KEY,
	like_name varchar(20) UNIQUE NOT NULL
);

CREATE TABLE user_likes(
	profile_id_fk int,
	like_id_fk int,
	CONSTRAINT ref_name FOREIGN KEY (profile_id_fk) REFERENCES profile (profile_id),
	CONSTRAINT ref_name2 FOREIGN KEY (like_id_fk) REFERENCES likes (like_id)
);

CREATE TABLE user_dislikes(
	profile_id_fk int,
	like_id_fk int,
	CONSTRAINT ref_name FOREIGN KEY (profile_id_fk) REFERENCES profile (profile_id),
	CONSTRAINT ref_name2 FOREIGN KEY (like_id_fk) REFERENCES likes (like_id)
);

INSERT INTO likes VALUES (DEFAULT, 'Reading');
INSERT INTO likes VALUES (DEFAULT, 'Writing');
INSERT INTO likes VALUES (DEFAULT, 'Running');
INSERT INTO likes VALUES (DEFAULT, 'Traveling');

INSERT INTO user_likes VALUES(1, 3);
INSERT INTO user_likes VALUES(1,1);

INSERT INTO user_dislikes VALUES(1, 3);
INSERT INTO user_dislikes VALUES(1,1);

--DELETE FROM profile WHERE profile_id = 2;
DELETE FROM likes WHERE like_id = 5;

SELECT * FROM login;
SELECT * FROM profile;
SELECT * FROM likes;
SELECT * FROM user_likes;
