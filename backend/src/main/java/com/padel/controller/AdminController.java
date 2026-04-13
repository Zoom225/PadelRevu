package com.padel.controller;

import com.padel.dto.StatsCAResponse;
import com.padel.service.StatistiquesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/global")
@RequiredArgsConstructor
public class AdminController {
    private final StatistiquesService statsService;

    // Grande action: récupérer les statistiques de chiffre d'affaires
    @GetMapping("/stats/ca")
    public StatsCAResponse getCA() {
        return statsService.getCA();
    }
}
