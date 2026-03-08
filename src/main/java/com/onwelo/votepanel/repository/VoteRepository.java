package com.onwelo.votepanel.repository;

import com.onwelo.votepanel.entity.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {
}
