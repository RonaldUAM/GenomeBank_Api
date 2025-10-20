package com.breaze.genomebank.genome.application.service;

import com.breaze.genomebank.chromosome.entities.Chromosome;
import com.breaze.genomebank.chromosome.infraestructure.repository.IChromosomeRepository;
import com.breaze.genomebank.genome.application.dto.GenomeInDto;
import com.breaze.genomebank.genome.application.dto.GenomeOutDto;
import com.breaze.genomebank.genome.application.mappers.GenomeMapper;
import com.breaze.genomebank.genome.application.ports.IGenomeService;
import com.breaze.genomebank.genome.entities.Genome;
import com.breaze.genomebank.genome.infraestructure.repository.IGenomeRepository;
import com.breaze.genomebank.specie.entities.Specie;
import com.breaze.genomebank.specie.infraestructure.repository.SpecieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenomeService implements IGenomeService {
    private IGenomeRepository genomeRepository;
    private SpecieRepository specieRepository;
    private IChromosomeRepository chromosomeRepository;

    private GenomeMapper genomeMapper;

    @Override
    public List<GenomeOutDto> findAllGenomeOrByGenomeId(Integer genomeId) {
        if (genomeId != null){
            return genomeRepository.findBySpecieId(genomeId).stream()
                    .map(genomeMapper::toDto)
                    .toList();
        }
        return genomeRepository.findAll().stream().map(genomeMapper::toDto).toList();
    }

    @Override
    public GenomeOutDto getGenomeById(Integer genomeId) {
        Genome genome = genomeRepository.findById(genomeId).orElseThrow(() ->
                new RuntimeException("No se encontro un Genoma con el id: "+genomeId));
        return genomeMapper.toDto(genome);
    }

    @Override
    public GenomeOutDto createGenome(GenomeInDto genomeInDto) {
        List<Chromosome> chromosomes = genomeInDto.getChromosomes().stream()
                .map(id -> chromosomeRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Cromosoma no encontrado: " + id)))
                .toList();
        Specie specie = specieRepository.findById(genomeInDto.getSpecieId()).
                orElseThrow(() -> new EntityNotFoundException("Specie no encontrado: " + id));
        Genome genome = Genome.builder()
                .specie(specie)
                .chromosomes(chromosomes)
                .build();
        return genomeMapper.toDto(genomeRepository.save(genome));
    }

    @Override
    public void deleteGenomeById(Integer genomeId) {
        // Verifica que la especie exista
        if (!genomeRepository.existsById(genomeId))
        {
            throw new RuntimeException("Genoma no encontrada con ID: " + id);
        }

        // Elimina la especie (junto a sus genomas en cascada)
        genomeRepository.deleteById(genomeId);
    }
}
