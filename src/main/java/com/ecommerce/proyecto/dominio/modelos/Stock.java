package com.ecommerce.proyecto.dominio.modelos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Stock {
    private UUID id;
    private String talla;
    private Integer cantidad;
    private String color;
    private Productos producto;

}
