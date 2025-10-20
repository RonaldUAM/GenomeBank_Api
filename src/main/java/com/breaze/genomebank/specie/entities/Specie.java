package com.breaze.genomebank.specie.entities;


import com.breaze.genomebank.chromosome.entities.Chromosome;
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
@Table(name = "specie")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Specie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "scientific_name")
    private String scientificName;

    @Column(name = "common_name")
    private String commonName;

    @OneToMany(mappedBy = "specie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Genome> genomes = new ArrayList<>();
}
