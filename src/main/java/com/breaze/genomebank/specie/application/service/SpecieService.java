package com.breaze.genomebank.specie.application.service;

import com.breaze.genomebank.specie.application.ports.ISpecieService;
import com.breaze.genomebank.specie.infraestructure.repository.SpecieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpecieService implements ISpecieService {
    private SpecieRepository specieRepository;
}
