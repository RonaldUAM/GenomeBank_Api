package com.breaze.genomebank.chromosome.entities;

/**
 * Entidad que representa un cromosoma en la base de datos,
 * con relaciones a entidades Genome y Gene.
 * @author nmedinaa
 * @version 1.0.0
 * @since 2025-10-20
 */
import com.breaze.genomebank.gene.entities.Gene;
import com.breaze.genomebank.genome.entities.Genome;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "chromosome")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Chromosome {
    /**
     * ID primario, generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Nombre del cromosoma (e.g., "2L").
     */
    @Column(name = "name")
    private String name;

    /**
     * Longitud de la secuencia de ADN.
     */
    @Column(name = "size")
    private Integer size;

    /**
     * Secuencia de ADN (e.g., "ATCG").
     */
    @Column(name = "sequence_adn")
    private String sequenceAdn;

    /**
     * Relación muchos-a-uno con la entidad Genome.
     * FetchType.LAZY para optimizar el rendimiento.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genome", nullable = false)
    private Genome genome;

    /**
     * Relación uno-a-muchos con la entidad Gene.
     * Inicializada como ArrayList para evitar null pointers.
     */
    @OneToMany(mappedBy = "chromosome")
    private List<Gene> genes = new ArrayList<>();
}