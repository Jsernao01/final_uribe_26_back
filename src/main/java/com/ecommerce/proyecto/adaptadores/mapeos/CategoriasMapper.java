package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.respuesta.CategoriaDto;
import com.ecommerce.proyecto.dominio.enums.Caracteristicas;
import com.ecommerce.proyecto.dominio.modelos.Categorias;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriasMapper {

    default Categorias deString(String caracteristica) {
        Categorias categoria = new Categorias();
        try {
            Caracteristicas valor = Caracteristicas.valueOf(caracteristica.toUpperCase().trim());
            categoria.setCaracteristica(valor);
        } catch (Exception e) {
            // Si no coincide con el Enum, asignamos GENERAL por defecto para evitar error 400
            categoria.setCaracteristica(Caracteristicas.GENERAL);
        }

        return categoria;
    }

    List<Categorias> deStringList(List<String> caracteristicas);

    @Mapping(target = "caracteristica", expression = "java(categoria.getCaracteristica().getDescripcion())")
    @Mapping(target = "producto", ignore = true)
    CategoriaDto deResponseCategoria(Categorias categoria);

    List<CategoriaDto> deResponseCategoriaList(List<Categorias> categoria);
}
