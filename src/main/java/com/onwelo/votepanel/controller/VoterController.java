package com.onwelo.votepanel.controller;

import com.onwelo.votepanel.dto.VoterDto;
import com.onwelo.votepanel.service.VoterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("voter")
public class VoterController {

    private final VoterService voterService;

    @PostMapping
    public ResponseEntity<Void> addVoter(@RequestBody @Valid VoterDto voterDto) {
        voterService.addVoter(voterDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<VoterDto> findVoter(@RequestParam String personalNumber) {
        return ResponseEntity.ok(voterService.findVoter(personalNumber));
    }

    @PutMapping("block")
    public ResponseEntity<Void> blockVoter(@RequestParam long id) {
        voterService.blockVoter(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("unblock")
    public ResponseEntity<Void> unblockVoter(@RequestParam long id) {
        voterService.unlockVoter(id);
        return ResponseEntity.ok().build();
    }
}
