package com.breaze.genomebank.chromosome.application.dto;


import com.breaze.genomebank.common.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto de entrada para crear o actualizar un Chromosome.
 * Este se utiliza para recibir datos desde el cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChromosomeInDto {
    private String name;
    private Integer size;
}
