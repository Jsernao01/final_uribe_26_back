package com.ecommerce.proyecto.dominio.dtos.peticiones;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardarProductoDto {

    private String nombre;
    private Integer precio;
    private List<GuardarStockDto> stock;
    private List<String> categorias;

}
