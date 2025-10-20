package com.breaze.genomebank.chromosome.infraestructure.repository;

import com.breaze.genomebank.chromosome.entities.Chromosome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChromosomeRepository extends JpaRepository<Chromosome, Integer> {
    List<Chromosome> findByGenomeId(Integer genomeId);
}
