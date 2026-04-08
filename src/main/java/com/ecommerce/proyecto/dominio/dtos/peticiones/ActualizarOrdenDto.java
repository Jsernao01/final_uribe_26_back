package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarOrdenDto {

    private String referencia;
    private String estado;

}
