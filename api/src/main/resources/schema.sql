
CREATE SEQUENCE ARTISTS_SEQUENCE
 START WITH     100
 INCREMENT BY   10
 NOCACHE
 NOCYCLE;

CREATE TABLE ARTISTS (
   	ID INTEGER NOT NULL PRIMARY KEY,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    UPDATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    FIRST_NAME VARCHAR(100) NOT NULL,
    MIDDLE_NAME VARCHAR(100),
    LAST_NAME VARCHAR(100) NOT NULL,
    BIRTHDAY TIMESTAMP NOT NULL,
    EMAIL VARCHAR(100) NOT NULL UNIQUE,
    CATEGORY ENUM('ACTOR', 'PAINTER', 'SCULPTOR') NOT NULL,
    NOTES VARCHAR(255),
    BIRTHDAY_MONTH VARCHAR(20) AS LOWER(MONTHNAME(BIRTHDAY)),
    FULL_NAME VARCHAR(256) AS LOWER(CONCAT_WS(' ', FIRST_NAME, LAST_NAME))
);

CREATE INDEX ARTIST_CATEGORY_IDX ON ARTISTS(CATEGORY);

CREATE INDEX ARTIST_BIRTHDAY_MONTH_IDX ON ARTISTS(BIRTHDAY_MONTH);

CREATE INDEX ARTIST_FULL_NAME_IDX ON ARTISTS(FULL_NAME);
