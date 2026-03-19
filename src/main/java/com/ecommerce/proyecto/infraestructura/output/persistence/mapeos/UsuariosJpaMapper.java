package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.UsuariosJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuariosJpaMapper {

    @Mapping(target = "fechaRegistro", ignore = true)
    Usuarios aModelo (UsuariosJpa entidad);

    UsuariosJpa aEntidad (Usuarios modelo);

    List<Usuarios> aModeloLista (List<UsuariosJpa> entidades);

    List<UsuariosJpa> aEntidadLista (List<Usuarios> modelos);

}
