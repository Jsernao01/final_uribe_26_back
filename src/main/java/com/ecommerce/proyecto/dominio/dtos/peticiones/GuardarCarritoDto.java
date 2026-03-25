package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardarCarritoDto {

    private Integer cantidad;
    private UUID producto;

}
