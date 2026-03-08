package com.onwelo.votepanel.service;

import com.onwelo.votepanel.dto.VoteDto;
import com.onwelo.votepanel.entity.Choice;
import com.onwelo.votepanel.entity.Voter;
import com.onwelo.votepanel.exception.*;
import com.onwelo.votepanel.mapper.VoteMapper;
import com.onwelo.votepanel.repository.ChoiceRepository;
import com.onwelo.votepanel.repository.VoteRepository;
import com.onwelo.votepanel.repository.VoterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteMapper mapper;
    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final ChoiceRepository choiceRepository;

    public void vote(VoteDto dto) {

        Voter voter = voterRepository.findById(dto.voterId())
                .orElseThrow(() -> new VoterNotFoundException(dto.voterId()));

        if (voter.isBlocked()) {
            throw new VoterBlockedException(dto.voterId());
        }

        Choice choice = choiceRepository.findById(dto.choiceId())
                .orElseThrow(() -> new ChoiceNotFoundException(dto.choiceId()));

        if (!choice.getElectionId().equals(dto.electionId())) {
            throw new ChoiceNotInElectionException(dto.choiceId(), dto.electionId());
        }

        try {
            voteRepository.save(mapper.toEntity(dto));
        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyVotedException(dto.voterId());
        }
    }

}
