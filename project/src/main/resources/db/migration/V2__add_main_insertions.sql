INSERT INTO artist (name) VALUES ('Blues Magoos');
INSERT INTO artist (name) VALUES ('Aswat Almadina');
INSERT INTO artist (name) VALUES ('Elias Karam');
INSERT INTO artist (name) VALUES ('Blind Blake');
INSERT INTO artist (name) VALUES ('Gorillaz');
INSERT INTO artist (name) VALUES ('Claire Kuo');
INSERT INTO artist (name) VALUES ('Robert Galea');
INSERT INTO artist (name) VALUES ('Sandi Patty');

insert into genre (name) values ('Renaissance');
insert into genre (name) values ('Baroque');
insert into genre (name) values ('Galant');
insert into genre (name) values ('Classical');
insert into genre (name) values ('Romantic');
insert into genre (name) values ('Blues');
insert into genre (name) values ('Rock');
insert into genre (name) values ('Pop');
insert into genre (name) values ('C-pop');
insert into genre (name) values ('Christian vocal');
insert into genre (name) values ('Hip hop');

insert into impresario (name) values ('Quincy Jones');
insert into impresario (name) values ('Lawrence Welk');
insert into impresario (name) values ('Henry Cowell');
insert into impresario (name) values ('Brian Epstein');
insert into impresario (name) values ('Ned Sherrin');
insert into impresario (name) values ('Malcolm McLaren');
insert into impresario (name) values ('Bill Graham');
insert into impresario (name) values ('Andrew Lloyd Webber');
insert into impresario (name) values ('Jacques Offenbach');

insert into artist_genre values (1, 4);
insert into artist_genre values (1, 6);
insert into artist_genre values (2, 1);
insert into artist_genre values (3, 1);
insert into artist_genre values (3, 4);
insert into artist_genre values (4, 3);
insert into artist_genre values (5, 5);
insert into artist_genre values (5, 6);
insert into artist_genre values (5, 8);
insert into artist_genre values (6, 9);
insert into artist_genre values (7, 7);
insert into artist_genre values (7, 10);
insert into artist_genre values (7, 11);
insert into artist_genre values (8, 5);

insert into artist_impresario values (1, 3);
insert into artist_impresario values (1, 4);
insert into artist_impresario values (1, 5);
insert into artist_impresario values (2, 1);
insert into artist_impresario values (2, 6);
insert into artist_impresario values (3, 2);
insert into artist_impresario values (4, 1);
insert into artist_impresario values (4, 6);
insert into artist_impresario values (4, 7);
insert into artist_impresario values (5, 9);
insert into artist_impresario values (6, 3);
insert into artist_impresario values (7, 8);
insert into artist_impresario values (7, 6);
insert into artist_impresario values (8, 5);


insert into cultural_building (name, num_of_seats) VALUES ('Palace and Gardens of Schönbrunn', 168); -- palace of culture
insert into cultural_building (name, num_of_seats) VALUES ('Palace of culture of railway workers', 500); -- palace of culture

insert into cultural_building (name, num_of_seats) VALUES ('Titanic Memorial Bandstand', 300); -- bandstand
insert into cultural_building (name, num_of_seats) VALUES ('Bothwell Road Park', 1000); -- bandstand
insert into cultural_building (name, num_of_seats) VALUES ('Eastbourne Bandstand', 421); -- bandstand

insert into cultural_building (name, num_of_seats) VALUES ('Apollo Theatre', 342); -- theatre
insert into cultural_building (name, num_of_seats) VALUES ('Buckley''s Minstrel Hall', 273); -- theatre
insert into cultural_building (name, num_of_seats) VALUES ('Cobb Theatre', 456); -- theatre
insert into cultural_building (name, num_of_seats)
VALUES ('Roman Theatre and its Surroundings and the "Triumphal Arch" of Orange', 200); --theatre

insert into cultural_building (name, num_of_seats) VALUES ('Imatra', 356); -- cinema
insert into cultural_building (name, num_of_seats) VALUES ('Star Cineplex', 432); --cinema
insert into cultural_building (name, num_of_seats) VALUES ('Shyamoli Cinema', 291); -- cinema

insert into cultural_building (name, num_of_seats) VALUES ('Sun Dome Fukui', 967); -- concert venue
insert into cultural_building (name, num_of_seats) VALUES ('Nippon Gaishi Hall', 768); -- concert venue
insert into cultural_building (name, num_of_seats) VALUES ('Barys Arena', 935); -- concert venue


insert into palace_of_culture (id, direction_of_activity) VALUES (1, 'increasing the social activity of citizens');
insert into palace_of_culture (id, direction_of_activity) VALUES (2, 'creative self-realization of the individual');

insert into bandstand (id, bandstand_type) VALUES (3, 'collapsible');
insert into bandstand (id, bandstand_type) VALUES (4, 'portal');
insert into bandstand (id, bandstand_type)  VALUES (5, 'open to the variety');

