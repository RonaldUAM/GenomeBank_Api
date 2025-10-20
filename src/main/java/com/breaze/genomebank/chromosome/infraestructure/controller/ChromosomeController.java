package com.breaze.genomebank.chromosome.infraestructure.controller;

import com.breaze.genomebank.chromosome.application.dto.ChromosomeOutDto;
import com.breaze.genomebank.chromosome.application.ports.IChromosomeService;
import com.breaze.genomebank.common.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chromosomes")
@AllArgsConstructor
public class ChromosomeController {
    private IChromosomeService chromosomeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('User', 'Admin')")
    public ResponseEntity<ApiResponse<List<ChromosomeOutDto>>> getAllChromosomes() {
        List<ChromosomeOutDto> dtos = chromosomeService.findAllChromosomeOrByGenomeId(null);
        return buildResponse(dtos);
    }

    @GetMapping("/{genomeId}")
    @PreAuthorize("hasAnyRole('User', 'Admin')")
    public ResponseEntity<ApiResponse<List<ChromosomeOutDto>>> getChromosomesByGenomeId(@PathVariable Integer genomeId) {
        List<ChromosomeOutDto> dtos = chromosomeService.findAllChromosomeOrByGenomeId(genomeId);
        return buildResponse(dtos);
    }

    private ResponseEntity<ApiResponse<List<ChromosomeOutDto>>> buildResponse(List<ChromosomeOutDto> dtos) {
        return new ResponseEntity<>(
                dtos.isEmpty()
                        ? new ApiResponse<>("Not found", "No se encontraron datos relacionados", null)
                        : new ApiResponse<>("Success", "Datos encontrados con éxito", dtos),
                dtos.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }
}
