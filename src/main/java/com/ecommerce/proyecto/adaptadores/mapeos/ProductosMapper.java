package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.ProductosDto;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductosMapper {

    @Mapping(target = "lanzamiento", ignore = true)
    @Mapping(target = "activo", constant = "true")
    Productos deGuardarProducto(GuardarProductoDto dto);

    @Mapping(target = "descuento", ignore = true)
    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    ProductosDto deResponseProducto(Productos producto);

    List<ProductosDto> deResponseProductoList(List<Productos> productos);

}
