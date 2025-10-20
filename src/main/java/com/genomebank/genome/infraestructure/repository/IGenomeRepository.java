package com.genomebank.genome.infraestructure.repository;

import com.genomebank.genome.entities.Genome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenomeRepository extends JpaRepository<Genome, Long> {
}
