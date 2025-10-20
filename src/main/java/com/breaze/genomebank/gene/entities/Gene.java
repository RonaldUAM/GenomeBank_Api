package com.breaze.genomebank.gene.entities;

import com.breaze.genomebank.chromosome.entities.Chromosome;
import com.breaze.genomebank.function.entities.Function;
import com.breaze.genomebank.gene_function.entities.GeneFunction;
import com.breaze.genomebank.genome.entities.Genome;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "gene")
@NoArgsConstructor
@AllArgsConstructor
public class Gene {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "start_position")
    private String startPosition;

    @Column(name = "end_position")
    private String endPosition;

    @Column(name = "orientation")// True significa el simbolo + y false el signo -
    private Boolean orientation;

    @Column(name = "sequence_adn")
    private String sequenceAdn;

    // Relación con cromosoma
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chromosome", nullable = false)
    private Chromosome chromosome;

    // Funciones del gen
    @OneToMany(mappedBy = "gene")
    private List<GeneFunction> functions = new ArrayList<>();

}
