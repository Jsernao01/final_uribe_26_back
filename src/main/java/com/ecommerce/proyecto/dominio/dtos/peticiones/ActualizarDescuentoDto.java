package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarDescuentoDto {

    private UUID id;
    private Integer descuento;
    private String razon;
    private LocalDateTime inicio;
    private LocalDateTime fin;
}
