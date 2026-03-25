package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCarritoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CarritoDto;

import java.util.List;
import java.util.UUID;

public interface CarritoPuerto {
    CarritoDto guardarCarrito(GuardarCarritoDto dto, UUID idCliente, String referencia);
}
