package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarContrasena {
    public UUID id;
    public String contrasenaNueva;
    public String contrasenaVieja;
}
