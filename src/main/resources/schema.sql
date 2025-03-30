CREATE TABLE IF NOT EXISTS venues
(
    venue_id
             SERIAL8
        PRIMARY KEY,
    venue_name
             VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL
);

-- To restart id
ALTER SEQUENCE venues_venue_id_seq RESTART WITH 5;

CREATE TABLE IF NOT EXISTS attendees
(
    attendee_id   SERIAL8 PRIMARY KEY,
    attendee_name VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE
);

DROP TABLE events;

CREATE TABLE IF NOT EXISTS events
(
    event_id   SERIAL8 PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    event_date TIMESTAMP    NOT NULL,
    venue_id   INT8         NOT NULL,
    CONSTRAINT fk_venue
        FOREIGN KEY (venue_id)
            REFERENCES venues (venue_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS events_attendees
(
    id          SERIAL8 PRIMARY KEY,
    event_id    INT8 NOT NULL,
    attendee_id INT8 NOT NULL,
    CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES events (event_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_attendee FOREIGN KEY (attendee_id) REFERENCES attendees (attendee_id) ON UPDATE CASCADE ON DELETE CASCADE
);