insert into theatre (id, theatre_type) VALUES (6, 'drama');
insert into theatre (id, theatre_type) VALUES (7, 'ballet');
insert into theatre (id, theatre_type) VALUES (8, 'opera');
insert into theatre (id, theatre_type) VALUES (9, 'puppet');

insert into cinema (id, screen_size) VALUES (10, 200);
insert into cinema (id, screen_size) VALUES (11, 300);
insert into cinema (id, screen_size) VALUES (12, 400);

insert into concert_venue (id, venue_type) VALUES (13, 'festival venue');
insert into concert_venue (id, venue_type) VALUES (14, 'stadium');
insert into concert_venue (id, venue_type) VALUES (15, 'arena');


insert into organizer (name) VALUES ('Chris Sylla');
insert into organizer (name) VALUES ('Kitty Norris');
insert into organizer (name) VALUES ('Amanda Gerace');
insert into organizer (name) VALUES ('Riccardo Tesi');
insert into organizer (name) VALUES ('Jim McLaughlin');


insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('24.11.10', 'DD.MM.YY'), 'Coronation of Królowa Mleka', 1, 1);
insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('13.07.15', 'DD.MM.YY'), 'Wikimedia Polska at European Year of Volunteering', 2, 4);

insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('04.09.04', 'DD.MM.YY'), 'Open mic by Kasa Kai Mumbai', 3, 2);
insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('06.02.18', 'DD.MM.YY'), 'HIKERWOLF', 4, 5);
insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('17.10.09', 'DD.MM.YY'), 'The Luton Gospel All Stars', 5, 4);

insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('16.12.01', 'DD.MM.YY'), 'misunderstanding performance', 6, 1);
insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('26.08.11', 'DD.MM.YY'), 'The Hunchback Horse', 7, 3);
insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('27.06.14', 'DD.MM.YY'), 'The Threepenny Opera', 8, 4);

insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('14.02.15', 'DD.MM.YY'), 'Megalodon', 10, 2);
insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('09.10.20', 'DD.MM.YY'), 'Alien. Invasion', 11, 3);
insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('01.02.19', 'DD.MM.YY'), 'Benin', 12, 5);

insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('11.04.06', 'DD.MM.YY'), 'Holi Festival', 13, 1);
insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('10.02.04', 'DD.MM.YY'), 'London Diamond League Athletics', 14, 3);
insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('12.05.07', 'DD.MM.YY'), 'Quick Draft March of the Machine', 15, 2);
insert into event (date, name, cultural_building_id, organizer_id)
VALUES (to_date('12.05.07', 'DD.MM.YY'), 'Microwave International New Media Arts Festival', 1, 5);


insert into event_artist (event_id, artist_id) VALUES (1, 1);
insert into event_artist (event_id, artist_id) VALUES (1, 5);
insert into event_artist (event_id, artist_id) VALUES (1, 6);
insert into event_artist (event_id, artist_id) VALUES (1, 7);
insert into event_artist (event_id, artist_id) VALUES (1, 8);

insert into event_artist (event_id, artist_id) VALUES (2, 2);
insert into event_artist (event_id, artist_id) VALUES (2, 6);
insert into event_artist (event_id, artist_id) VALUES (2, 7);
insert into event_artist (event_id, artist_id) VALUES (2, 8);

insert into event_artist (event_id, artist_id) VALUES (3, 1);
insert into event_artist (event_id, artist_id) VALUES (3, 2);
insert into event_artist (event_id, artist_id) VALUES (3, 5);
insert into event_artist (event_id, artist_id) VALUES (3, 4);

insert into event_artist (event_id, artist_id) VALUES (4, 1);
insert into event_artist (event_id, artist_id) VALUES (4, 2);
insert into event_artist (event_id, artist_id) VALUES (4, 3);
insert into event_artist (event_id, artist_id) VALUES (4, 4);
insert into event_artist (event_id, artist_id) VALUES (4, 6);
insert into event_artist (event_id, artist_id) VALUES (4, 7);
insert into event_artist (event_id, artist_id) VALUES (4, 8);

insert into event_artist (event_id, artist_id) VALUES (5, 4);
insert into event_artist (event_id, artist_id) VALUES (5, 6);
insert into event_artist (event_id, artist_id) VALUES (5, 7);
insert into event_artist (event_id, artist_id) VALUES (5, 8);

insert into event_artist (event_id, artist_id) VALUES (6, 1);
insert into event_artist (event_id, artist_id) VALUES (6, 5);
insert into event_artist (event_id, artist_id) VALUES (6, 8);
insert into event_artist (event_id, artist_id) VALUES (6, 2);

insert into event_artist (event_id, artist_id) VALUES (7, 6);
insert into event_artist (event_id, artist_id) VALUES (7, 7);
insert into event_artist (event_id, artist_id) VALUES (7, 3);
insert into event_artist (event_id, artist_id) VALUES (7, 1);

