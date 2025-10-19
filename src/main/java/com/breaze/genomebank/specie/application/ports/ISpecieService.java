package com.breaze.genomebank.specie.application.ports;

import com.breaze.genomebank.specie.application.dto.SpecieInDTO;
import com.breaze.genomebank.specie.application.dto.SpecieOutDTO;
import java.util.List;

/**
 * Interfaz que define los servicios disponibles para la gestión de especies.
 * Define el contrato que debe cumplir la implementación del servicio.
 */
public interface ISpecieService {
    
    /**
     * Obtiene todas las especies registradas.
     * @return Lista de SpecieOutDTO con todas las especies
     */
    List<SpecieOutDTO> getAllSpecies();
    
    /**
     * Obtiene una especie específica por su id.
     * @param id de la especie a buscar
     * @return SpecieOutDTO con los datos de la especie
     * @throws RuntimeException si es que no se encuentra
     */
    SpecieOutDTO getSpecieById(Long id);
    
    /**
     * Crea una nueva especie (solo para ADMIN).
     * @param specieInDTO Datos de la especie a crear
     * @return SpecieOutDTO con los datos de la especie creada
     * @throws RuntimeException si el nombre ya existe
     */
    SpecieOutDTO createSpecie(SpecieInDTO specieInDTO);
    
    /**
     * Actualiza una especie existente (solo para ADMIN).
     * @param id de la especie a actualizar
     * @param specieInDTO datos de la especie a actualizar
     * @return SpecieOutDTO con los datos actualizados
     * @throws RuntimeException si no se encuentra la especie
     */
    SpecieOutDTO updateSpecie(Long id, SpecieInDTO specieInDTO);
    
    /**
     * Elimina una especie y se eliminan en cascada todos sus genomas (solo para ADMIN).
     * @param id de la especie a eliminar
     * @throws RuntimeException si no se encuentra o no existe
     */
    void deleteSpecie(Long id);
}