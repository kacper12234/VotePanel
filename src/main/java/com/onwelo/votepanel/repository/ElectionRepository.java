package com.onwelo.votepanel.repository;

import com.onwelo.votepanel.entity.Election;
import org.springframework.data.repository.CrudRepository;

public interface ElectionRepository extends CrudRepository<Election, Long> {
}
