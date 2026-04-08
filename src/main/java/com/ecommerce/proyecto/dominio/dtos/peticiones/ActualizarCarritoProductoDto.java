package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarCarritoProductoDto {
    private UUID id;
    private Integer cantidad;
}
