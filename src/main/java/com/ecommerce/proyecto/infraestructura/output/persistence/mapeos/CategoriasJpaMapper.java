package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Categorias;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CategoriasJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProductosJpaMapper.class})
public interface CategoriasJpaMapper {

    @Mapping(target = "caracteristica", expression = "java(com.ecommerce.proyecto.dominio.enums.Caracteristicas.valueOf(entidad.getCaracteristica().toUpperCase().trim()))")
    Categorias aModelo (CategoriasJpa entidad);

    @Mapping(target = "caracteristica", expression = "java(modelo.getCaracteristica().name())")
    CategoriasJpa aEntidad (Categorias modelo);

    List<Categorias> aModeloLista (List<CategoriasJpa> entidades);

    List<CategoriasJpa> aEntidadLista (List<Categorias> modelos);
}
