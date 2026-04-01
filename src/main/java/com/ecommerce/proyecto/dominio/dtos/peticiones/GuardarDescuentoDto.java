package com.ecommerce.proyecto.dominio.dtos.peticiones;

import com.ecommerce.proyecto.dominio.enums.DescuentoObjetivo;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardarDescuentoDto {

    private Integer descuento;
    private String tipoDescuento;
    private String razon;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private DescuentoObjetivo objetivo;
    private UUID idObjetivo;

}
