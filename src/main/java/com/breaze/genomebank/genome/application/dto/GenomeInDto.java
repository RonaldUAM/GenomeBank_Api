package com.breaze.genomebank.genome.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Dto de entrada para crear o actualizar un Genoma.
 * Este se utiliza para recibir datos desde el cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenomeInDto {
    /**
     * Para Crear el genomas se debe pedir los ids de los cromosomas existentes
     */
    Integer specieId;
    List<Integer> chromosomes;
}
