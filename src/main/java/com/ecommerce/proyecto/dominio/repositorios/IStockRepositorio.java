package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.Stock;

import java.util.List;

public interface IStockRepositorio {

    List<Stock> guardar(List<Stock> stock);

}
