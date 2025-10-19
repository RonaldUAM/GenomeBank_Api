package com.breaze.genomebank.specie.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto de entrada para crear o actualizar una especie.
 * Este se utiliza para recibir datos desde el cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecieInDTO 
{    
    /**
     * Nombre científico de la especie (obligatorio).
     * Ejemplo: "Homo sapiens", "Drosophila melanogaster"
     */
    private String scientificName;
    
    /**
     * Nombre común de la especie (opcional).
     * Ejemplo: "Humano", "Mosca de la fruta"
     */
    private String commonName;
}
