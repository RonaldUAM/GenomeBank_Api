package com.genomebank.chromosome.application.ports;

import com.genomebank.chromosome.entities.Chromosome;

public interface IChromosomeService {
    public Chromosome getChromosomeById(Long id);


}
