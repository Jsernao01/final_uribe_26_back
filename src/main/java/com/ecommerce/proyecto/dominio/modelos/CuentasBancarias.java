package com.ecommerce.proyecto.dominio.modelos;

import com.ecommerce.proyecto.dominio.enums.TiposCuentas;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CuentasBancarias {
    private UUID id;
    private String nombreBanco;
    private Integer cuenta;
    private TiposCuentas tipoCuenta;
    private Usuarios idCliente;

}
