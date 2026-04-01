package com.ecommerce.proyecto.dominio.enums;

import lombok.Getter;

@Getter
public enum DescuentoObjetivo {

    CLIENTE("cliente"),
    PRENDA("prenda");

    private final String descripcion;

    DescuentoObjetivo(String descripcion) {
        this.descripcion = descripcion;
    }

}
