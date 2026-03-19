package com.ecommerce.proyecto.dominio.dtos.respuesta;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaBancariaDto {
    private UUID id;
    private String nombreBanco;
    private String cuenta;
    private String tipoCuenta;

}
