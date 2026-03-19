package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Descuentos;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.DescuentosJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface descuentosJpaMapper {

    @Mapping(target = "fechaCreacion", ignore = true)
    Descuentos aModelo (DescuentosJpa entidad);

    DescuentosJpa aEntidad (Descuentos modelo);

    List<Descuentos> aModeloLista (List<DescuentosJpa> entidades);

    List<DescuentosJpa> aEntidadLista (List<Descuentos> modelos);
    
}
