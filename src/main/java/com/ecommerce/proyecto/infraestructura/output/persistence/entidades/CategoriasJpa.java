package com.ecommerce.proyecto.infraestructura.output.persistence.entidades;

import com.ecommerce.proyecto.dominio.enums.Caracteristicas;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "categorias")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CategoriasJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "caracteristica")
    private Caracteristicas caracteristica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private ProductosJpa producto;
}
