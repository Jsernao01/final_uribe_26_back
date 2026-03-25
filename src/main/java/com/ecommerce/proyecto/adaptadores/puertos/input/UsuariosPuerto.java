package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarContrasena;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarUsuarioDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarUsuarioDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.UsuarioDto;

import java.util.UUID;

public interface UsuariosPuerto {

    UsuarioDto guardarUsuario(GuardarUsuarioDto usuario, String rol);
    UsuarioDto actualizarUsuario(ActualizarUsuarioDto usuario, UUID id);
    boolean actualizarContrasena(ActualizarContrasena actualizarContrasena);

}
