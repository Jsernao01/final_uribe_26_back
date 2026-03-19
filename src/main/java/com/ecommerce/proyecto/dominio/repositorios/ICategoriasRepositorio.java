package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.Categorias;

import java.util.List;

public interface ICategoriasRepositorio {

    List<Categorias> guardar(List<Categorias> categorias);

}
