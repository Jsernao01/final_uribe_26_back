package com.ecommerce.proyecto.dominio.dtos.respuesta;

import com.ecommerce.proyecto.dominio.enums.Caracteristicas;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDto {

    private UUID id;
    private String caracteristica;
    private String producto;

}
