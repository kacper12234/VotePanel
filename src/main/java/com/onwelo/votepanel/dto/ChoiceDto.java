package com.onwelo.votepanel.dto;

import com.onwelo.votepanel.entity.Choice;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link Choice}
 */
public record ChoiceDto(@NotBlank String name) implements Serializable {
}