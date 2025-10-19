package com.breaze.genomebank.specie.infraestructure.controller;

import com.breaze.genomebank.common.ApiResponse;
import com.breaze.genomebank.specie.application.dto.SpecieInDTO;
import com.breaze.genomebank.specie.application.dto.SpecieOutDTO;
import com.breaze.genomebank.specie.application.ports.ISpecieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador de la gestión de especies.
 * 
 * (Dejo esto para que entienda el que lea, pero no se si se pueda poner, sino, igualmente se quita y ya)
 * Los endpoints que hay son:
 * -GET /species → Lista todas las especies (USER y ADMIN)
 * -GET /species/{id} → Consulta una especie específica (USER y ADMIN)
 * -POST /species → Crea una nueva especie (solo ADMIN)
 * -PUT /species/{id} → Actualiza una especie (solo ADMIN)
 * -DELETE /species/{id} → Elimina una especie (solo ADMIN)
 * 
 * Y se maneja @PreAuthorize para verificar roles.
 */
@RestController
@RequestMapping("/species")
@AllArgsConstructor
public class SpecieController
{
    private ISpecieService specieService;
    /**
     * Lista todas las especies registradas en el sistema.
     * Endpoint: GET /species
     * @return ResponseEntity con ApiResponse conteniendo la lista de especies
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ApiResponse<List<SpecieOutDTO>>> getAllSpecies()
    {
        try
        {
            //Obtiene todas las especies del servicio
            List<SpecieOutDTO> species = specieService.getAllSpecies();
            //Retorna respuesta exitosa con los datos
            return ResponseEntity.ok(
                new ApiResponse<>("success", "Especies obtenidas exitosamente", species)
            );
        } catch (Exception e) {
            // En caso de error, retorna respuesta con error interno
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse<>("error", "Error al obtener las especies: " + e.getMessage(), null)
            );
        }
    }

    /**
     * Consulta una especie específica por su id.
     * Endpoint: GET /species/{id}
     * @param id de la especie a consultar
     * @return ResponseEntity con ApiResponse conteniendo los datos de la especie
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ApiResponse<SpecieOutDTO>> getSpecieById(@PathVariable Long id)
    {
        try
        {
            //Busca la especie por id
            SpecieOutDTO specie = specieService.getSpecieById(id);
            //Retorna respuesta exitosa junto con los datos
            return ResponseEntity.ok(
                new ApiResponse<>("success", "Especie encontrada", specie)
            );
        } catch (RuntimeException e) {
            //Si no se encuentra la especie, retorna un 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>("error", e.getMessage(), null)
            );
        } catch (Exception e) {
            //Los demás errores que retornan un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse<>("error", "Error al obtener la especie: " + e.getMessage(), null)
            );
        }
    }

    /**
     * Crea una nueva especie (solo para ADMIN).
     * Endpoint: POST /species
     * @param specieInDTO Datos de la especie a crear
     * @return ResponseEntity con ApiResponse conteniendo la especie creada
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SpecieOutDTO>> createSpecie(
            @RequestBody SpecieInDTO specieInDTO)
    {
        try
        {
            //Crea nueva especie
            SpecieOutDTO createdSpecie = specieService.createSpecie(specieInDTO);
            //Retorna respuesta exitosa, es decir un 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>("success", "Especie creada exitosamente", createdSpecie)
            );
        } catch (RuntimeException e) {
            //Si hay error de validación, como un nombre duplicado, retornará un 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse<>("error", e.getMessage(), null)
            );
        } catch (Exception e) {
            //Otros errores que retornan un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse<>("error", "Error al crear la especie: " + e.getMessage(), null)
            );
        }
    }

    /**
     * Actualiza una especie existente (solo para ADMIN).
     * Endpoint: PUT /species/{id}
     * @param id de la especie a actualizar
     * @param specieInDTO nuevos datos de la especie
     * @return ResponseEntity con ApiResponse conteniendo la especie actualizada
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SpecieOutDTO>> updateSpecie(
            @PathVariable Long id,
            @RequestBody SpecieInDTO specieInDTO)
    {
        try
        {
            //Actualiza la especie
            SpecieOutDTO updatedSpecie = specieService.updateSpecie(id, specieInDTO);
            //Retorna respuesta exitosa con los datos actualizados
            return ResponseEntity.ok(
                new ApiResponse<>("success", "Especie actualizada exitosamente", updatedSpecie)
            );
        } catch (RuntimeException e) {
            //Si no se encuentra la especie o falla la validación, retorna un 404-400
            HttpStatus status = e.getMessage().contains("no encontrada") ? 
                HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(status).body(
                new ApiResponse<>("error", e.getMessage(), null)
            );
        } catch (Exception e) {
            //Otros errores que retornan un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse<>("error", "Error al actualizar la especie: " + e.getMessage(), null)
            );
        }
    }

    /**
     * Elimina una especie y se eliminan en cascada todos sus genomas (solo para ADMIN).
     * Endpoint: DELETE /species/{id}
     * @param id de la especie a eliminar
     * @return ResponseEntity con ApiResponse indicando el resultado
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteSpecie(@PathVariable Long id)
    {
        try 
        {
            //Elimina la especie
            specieService.deleteSpecie(id);
            //Retorna un 204 y respuesta exitosa
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>("success", "Especie eliminada exitosamente", null)
            );
        } catch (RuntimeException e) {
            //Si no se encuentra la especie, retorna un 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>("error", e.getMessage(), null)
            );
        } catch (Exception e) {
            //Otros errores que retornan un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse<>("error", "Error al eliminar la especie: " + e.getMessage(), null)
            );
        }
    }
}
