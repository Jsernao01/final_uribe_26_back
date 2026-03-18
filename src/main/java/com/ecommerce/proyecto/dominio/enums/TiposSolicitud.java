package com.ecommerce.proyecto.dominio.enums;

public enum TiposSolicitud {
    CANCELACION("cancelacion"),
    DEVOLUCION("devolucion"),
    CONTINUACION("continuacion");

    private final String descripcion;

    TiposSolicitud(String descripcion) {
        this.descripcion = descripcion;
    }
}
