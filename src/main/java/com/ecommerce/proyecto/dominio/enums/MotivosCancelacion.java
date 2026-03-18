package com.ecommerce.proyecto.dominio.enums;

public enum MotivosCancelacion {

    ERROR_PEDIDO("error pedido"),
    ENTREGA_TARDADA("entrega tardada"),
    OTRO("otro");

    private final String descripcion;

    MotivosCancelacion(String descripcion) {
        this.descripcion = descripcion;
    }

}
