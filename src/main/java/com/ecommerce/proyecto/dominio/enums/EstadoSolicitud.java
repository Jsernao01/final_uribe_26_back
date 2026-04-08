package com.ecommerce.proyecto.dominio.enums;

import lombok.Getter;

@Getter
public enum EstadoSolicitud {
    CREADA("creada"),
    FINALIZADA("finalizada"),
    EN_ESPERA("en_espera");

    private final String descripcion;

    EstadoSolicitud(String descripcion) {
        this.descripcion = descripcion;
    }
}
