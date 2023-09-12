create function check_event_date() RETURNS trigger AS $$
    declare
        x integer = 0;
    begin
        select count(*)
        into x
        from event
        where (new.date = event.date and new.cultural_building_id = event.cultural_building_id);
        if (x > 0) then
            RAISE EXCEPTION 'The event at this time and place already exists.'
                USING HINT = 'Please choose a different time or cultural building';
        end if;
        RETURN new;
    end;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS is_such_event_exist
    ON event;

create trigger is_such_event_exist
    before insert on event
    for each row
    execute function check_event_date();
end;

-------------------------------------------------------------------
-- артисты не могут быть одновременно на двух мероприятиях в одну и ту же дату
create function check_artist_events() RETURNS trigger AS $$
declare
    x integer = 0;
begin
    select count(*)
    into x
    from event_artist
    left join event on event.id = event_artist.event_id left join event e on e.id = new.event_id
    where event_artist.artist_id = new.artist_id and e.id = new.event_id and event.date = e.date;
    if (x > 0) then
        RAISE EXCEPTION 'This artist is already busy on this date.'
            USING HINT = 'Please choose a different time or artist';
    end if;
    RETURN new;
end;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS is_event_with_the_same_artists_exist
    ON event_artist;

create trigger is_event_with_the_same_artists_exist
    before insert on event_artist
    for each row
    execute function check_artist_events();
end;

-------------------------------------------------------------------

create function check_organizer_events() RETURNS trigger AS $$
    declare
        x integer = 0;
    begin
        select count(*)
        into x
        from event
        where (new.date = event.date and new.organizer_id = event.organizer_id);
        if (x > 0) then
            RAISE EXCEPTION 'The organizer is already organizing another event on this day.'
                USING HINT = 'Please choose a different time or organizer';
        end if;
        RETURN new;
    end;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS is_organizer_busy
    ON event;

create trigger is_organizer_busy
    before insert on event
    for each row
    execute function check_organizer_events();
end;

-------------------------------------------------------------------
-- занять одно и то же место на одном конкурсе два разных артиста не могут
create function check_winner_places() RETURNS trigger AS $$
    declare
        x integer = 0;
    begin
        select count(*)
        into x
        from competition_artist
        where (new.competition_id = competition_artist.competition_id and new.place_in_rating = competition_artist.place_in_rating);
        if (x > 0) then
            RAISE EXCEPTION 'This place is already occupied by another artist.';
        end if;
        RETURN new;
    end;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS is_place_occupied
    ON competition_artist;

create trigger is_place_occupied
    before insert on competition_artist
    for each row
    execute function check_winner_places();
end;

-------------------------------------------------------------------
-- конкурс проходит на мероприятии, на котором нет этого артиста
create function check_competition_event() RETURNS trigger AS $$
declare
    x integer = 0;
begin
    select count(*)
    into x
    from competition c left join event e on e.id = c.event_id
                       left join event_artist ea on ea.event_id = e.id
    where ea.artist_id = new.artist_id and c.id = new.competition_id;
    if (x = 0) then
        RAISE EXCEPTION 'The competition takes place at an event where this artist is not present';
    end if;
    RETURN new;
end;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS is_competition_from_another_event
    ON competition_artist;

create trigger is_competition_from_another_event
    before insert on competition_artist
    for each row
execute function check_competition_event();
end;