package com.ecommerce.proyecto.infraestructura.output.persistence.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "carrito")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CarritoJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referencia")
    private OrdenesJpa referencia;

    @Column(name = "precio_parcial", nullable = false)
    private Integer precioParcial;

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductosJpa idProducto;

}
