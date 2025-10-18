package com.breaze.genomebank.chromosome.application.service;

import com.breaze.genomebank.chromosome.application.ports.IChromosomeService;
import com.breaze.genomebank.chromosome.infraestructure.repository.IChromosomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChromosomeService implements IChromosomeService {

    private IChromosomeRepository chromosomeRepository;

}
