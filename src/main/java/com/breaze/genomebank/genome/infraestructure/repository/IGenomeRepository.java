package com.breaze.genomebank.genome.infraestructure.repository;

import com.breaze.genomebank.genome.entities.Genome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenomeRepository extends JpaRepository<Genome, Long> {
}
