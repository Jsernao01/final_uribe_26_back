package com.ecommerce.proyecto.dominio.dtos.peticiones;

import com.ecommerce.proyecto.dominio.modelos.Ordenes;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardarCarritoDto {

    private String referencia;
    private Integer precioParcial;
    private Integer cantidad;
    private UUID producto;

}
