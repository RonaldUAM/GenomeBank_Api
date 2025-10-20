package com.genomebank.gene.application.service;

import com.genomebank.gene.application.ports.IGeneService;
import com.genomebank.gene.infraestructure.repository.IGeneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GeneService implements IGeneService {
    private IGeneRepository geneRepository;
}
