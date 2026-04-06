package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.respuesta.SolicitudesDto;
import com.ecommerce.proyecto.dominio.modelos.Solicitudes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SolicitudesMapper {

    @Mapping(target = "estadoSolicitud", ignore = true)
    @Mapping(target = "motivoDevolucion", ignore = true)
    @Mapping(target = "motivoCancelacion", ignore = true)
    @Mapping(target = "referencia", ignore = true)
    SolicitudesDto deResponseSolicitudes(Solicitudes solicitud);

}
