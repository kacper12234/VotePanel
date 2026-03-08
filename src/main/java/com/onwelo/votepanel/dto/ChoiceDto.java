package com.onwelo.votepanel.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record ChoiceDto(@NotBlank String name) implements Serializable {
}