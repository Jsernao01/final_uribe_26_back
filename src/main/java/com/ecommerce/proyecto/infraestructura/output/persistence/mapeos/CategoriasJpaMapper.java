package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Categorias;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CategoriasJpa;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriasJpaMapper {

    Categorias aModelo (CategoriasJpa entidad);

    CategoriasJpa aEntidad (Categorias modelo);

    List<Categorias> aModeloLista (List<CategoriasJpa> entidades);

    List<CategoriasJpa> aEntidadLista (List<Categorias> modelos);
}
