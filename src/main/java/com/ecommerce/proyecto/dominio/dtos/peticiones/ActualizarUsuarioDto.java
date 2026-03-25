package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarUsuarioDto {
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String direccion;
}
