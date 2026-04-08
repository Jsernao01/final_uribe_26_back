package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.Descuentos;

import java.util.Optional;
import java.util.UUID;

public interface IDescuentosRepositorio {

    Descuentos guardarDescuento(Descuentos descuento);
    Optional<Descuentos> buscarDescuentoPorProductoActivo(UUID idProducto);
    Optional<Descuentos> findById(UUID idDescuento);
    Boolean eliminarDescuento(UUID idDescuento);

}
