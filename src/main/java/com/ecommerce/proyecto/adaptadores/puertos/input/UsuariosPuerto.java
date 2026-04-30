package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarContrasena;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarUsuarioDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosUsuariosDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarUsuarioDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.UsuarioDto;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;

import java.util.List;
import java.util.UUID;

public interface UsuariosPuerto {

    UsuarioDto guardarUsuario(GuardarUsuarioDto usuario, String rol);
    UsuarioDto actualizarUsuario(ActualizarUsuarioDto usuario, UUID id);
    boolean actualizarContrasena(ActualizarContrasena actualizarContrasena);
    List<Usuarios> FiltrarUsuarios(FiltrosUsuariosDto filtrosUsuariosDto);
    boolean eliminarUsuario(UUID id);
    UsuarioDto obtenerPorId(UUID id);
}
