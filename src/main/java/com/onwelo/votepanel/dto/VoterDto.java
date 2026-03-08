package com.onwelo.votepanel.dto;

import jakarta.validation.constraints.NotBlank;

public record VoterDto(@NotBlank String name,
                       @NotBlank String personalNumber,
                       @NotBlank String address,
                       Boolean blocked) {
}
