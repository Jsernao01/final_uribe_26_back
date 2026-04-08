package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.Carrito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICarritoRepositorio {

    Carrito guardar(Carrito carrito);
    Carrito findById(UUID id);
    List<Carrito> findAllByReferencia(String referencia);
    void eliminar(Carrito carrito);


}
