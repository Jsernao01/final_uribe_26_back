package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Productos;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.ProductosJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductosJpaMapper {

    @Mapping(target = "lanzamiento", ignore = true)
    Productos aModelo (ProductosJpa entidad);

    ProductosJpa aEntidad (Productos modelo);

    List<Productos> aModeloLista (List<ProductosJpa> entidades);

    List<ProductosJpa> aEntidadLista (List<Productos> modelos);
    
}
