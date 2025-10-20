package com.genomebank.gene.infraestructure.controller.dto;

import com.breaze.genomebank.function.application.dto.FunctionOutDto;

import java.util.List;

public class GeneOutDto {
    private String symbol;
    private String startPosition;
    private String endPosition;
    private String orientation;
    private String sequenceAdn;
    private List<FunctionOutDto> genFunction;
}