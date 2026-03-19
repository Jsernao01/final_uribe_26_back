package com.ecommerce.proyecto.infraestructura.output.persistence.mapeos;

import com.ecommerce.proyecto.dominio.modelos.CuentasBancarias;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CuentasBancariasJpa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentasBancariasJpaMapper {

    CuentasBancarias aModelo (CuentasBancariasJpa entidad);

    CuentasBancariasJpa aEntidad (CuentasBancarias modelo);

    List<CuentasBancarias> aModeloLista (List<CuentasBancariasJpa> entidades);

    List<CuentasBancariasJpa> aEntidadLista (List<CuentasBancarias> modelos);
    
}
