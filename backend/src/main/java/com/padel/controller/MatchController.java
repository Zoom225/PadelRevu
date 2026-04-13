package com.padel.controller;

import com.padel.dto.MatchDTO;
import com.padel.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<MatchDTO>> getMatchesByPlayer(@PathVariable Long playerId) {
        return ResponseEntity.ok(matchService.getMatchesByPlayer(playerId));
    }

    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@Valid @RequestBody MatchDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.createMatch(dto));
    }

    @PutMapping("/{id}/score")
    public ResponseEntity<MatchDTO> updateScore(@PathVariable Long id,
                                                 @RequestBody Map<String, Integer> scores) {
        return ResponseEntity.ok(matchService.updateScore(id, scores.get("scoreTeam1"), scores.get("scoreTeam2")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}

