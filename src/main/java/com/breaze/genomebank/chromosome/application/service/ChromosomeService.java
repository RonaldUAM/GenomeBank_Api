package com.breaze.genomebank.chromosome.application.service;

import com.breaze.genomebank.chromosome.application.dto.ChromosomeOutDto;
import com.breaze.genomebank.chromosome.application.mappers.ChromosomeMapper;
import com.breaze.genomebank.chromosome.application.ports.IChromosomeService;
import com.breaze.genomebank.chromosome.infraestructure.repository.IChromosomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChromosomeService implements IChromosomeService {

    private IChromosomeRepository chromosomeRepository;
    private ChromosomeMapper chromosomeMapper;

    @Override
    public List<ChromosomeOutDto> findAllChromosomeOrByGenomeId(Integer genomeId) {
        if (genomeId != null){
            return chromosomeRepository.findByGenomeId(genomeId).stream()
                    .map(chromosomeMapper::toDto)
                    .toList();
        }
        return chromosomeRepository.findAll().stream().map(chromosomeMapper::toDto).toList();
    }
}
