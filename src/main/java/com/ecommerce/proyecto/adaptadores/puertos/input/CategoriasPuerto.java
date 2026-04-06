package com.ecommerce.proyecto.adaptadores.puertos.input;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCategoriaDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CategoriaDto;

import java.util.List;
import java.util.UUID;

public interface CategoriasPuerto {

    List<CategoriaDto> guardarCategoria(GuardarCategoriaDto dto);
    boolean eliminarCategoria(UUID idCategoria);

}
