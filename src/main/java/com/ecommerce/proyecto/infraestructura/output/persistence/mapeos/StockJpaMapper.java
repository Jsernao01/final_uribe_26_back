package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Stock;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.StockJpa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockJpaMapper {

    Stock aModelo (StockJpa entidad);

    StockJpa aEntidad (Stock modelo);

    List<Stock> aModeloLista (List<StockJpa> entidades);

    List<StockJpa> aEntidadLista (List<Stock> modelos);
    
}
