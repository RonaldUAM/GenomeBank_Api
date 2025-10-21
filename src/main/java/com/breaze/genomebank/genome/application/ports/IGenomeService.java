package com.breaze.genomebank.genome.application.ports;

import com.breaze.genomebank.genome.application.dto.GenomeInDto;
import com.breaze.genomebank.genome.application.dto.GenomeOutDto;

import java.util.List;

public interface IGenomeService {
    List<GenomeOutDto> findAllGenomeOrByGenomeId(Integer specieId);
    GenomeOutDto getGenomeById(Integer genomeId);
    GenomeOutDto createGenome(GenomeInDto genomeInDto);
    void deleteGenomeById(Integer genomeId);
    GenomeOutDto updateGenome(Integer idGenome, GenomeInDto genomeInDto);
}
