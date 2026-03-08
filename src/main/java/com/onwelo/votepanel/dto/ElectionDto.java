package com.onwelo.votepanel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.onwelo.votepanel.entity.Election}
 */
public record ElectionDto(@NotBlank String name, @NotNull LocalDate date,
                          @NotNull @Size(min = 1) List<ChoiceDto> choices) implements Serializable {
}