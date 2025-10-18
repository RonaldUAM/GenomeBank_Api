package com.breaze.genomebank.specie.infraestructure.repository;

import com.breaze.genomebank.specie.entities.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepository extends JpaRepository<Specie,Long> {
}
