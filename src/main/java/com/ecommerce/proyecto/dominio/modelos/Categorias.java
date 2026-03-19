package com.ecommerce.proyecto.dominio.modelos;

import com.ecommerce.proyecto.dominio.enums.Caracteristicas;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Categorias {
    private UUID id;
    private Caracteristicas caracteristica;
    private Productos producto;
}
