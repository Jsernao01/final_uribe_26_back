package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.UsuariosJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuariosJpaMapper {

    Usuarios aModelo (UsuariosJpa entidad);

    UsuariosJpa aEntidad (Usuarios modelo);

    List<Usuarios> aModeloLista (List<UsuariosJpa> entidades);

    List<UsuariosJpa> aEntidadLista (List<Usuarios> modelos);

}
