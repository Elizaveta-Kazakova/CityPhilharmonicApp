package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.CompetitionDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.CompetitionRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Competition;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    private CompetitionDto toDto(Competition competition) {
        return new CompetitionDto(competition.getId(), competition.getName());
    }

    @Override
    public List<CompetitionDto> list() {
        return competitionRepository.findAll().stream().map(this::toDto).toList();
    }
}
