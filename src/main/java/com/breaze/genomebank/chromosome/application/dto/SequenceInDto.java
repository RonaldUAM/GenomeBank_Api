package com.breaze.genomebank.chromosome.application.dto;

/**
 * Dto de entrada para actualizar la secuencia de ADN de un cromosoma.
 * @author nmedinaa
 * @version 1.0.0
 * @since 2025-10-20
 */
import lombok.Data;

@Data
public class SequenceInDto {
    /**
     * Nueva secuencia de ADN a actualizar.
     */
    private String sequenceAdn;
}