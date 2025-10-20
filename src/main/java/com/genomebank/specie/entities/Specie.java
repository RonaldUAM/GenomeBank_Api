package com.genomebank.specie.entities;


import com.genomebank.genome.entities.Genome;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;

    @Column(name = "scientific_name")
    private String scientificName;

    @Column(name = "common_name")
    private String commonName;

    @OneToMany
    @JoinColumn(name = "id_specie", nullable = false)
    private List<Genome> genomes;
}
