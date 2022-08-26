INSERT INTO superpower (power_name, power_desc) 
VALUES ('Webshooting', 'Can shoot webs from their wrists.');

INSERT INTO superpower (power_name, power_desc) 
VALUES ('Superhuman strength', 'Signifacantly enhanced strength compared to a normal person.');

INSERT INTO superpower (power_name, power_desc) 
VALUES ('Genius', 'Genius level intelligence.');

INSERT INTO superpower (power_name, power_desc) 
VALUES ('Accelerated healing', 'Wounds heal significantly faster than on normal people.');

/* Associate power with superhero */
INSERT INTO hero_power (hero_id, power_id) VALUES (1, 1);
INSERT INTO hero_power (hero_id, power_id) VALUES (1, 2);
INSERT INTO hero_power (hero_id, power_id) VALUES (1, 3);
INSERT INTO hero_power (hero_id, power_id) VALUES (1, 4);
INSERT INTO hero_power (hero_id, power_id) VALUES (2, 2);
INSERT INTO hero_power (hero_id, power_id) VALUES (2, 4);
INSERT INTO hero_power (hero_id, power_id) VALUES (3, 1);