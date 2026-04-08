package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiltrosProductosDto {

    private String nombre;
    private Integer minPrecio;
    private Integer maxPrecio;
    private List<String> categorias;
    private Integer descuento;
    private String color;

}
