package com.padel.controller;

import com.padel.dto.PaiementRequest;
import com.padel.dto.PaiementResponse;
import com.padel.service.PaiementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {
    private final PaiementService paiementService;

    // Grande action: payer sa part de match
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaiementResponse payerPart(@RequestHeader("X-Matricule") String matricule,
                                      @Valid @RequestBody PaiementRequest request) {
        return paiementService.payerPart(matricule, request);
    }
}
