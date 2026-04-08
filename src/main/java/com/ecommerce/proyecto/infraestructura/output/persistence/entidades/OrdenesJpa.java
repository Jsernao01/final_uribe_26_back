package com.ecommerce.proyecto.infraestructura.output.persistence.entidades;

import com.ecommerce.proyecto.dominio.enums.Estados;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ordenes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrdenesJpa {
    @Id
    @Column()
    private String  referencia;

    @CreationTimestamp
    @Column(name = "fecha_registro")
    private LocalDateTime fecha;

    @Column(name = "precio_total")
    private Integer precioTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "estado_orden")
    private Estados estado;

    @OneToMany(mappedBy = "referencia")
    private List<CarritoJpa> carritos;

    @OneToMany(mappedBy = "orden")
    private List<SolicitudesJpa> solicitudes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private UsuariosJpa cliente;
}
