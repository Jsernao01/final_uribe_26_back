package com.ecommerce.proyecto.dominio.modelos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Productos {
    private UUID id;
    private String nombre;
    private LocalDateTime lanzamiento;
    private Integer precio;
    private Boolean activo;
}
