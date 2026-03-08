package com.onwelo.votepanel.repository;

import com.onwelo.votepanel.entity.Voter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VoterRepository extends CrudRepository<Voter, Long> {

    Optional<Voter> findByPersonalNumber(String personalNumber);
}
