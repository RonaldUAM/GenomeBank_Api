package com.breaze.genomebank.specie.infraestructure.repository;

import com.breaze.genomebank.specie.entities.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Specie.
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface SpecieRepository extends JpaRepository<Specie,Long> {
    
    /**
     * Busca una especie por su nombre científico.
     * @param scientificName Nombre científico de la especie
     * @return Optional con la especie si existe, vacío si no
     */
    Optional<Specie> findByScientificName(String scientificName);
    
    /**
     * Verifica si existe una especie con el nombre científico especificado.
     * Útil para validar duplicados al crear o actualizar especies.
     * @param scientificName Nombre científico a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByScientificName(String scientificName);
}
