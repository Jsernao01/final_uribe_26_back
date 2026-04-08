package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosProductosDto;
import com.ecommerce.proyecto.dominio.modelos.Productos;

import java.util.List;
import java.util.UUID;

public interface IProductosRepositorio {

    Productos guardar(Productos producto);
    Productos findByid(UUID id);
    List<Productos> filtrarProductos(FiltrosProductosDto filtros);

}
