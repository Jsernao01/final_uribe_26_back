package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.respuesta.OrdenesDto;
import com.ecommerce.proyecto.dominio.modelos.Ordenes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrdenesMapper {

    @Mapping(target = "nombreCliente", ignore = true)
    @Mapping(target = "tipoDocumento", ignore = true)
    @Mapping(target = "documento", ignore = true)
    OrdenesDto deResponseOrdenes(Ordenes orden);

}
