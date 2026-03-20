package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.Productos;

import java.util.UUID;

public interface IProductosRepositorio {

    Productos guardar(Productos producto);
    Productos findByid(UUID id);

}
