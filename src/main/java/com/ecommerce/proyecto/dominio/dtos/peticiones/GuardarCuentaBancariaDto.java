package com.ecommerce.proyecto.dominio.dtos.peticiones;

import com.ecommerce.proyecto.dominio.enums.TiposCuentas;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardarCuentaBancariaDto {
    private String nombreBanco;
    private Integer cuenta;
    private TiposCuentas tipoCuenta;
}
