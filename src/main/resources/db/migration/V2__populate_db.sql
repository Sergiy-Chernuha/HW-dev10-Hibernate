INSERT INTO client (name)
VALUES ('Michale'),
       ('Sergio'),
       ('Nataly'),
       ('Christa'),
       ('Den'),
       ('Gen'),
       ('Sem'),
       ('Joy'),
       ('Lee'),
       ('Sophie');

INSERT INTO planet (ID, name)
VALUES ('MARS', 'Mars'),
       ('SUN23', 'Sun number23'),
       ('QWERTY3', 'Qwerty and 3'),
       ('ZOOM', 'Zoom'),
       ('ROCKET', 'Rocket');

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id)
VALUES ('2005-12-31 23:59:59+05', 1, 'MARS', 'SUN23'),
       ('2005-12-31 23:59:59Z', 3, 'MARS', 'QWERTY3'),
       ('2006-05-31 23:59:59+02:00', 3, 'MARS', 'QWERTY3'),
       ('2006-12-31 23:59:59+01', 3, 'MARS', 'QWERTY3'),
       ('2008-08-31 23:59:59+02:00', 3, 'MARS', 'QWERTY3'),
       ('2008-12-31 23:59:59+05:00', 3, 'MARS', 'QWERTY3'),
       ('2009-09-30 23:59:59+05:00', 4, 'MARS', 'QWERTY3'),
       ('2009-12-31 23:59:59-05', 3, 'MARS', 'QWERTY3'),
       ('2010-12-15 23:59:59Z', 3, 'MARS', 'QWERTY3'),
       ('2012-12-31 23:59:59+08:00', 3, 'MARS', 'QWERTY3');
