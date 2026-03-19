package com.ecommerce.proyecto.dominio.enums;

import lombok.Getter;

@Getter
public enum Caracteristicas {
    PANTALON("pantalon"),
    CAMISA("camisa"),
    CHAQUETA("chaqueta"),
    JEAN("jean");

    private final String descripcion;

    Caracteristicas(String descripcion) {
        this.descripcion = descripcion;
    }
}
