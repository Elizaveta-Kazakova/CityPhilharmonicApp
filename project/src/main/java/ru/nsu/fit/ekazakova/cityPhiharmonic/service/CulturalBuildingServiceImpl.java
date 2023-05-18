package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.CulturalBuildingNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.culturalBuilding.CulturalBuildingDetails;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.culturalBuilding.CulturalBuildingRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.cultural_building.CulturalBuilding;

import java.time.LocalDate;
import java.util.List;

@Service
public class CulturalBuildingServiceImpl implements CulturalBuildingService {
    private final CulturalBuildingRepository culturalBuildingRepository;

    private CulturalBuildingDto toDto(CulturalBuilding culturalBuilding) {
        return new CulturalBuildingDto(culturalBuilding.getName(), culturalBuilding.getNumOfSeats());
    }

    private CulturalBuildingDetailsDto toDetailsDto(CulturalBuildingDetails culturalBuildingDetails) {
        return new CulturalBuildingDetailsDto(toDto(culturalBuildingDetails.getCulturalBuilding()),
                culturalBuildingDetails.getDatesOfPlannedEvents());
    }

    @Autowired
    public CulturalBuildingServiceImpl(CulturalBuildingRepository culturalBuildingRepository) {
        this.culturalBuildingRepository = culturalBuildingRepository;
    }

    //TODO: добавить библиотечный mapper для дто (MapStruct ? ModelMapper ? )
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
    @Transactional
    public List<CulturalBuildingDto> findCulturalBuildingByType(String type) {
        return culturalBuildingRepository.findCulturalBuildingByType(type).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public List<CulturalBuildingDto> findCulturalBuildingByNumOfSeats(Integer numOfSeats) {
        return culturalBuildingRepository.findCulturalBuildingByNumOfSeats(numOfSeats).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public List<CulturalBuildingDetailsDto> findCulturalBuildingsInPeriod(LocalDate startDate, LocalDate endDate) {
        return culturalBuildingRepository.findCulturalBuildingsInPeriod(startDate, endDate)
                .stream().map(this::toDetailsDto).toList();
    }


}
