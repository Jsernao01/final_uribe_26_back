package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.CuentasBancarias;

import java.util.List;
import java.util.UUID;

public interface ICuentasBancariasRepositorio {

    List<CuentasBancarias> guardar(List<CuentasBancarias> cuentas);
    CuentasBancarias findById(UUID idCuenta);
    CuentasBancarias actualizar(CuentasBancarias cuenta, UUID id);
    Boolean eliminar(UUID id);

}
