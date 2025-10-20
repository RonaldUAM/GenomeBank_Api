package com.breaze.genomebank.chromosome.entities;

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Integer size;

    @Column(name = "sequence_adn")
    private String sequenceAdn;

    // Relación inversa: un cromosoma pertenece a un genoma
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genome", nullable = false)
    private Genome genome;

    // Genes en este cromosoma
    @OneToMany(mappedBy = "chromosome")
    private List<Gene> genes = new ArrayList<>();
}
