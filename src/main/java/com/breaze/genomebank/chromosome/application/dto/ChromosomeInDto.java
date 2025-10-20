package com.breaze.genomebank.chromosome.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Dto de entrada para crear o actualizar un Cromosoma.
 * Este se utiliza para recibir datos desde el cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChromosomeInDto {
    private String name;
    private Integer size;
    private String sequenceAdn;
    /**
     * Para poder crear un cromosoma, se necesitan los id's de los genes
     */
    private List<Integer> genesId;
}
