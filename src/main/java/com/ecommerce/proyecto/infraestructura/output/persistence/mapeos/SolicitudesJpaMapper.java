package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Solicitudes;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.SolicitudesJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SolicitudesJpaMapper {

    Solicitudes aModelo (SolicitudesJpa entidad);

    SolicitudesJpa aEntidad (Solicitudes modelo);

    List<Solicitudes> aModeloLista (List<SolicitudesJpa> entidades);

    List<SolicitudesJpa> aEntidadLista (List<Solicitudes> modelos);
    
}
