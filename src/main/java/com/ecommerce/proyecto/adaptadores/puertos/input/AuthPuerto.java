package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.IniciarSesionDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.RespuestaInicioSesionDto;

public interface AuthPuerto {
    RespuestaInicioSesionDto iniciarSesion(IniciarSesionDto credenciales);
}
