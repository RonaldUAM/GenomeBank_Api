package com.breaze.genomebank.chromosome.application.ports;

import com.breaze.genomebank.chromosome.application.dto.ChromosomeOutDto;

import java.util.List;

public interface IChromosomeService {
    List<ChromosomeOutDto> findAllChromosomeOrByGenomeId(Integer genomeId);
}
