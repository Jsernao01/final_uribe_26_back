package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCarritoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CarritoDto;
import com.ecommerce.proyecto.dominio.modelos.Carrito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarritoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "referencia", ignore = true)
    @Mapping(target = "producto", ignore = true)
    Carrito deGuardarCarrito(GuardarCarritoDto dto);

    List<Carrito> deGuardarCarritoList(List<GuardarCarritoDto> dto);

}
