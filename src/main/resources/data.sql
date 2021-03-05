INSERT INTO superpowers (id, name, description) VALUES (0, 'Fly', 'Ability to levitate');
INSERT INTO superpowers (id, name, description) VALUES (1, 'Strength', 'Super strength power');
INSERT INTO superpowers (id, name, description) VALUES (2, 'Spiderwebs', 'Creates webs to bounce or to capture enemies');
INSERT INTO superpowers (id, name, description) VALUES (3, 'Fire balls', 'Ability to shot fire (or energy) balls');
INSERT INTO superpowers (id, name, description) VALUES (4, 'Metal jaws', 'Metals jaws to tear anything on parts');
INSERT INTO superpowers (id, name, description) VALUES (5, 'Instinct', 'Special instinct to detect dangers');

INSERT INTO superheroes (id, name) VALUES (0, 'Superman');
INSERT INTO superheroes (id, name) VALUES (1, 'Spiderman');
INSERT INTO superheroes (id, name) VALUES (2, 'Ironman');
INSERT INTO superheroes (id, name) VALUES (3, 'Wolverine');
INSERT INTO superheroes (id, name) VALUES (4, 'Captain America');

INSERT INTO superheroes_superpowers (superhero_id, superpowers_id) VALUES (0, 0);
INSERT INTO superheroes_superpowers (superhero_id, superpowers_id) VALUES (0, 1);
INSERT INTO superheroes_superpowers (superhero_id, superpowers_id) VALUES (1, 1);
INSERT INTO superheroes_superpowers (superhero_id, superpowers_id) VALUES (1, 2);
INSERT INTO superheroes_superpowers (superhero_id, superpowers_id) VALUES (1, 5);
INSERT INTO superheroes_superpowers (superhero_id, superpowers_id) VALUES (2, 0);
INSERT INTO superheroes_superpowers (superhero_id, superpowers_id) VALUES (2, 1);
INSERT INTO superheroes_superpowers (superhero_id, superpowers_id) VALUES (3, 1);
INSERT INTO superheroes_superpowers (superhero_id, superpowers_id) VALUES (3, 4);
INSERT INTO superheroes_superpowers (superhero_id, superpowers_id) VALUES (4, 1);
