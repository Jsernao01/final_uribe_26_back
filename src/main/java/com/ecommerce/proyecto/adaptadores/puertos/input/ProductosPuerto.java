package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.ProductosDto;

public interface ProductosPuerto {

    ProductosDto guardarProducto(GuardarProductoDto producto);

}
