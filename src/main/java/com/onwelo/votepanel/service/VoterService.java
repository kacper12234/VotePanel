package com.onwelo.votepanel.service;

import com.onwelo.votepanel.dto.VoterDto;
import com.onwelo.votepanel.entity.Voter;
import com.onwelo.votepanel.exception.VoterNotFoundException;
import com.onwelo.votepanel.mapper.VoterMapper;
import com.onwelo.votepanel.repository.VoterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoterService {

    private final VoterMapper mapper;
    private final VoterRepository repository;

    public void addVoter(VoterDto voterDto) {
        repository.save(mapper.toEntity(voterDto));
    }

    public VoterDto findVoter(String personalNumber) {
        return repository.findByPersonalNumber(personalNumber)
                .map(mapper::toDto)
                .orElseThrow(() -> new VoterNotFoundException(personalNumber));
    }

    public void unlockVoter(long id) {
        updateVoter(id, false);
    }

    public void blockVoter(long id) {
        updateVoter(id, true);
    }

    private void updateVoter(long id, boolean blocked) {
        Voter voter = repository.findById(id).orElseThrow(() -> new VoterNotFoundException(id));
        if (voter.isBlocked() != blocked) {
            voter.setBlocked(blocked);
            repository.save(voter);
        }
    }

}
