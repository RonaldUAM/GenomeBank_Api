package com.breaze.genomebank.gene.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Dto de entrada para crear o actualizar un Gen.
 * Este se utiliza para recibir datos desde el cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneInDto {
    private String symbol;
    private String startPosition;
    private String endPosition;
    private String orientation;
    private String sequenceAdn;
    private List<Integer> genFunctionId;
}
