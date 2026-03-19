package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Categorias;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CategoriasJpa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriasJpaMapper {

    Categorias aModelo (CategoriasJpa entidad);

    CategoriasJpa aEntidad (Categorias modelo);

    List<Categorias> aModeloLista (List<CategoriasJpa> entidades);

    List<CategoriasJpa> aEntidadLista (List<Categorias> modelos);
}
