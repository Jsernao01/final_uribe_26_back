package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IniciarSesionDto {
    private String correo;
    private String contrasena;
}