insert into event_artist (event_id, artist_id) VALUES (8, 1);
insert into event_artist (event_id, artist_id) VALUES (8, 2);
insert into event_artist (event_id, artist_id) VALUES (8, 7);
insert into event_artist (event_id, artist_id) VALUES (8, 4);
insert into event_artist (event_id, artist_id) VALUES (8, 6);
insert into event_artist (event_id, artist_id) VALUES (8, 8);

insert into event_artist (event_id, artist_id) VALUES (9, 1);
insert into event_artist (event_id, artist_id) VALUES (9, 2);
insert into event_artist (event_id, artist_id) VALUES (9, 3);
insert into event_artist (event_id, artist_id) VALUES (9, 4);

insert into event_artist (event_id, artist_id) VALUES (10, 1);
insert into event_artist (event_id, artist_id) VALUES (10, 6);
insert into event_artist (event_id, artist_id) VALUES (10, 7);
insert into event_artist (event_id, artist_id) VALUES (10, 5);

insert into event_artist (event_id, artist_id) VALUES (11, 8);
insert into event_artist (event_id, artist_id) VALUES (11, 6);
insert into event_artist (event_id, artist_id) VALUES (11, 7);
insert into event_artist (event_id, artist_id) VALUES (11, 5);

insert into event_artist (event_id, artist_id) VALUES (12, 2);
insert into event_artist (event_id, artist_id) VALUES (12, 6);
insert into event_artist (event_id, artist_id) VALUES (12, 7);
insert into event_artist (event_id, artist_id) VALUES (12, 5);

insert into event_artist (event_id, artist_id) VALUES (13, 2);
insert into event_artist (event_id, artist_id) VALUES (13, 1);
insert into event_artist (event_id, artist_id) VALUES (13, 6);
insert into event_artist (event_id, artist_id) VALUES (13, 3);

insert into event_artist (event_id, artist_id) VALUES (14, 8);
insert into event_artist (event_id, artist_id) VALUES (14, 1);
insert into event_artist (event_id, artist_id) VALUES (14, 7);
insert into event_artist (event_id, artist_id) VALUES (14, 5);


insert into competition (name, event_id) VALUES ('The Gielgud Award', 1);
insert into competition (name, event_id) VALUES ('Doris Duke Performing Artist Award', 1);
insert into competition (name, event_id) VALUES ('Empire Award for Best Newcomer', 2);
insert into competition (name, event_id) VALUES ('Golden Lotus Award', 3);
insert into competition (name, event_id) VALUES ('Nandi Award', 4);
insert into competition (name, event_id) VALUES ('Black Reel Award for Outstanding Ensemble', 4);
insert into competition (name, event_id) VALUES ('Critics'' Choice Movie Award for Best Acting Ensemble', 5);
insert into competition (name, event_id) VALUES ('Independent Spirit Robert Altman Award', 6);
insert into competition (name, event_id) VALUES ('Vijay Award for Best Debut Actress', 7);
insert into competition (name, event_id) VALUES ('Huabiao Award for Outstanding New Actress', 8);
insert into competition (name, event_id) VALUES ('Dallas–Fort Worth Film Critics Association Award', 8);
insert into competition (name, event_id) VALUES ('Houston Film Critics Society Award', 9);
insert into competition (name, event_id) VALUES ('Saturn Award', 10);
insert into competition (name, event_id) VALUES ('Young Artist Award for Best Leading Young Actress', 11);
insert into competition (name, event_id) VALUES ('Hundred Flowers Award', 12);
insert into competition (name, event_id) VALUES ('TVB Star Awards', 13);
insert into competition (name, event_id) VALUES ('Vijay Award', 14);

-- event_id = 1
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (8, 1, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (7, 1, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (6, 1, 3);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (5, 2, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (6, 2, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (7, 2, 3);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (1, 2, 4);

-- event_id = 2
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (2, 3, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (6, 3, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (7, 3, 3);

-- event_id = 3
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (1, 4, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (2, 4, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (5, 4, 3);

-- event_id = 4
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (1, 5, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (2, 5, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (3, 5, 3);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (4, 5, 4);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (6, 6, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (3, 6, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (7, 6, 3);

-- event_id = 5
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (4, 7, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (6, 7, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (8, 7, 3);

-- event_id = 6
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (8, 8, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (1, 8, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (5, 8, 3);

-- event_id = 7
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (6, 9, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (3, 9, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (7, 9, 3);

-- event_id = 8
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (2, 10, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (7, 10, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (4, 10, 3);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (1,11, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (8, 11, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (6, 11, 3);

-- event_id = 9
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (1, 12, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (2, 12, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (3, 12, 3);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (4, 12, 4);

-- event_id = 10
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (5, 13, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (6, 13, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (7, 13, 3);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (1, 13, 4);

-- event_id = 11
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (8, 14, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (7, 14, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (6, 14, 3);

-- event_id = 12
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (6, 15, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (2, 15, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (7, 15, 3);

-- event_id = 13
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (1, 16, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (6, 16, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (2, 16, 3);

-- event_id = 14
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (8, 17, 1);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (1, 17, 2);
insert into competition_artist (artist_id, competition_id, place_in_rating) VALUES (5, 17, 3);

