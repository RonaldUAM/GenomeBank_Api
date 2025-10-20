package com.genomebank.genome.application.service;
import com.genomebank.chromosome.application.ports.IChromosomeService;
import com.genomebank.chromosome.entities.Chromosome;
import com.genomebank.genome.infraestructure.dto.CreatGenomeInDTO;
import com.genomebank.genome.application.ports.IGenomeService;
import com.genomebank.genome.entities.Genome;
import com.genomebank.genome.infraestructure.repository.IGenomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenomeService implements IGenomeService {
    private IGenomeRepository IGenomeRepository;
    private IChromosomeService IChromosomeService;

    @Override
    public List<Genome>getGenomes(){
        return this.IGenomeRepository.findAll();
    }


    @Override
    public List<Genome> getGenomesBySpecieId(Long specieId) {
        return this.IGenomeRepository.findBySpecieId(specieId);
    }

    @Override
    public Optional<Genome> getGenomesById(Long id) {
        return this.IGenomeRepository.findById(id);
    }


    @Override
    public Genome createGenome(CreatGenomeInDTO creatGenomeInDTO) {
        //Traer la lista de cromosomas
        List<Integer>idCromosomas= creatGenomeInDTO.getGenome();
        List<Chromosome> cromosomas=new ArrayList<>();
        for (Integer idCromosoma : idCromosomas) {

            Chromosome cromosoma=IChromosomeService.getChromosomeById(Long.valueOf(idCromosoma));

            cromosomas.add(cromosoma);


        }

        Genome genoma=Genome.builder().chromosomes(cromosomas).build();

        return IGenomeRepository.save(genoma);

    }

    @Override
    public Optional<Genome> updateGenome(Long id, Genome genoma) {
        return this.IGenomeRepository.findById(id)
                .map(genomaEncontrado->{
                    genomaEncontrado.setId(genomaEncontrado.getId());
                    return this.IGenomeRepository.save(genomaEncontrado);
                });
    }

    @Override
    public boolean deleteGenome(Long id) {
        if (IGenomeRepository.existsById(id)) {
            IGenomeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
