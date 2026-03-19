package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.CuentasBancarias;

import java.util.List;

public interface ICuentasBancariasRepositorio {

    List<CuentasBancarias> guardar(List<CuentasBancarias> cuentas);

}
