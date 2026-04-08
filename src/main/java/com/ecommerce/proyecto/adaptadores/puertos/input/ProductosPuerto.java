package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosProductosDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.ProductosDto;

import java.util.List;
import java.util.UUID;

public interface ProductosPuerto {

    ProductosDto guardarProducto(GuardarProductoDto producto);
    boolean cambiarActivacionProducto(UUID idProducto);
    List<ProductosDto> filtrarProductos(FiltrosProductosDto filtros);

}
