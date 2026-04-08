package com.ecommerce.proyecto.infraestructura.output.persistence.entidades;

import com.ecommerce.proyecto.dominio.enums.TiposCuentas;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cuentasBancarias")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CuentasBancariasJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(name = "nombre_banco", nullable = false)
    private String nombreBanco;

    @Column(name = "numero_cuenta", nullable = false)
    private String cuenta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "tipo_cuenta")
    private TiposCuentas tipoCuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private UsuariosJpa cliente;
}
