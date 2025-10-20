package com.genomebank.function.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto de salida de una Funcion Biologica.
 * Este se utiliza para mostrar los datos al cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FunctionOutDto {
    private String code;
    private String name;
    private String category;
}