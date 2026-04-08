package com.ecommerce.proyecto.dominio.dtos.respuesta;

import com.ecommerce.proyecto.dominio.modelos.Ordenes;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoDto {

    private UUID id;
    private String referencia;
    private Integer precioParcial;
    private Integer cantidad;

}
