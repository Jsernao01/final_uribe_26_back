package com.ecommerce.proyecto.infraestructura.output.persistence.entidades;

import com.ecommerce.proyecto.dominio.enums.TiposDescuento;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "descuentos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DescuentosJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false)
    private Integer descuento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "tipo_descuento")
    private TiposDescuento tipoDescuento;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private Boolean activo;

    @Column()
    private String razon;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime inicio;

    @Column(name = "fecha_final")
    private LocalDateTime fin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private ProductosJpa producto;
}
