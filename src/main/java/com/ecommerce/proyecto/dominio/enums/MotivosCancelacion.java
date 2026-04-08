package com.ecommerce.proyecto.dominio.enums;

import lombok.Getter;

@Getter
public enum MotivosCancelacion {

    ERROR_PEDIDO("error_pedido"),
    ENTREGA_TARDADA("entrega_tardada"),
    OTRO("otro");

    private final String descripcion;

    MotivosCancelacion(String descripcion) {
        this.descripcion = descripcion;
    }

}
