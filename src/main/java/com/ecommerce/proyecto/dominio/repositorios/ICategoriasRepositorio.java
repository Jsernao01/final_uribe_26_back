package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.Categorias;

import java.util.List;
import java.util.UUID;

public interface ICategoriasRepositorio {

    List<Categorias> guardar(List<Categorias> categorias);
    boolean eliminar(UUID idCategoria);
    List<Categorias> findByProducto(UUID idProducto);

}
