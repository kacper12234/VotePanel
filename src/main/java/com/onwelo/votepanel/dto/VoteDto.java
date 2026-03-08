package com.onwelo.votepanel.dto;

import jakarta.validation.constraints.NotNull;

public record VoteDto(@NotNull Long choiceId,@NotNull Long electionId,@NotNull Long voterId) {
}