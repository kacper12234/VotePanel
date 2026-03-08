package com.onwelo.votepanel.mapper;

import com.onwelo.votepanel.dto.ElectionDto;
import com.onwelo.votepanel.entity.Election;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ElectionMapper {
    Election toEntity(ElectionDto electionDto);

    @AfterMapping
    default void linkChoices(@MappingTarget Election election) {
        if (election.getChoices() != null) {
            election.getChoices().forEach(c -> c.setElection(election));
        }
    }

}