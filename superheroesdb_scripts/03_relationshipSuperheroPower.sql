/*Create linking table for superhero and assistant*/
DROP TABLE IF EXISTS hero_power; /*Ensure no re-creation*/
CREATE TABLE hero_power (
    hero_id int REFERENCES superhero,
    power_id int REFERENCES superpower,
    PRIMARY KEY (hero_id, power_id)
);