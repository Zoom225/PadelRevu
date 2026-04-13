package com.padel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatsCAResponse {
    private Double totalCA;
    private Long nombreMatchesPayes;
    private Double montantMoyen;
    private Double soldeTotal;
}

