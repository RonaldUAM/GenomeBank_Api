package com.breaze.genomebank.chromosome.application.dto;

import com.breaze.genomebank.gene.application.dto.GeneOutDto;

import java.util.List;

public class ChromosomeOutDto {
    private String name;
    private Integer size;
    private String sequenceAdn;
    private List<GeneOutDto> genesId;
}