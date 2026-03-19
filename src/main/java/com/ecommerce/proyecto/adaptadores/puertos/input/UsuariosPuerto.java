package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarUsuarioDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.UsuarioDto;

public interface UsuariosPuerto {

    UsuarioDto guardarUsuario(GuardarUsuarioDto usuario, String rol);

}
