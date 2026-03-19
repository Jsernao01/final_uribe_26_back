package com.ecommerce.proyecto.dominio.enums;

public enum Roles {

    CLIENTE("cliente"),
    VENDEDOR("vendedor"),
    ADMINISTRADOR("administrador"),
    PROGRAMADOR("programador");

    private final String descripcion;

    Roles(String descripcion) {
        this.descripcion = descripcion;
    }
}
