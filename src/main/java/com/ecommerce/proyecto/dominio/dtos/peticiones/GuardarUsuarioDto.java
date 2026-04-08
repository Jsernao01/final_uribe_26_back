package com.ecommerce.proyecto.dominio.dtos.peticiones;

import com.ecommerce.proyecto.dominio.enums.Roles;
import com.ecommerce.proyecto.dominio.enums.TipoDocumento;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardarUsuarioDto {
    private String nombres;
    private String apellidos;
    private TipoDocumento tipoDocumento;
    private String documento;
    private String correo;
    private String telefono;
    private Roles rol;
    private LocalDate nacimiento;
    private String direccion;
    private String contrasena;
    private List<GuardarCuentaBancariaDto> cuentasBancarias;
}
