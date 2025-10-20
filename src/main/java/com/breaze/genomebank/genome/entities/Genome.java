package com.breaze.genomebank.genome.entities;


import com.breaze.genomebank.chromosome.entities.Chromosome;
import com.breaze.genomebank.specie.entities.Specie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private Integer id;

    @OneToMany(mappedBy = "genome", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chromosome> chromosomes = new ArrayList<>();

    // Relación inversa: un genoma pertenece a una especie
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_specie", nullable = false)
    private Specie specie;
}
