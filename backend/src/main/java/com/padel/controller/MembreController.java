package com.padel.controller;

import com.padel.dto.MembreRequest;
import com.padel.dto.MembreResponse;
import com.padel.service.MembreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membres")
@RequiredArgsConstructor
public class MembreController {

    private final MembreService membreService;

    @GetMapping("/{matricule}")
    public MembreResponse getByMatricule(@PathVariable String matricule) {
        return membreService.getByMatricule(matricule);
    }

    @GetMapping
    public List<MembreResponse> getAll() {
        return membreService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MembreResponse create(@Valid @RequestBody MembreRequest request) {
        return membreService.create(request);
    }

    @PutMapping("/{id}")
    public MembreResponse update(@PathVariable Long id,
                                  @Valid @RequestBody MembreRequest request) {
        return membreService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        membreService.delete(id);
    }
}
