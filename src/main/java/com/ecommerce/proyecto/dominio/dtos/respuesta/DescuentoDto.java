package com.ecommerce.proyecto.dominio.dtos.respuesta;

import com.ecommerce.proyecto.dominio.enums.TiposDescuento;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DescuentoDto {

    private UUID id;
    private Integer descuento;
    private String tipoDescuento;
    private Boolean activo;
    private String razon;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private String objetivo;
    private String nombreObjeivo;

}
