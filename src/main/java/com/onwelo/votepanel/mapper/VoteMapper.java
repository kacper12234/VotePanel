package com.onwelo.votepanel.mapper;

import com.onwelo.votepanel.dto.VoteDto;
import com.onwelo.votepanel.entity.Vote;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VoteMapper {

    Vote toEntity(VoteDto voteDto);

}