package com.breaze.genomebank.gene_function.entities;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum EvidenceLink {
    experimental("experimental"),
    computational("computational"),
    predicted("predicted");

    // Obtiene el valor que se almacenará en la base de datos
    private final String value;

    // Constructor privado para asociar el valor de la base de datos
    EvidenceLink(String value) {
        this.value = value;
    }

    // Método estático para buscar un Category por su valor en la base de datos
    public static Optional<EvidenceLink> fromValue(String value) {
        return Arrays.stream(EvidenceLink.values())
                .filter(evidenceLink -> evidenceLink.value.equalsIgnoreCase(value))
                .findFirst();
    }

    // Método toString para devolver el valor legible
    @Override
    public String toString() {
        return value;
    }
}
