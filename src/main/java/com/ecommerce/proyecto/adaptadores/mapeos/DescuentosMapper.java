package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarDescuentoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.DescuentoDto;
import com.ecommerce.proyecto.dominio.modelos.Descuentos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DescuentosMapper {

    @Mapping(target = "tipoDescuento", ignore = true)
    @Mapping(target = "objetivo", ignore = true)
    @Mapping(target = "nombreObjeivo", ignore = true)
    DescuentoDto deResponseDescuentos (Descuentos descuento);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoDescuento", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    Descuentos deGuardarDescuento(GuardarDescuentoDto descuento);

}
