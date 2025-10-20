package com.breaze.genomebank.chromosome.application.dto;

import com.breaze.genomebank.gene.application.dto.GeneOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChromosomeOutDto {
    private String name;
    private Integer size;
    private String sequenceAdn;
    private List<GeneOutDto> genes;
}