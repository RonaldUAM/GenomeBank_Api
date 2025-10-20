package com.genomebank.gene.infraestructure.repository;

import com.genomebank.gene.entities.Gene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeneRepository extends JpaRepository<Gene, Long> {
}
