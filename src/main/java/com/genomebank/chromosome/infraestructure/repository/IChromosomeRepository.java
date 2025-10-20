package com.genomebank.chromosome.infraestructure.repository;

import com.genomebank.chromosome.entities.Chromosome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChromosomeRepository extends JpaRepository<Chromosome, Long> {
}
