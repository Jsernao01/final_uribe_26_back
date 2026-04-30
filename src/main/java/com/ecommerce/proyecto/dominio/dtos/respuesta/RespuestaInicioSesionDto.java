package com.ecommerce.proyecto.dominio.dtos.respuesta;

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
public class RespuestaInicioSesionDto {
    private String mensaje;
    private String token;
    private SesionUsuarioDto usuario;
}
