package com.ecommerce.proyecto.dominio.dtos.peticiones;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardarSolicitudDto {

    private String motivoDevolucion;
    private String motivoCancelacion;
    private String referencia;

}
