package com.ecommerce.proyecto.dominio.enums;

import lombok.Getter;

@Getter
public enum TiposCuentas {
    DEBITO("debito"),
    CREDITO("credito");

    private final String descripcion;

    TiposCuentas(String descripcion) {
        this.descripcion = descripcion;
    }
}
