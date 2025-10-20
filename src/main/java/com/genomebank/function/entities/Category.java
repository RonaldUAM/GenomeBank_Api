package com.genomebank.function.entities;

import java.util.Arrays;
import java.util.Optional;

public enum Category {
    BP("bp"),
    MF("mf"),
    CC("cc");

    private final String value;

    // Constructor privado para asociar el valor de la base de datos
    Category(String value) {
        this.value = value;
    }

    // Obtiene el valor que se almacenará en la base de datos
    public String getValue() {
        return value;
    }

    // Método estático para buscar un Category por su valor en la base de datos
    public static Optional<Category> fromValue(String value) {
        return Arrays.stream(Category.values())
                .filter(category -> category.value.equalsIgnoreCase(value))
                .findFirst();
    }

    // Método toString para devolver el valor legible
    @Override
    public String toString() {
        return value;
    }

}
