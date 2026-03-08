package com.onwelo.votepanel.controller;

import com.onwelo.votepanel.dto.ElectionDto;
import com.onwelo.votepanel.dto.ElectionResultDto;
import com.onwelo.votepanel.service.ElectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("election")
public class ElectionController {

    private final ElectionService service;

    @PostMapping
    public ResponseEntity<Void> addElection(@RequestBody @Valid ElectionDto dto) {
        service.addElection(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ElectionResultDto> getElectionResult(@RequestParam long id) {
        return ResponseEntity.ok(service.getElectionResult(id));
    }

}
