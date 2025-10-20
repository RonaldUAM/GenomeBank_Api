package com.breaze.genomebank.specie.application.dto;

import com.breaze.genomebank.genome.application.dto.GenomeOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Dto de salida para representar una especie y para enviar información al cliente.
 * Solo contiene la información necesaria para el cliente, 
 * sin exponer otros detalles internos de la entidad 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecieOutDTO 
{    
    /**
     * id único de la especie
     */
    private Long id;
    
    /**
     * Nombre científico de la especie
     */
    private String scientificName;
    
    /**
     * Nombre común de la especie
     */
    private String commonName;
    
    /**
     * Cantidad de genomas asociados a la especie
     */
    private Integer genomeCount;
    private List<GenomeOutDto> genomes;
}