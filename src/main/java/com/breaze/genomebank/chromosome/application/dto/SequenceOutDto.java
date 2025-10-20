package com.breaze.genomebank.chromosome.application.dto;

/**
 * Dto de salida para devolver la secuencia de ADN de un cromosoma.
 * @author nmedinaa
 * @version 1.0.0
 * @since 2025-10-20
 */
import lombok.Data;

@Data
public class SequenceOutDto {
    /**
     * Secuencia de ADN del cromosoma.
     */
    private String sequenceAdn;
}