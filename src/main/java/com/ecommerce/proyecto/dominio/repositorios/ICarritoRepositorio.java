package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.Carrito;

import java.util.List;

public interface ICarritoRepositorio {

    List<Carrito> guardar(List<Carrito> carrito);

}
