package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Ordenes;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.OrdenesJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrdenesJpaMapper {

    @Mapping(target = "fecha", ignore = true)
    Ordenes aModelo (OrdenesJpa entidad);

    OrdenesJpa aEntidad (Ordenes modelo);

    List<Ordenes> aModeloLista (List<OrdenesJpa> entidades);

    List<OrdenesJpa> aEntidadLista (List<Ordenes> modelos);
    
}
