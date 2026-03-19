package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCuentaBancariaDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CuentaBancariaDto;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;

import java.util.List;
import java.util.UUID;

public interface CuentasBancariasPuerto {

    List<CuentaBancariaDto> guardarCuentasBancarias(List<GuardarCuentaBancariaDto> cuentas, Usuarios cliente);

}
