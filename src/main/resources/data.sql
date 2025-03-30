INSERT INTO venues (venue_name, location)
VALUES ('Park View', 'Boeung Kok'),
       ('Koh Pich Park', 'Koh Pich'),
       ('Koh Norea BayView', 'Koh Norea'),
       ('Sabay Nas Park', 'Toul Kork');

INSERT INTO attendees (attendee_name, email)
VALUES ('Davith', 'davith@gmail.com'),
       ('Roth Sep', 'rothsep@gmail.com'),
       ('RolexNumberSun', 'laksun@gmail.com'),
       ('ManySi', 'zuukie333@gmail.com');

INSERT INTO events (event_name, event_date, venue_id)
VALUES ('Khmer New Year Festival', '2025-04-04', 1),
       ('Concert Night', '2025-05-15', 2),
       ('Tech Conference', '2025-06-20', 3),
       ('Art Exhibition', '2025-07-10', 4);

INSERT INTO events_attendees
VALUES (DEFAULT, 1, 3),
       (DEFAULT, 2, 1),
       (DEFAULT, 4, 2),
       (DEFAULT, 3, 4);
