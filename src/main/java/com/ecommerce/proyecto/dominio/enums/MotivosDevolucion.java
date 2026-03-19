package com.ecommerce.proyecto.dominio.enums;

import lombok.Getter;

@Getter
public enum MotivosDevolucion {

    TALLA_INCORRECTA("talla incorrecta"),
    PRENDA_DAÑADA("prenda dañada"),
    OTRO("otro");

    private final String descripcion;

    MotivosDevolucion(String descripcion) {
        this.descripcion = descripcion;
    }
}
