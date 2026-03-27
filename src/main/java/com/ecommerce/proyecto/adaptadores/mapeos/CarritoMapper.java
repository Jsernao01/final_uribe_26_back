package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.respuesta.CarritoDto;
import com.ecommerce.proyecto.dominio.modelos.Carrito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarritoMapper {

    @Mapping(target = "referencia", source = "referencia.referencia")
    CarritoDto deResponseCarrito(Carrito carrito);

}
