package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiltrosUsuariosDto {
    public String nombres;
    public String apellidos;
    public String correo;
}
