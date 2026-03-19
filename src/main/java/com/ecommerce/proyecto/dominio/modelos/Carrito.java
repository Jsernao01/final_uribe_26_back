package com.ecommerce.proyecto.dominio.modelos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Carrito {
    private UUID id;
    private Ordenes referencia;
    private Integer precioParcial;
    private Integer cantidad;
    private Productos producto;
}
