package com.ecommerce.proyecto.infraestructura.output.persistence.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductosJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @CreationTimestamp
    @Column(name = "fecha_lanzamiento", nullable = false)
    private LocalDateTime lanzamiento;

    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false)
    private Boolean activo;

    @OneToMany(mappedBy = "producto")
    private  List<CategoriasJpa> categorias;

    @OneToMany(mappedBy = "producto")
    private List<StockJpa> stocks;

    @OneToOne(mappedBy = "producto")
    private DescuentosJpa descuento;
}
