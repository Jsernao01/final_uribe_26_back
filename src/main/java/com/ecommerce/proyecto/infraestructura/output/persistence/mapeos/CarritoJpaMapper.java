package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.Carrito;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CarritoJpa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarritoJpaMapper {

    Carrito aModelo (CarritoJpa entidad);
    
    CarritoJpa aEntidad (Carrito modelo);
    
    List<Carrito> aModeloLista (List<CarritoJpa> entidades);
    
    List<CarritoJpa> aEntidadLista (List<Carrito> modelos);

}
