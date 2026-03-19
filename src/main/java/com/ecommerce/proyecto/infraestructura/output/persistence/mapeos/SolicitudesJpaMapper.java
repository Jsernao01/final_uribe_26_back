package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Solicitudes;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.SolicitudesJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SolicitudesJpaMapper {

    @Mapping(target = "fecha", ignore = true)
    Solicitudes aModelo (SolicitudesJpa entidad);

    SolicitudesJpa aEntidad (Solicitudes modelo);

    List<Solicitudes> aModeloLista (List<SolicitudesJpa> entidades);

    List<SolicitudesJpa> aEntidadLista (List<Solicitudes> modelos);
    
}
