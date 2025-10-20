package com.breaze.genomebank.chromosome.infraestructure.controller;

/**
 * Controlador REST para manejar solicitudes HTTP relacionadas con cromosomas.
 * Incluye control de acceso basado en roles.
 * @author nmedinaa
 * @version 1.0.0
 * @since 2025-10-20
 */
import com.breaze.genomebank.chromosome.application.dto.ChromosomeInDto;
import com.breaze.genomebank.chromosome.application.dto.ChromosomeOutDto;
import com.breaze.genomebank.chromosome.application.dto.RangeOutDto;
import com.breaze.genomebank.chromosome.application.dto.SequenceInDto;
import com.breaze.genomebank.chromosome.application.dto.SequenceOutDto;
import com.breaze.genomebank.chromosome.application.ports.IChromosomeService;
import com.breaze.genomebank.common.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chromosomes")
@AllArgsConstructor
public class ChromosomeController {
    /**
     * Servicio inyectado para manejar la lógica de negocio de cromosomas.
     */
    private IChromosomeService chromosomeService;

    /**
     * Maneja solicitudes GET para listar cromosomas, con filtro opcional por genoma.
     * @param genomeId ID del genoma para filtrar (opcional).
     * @return ResponseEntity con la lista de DTOs de cromosomas.
     * @PreAuthorize Permite acceso a roles 'user' y 'admin'.
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<ApiResponse<List<ChromosomeOutDto>>> getAllChromosomes(
            @RequestParam(required = false) Integer genomeId) {
        List<ChromosomeOutDto> dtos = chromosomeService.findAllChromosomeOrByGenomeId(genomeId);
        return buildResponse(dtos);
    }

    /**
     * Maneja solicitudes GET para recuperar un cromosoma por ID.
     * @param id ID del cromosoma a buscar.
     * @return ResponseEntity con el DTO del cromosoma.
     * @PreAuthorize Permite acceso a roles 'user' y 'admin'.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<ApiResponse<ChromosomeOutDto>> getById(@PathVariable Integer id) {
        ChromosomeOutDto dto = chromosomeService.findById(id);
        return new ResponseEntity<>(new ApiResponse<>("Success", "Cromosoma encontrado", dto), HttpStatus.OK);
    }

    /**
     * Maneja solicitudes POST para crear un nuevo cromosoma.
     * @param dto DTO con los datos del cromosoma a crear.
     * @return ResponseEntity con el DTO del cromosoma creado.
     * @PreAuthorize Requiere rol 'admin'.
     */
    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ApiResponse<ChromosomeOutDto>> create(@RequestBody ChromosomeInDto dto) {
        ChromosomeOutDto created = chromosomeService.create(dto);
        return new ResponseEntity<>(new ApiResponse<>("Created", "Cromosoma creado", created), HttpStatus.CREATED);
    }

    /**
     * Maneja solicitudes PUT para actualizar un cromosoma.
     * @param id ID del cromosoma a actualizar.
     * @param dto DTO con los datos actualizados.
     * @return ResponseEntity con el DTO del cromosoma actualizado.
     * @PreAuthorize Requiere rol 'admin'.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ApiResponse<ChromosomeOutDto>> update(@PathVariable Integer id, @RequestBody ChromosomeInDto dto) {
        ChromosomeOutDto updated = chromosomeService.update(id, dto);
        return new ResponseEntity<>(new ApiResponse<>("Success", "Cromosoma actualizado", updated), HttpStatus.OK);
    }

    /**
     * Maneja solicitudes DELETE para eliminar un cromosoma.
     * @param id ID del cromosoma a eliminar.
     * @return ResponseEntity con el resultado de la eliminación.
     * @PreAuthorize Requiere rol 'admin'.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        chromosomeService.delete(id);
        return new ResponseEntity<>(new ApiResponse<>("Success", "Cromosoma eliminado", null), HttpStatus.OK);
    }

    /**
     * Maneja solicitudes GET para recuperar la secuencia de ADN de un cromosoma.
     * @param id ID del cromosoma.
     * @return ResponseEntity con el DTO de la secuencia.
     * @PreAuthorize Permite acceso a roles 'user' y 'admin'.
     */
    @GetMapping("/{id}/sequence")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<ApiResponse<SequenceOutDto>> getSequence(@PathVariable Integer id) {
        SequenceOutDto dto = chromosomeService.getSequence(id);
        return new ResponseEntity<>(new ApiResponse<>("Success", "Secuencia encontrada", dto), HttpStatus.OK);
    }

    /**
     * Maneja solicitudes GET para recuperar una subsecuencia de la secuencia de ADN.
     * @param id ID del cromosoma.
     * @param start Posición inicial (1-based).
     * @param end Posición final.
     * @return ResponseEntity con el DTO de la subsecuencia.
     * @PreAuthorize Permite acceso a roles 'user' y 'admin'.
     */
    @GetMapping("/{id}/sequence/range")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<ApiResponse<RangeOutDto>> getSequenceRange(@PathVariable Integer id,
                                                                     @RequestParam Integer start,
                                                                     @RequestParam Integer end) {
        RangeOutDto dto = chromosomeService.getSequenceRange(id, start, end);
        return new ResponseEntity<>(new ApiResponse<>("Success", "Subsecuencia encontrada", dto), HttpStatus.OK);
    }

    /**
     * Maneja solicitudes PUT para actualizar la secuencia de ADN de un cromosoma.
     * @param id ID del cromosoma.
     * @param dto DTO con la nueva secuencia.
     * @return ResponseEntity con el DTO de la secuencia actualizada.
     * @PreAuthorize Requiere rol 'admin'.
     */
    @PutMapping("/{id}/sequence")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ApiResponse<SequenceOutDto>> updateSequence(@PathVariable Integer id,
                                                                      @RequestBody SequenceInDto dto) {
        SequenceOutDto updated = chromosomeService.updateSequence(id, dto);
        return new ResponseEntity<>(new ApiResponse<>("Success", "Secuencia actualizada", updated), HttpStatus.OK);
    }

    /**
     * Construye una respuesta condicional basada en la presencia de datos.
     * @param dtos Lista de DTOs de cromosomas.
     * @return ResponseEntity con el resultado.
     */
    private ResponseEntity<ApiResponse<List<ChromosomeOutDto>>> buildResponse(List<ChromosomeOutDto> dtos) {
        return new ResponseEntity<>(
                dtos.isEmpty()
                        ? new ApiResponse<>("Not found", "No se encontraron datos relacionados", null)
                        : new ApiResponse<>("Success", "Datos encontrados con éxito", dtos),
                dtos.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }
}