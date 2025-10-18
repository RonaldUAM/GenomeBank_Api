package com.breaze.genomebank.genome.application.service;

import com.breaze.genomebank.genome.application.ports.IGenomeService;
import com.breaze.genomebank.genome.infraestructure.repository.IGenomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenomeService implements IGenomeService {
    private IGenomeRepository IGenomeRepository;
}
