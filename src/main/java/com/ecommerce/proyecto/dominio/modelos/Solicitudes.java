package com.ecommerce.proyecto.dominio.modelos;

import com.ecommerce.proyecto.dominio.enums.MotivosCancelacion;
import com.ecommerce.proyecto.dominio.enums.MotivosDevolucion;
import com.ecommerce.proyecto.dominio.enums.TiposSolicitud;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Solicitudes {
    private UUID id;
    private LocalDateTime fecha;
    private TiposSolicitud tipo;
    private MotivosDevolucion motivooDevolucion;
    private MotivosCancelacion motivoCancelacion;
    private Usuarios idCliente;
    private Ordenes idOrden;
}
