package com.breaze.genomebank.chromosome.application.service;

/**
 * Implementación del servicio para operaciones de cromosomas.
 * Maneja la lógica de negocio, incluyendo CRUD, gestión de secuencias y asociaciones de genes.
 * @author nmedinaa
 * @version 1.0.0
 * @since 2025-10-20
 */
import com.breaze.genomebank.chromosome.application.dto.ChromosomeInDto;
import com.breaze.genomebank.chromosome.application.dto.ChromosomeOutDto;
import com.breaze.genomebank.chromosome.application.dto.RangeOutDto;
import com.breaze.genomebank.chromosome.application.dto.SequenceInDto;
import com.breaze.genomebank.chromosome.application.dto.SequenceOutDto;
import com.breaze.genomebank.chromosome.application.mappers.ChromosomeMapper;
import com.breaze.genomebank.chromosome.application.ports.IChromosomeService;
import com.breaze.genomebank.chromosome.entities.Chromosome;
import com.breaze.genomebank.chromosome.infraestructure.repository.IChromosomeRepository;
import com.breaze.genomebank.gene.entities.Gene;
import com.breaze.genomebank.gene.infraestructure.repository.IGeneRepository;
import com.breaze.genomebank.genome.entities.Genome;
import com.breaze.genomebank.genome.infraestructure.repository.IGenomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChromosomeService implements IChromosomeService {

    /**
     * Repositorio para operaciones de cromosomas.
     */
    private IChromosomeRepository chromosomeRepository;

    /**
     * Repositorio para operaciones de genomas.
     */
    private IGenomeRepository genomeRepository;

    /**
     * Repositorio para operaciones de genes.
     */
    private IGeneRepository geneRepository;

    /**
     * Mapper para transformar entre entidades y DTOs de cromosomas.
     */
    private ChromosomeMapper chromosomeMapper;

    @Override
    public List<ChromosomeOutDto> findAllChromosomeOrByGenomeId(Integer genomeId) {
        /**
         * Recupera una lista de cromosomas, filtrada por ID de genoma si se proporciona.
         * @param genomeId ID del genoma para filtrar (opcional, null para todos).
         * @return Lista de DTOs de cromosomas.
         */
        List<Chromosome> chromosomes;
        if (genomeId != null) {
            chromosomes = chromosomeRepository.findByGenomeId(genomeId);
        } else {
            chromosomes = chromosomeRepository.findAll();
        }
        return chromosomes.stream().map(chromosomeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ChromosomeOutDto findById(Integer id) {
        /**
         * Recupera un cromosoma por su ID.
         * @param id ID del cromosoma a buscar.
         * @return DTO del cromosoma encontrado.
         * @throws RuntimeException si el cromosoma no se encuentra.
         */
        Chromosome chromosome = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));
        return chromosomeMapper.toDto(chromosome);
    }

    @Override
    @Transactional
    public ChromosomeOutDto create(ChromosomeInDto dto) {
        /**
         * Crea un nuevo cromosoma con genes asociados.
         * @param dto DTO con los datos del cromosoma a crear.
         * @return DTO del cromosoma creado.
         * @throws RuntimeException si el genoma no existe o hay errores de validación.
         */
        validateSizeAndSequence(dto.getSize(), dto.getSequenceAdn());
        Genome genome = genomeRepository.findById(dto.getGenomeId())
                .orElseThrow(() -> new RuntimeException("Genoma no encontrado"));
        Chromosome chromosome = chromosomeMapper.toEntity(dto);
        chromosome.setGenome(genome);
        chromosome = chromosomeRepository.save(chromosome); // Persistir primero el cromosoma

        // Asignar genes existentes
        if (dto.getGenesId() != null && !dto.getGenesId().isEmpty()) {
            List<Gene> genes = geneRepository.findAllById(dto.getGenesId());
            final Chromosome finalChromosome = chromosome; // Hacer una copia final
            genes.forEach(gene -> {
                gene.setChromosome(finalChromosome); // Usar la copia final
                geneRepository.save(gene); // Persistir cada gen
            });
            chromosome.setGenes(genes); // Actualiza la lista en Chromosome
            chromosomeRepository.save(chromosome); // Actualizar el cromosoma
        }

        return chromosomeMapper.toDto(chromosome);
    }

    @Override
    @Transactional
    public ChromosomeOutDto update(Integer id, ChromosomeInDto dto) {
        /**
         * Actualiza un cromosoma existente.
         * @param id ID del cromosoma a actualizar.
         * @param dto DTO con los datos actualizados.
         * @return DTO del cromosoma actualizado.
         * @throws RuntimeException si el cromosoma no se encuentra.
         */
        Chromosome chromosome = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));

        if (dto.getName() != null) chromosome.setName(dto.getName());
        if (dto.getSize() != null && dto.getSequenceAdn() != null) {
            validateSizeAndSequence(dto.getSize(), dto.getSequenceAdn());
            chromosome.setSize(dto.getSize());
            chromosome.setSequenceAdn(dto.getSequenceAdn());
        }
        if (dto.getGenomeId() != null) {
            Genome genome = genomeRepository.findById(dto.getGenomeId())
                    .orElseThrow(() -> new RuntimeException("Genoma no encontrado"));
            chromosome.setGenome(genome);
        }

        // Actualizar genes si se proporcionan
        if (dto.getGenesId() != null) {
            // Limpiar genes actuales (opcional, según lógica de negocio)
            chromosome.getGenes().forEach(gene -> gene.setChromosome(null));
            geneRepository.saveAll(chromosome.getGenes()); // Desasociar genes antiguos

            List<Gene> newGenes = geneRepository.findAllById(dto.getGenesId());
            final Chromosome finalChromosome = chromosome; // Hacer una copia final
            newGenes.forEach(gene -> {
                gene.setChromosome(finalChromosome); // Usar la copia final
                geneRepository.save(gene); // Persistir cada gen
            });
            chromosome.setGenes(newGenes); // Actualizar lista en Chromosome
            chromosomeRepository.save(chromosome); // Actualizar el cromosoma
        } else {
            chromosomeRepository.save(chromosome); // Guardar cambios sin modificar genes
        }

        return chromosomeMapper.toDto(chromosome);
    }

    @Override
    public void delete(Integer id) {
        /**
         * Elimina un cromosoma por su ID.
         * @param id ID del cromosoma a eliminar.
         * @throws RuntimeException si el cromosoma no se encuentra.
         */
        Chromosome chromosome = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));
        chromosomeRepository.delete(chromosome);
    }

    @Override
    public SequenceOutDto getSequence(Integer id) {
        /**
         * Recupera la secuencia de ADN completa de un cromosoma.
         * @param id ID del cromosoma.
         * @return DTO con la secuencia de ADN.
         * @throws RuntimeException si el cromosoma no se encuentra.
         */
        Chromosome chromosome = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));
        SequenceOutDto outDto = new SequenceOutDto();
        outDto.setSequenceAdn(chromosome.getSequenceAdn());
        return outDto;
    }

    @Override
    public RangeOutDto getSequenceRange(Integer id, Integer start, Integer end) {
        /**
         * Recupera una subsecuencia de la secuencia de ADN de un cromosoma.
         * @param id ID del cromosoma.
         * @param start Posición inicial (1-based).
         * @param end Posición final.
         * @return DTO con la subsecuencia de ADN.
         * @throws RuntimeException si el rango es inválido o el cromosoma no se encuentra.
         */
        Chromosome chromosome = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));
        String sequence = chromosome.getSequenceAdn();
        if (start < 1 || end > sequence.length() || start > end) {
            throw new RuntimeException("Rango inválido");
        }
        String subSequence = sequence.substring(start - 1, end); // 1-based
        RangeOutDto outDto = new RangeOutDto();
        outDto.setSubSequenceAdn(subSequence);
        return outDto;
    }

    @Override
    @Transactional
    public SequenceOutDto updateSequence(Integer id, SequenceInDto dto) {
        /**
         * Actualiza la secuencia de ADN de un cromosoma.
         * @param id ID del cromosoma.
         * @param dto DTO con la nueva secuencia.
         * @return DTO con la secuencia actualizada.
         * @throws RuntimeException si el cromosoma no se encuentra.
         */
        Chromosome chromosome = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));
        String newSequence = dto.getSequenceAdn();
        chromosome.setSequenceAdn(newSequence);
        chromosome.setSize(newSequence.length());
        chromosome = chromosomeRepository.save(chromosome);
        SequenceOutDto outDto = new SequenceOutDto();
        outDto.setSequenceAdn(chromosome.getSequenceAdn());
        return outDto;
    }

    /**
     * Valida que el tamaño coincida con la longitud de la secuencia.
     * @param size Tamaño de la secuencia.
     * @param sequence Secuencia de ADN.
     * @throws RuntimeException si el tamaño no coincide con la longitud.
     */
    private void validateSizeAndSequence(Integer size, String sequence) {
        if (size == null || sequence == null || size != sequence.length()) {
            throw new RuntimeException("El tamaño debe coincidir con la longitud de la secuencia");
        }
    }
}