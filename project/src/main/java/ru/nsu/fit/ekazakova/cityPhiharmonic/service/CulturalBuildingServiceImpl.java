package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingInPeriodDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.CulturalBuildingNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.culturalBuilding.CulturalBuildingRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.culturalBuilding.CulturalBuilding;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.culturalBuilding.CulturalBuildingInPeriod;

import java.time.LocalDate;
import java.util.List;

@Service
public class CulturalBuildingServiceImpl implements CulturalBuildingService {
    private final CulturalBuildingRepository culturalBuildingRepository;

    private CulturalBuildingDto toDto(CulturalBuilding culturalBuilding) {
        return new CulturalBuildingDto(culturalBuilding.getName(), culturalBuilding.getNumOfSeats());
    }

    private CulturalBuildingInPeriodDto toDtoInPeriod(CulturalBuildingInPeriod culturalBuilding) {
        return new CulturalBuildingInPeriodDto(new CulturalBuildingDto(culturalBuilding.getName(),
                culturalBuilding.getNumOfSeats()), culturalBuilding.getDate());
    }

    @Autowired
    public CulturalBuildingServiceImpl(CulturalBuildingRepository culturalBuildingRepository) {
        this.culturalBuildingRepository = culturalBuildingRepository;
    }

    @Override
    @Transactional
    public void createCulturalBuilding(CulturalBuildingDto culturalBuildingDto) {
        final CulturalBuilding culturalBuilding = new CulturalBuilding();
        culturalBuilding.setName(culturalBuildingDto.getName());
        culturalBuilding.setNumOfSeats(culturalBuildingDto.getNumOfSeats());
        culturalBuildingRepository.save(culturalBuilding);

    }

    @Override
    @Transactional
    public void updateCulturalBuilding(CulturalBuildingDto culturalBuildingDto, Long id) {
        CulturalBuilding culturalBuilding = culturalBuildingRepository.findById(id).orElseThrow(() ->
                new CulturalBuildingNotFoundException("building with id = " + id + " not found"));
        culturalBuilding.setName(culturalBuildingDto.getName());
        culturalBuilding.setNumOfSeats(culturalBuildingDto.getNumOfSeats());
        culturalBuildingRepository.save(culturalBuilding);
    }

    @Override
    @Transactional
    public CulturalBuildingDto findCulturalBuildingById(Long id) {
        return culturalBuildingRepository.findById(id).map(this::toDto).orElseThrow(() ->
                new CulturalBuildingNotFoundException("cultural building with id = " + id + " not found"));
    }

    @Override
    public List<CulturalBuildingDto> list() {
        return culturalBuildingRepository.findAll().stream().map(this::toDto).toList();
    }

//
//    @Override
//    @Transactional
//    public List<CulturalBuildingDto> findCulturalBuildingByType(String type) {
//        return culturalBuildingRepository.findCulturalBuildingByType(type).stream().map(this::toDto).toList();
//    }

    @Override
    @Transactional
    public List<CulturalBuildingDto> findCulturalBuildingByNumOfSeats(Integer numOfSeats) {
        return culturalBuildingRepository.findCulturalBuildingByNumOfSeats(numOfSeats)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional
    public List<CulturalBuildingInPeriodDto> findCulturalBuildingsInPeriod(LocalDate startDate, LocalDate endDate) {
        return culturalBuildingRepository.findCulturalBuildingsInPeriod(startDate, endDate)
                .stream()
                .map(this::toDtoInPeriod)
                .toList();
    }


}
