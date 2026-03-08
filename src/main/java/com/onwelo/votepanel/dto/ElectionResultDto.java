package com.onwelo.votepanel.dto;

import java.util.List;

public record ElectionResultDto(String name, List<ChoiceResultDto> choices) {

}
