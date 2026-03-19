package com.ecommerce.proyecto.dominio.enums;

public enum TipoDocumento {

    CEDULA("cedula"),
    TARGETA_IDENTIDAD("targeta identidad");

    private final String descripcion;

    TipoDocumento(String descripcion) {
        this.descripcion = descripcion;
    }
}
