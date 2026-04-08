package com.ecommerce.proyecto.dominio.enums;

import lombok.Getter;

@Getter
public enum MotivosDevolucion {

    TALLA_INCORRECTA("talla_incorrecta"),
    PRENDA_ROTA("prenda_rota"),
    OTRO("otro");

    private final String descripcion;

    MotivosDevolucion(String descripcion) {
        this.descripcion = descripcion;
    }
}
