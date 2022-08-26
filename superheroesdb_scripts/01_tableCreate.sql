/*Create table for superheroes*/
DROP TABLE IF EXISTS superhero;	/*Ensure no re-creation*/
CREATE TABLE superhero (
hero_id serial PRIMARY KEY,
hero_name varchar(50) NOT NULL,
hero_alias varchar(50) NOT NULL,
hero_origin varchar(50) NOT NULL
);

/*Create table for assistants of superheroes*/
DROP TABLE IF EXISTS assistant; /*Ensure no re-creation*/
CREATE TABLE assistant (
assist_id serial PRIMARY KEY,
assist_name varchar(50) NOT NULL
);

/*Create table for powers of superheroes*/
DROP TABLE IF EXISTS superpower; /*Ensure no re-creation*/
CREATE TABLE superpower (
power_id serial PRIMARY KEY,
power_name varchar(50) NOT NULL,
power_desc varchar(100) NOT NULL
);