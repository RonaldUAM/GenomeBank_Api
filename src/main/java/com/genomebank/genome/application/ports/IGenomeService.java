package com.genomebank.genome.application.ports;

import com.genomebank.genome.infraestructure.dto.CrearinDTO;
import com.genomebank.genome.entities.Genome;


import java.util.List;
import java.util.Optional;

public interface IGenomeService {
    //Metodos
    public List<Genome> obtenerGenomas();
    public Optional<Genome> obtenerGenomaPorId(Long id);
    public Genome crearGenoma(CrearinDTO crearinDTO);
    public Optional<Genome> actualizarGenoma(Long id, Genome genoma);
    public boolean eliminarGenoma(Long id);


}

