package com.breaze.genomebank.gene_function.application.dto;

import com.breaze.genomebank.function.application.dto.FunctionOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneFunctionOutDto {
    private String evidenceLink;
    private FunctionOutDto functionOutDto;
}
