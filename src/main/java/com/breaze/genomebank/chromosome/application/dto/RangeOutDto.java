package com.breaze.genomebank.chromosome.application.dto;

/**
 * Dto de salida para devolver una subsecuencia de la secuencia de ADN de un cromosoma.
 * @author nmedinaa
 * @version 1.0.0
 * @since 2025-10-20
 */
import lombok.Data;

@Data
public class RangeOutDto {
    /**
     * Subsecuencia de ADN extraída del cromosoma.
     */
    private String subSequenceAdn;
}