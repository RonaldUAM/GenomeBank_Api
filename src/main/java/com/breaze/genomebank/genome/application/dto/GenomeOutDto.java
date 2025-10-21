package com.breaze.genomebank.genome.application.dto;

import com.breaze.genomebank.chromosome.application.dto.ChromosomeOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenomeOutDto {
    List<ChromosomeOutDto> chromosomes;
}
