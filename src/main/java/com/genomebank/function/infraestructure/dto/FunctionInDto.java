package com.breaze.genomebank.function.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto de entrada para crear o actualizar un Funcion Biologica.
 * Este se utiliza para recibir datos desde el cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FunctionInDto {
    private String code;
    private String name;
    private String category;
}