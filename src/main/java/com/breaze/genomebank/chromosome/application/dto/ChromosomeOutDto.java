package com.breaze.genomebank.chromosome.application.dto;

/**
 * Dto de salida para devolver datos de un cromosoma al cliente.
 * Incluye información de genes relacionados.
 * @author nmedinaa
 * @version 1.0.0
 * @since 2025-10-20
 */
import com.breaze.genomebank.gene.application.dto.GeneOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChromosomeOutDto {
    /**
     * Nombre del cromosoma.
     */
    private String name;

    /**
     * Longitud de la secuencia de ADN.
     */
    private Integer size;

    /**
     * Secuencia de ADN.
     */
    private String sequenceAdn;

    /**
     * Lista de DTOs de genes asociados al cromosoma.
     */
    private List<GeneOutDto> genes;
}