package com.breaze.genomebank.gene.application.mappers;

import com.breaze.genomebank.gene.application.dto.GeneOutDto;
import com.breaze.genomebank.gene.entities.Gene;
import com.breaze.genomebank.gene_function.application.dto.GeneFunctionOutDto;
import com.breaze.genomebank.gene_function.application.mappers.GeneFunctionMapper;
import com.breaze.genomebank.gene_function.entities.GeneFunction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GeneMapper {
    private GeneFunctionMapper geneMapper;

    public GeneOutDto toGeneOutDto(Gene gene){
        return GeneOutDto.builder()
                .endPosition(gene.getEndPosition())
                .sequenceAdn(gene.getSequenceAdn())
                .orientation(Boolean.TRUE.equals(gene.getOrientation()) ? "+" : "-")
                .symbol(gene.getSymbol())
                .startPosition(gene.getStartPosition())
                .geneFunction(getListGeneFunctionOut(gene.getFunctions()))
                .build();
    }

    private List<GeneFunctionOutDto> getListGeneFunctionOut(List<GeneFunction> geneFunctions){
        return geneFunctions.stream().map(geneMapper::geneFunctionOutDto).toList();
    }
}
