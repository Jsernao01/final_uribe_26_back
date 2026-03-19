package com.ecommerce.proyecto.infraestructura.output.persistence.entidades;

import com.ecommerce.proyecto.dominio.enums.MotivosCancelacion;
import com.ecommerce.proyecto.dominio.enums.MotivosDevolucion;
import com.ecommerce.proyecto.dominio.enums.TiposSolicitud;
import com.ecommerce.proyecto.dominio.modelos.Ordenes;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "solicitudes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SolicitudesJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @CreationTimestamp
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "tipo_solicitud")
    private TiposSolicitud tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "motivo_devolucion")
    private MotivosDevolucion motivooDevolucion;

    @Enumerated(EnumType.STRING)
    @Column(name = "motivo_cancelacion")
    private MotivosCancelacion motivoCancelacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private UsuariosJpa cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden")
    private OrdenesJpa orden;

}
