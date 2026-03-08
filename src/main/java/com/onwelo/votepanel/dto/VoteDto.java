package com.onwelo.votepanel.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.onwelo.votepanel.entity.Vote}
 */
public record VoteDto(@NotNull Long choiceId,@NotNull Long electionId,@NotNull Long voterId) implements Serializable {
}