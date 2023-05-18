create table artist
(
    id   bigserial    not null,
    name varchar(255) not null,
    primary key (id)
);
create table artist_genre
(
    artist_id bigint not null,
    genre_id  bigint not null
);
create table artist_impresario
(
    artist_id     bigint not null,
    impresario_id bigint not null
);
create table bandstand
(
    bandstand_type varchar(255),
    id             bigint not null,
    primary key (id)
);
create table cinema
(
    screen_size integer,
    id          bigint not null,
    primary key (id)
);
create table competition
(
    id       bigserial    not null,
    name     varchar(255) not null,
    event_id bigint,
    primary key (id)
);
create table competition_artist
(
    artist_id       bigint not null,
    competition_id  bigint not null,
    place_in_rating integer,
    primary key (artist_id, competition_id)
);
create table concert_venue
(
    venue_type varchar(255),
    id         bigint not null,
    primary key (id)
);
create table cultural_building
(
    id           bigserial    not null,
    name         varchar(255) not null,
    num_of_seats integer,
    primary key (id)
);
create table event
(
    id                   bigserial    not null,
    date                 date,
    name                 varchar(255) not null,
    cultural_building_id bigint,
    organizer_id         bigint,
    primary key (id)
);
create table event_artist
(
    event_id  bigint not null,
    artist_id bigint not null
);
create table genre
(
    id   bigserial    not null,
    name varchar(255) not null,
    primary key (id)
);
create table impresario
(
    id   bigserial    not null,
    name varchar(255) not null,
    primary key (id)
);
create table organizer
(
    id   bigserial    not null,
    name varchar(255) not null,
    primary key (id)
);
create table palace_of_culture
(
    direction_of_activity varchar(255),
    id                    bigint not null,
    primary key (id)
);
create table theatre
(
    theatre_type varchar(255),
    id           bigint not null,
    primary key (id)
);

alter table if exists artist
    add constraint unique_artist unique (name);
alter table if exists competition
    add constraint unique_competition unique (name);
alter table if exists cultural_building
    add constraint unique_cultural_building unique (name);
alter table if exists event
    add constraint unique_event unique (name);
alter table if exists genre
    add constraint unique_genre unique (name);
alter table if exists impresario
    add constraint unique_impresario unique (name);
alter table if exists organizer
    add constraint unique_organizer unique (name);

alter table if exists artist_genre
    add constraint artist_genre_genre_fk foreign key (genre_id) references genre on delete cascade;
alter table if exists artist_genre
    add constraint artist_genre_artist_fk foreign key (artist_id) references artist on delete cascade;
alter table if exists artist_impresario
    add constraint artist_impresario_impresario_fk foreign key (impresario_id) references impresario on delete cascade;
alter table if exists artist_impresario
    add constraint artist_impresario_artist_fk foreign key (artist_id) references artist on delete cascade;
alter table if exists bandstand
    add constraint bandstand_cultural_building_fk foreign key (id) references cultural_building on delete cascade;;
alter table if exists cinema
    add constraint cinema_cultural_building_fk foreign key (id) references cultural_building on delete cascade;;
alter table if exists competition
    add constraint competition_event_fk foreign key (event_id) references event  on delete cascade;
alter table if exists competition_artist
    add constraint competition_artist_artist_fk foreign key (artist_id) references artist  on delete cascade;
alter table if exists competition_artist
    add constraint competition_artist_competition_fk foreign key (competition_id) references competition  on delete cascade;
alter table if exists concert_venue
    add constraint concert_venue_cultural_building_fk foreign key (id) references cultural_building on delete cascade;;
alter table if exists event
    add constraint event_cultural_building_fk foreign key (cultural_building_id) references cultural_building  on delete cascade;
alter table if exists event
    add constraint event_organizer_fk foreign key (organizer_id) references organizer  on delete cascade;
alter table if exists event_artist
    add constraint event_artist_artist_fk foreign key (artist_id) references artist on delete cascade;
alter table if exists event_artist
    add constraint event_artist_event_fk foreign key (event_id) references event on delete cascade;
alter table if exists palace_of_culture
    add constraint palace_of_culture_cultural_building_fk foreign key (id) references cultural_building on delete cascade;;
alter table if exists theatre
    add constraint theatre_cultural_building_fk foreign key (id) references cultural_building on delete cascade;;
