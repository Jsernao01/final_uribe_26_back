package com.ecommerce.proyecto.dominio.modelos;

import com.ecommerce.proyecto.dominio.enums.EstadoSolicitud;
import com.ecommerce.proyecto.dominio.enums.MotivosCancelacion;
import com.ecommerce.proyecto.dominio.enums.MotivosDevolucion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Solicitudes {

    private UUID id;
    private LocalDateTime fecha;
    private EstadoSolicitud estadoSolicitud;
    private MotivosDevolucion motivoDevolucion;
    private MotivosCancelacion motivoCancelacion;
    private Ordenes orden;
}
