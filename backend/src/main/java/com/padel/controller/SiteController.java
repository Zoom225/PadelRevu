package com.padel.controller;

import com.padel.dto.SiteRequest;
import com.padel.dto.SiteResponse;
import com.padel.service.SiteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;

    @GetMapping
    public List<SiteResponse> getAll() {
        return siteService.getAll();
    }

    @GetMapping("/{id}")
    public SiteResponse getById(@PathVariable Long id) {
        return siteService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SiteResponse create(@Valid @RequestBody SiteRequest request) {
        return siteService.create(request);
    }

    @PutMapping("/{id}")
    public SiteResponse update(@PathVariable Long id, @Valid @RequestBody SiteRequest request) {
        return siteService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        siteService.delete(id);
    }
}
