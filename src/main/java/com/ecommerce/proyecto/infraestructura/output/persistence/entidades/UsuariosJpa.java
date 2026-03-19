package com.ecommerce.proyecto.infraestructura.output.persistence.entidades;

import com.ecommerce.proyecto.dominio.enums.Roles;
import com.ecommerce.proyecto.dominio.enums.TipoDocumento;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UsuariosJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "tipo_documento")
    private TipoDocumento tipoDocumento;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(unique = true)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "rol")
    private Roles rol;

    @Column(name = "fecha_nacimiento")
    private LocalDate nacimiento;

    @CreationTimestamp
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column()
    private String direccion;

    @Column(nullable = false)
    private String contrasena;

    @OneToMany(mappedBy = "cliente")
    private List<OrdenesJpa> ordenes;

    @OneToMany(mappedBy = "cliente")
    private List<CuentasBancariasJpa> cuentasBancarias;

    @OneToMany(mappedBy = "cliente")
    private List<SolicitudesJpa> solicitudes;
}
