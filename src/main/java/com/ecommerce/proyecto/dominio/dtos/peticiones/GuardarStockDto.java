package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardarStockDto {

    private String talla;
    private Integer cantidad;
    private String color;
}
