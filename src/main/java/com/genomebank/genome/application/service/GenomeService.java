package com.genomebank.genome.application.service;
import com.genomebank.chromosome.application.ports.IChromosomeService;
import com.genomebank.chromosome.entities.Chromosome;
import com.genomebank.chromosome.infraestructure.controller.dto.CreateinDTO;
import com.genomebank.genome.infraestructure.dto.CrearinDTO;
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
    public List<Genome> obtenerGenomas() {
        return this.IGenomeRepository.findAll();
    }

    @Override
    public Optional<Genome> obtenerGenomaPorId(Long id) {
        return this.IGenomeRepository.findById(id);
    }


    @Override
    public Genome crearGenoma(CrearinDTO crearinDTO) {
        //Traer la lista de cromosomas
        List<Integer>idCromosomas=crearinDTO.getGenome();
        List<Chromosome> cromosomas=new ArrayList<>();
        for (Integer idCromosoma : idCromosomas) {

            Chromosome cromosoma=IChromosomeService.getChromosomeById(Long.valueOf(idCromosoma));

            cromosomas.add(cromosoma);


        }

        Genome genoma=Genome.builder().chromosomes(cromosomas).build();

        return IGenomeRepository.save(genoma);

    }

    @Override
    public Optional<Genome> actualizarGenoma(Long id, Genome genoma) {
        return this.IGenomeRepository.findById(id)
                .map(genomaEncontrado->{
                    genomaEncontrado.setId(genomaEncontrado.getId());
                    return this.IGenomeRepository.save(genomaEncontrado);
                });
    }

    @Override
    public boolean eliminarGenoma(Long id) {
        if (IGenomeRepository.existsById(id)) {
            IGenomeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
