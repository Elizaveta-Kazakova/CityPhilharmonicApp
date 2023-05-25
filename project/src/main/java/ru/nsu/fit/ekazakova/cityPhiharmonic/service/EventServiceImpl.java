package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.EventDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.EventDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.EventNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.CompetitionRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.artist.ArtistRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.culturalBuilding.CulturalBuildingRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Competition;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Genre;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Impresario;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.event.EventRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.organizer.OrganizerRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Event;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final CulturalBuildingRepository culturalBuildingRepository;
    private final ArtistRepository artistRepository;
    private final CompetitionRepository competitionRepository;

    private EventDto toDto(Event event) {
        return new EventDto(event.getId(), event.getName(), event.getDate(), event.getOrganizer().getName(),
                event.getCulturalBuilding().getName(), event.getArtists().stream().map(Artist::getName).toList(),
                event.getCompetitions().stream().map(Competition::getName).toList());
    }

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, OrganizerRepository organizerRepository,
                            CulturalBuildingRepository culturalBuildingRepository, ArtistRepository artistRepository,
                            CompetitionRepository competitionRepository) {
        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
        this.culturalBuildingRepository = culturalBuildingRepository;
        this.artistRepository = artistRepository;
        this.competitionRepository = competitionRepository;
    }

    @Override
    @Transactional
    public void createEvent(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDate(eventDto.getDate());
        event.setOrganizer(organizerRepository.findOrganizerByName(eventDto.getOrganizer()));
        event.setCulturalBuilding(culturalBuildingRepository.findCulturalBuildingByName(eventDto.getCulturalBuilding()));
        event.setArtists(eventDto.getArtists().stream().map(artistRepository::findArtistByName).toList());
        event.setCompetitions(eventDto.getCompetitions().stream().map(competitionRepository::findCompetitionByName).toList());

        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void updateEvent(EventDto eventDto, Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("event with id = " +
                id + " not found"));
        event.setName(eventDto.getName());
        event.setDate(eventDto.getDate());
        event.setOrganizer(organizerRepository.findOrganizerByName(eventDto.getOrganizer()));
        event.setCulturalBuilding(culturalBuildingRepository.findCulturalBuildingByName(eventDto.getCulturalBuilding()));

        eventRepository.save(event);
    }

    @Override
    @Transactional
    public EventDto findEventById(Long id) {
        return eventRepository.findById(id).map(this::toDto).orElseThrow(() -> new EventNotFoundException("event with id = " +
                id + " not found"));
    }

    @Override
    @Transactional
    public List<EventDto> list() {
        return eventRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public List<EventDto> findEventInPeriod(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findEventInPeriod(startDate, endDate).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public List<EventDto> findEventInPeriodByOrganizer(LocalDate startDate, LocalDate endDate, String organizer) {
        return eventRepository.findEventInPeriodByOrganizer(startDate, endDate, organizer)
                .stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public List<EventDetailsDto> findEventByCulturalBuilding(String culturalBuilding) {
        return eventRepository.findEventByCulturalBuilding(culturalBuilding);
    }


}
