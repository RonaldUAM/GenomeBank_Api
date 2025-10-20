package com.breaze.genomebank.gene_function.entities;

import com.breaze.genomebank.chromosome.entities.Chromosome;
import com.breaze.genomebank.function.entities.Function;
import com.breaze.genomebank.gene.entities.Gene;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "gen_function")
@NoArgsConstructor
@AllArgsConstructor
public class GeneFunction {
    @Id
    @Column(name = "codigo_funcion")
    private String codigoFunction;

    @Id
    @Column(name = "id_gene")
    private Integer idGene;

    // Relación con Function
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_funcion", nullable = false)
    private Function function;

    // Relación con Gene
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gene", nullable = false)
    private Gene gene;


    @Column(name = "evidence_link", nullable = false)
    @Enumerated(EnumType.STRING)
    private EvidenceLink evidenceLink;
}
