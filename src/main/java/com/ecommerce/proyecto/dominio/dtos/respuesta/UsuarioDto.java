package com.ecommerce.proyecto.dominio.dtos.respuesta;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCuentaBancariaDto;
import com.ecommerce.proyecto.dominio.enums.Roles;
import com.ecommerce.proyecto.dominio.enums.TipoDocumento;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {
    private UUID id;
    private String nombres;
    private String apellidos;
    private String  tipoDocumento;
    private String documento;
    private String correo;
    private String telefono;
    private LocalDate nacimiento;
    private LocalDateTime fechaRegistro;
    private String direccion;
    private String contrasena;
    private List<CuentaBancariaDto> cuentasBancarias;

}
