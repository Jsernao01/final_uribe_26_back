package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarUsuarioDto;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    Usuarios deGuardarUsuario(GuardarUsuarioDto dto);

}
