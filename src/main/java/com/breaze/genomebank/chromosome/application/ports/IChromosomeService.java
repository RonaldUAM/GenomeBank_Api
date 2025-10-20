package com.breaze.genomebank.chromosome.application.ports;

/**
 * Interfaz que define el contrato del servicio para operaciones de cromosomas.
 * Especifica todas las operaciones disponibles en la capa de servicio.
 * @author nmedinaa
 * @version 1.0.0
 * @since 2025-10-20
 */
import com.breaze.genomebank.chromosome.application.dto.ChromosomeInDto;
import com.breaze.genomebank.chromosome.application.dto.ChromosomeOutDto;
import com.breaze.genomebank.chromosome.application.dto.RangeOutDto;
import com.breaze.genomebank.chromosome.application.dto.SequenceInDto;
import com.breaze.genomebank.chromosome.application.dto.SequenceOutDto;

import java.util.List;

public interface IChromosomeService {
    /**
     * Recupera una lista de cromosomas, opcionalmente filtrada por ID de genoma.
     * @param genomeId ID del genoma para filtrar (opcional, null para todos).
     * @return Lista de DTOs de cromosomas.
     */
    List<ChromosomeOutDto> findAllChromosomeOrByGenomeId(Integer genomeId);

    /**
     * Recupera un cromosoma por su ID.
     * @param id ID del cromosoma a buscar.
     * @return DTO del cromosoma encontrado.
     * @throws RuntimeException si el cromosoma no se encuentra.
     */
    ChromosomeOutDto findById(Integer id);

    /**
     * Crea un nuevo cromosoma con genes asociados.
     * @param dto DTO con los datos del cromosoma a crear.
     * @return DTO del cromosoma creado.
     * @throws RuntimeException si el genoma no existe o hay errores de validación.
     */
    ChromosomeOutDto create(ChromosomeInDto dto);

    /**
     * Actualiza un cromosoma existente.
     * @param id ID del cromosoma a actualizar.
     * @param dto DTO con los datos actualizados.
     * @return DTO del cromosoma actualizado.
     * @throws RuntimeException si el cromosoma no se encuentra.
     */
    ChromosomeOutDto update(Integer id, ChromosomeInDto dto);

    /**
     * Elimina un cromosoma por su ID.
     * @param id ID del cromosoma a eliminar.
     * @throws RuntimeException si el cromosoma no se encuentra.
     */
    void delete(Integer id);

    /**
     * Recupera la secuencia de ADN completa de un cromosoma.
     * @param id ID del cromosoma.
     * @return DTO con la secuencia de ADN.
     * @throws RuntimeException si el cromosoma no se encuentra.
     */
    SequenceOutDto getSequence(Integer id);

    /**
     * Recupera una subsecuencia de la secuencia de ADN de un cromosoma.
     * @param id ID del cromosoma.
     * @param start Posición inicial (1-based).
     * @param end Posición final.
     * @return DTO con la subsecuencia de ADN.
     * @throws RuntimeException si el rango es inválido o el cromosoma no se encuentra.
     */
    RangeOutDto getSequenceRange(Integer id, Integer start, Integer end);

    /**
     * Actualiza la secuencia de ADN de un cromosoma.
     * @param id ID del cromosoma.
     * @param dto DTO con la nueva secuencia.
     * @return DTO con la secuencia actualizada.
     * @throws RuntimeException si el cromosoma no se encuentra.
     */
    SequenceOutDto updateSequence(Integer id, SequenceInDto dto);
}