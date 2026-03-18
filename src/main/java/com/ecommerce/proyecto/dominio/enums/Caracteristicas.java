package com.ecommerce.proyecto.dominio.enums;

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
