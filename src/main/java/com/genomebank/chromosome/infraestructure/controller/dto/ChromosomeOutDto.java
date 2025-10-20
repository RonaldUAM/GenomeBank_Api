package com.genomebank.chromosome.infraestructure.controller.dto;

import com.genomebank.gene.application.dto.GeneOutDto;

import java.util.List;

public class ChromosomeOutDto {
    private String name;
    private Integer size;
    private String sequenceAdn;
    private List<GeneOutDto> genesId;
}