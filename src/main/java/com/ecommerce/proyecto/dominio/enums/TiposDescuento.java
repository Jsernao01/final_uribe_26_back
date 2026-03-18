package com.ecommerce.proyecto.dominio.enums;

public enum TiposDescuento {

    TEMPORADA("temporada"),
    UNICO("unico");

    private final String descripcion;

    TiposDescuento(String descripcion) {
        this.descripcion = descripcion;
    }
}
