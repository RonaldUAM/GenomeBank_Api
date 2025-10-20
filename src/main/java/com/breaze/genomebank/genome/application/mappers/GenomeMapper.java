package com.breaze.genomebank.genome.application.mappers;

import com.breaze.genomebank.chromosome.application.dto.ChromosomeOutDto;
import com.breaze.genomebank.chromosome.application.mappers.ChromosomeMapper;
import com.breaze.genomebank.chromosome.entities.Chromosome;
import com.breaze.genomebank.gene.application.dto.GeneOutDto;
import com.breaze.genomebank.genome.application.dto.GenomeInDto;
import com.breaze.genomebank.genome.application.dto.GenomeOutDto;
import com.breaze.genomebank.genome.entities.Genome;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GenomeMapper {
    private ChromosomeMapper chromosomeMapper;
    public GenomeOutDto toDto(Genome genome){
        return GenomeOutDto.builder()
                .chromosomes(getListChromosome(genome.getChromosomes()))
                .build();
    }
    private List<ChromosomeOutDto> getListChromosome(List<Chromosome> chromosomes){
        return chromosomes.stream().map(chromosomeMapper::toDto).toList();
    }
}
