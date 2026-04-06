package com.ecommerce.proyecto.dominio.dtos.respuesta;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudesDto {

    private UUID id;
    private LocalDateTime fecha;
    private String estadoSolicitud;
    private String motivoDevolucion;
    private String motivoCancelacion;
    private String referencia;

}
