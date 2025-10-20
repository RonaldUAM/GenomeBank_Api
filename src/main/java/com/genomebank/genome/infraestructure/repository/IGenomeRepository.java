package com.genomebank.genome.infraestructure.repository;

import com.genomebank.genome.entities.Genome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGenomeRepository extends JpaRepository<Genome, Long> {

    List<Genome> findBySpecieId(Long specieId);

}
