package com.breaze.genomebank.chromosome.application.mappers;

import com.breaze.genomebank.chromosome.application.dto.ChromosomeOutDto;
import com.breaze.genomebank.chromosome.entities.Chromosome;
import com.breaze.genomebank.gene.application.dto.GeneOutDto;
import com.breaze.genomebank.gene.application.mappers.GeneMapper;
import com.breaze.genomebank.gene.entities.Gene;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ChromosomeMapper {
    private GeneMapper geneMapper;
    public ChromosomeOutDto toDto(Chromosome chromosome){
        return ChromosomeOutDto.builder()
                .name(chromosome.getName())
                .size(chromosome.getSize())
                .sequenceAdn(chromosome.getSequenceAdn())
                .genes(getListGeneOutDto(chromosome.getGenes()))
                .build();

    }

    private List<GeneOutDto> getListGeneOutDto(List<Gene> genes){
        return genes.stream().map(geneMapper::toGeneOutDto).toList();
    }
}
