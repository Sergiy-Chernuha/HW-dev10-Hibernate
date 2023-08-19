CREATE TABLE client
(
    ID   INTEGER auto_increment PRIMARY KEY,
    NAME VARCHAR(200) NOT NULL
);

ALTER TABLE client
    ADD CONSTRAINT check_min_length CHECK ( length(NAME) >= 3 );

CREATE TABLE planet
(
    ID   VARCHAR(255) PRIMARY KEY,
    NAME VARCHAR(500) NOT NULL
);

ALTER TABLE planet
    ADD CONSTRAINT check_alphabet_and_numbers CHECK (REGEXP_LIKE(ID, '\p{Alnum}'));

ALTER TABLE planet
    ADD CONSTRAINT check_upper_case_in_alphabet CHECK (NOT (REGEXP_LIKE(ID, '\p{Lower}')));

ALTER TABLE planet
    ADD CONSTRAINT check_punctuations CHECK (NOT (REGEXP_LIKE(ID, ' \b')));

CREATE TABLE ticket
(
    ID             INTEGER auto_increment PRIMARY KEY,
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL,
    client_id      INTEGER                  NOT NULL,
    from_planet_id VARCHAR(255)             NOT NULL,
    to_planet_id   VARCHAR(255)             NOT NULL
);

ALTER TABLE ticket
    ADD CONSTRAINT client_id_fk
        FOREIGN KEY (CLIENT_ID)
            REFERENCES client (ID);

ALTER TABLE ticket
    ADD CONSTRAINT from_planet_id_fk
        FOREIGN KEY (from_planet_id)
            REFERENCES planet (ID);

ALTER TABLE ticket
    ADD CONSTRAINT to_planet_id_fk
        FOREIGN KEY (to_planet_id)
            REFERENCES planet (ID);
