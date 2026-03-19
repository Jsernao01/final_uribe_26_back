package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductosMapper {

    @Mapping(target = "lanzamiento", ignore = true)
    @Mapping(target = "activo", constant = "true")
    Productos deGuardarProducto(GuardarProductoDto dto);
}
