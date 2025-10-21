package com.breaze.genomebank.genome.infraestructure.controller;

import com.breaze.genomebank.common.ApiResponse;
import com.breaze.genomebank.genome.application.dto.GenomeInDto;
import com.breaze.genomebank.genome.application.dto.GenomeOutDto;
import com.breaze.genomebank.genome.application.ports.IGenomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/genomes")
@AllArgsConstructor
public class GenomeController {
    private IGenomeService genomeService;


    @GetMapping("/{specieId}")
    @PreAuthorize("hasAnyRole('User', 'Admin')")
    public ResponseEntity<ApiResponse<List<GenomeOutDto>>> getGenomeBySpecieId(@PathVariable Integer specieId) {
        List<GenomeOutDto> dtos = genomeService.findAllGenomeOrByGenomeId(specieId);
        return buildResponse(dtos);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('User', 'Admin')")
    public ResponseEntity<ApiResponse<List<GenomeOutDto>>> getAllGenome() {
        List<GenomeOutDto> dtos = genomeService.findAllGenomeOrByGenomeId(null);
        return buildResponse(dtos);
    }



    private ResponseEntity<ApiResponse<List<GenomeOutDto>>> buildResponse(List<GenomeOutDto> dtos) {
        return new ResponseEntity<>(
                dtos.isEmpty()
                        ? new ApiResponse<>("Not found", "No se encontraron datos relacionados", null)
                        : new ApiResponse<>("Success", "Datos encontrados con éxito", dtos),
                dtos.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }

    @GetMapping("/{genomeId}")
    @PreAuthorize("hasAnyRole('User', 'Admin')")
    private ResponseEntity<ApiResponse<GenomeOutDto>> getGenomeById(@PathVariable Integer genomeId){
        try
        {
            //Busca la especie por id
            GenomeOutDto genome = genomeService.getGenomeById(genomeId);
            //Retorna respuesta exitosa junto con los datos
            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Genoma encontrado", genome)
            );
        } catch (RuntimeException e) {
            //Si no se encuentra la genoma, retorna un 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("error", e.getMessage(), null)
            );
        } catch (Exception e) {
            //Los demás errores que retornan un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>("error", "Error al obtener el Genoma: " + e.getMessage(), null)
            );
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<ApiResponse<GenomeOutDto>> createGenome(@RequestBody GenomeInDto genome){
        try
        {
            //Crea nueva genoma
            GenomeOutDto genomeOutDto = genomeService.createGenome(genome);
            //Retorna respuesta exitosa, es decir un 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("success", "Genoma creada exitosamente", genomeOutDto)
            );
        } catch (RuntimeException e) {
            //Si hay error de validación, como un nombre duplicado, retornará un 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>("error", e.getMessage(), null)
            );
        } catch (Exception e) {
            //Otros errores que retornan un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>("error", "Error al crear la genoma: " + e.getMessage(), null)
            );
        }
    }

    @DeleteMapping("/{genomeId}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Integer genomeId){
        try
        {
            genomeService.deleteGenomeById(genomeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new ApiResponse<>("success", "Genoma eliminado exitosamente", null)
            );
        } catch (RuntimeException e) {
            //Si no se encuentra la especie, retorna un 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>("error", e.getMessage(), null)
            );
        } catch (Exception e) {
            //Otros errores que retornan un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>("error", "Error al eliminar el genoma: " + e.getMessage(), null)
            );
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<GenomeOutDto>> updateSpecie(
            @PathVariable Integer id,
            @RequestBody GenomeInDto genomeInDto)
    {
        try
        {
            GenomeOutDto updatedSOutDto = genomeService.updateGenome(id, genomeInDto);
            //Retorna respuesta exitosa con los datos actualizados
            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Genoma actualizada exitosamente", updatedSOutDto)
            );
        } catch (RuntimeException e) {
            HttpStatus status = e.getMessage().contains("no encontrado") ?
                    HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(status).body(
                    new ApiResponse<>("error", e.getMessage(), null)
            );
        } catch (Exception e) {
            //Otros errores que retornan un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>("error", "Error al actualizar el Genoma: " + e.getMessage(), null)
            );
        }
    }
}
