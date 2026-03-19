package com.ecommerce.proyecto.dominio.modelos;

import com.ecommerce.proyecto.dominio.enums.Roles;
import com.ecommerce.proyecto.dominio.enums.TipoDocumento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Usuarios {
    private UUID id;
    private String nombres;
    private String apellidos;
    private TipoDocumento tipoDocumento;
    private String documento;
    private String correo;
    private String telefono;
    private Roles rol;
    private LocalDate nacimiento;
    private LocalDateTime fechaRegistro;
    private String direccion;
    private String contrasena;

}
