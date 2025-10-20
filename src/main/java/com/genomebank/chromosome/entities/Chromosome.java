package com.genomebank.chromosome.entities;

import com.genomebank.gene.entities.Gene;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Integer size;

    @Column(name = "sequence_adn")
    private String sequenceAdn;

    @OneToMany
    @JoinColumn(name = "id_chromosome", nullable = false)
    private List<Gene> genes;
}
