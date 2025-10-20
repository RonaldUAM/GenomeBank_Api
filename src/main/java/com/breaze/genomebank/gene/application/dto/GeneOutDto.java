package com.breaze.genomebank.gene.application.dto;

import com.breaze.genomebank.gene_function.application.dto.GeneFunctionOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneOutDto {
    private String symbol;
    private String startPosition;
    private String endPosition;
    private String orientation;
    private String sequenceAdn;
    private List<GeneFunctionOutDto> geneFunction;
}
