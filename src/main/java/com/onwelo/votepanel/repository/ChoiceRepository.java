package com.onwelo.votepanel.repository;

import com.onwelo.votepanel.dto.ChoiceResultDto;
import com.onwelo.votepanel.entity.Choice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChoiceRepository extends CrudRepository<Choice, Long> {

    @Query("""
            select
                c.name as choiceName,
                count(v) as votes
            from Choice c
            left join c.votes v
            where c.electionId = :electionId
            group by c.name
            """)
    List<ChoiceResultDto> getElectionResults(long electionId);
}
