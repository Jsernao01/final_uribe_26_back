package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.enums.Caracteristicas;
import com.ecommerce.proyecto.dominio.modelos.Categorias;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriasMapper {

    default Categorias deString(String caracteristica) {
        Caracteristicas valor = Caracteristicas.valueOf(caracteristica.toUpperCase());

        Categorias categoria = new Categorias();
        categoria.setCaracteristica(valor);

        return categoria;
    }

    List<Categorias> deStringList(List<String> caracteristicas);
}
