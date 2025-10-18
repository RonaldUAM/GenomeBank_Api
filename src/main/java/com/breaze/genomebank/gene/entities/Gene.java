package com.breaze.genomebank.gene.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "gene")
@NoArgsConstructor
@AllArgsConstructor
public class Gene {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

}
