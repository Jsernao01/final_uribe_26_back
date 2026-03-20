package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCarritoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CarritoDto;

import java.util.List;

public interface CarritoPuerto {
    List<CarritoDto> guardarCarrito(List<GuardarCarritoDto> dto);
}
