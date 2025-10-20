package com.breaze.genomebank.chromosome.infraestructure.repository;

/**
 * Repositorio JPA para la entidad Chromosome.
 * Extiende operaciones CRUD básicas con consultas personalizadas.
 * @author nmedinaa
 * @version 1.0.0
 * @since 2025-10-20
 */
import com.breaze.genomebank.chromosome.entities.Chromosome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChromosomeRepository extends JpaRepository<Chromosome, Integer> {
    /**
     * Recupera una lista de cromosomas asociados a un ID de genoma específico.
     * @param genomeId ID del genoma.
     * @return Lista de entidades Chromosome.
     */
    List<Chromosome> findByGenomeId(Integer genomeId);
}