package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarSolicitudesDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarSolicitudDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.SolicitudesDto;

public interface SolicitudesPuerto {

    SolicitudesDto guardarSolicitud(GuardarSolicitudDto dto);
    SolicitudesDto actualizarSolicitud(ActualizarSolicitudesDto dto);

}
