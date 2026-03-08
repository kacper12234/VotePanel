package com.onwelo.votepanel.controller;

import com.onwelo.votepanel.dto.VoteDto;
import com.onwelo.votepanel.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("vote")
public class VoteController {

    private final VoteService service;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody @Valid VoteDto dto) {
        service.vote(dto);
        return ResponseEntity.ok().build();
    }
}
