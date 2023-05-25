insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('24.11.10', 'DD.MM.YY'), 'Clockenflap', 1, 3);

insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('13.07.15', 'DD.MM.YY'), 'Alternative Party', 5, 4);

insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (5, 5, 1);

insert into event_artist (event_id, artist_id) VALUES (17, 1);

insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (8, 16, 4);

