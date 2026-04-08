package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Descuentos;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.DescuentosJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DescuentosJpaMapper {

    Descuentos aModelo (DescuentosJpa entidad);

    DescuentosJpa aEntidad (Descuentos modelo);

    List<Descuentos> aModeloLista (List<DescuentosJpa> entidades);

    List<DescuentosJpa> aEntidadLista (List<Descuentos> modelos);
}
