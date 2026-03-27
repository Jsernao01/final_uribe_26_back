package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarUsuarioDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarUsuarioDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.UsuarioDto;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuariosMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "nombres", expression = "java(dto.getNombres().toLowerCase())")
    @Mapping(target = "apellidos", expression = "java(dto.getApellidos().toLowerCase())")
    @Mapping(target = "correo", expression = "java(dto.getCorreo().toLowerCase())")
    Usuarios deGuardarUsuario(GuardarUsuarioDto dto);

    Usuarios deActualizarUsuario(ActualizarUsuarioDto dto);

    @Mapping(target = "cuentasBancarias", ignore = true)
    UsuarioDto deResponseUsuario(Usuarios usuario);

}
