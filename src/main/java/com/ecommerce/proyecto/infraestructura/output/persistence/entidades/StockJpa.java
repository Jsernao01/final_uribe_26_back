package com.ecommerce.proyecto.infraestructura.output.persistence.entidades;

import com.ecommerce.proyecto.dominio.modelos.Productos;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "stock")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StockJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false)
    private String talla;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private ProductosJpa producto;
}
