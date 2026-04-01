package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCuentaBancariaDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CuentaBancariaDto;

import java.util.List;
import java.util.UUID;

public interface CuentasBancariasPuerto {

    List<CuentaBancariaDto> guardarCuentasBancarias(List<GuardarCuentaBancariaDto> cuentas, UUID idCliente);
    CuentaBancariaDto actualizarCuentaBancaria(GuardarCuentaBancariaDto dto, UUID idCuenta);
    Boolean eliminarCuentaBancaria(UUID idCuenta);

}
