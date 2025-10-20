package com.breaze.genomebank.gene_function.application.mappers;

import com.breaze.genomebank.function.application.mappers.FunctionMapper;
import com.breaze.genomebank.gene_function.application.dto.GeneFunctionOutDto;
import com.breaze.genomebank.gene_function.entities.GeneFunction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GeneFunctionMapper {
    private FunctionMapper functionMapper;

    public GeneFunctionOutDto geneFunctionOutDto(GeneFunction geneFunction){
        return GeneFunctionOutDto.builder()
                .evidenceLink(geneFunction.getEvidenceLink() == null ? "" : geneFunction.getEvidenceLink().getValue())
                .functionOutDto(functionMapper.toDto(geneFunction.getFunction()))
                .build();
    }
}
