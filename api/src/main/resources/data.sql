INSERT INTO ARTISTS (
	ID,
    CREATED_AT,
    UPDATED_AT,
    FIRST_NAME,
    MIDDLE_NAME,
    LAST_NAME,
    BIRTHDAY,
    EMAIL,
    CATEGORY,
    NOTES
) VALUES (
	1,
    CURRENT_DATE(),
    CURRENT_DATE(),
    'Max',
    NULL,
    'Payne',
    {ts '1990-01-01 00:00:00.000'},
    'max.payne@test.org',
    'ACTOR',
    'Max Payne killing it!'
);

INSERT INTO ARTISTS (
	ID,
    CREATED_AT,
    UPDATED_AT,
    FIRST_NAME,
    MIDDLE_NAME,
    LAST_NAME,
    BIRTHDAY,
    EMAIL,
    CATEGORY,
    NOTES
) VALUES (
	2,
    CURRENT_DATE(),
    CURRENT_DATE(),
    'Denzel',
    NULL,
    'Washington',
    {ts '1990-01-01 00:00:00.000'},
    'denzel.washington@test.org',
    'ACTOR',
    NULL
);