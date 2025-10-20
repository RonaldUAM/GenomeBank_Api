package com.genomebank.genome.infraestructure.dto;


import java.util.List;

/**
 * Dto de entrada para crear o actualizar un Genoma.
 * Este se utiliza para recibir datos desde el cliente.
 */
public class GenomeInDto {
    /**
     * Para Crear el genomas se debe pedir los ids de los cromosomas existentes
     */
    List<Integer> chromosomes;
}