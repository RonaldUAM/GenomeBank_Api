package com.genomebank.specie.application.service;

import com.genomebank.specie.application.ports.ISpecieService;
import com.genomebank.specie.infraestructure.repository.SpecieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpecieService implements ISpecieService {
    private SpecieRepository specieRepository;
}
