package com.ecommerce.proyecto.dominio.modelos;

import com.ecommerce.proyecto.dominio.enums.Estados;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Ordenes {
    private String referencia;
    private LocalDateTime fecha;
    private Integer precioTotal;
    private Estados estado;
    private Usuarios cliente;
}
