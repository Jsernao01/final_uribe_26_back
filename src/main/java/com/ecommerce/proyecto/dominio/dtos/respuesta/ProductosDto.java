package com.ecommerce.proyecto.dominio.dtos.respuesta;

import com.ecommerce.proyecto.dominio.enums.Caracteristicas;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductosDto {

    private UUID id;
    private String nombre;
    private Integer descuento;
    private LocalDateTime lanzamiento;
    private Integer precio;
    private List<InStockDto> stock;
    private List<InCategoriasDto> categorias;
    private Boolean activo;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class InStockDto{
        private String talla;
        private Integer cantidad;
        private String color;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class InCategoriasDto{
        private UUID id;
        private String caracteristica;
    }

}
