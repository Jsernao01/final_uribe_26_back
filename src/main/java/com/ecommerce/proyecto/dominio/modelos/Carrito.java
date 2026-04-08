package com.ecommerce.proyecto.dominio.modelos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Carrito {
    private UUID id;
    private Ordenes referencia;
    private Integer precioParcial;
    private Integer cantidad;
    private Stock stock;
}
