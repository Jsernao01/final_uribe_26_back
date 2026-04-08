package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardarCategoriaDto {

    private UUID idProducto;
    private List<String> caracteristicas;

}
