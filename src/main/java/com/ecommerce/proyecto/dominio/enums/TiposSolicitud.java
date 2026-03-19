package com.ecommerce.proyecto.dominio.enums;

import lombok.Getter;

@Getter
public enum TiposSolicitud {
    CANCELACION("cancelacion"),
    DEVOLUCION("devolucion"),
    CONTINUACION("continuacion");

    private final String descripcion;

    TiposSolicitud(String descripcion) {
        this.descripcion = descripcion;
    }
}
