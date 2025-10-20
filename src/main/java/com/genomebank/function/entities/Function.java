package com.genomebank.function.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "biological_function")
@NoArgsConstructor
@AllArgsConstructor
public class Function {
    @Id
    @Column(name = "codigo")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
}
