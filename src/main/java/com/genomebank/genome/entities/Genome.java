package com.genomebank.genome.entities;


import com.genomebank.chromosome.entities.Chromosome;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "genome")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Genome {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany
    //nullable: No puede estar vacio
    @JoinColumn(name = "id_genome", nullable = false)
    private List<Chromosome> chromosomes;


}
