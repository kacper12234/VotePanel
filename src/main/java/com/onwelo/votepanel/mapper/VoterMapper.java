package com.onwelo.votepanel.mapper;

import com.onwelo.votepanel.dto.VoterDto;
import com.onwelo.votepanel.entity.Voter;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VoterMapper {
    Voter toEntity(VoterDto voterDto);

    VoterDto toDto(Voter voter);

}