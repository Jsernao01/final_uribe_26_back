package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarDescuentoDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarDescuentoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.DescuentoDto;

import java.util.UUID;

public interface DescuentosPuerto {

    DescuentoDto guardarDescuento(GuardarDescuentoDto descuento);
    DescuentoDto buscarDescuentoPorProducto(UUID idProducto);
    DescuentoDto cambioDeActivacionDescuento(UUID idDescuento);
    Boolean eliminarDescuento(UUID idDescuento);
    DescuentoDto actualizarDescuento(ActualizarDescuentoDto dto);

}
