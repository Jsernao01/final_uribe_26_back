package com.ecommerce.proyecto.dominio.dtos.respuesta;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDto {

    private UUID id;
    private String nombreProducto;
    private String talla;
    private Integer cantidad;
    private String color;
}
