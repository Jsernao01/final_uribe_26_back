package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCuentaBancariaDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CuentaBancariaDto;
import com.ecommerce.proyecto.dominio.modelos.CuentasBancarias;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CuentasBancariasMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "tipoCuenta", ignore = true)
    CuentasBancarias deGuardarCuentasBancarias(GuardarCuentaBancariaDto dto);

    List<CuentasBancarias> deGuardarCuentasBancariasList(List<GuardarCuentaBancariaDto> dtoList);

    @Mapping(target = "tipoCuenta", ignore = true)
    CuentaBancariaDto deResponseCuentasBancarias(CuentasBancarias cuenta);

    List<CuentaBancariaDto> deResponseCuentasBancariasList(List<CuentasBancarias> cuentas);
}
