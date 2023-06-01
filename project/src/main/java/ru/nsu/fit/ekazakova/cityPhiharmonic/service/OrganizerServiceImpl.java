package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.OrganizerDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.OrganizerDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.OrganizerNotFoudException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.organizer.OrganizerRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Organizer;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OrganizerServiceImpl implements OrganizerService {
    private final OrganizerRepository organizerRepository;

    private OrganizerDto toDto(Organizer organizer) {
        return new OrganizerDto(organizer.getName());
    }


    @Autowired
    public OrganizerServiceImpl(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Override
    @Transactional
    public void createOrganizer(OrganizerDto organizerDto) {
        Organizer organizer = new Organizer();
        organizer.setName(organizerDto.getName());
        organizerRepository.save(organizer);
    }

    @Override
    @Transactional
    public void updateOrganizer(OrganizerDto organizerDto, Long id) {
        Organizer organizer = organizerRepository.findById(id).orElseThrow(() ->
                new OrganizerNotFoudException("organizer with id = " + id + " not found"));
        organizer.setName(organizerDto.getName());

        organizerRepository.save(organizer);
    }

    @Override
    @Transactional
    public List<OrganizerDetailsDto> findOrganizersInPeriod(LocalDate startDate, LocalDate endDate) {
        return organizerRepository.findOrganizersInPeriod(startDate, endDate);
    }

    @Override
    public List<OrganizerDto> list() {
        return organizerRepository.findAll().stream().map(this::toDto).toList();
    }
}
