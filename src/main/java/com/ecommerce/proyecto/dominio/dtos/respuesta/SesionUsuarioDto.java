package com.ecommerce.proyecto.dominio.dtos.respuesta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SesionUsuarioDto {
    private UUID id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String rol;
}
