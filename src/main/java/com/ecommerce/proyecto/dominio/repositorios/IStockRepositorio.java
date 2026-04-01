package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.Stock;

import java.util.List;
import java.util.UUID;

public interface IStockRepositorio {

    List<Stock> guardar(List<Stock> stock);
    Stock findById(UUID id);
    Boolean eliminarStock(UUID idStock);

}
