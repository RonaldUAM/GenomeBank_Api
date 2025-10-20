package com.genomebank.genome.application.ports;

import com.genomebank.genome.infraestructure.dto.CreatGenomeInDTO;
import com.genomebank.genome.entities.Genome;


import java.util.List;
import java.util.Optional;

public interface IGenomeService {
    //Metodos
    public List<Genome> getGenomesBySpecieId(Long specieId);
    public List<Genome> getGenomes();
    public Optional<Genome> getGenomesById(Long id);
    public Genome createGenome(CreatGenomeInDTO creatGenomeInDTO);
    public Optional<Genome> updateGenome(Long id, Genome genome);
    public boolean deleteGenome(Long id);


}

