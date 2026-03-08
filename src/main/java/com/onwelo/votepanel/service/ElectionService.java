package com.onwelo.votepanel.service;

import com.onwelo.votepanel.dto.ChoiceResultDto;
import com.onwelo.votepanel.dto.ElectionDto;
import com.onwelo.votepanel.dto.ElectionResultDto;
import com.onwelo.votepanel.entity.Election;
import com.onwelo.votepanel.exception.ElectionNotFoundException;
import com.onwelo.votepanel.mapper.ElectionMapper;
import com.onwelo.votepanel.repository.ChoiceRepository;
import com.onwelo.votepanel.repository.ElectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectionService {

    private final ElectionMapper mapper;
    private final ElectionRepository electionRepository;
    private final ChoiceRepository choiceRepository;

    public void addElection(ElectionDto dto) {
        electionRepository.save(mapper.toEntity(dto));
    }

    public ElectionResultDto getElectionResult(long id) {
        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new ElectionNotFoundException(id));
        List<ChoiceResultDto> choices = choiceRepository.getElectionResults(id);
        return new ElectionResultDto(election.getName(), choices);
    }
}
