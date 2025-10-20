package com.genomebank.chromosome.application.service;

import com.genomebank.chromosome.application.ports.IChromosomeService;
import com.genomebank.chromosome.entities.Chromosome;
import com.genomebank.chromosome.infraestructure.repository.IChromosomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChromosomeService implements IChromosomeService {

    private IChromosomeRepository chromosomeRepository;

    public Chromosome getChromosomeById(Long id) {
        //Funciones anonimas, compactar las funciones en una sola linea
        return chromosomeRepository.findById(id).orElseThrow(() -> new RuntimeException("Chromosome not found"));

    }

}
