package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarOrdenDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.OrdenesDto;

import java.util.UUID;

public interface OrdenesPuerto {

    String  crearOrden(UUID id);
    OrdenesDto guardarOrden(String referencia);
    OrdenesDto cambiarEstado(ActualizarOrdenDto dto);

}
