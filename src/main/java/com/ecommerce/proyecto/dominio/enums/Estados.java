package com.ecommerce.proyecto.dominio.enums;

public enum Estados {

    CREADA("creada"),
    ENTREGADA("entregada"),
    CANCELADA("cancelada"),
    REGRESADA("regresada");

    private final String descripcion;

    Estados(String descripcion) {
        this.descripcion = descripcion;
    }

}
