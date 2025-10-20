package com.breaze.genomebank.chromosome.application.mappers;

/**
 * Clase utilitaria para mapear entre entidades y DTOs de cromosomas.
 * Maneja la transformación de datos, incluyendo mapeos anidados de genes.
 * @author nmedinaa
 * @version 1.0.0
 * @since 2025-10-20
 */
import com.breaze.genomebank.chromosome.application.dto.ChromosomeInDto;
import com.breaze.genomebank.chromosome.application.dto.ChromosomeOutDto;
import com.breaze.genomebank.chromosome.entities.Chromosome;
import com.breaze.genomebank.gene.application.dto.GeneOutDto;
import com.breaze.genomebank.gene.application.mappers.GeneMapper;
import com.breaze.genomebank.gene.entities.Gene;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ChromosomeMapper {
    /**
     * Mapper de genes para transformar entidades de genes en DTOs.
     */
    private GeneMapper geneMapper;

    /**
     * Convierte una entidad Chromosome en un DTO ChromosomeOutDto.
     * Incluye la transformación de la lista de genes asociados.
     * @param chromosome Entidad Chromosome a mapear.
     * @return ChromosomeOutDto resultado del mapeo.
     */
    public ChromosomeOutDto toDto(Chromosome chromosome) {
        return ChromosomeOutDto.builder()
                .name(chromosome.getName())
                .size(chromosome.getSize())
                .sequenceAdn(chromosome.getSequenceAdn())
                .genes(getListGeneOutDto(chromosome.getGenes()))
                .build();
    }

    /**
     * Convierte una lista de entidades Gene en una lista de DTOs GeneOutDto.
     * @param genes Lista de entidades Gene a mapear.
     * @return Lista de GeneOutDto.
     */
    private List<GeneOutDto> getListGeneOutDto(List<Gene> genes) {
        return genes.stream().map(geneMapper::toGeneOutDto).toList();
    }

    /**
     * Convierte un DTO ChromosomeInDto en una entidad Chromosome.
     * Excluye las asociaciones de genoma y genes, que se manejan en el servicio.
     * @param dto DTO ChromosomeInDto a mapear.
     * @return Entidad Chromosome resultante.
     */
    public Chromosome toEntity(ChromosomeInDto dto) {
        return Chromosome.builder()
                .name(dto.getName())
                .size(dto.getSize())
                .sequenceAdn(dto.getSequenceAdn())
                .build(); // Genome y genes se setean en service
    }
}