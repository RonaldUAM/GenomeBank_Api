package com.breaze.genomebank.chromosome.application.dto;

/**
 * Dto de entrada para crear o actualizar un Cromosoma.
 * Este se utiliza para recibir datos desde el cliente.
 * @author nmedinaa
 * @version 1.0.0
 * @since 2025-10-20
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChromosomeInDto {
    /**
     * Nombre del cromosoma (e.g., "2L").
     */
    private String name;

    /**
     * Longitud de la secuencia de ADN.
     */
    private Integer size;

    /**
     * Secuencia de ADN (e.g., "ATCG").
     */
    private String sequenceAdn;

    /**
     * ID del genoma asociado al cromosoma.
     */
    private Integer genomeId;

    /**
     * Lista de IDs de genes asociados al cromosoma durante la creación.
     */
    private List<Integer> genesId;
}