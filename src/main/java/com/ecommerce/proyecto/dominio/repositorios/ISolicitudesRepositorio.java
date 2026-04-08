package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.Solicitudes;

import java.util.UUID;

public interface ISolicitudesRepositorio {

    Solicitudes guardarSolicitud(Solicitudes solicitud);
    Solicitudes findById(UUID id);

}
