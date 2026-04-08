package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarContenidoStock;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarStockDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.StockDto;

import java.util.List;
import java.util.UUID;

public interface StockPuerto {

    List<StockDto> guardarStock(List<GuardarStockDto> dto, UUID idProducto);
    Boolean eliminarStock(UUID idStock);
    List<StockDto> actualizarCantidad(List<ActualizarContenidoStock> dtoList);
}
