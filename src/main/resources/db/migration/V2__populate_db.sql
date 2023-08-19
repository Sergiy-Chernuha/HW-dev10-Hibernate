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
       ('2005-12-31 23:59:59Z', 2, 'MARS', 'QWERTY3'),
       ('2006-05-31 23:59:59+02:00', 3, 'MARS', 'ZOOM'),
       ('2006-12-31 23:59:59+01', 4, 'MARS', 'ZOOM'),
       ('2008-08-31 23:59:59+02:00', 5, 'MARS', 'ROCKET'),
       ('2008-12-31 23:59:59+05:00', 6, 'SUN23', 'ROCKET'),
       ('2009-09-30 23:59:59+05:00', 7, 'SUN23', 'QWERTY3'),
       ('2009-12-31 23:59:59-05', 8, 'SUN23', 'MARS'),
       ('2010-12-15 23:59:59Z', 9, 'ZOOM', 'QWERTY3'),
       ('2012-12-31 23:59:59+08:00', 10, 'ZOOM', 'SUN23');
