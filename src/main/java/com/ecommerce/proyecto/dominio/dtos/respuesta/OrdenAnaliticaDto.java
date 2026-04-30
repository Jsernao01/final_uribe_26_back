package com.ecommerce.proyecto.dominio.dtos.respuesta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenAnaliticaDto {
    private String id;
    private String referencia;
    private LocalDateTime fecha;
    private Double total;
    private String estado;
    private UUID clienteId;
    private String clienteNombre;
    private UUID empleadoId;
    private String empleadoNombre;
    private List<ProductoOrdenAnaliticaDto> productos;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductoOrdenAnaliticaDto {
        private UUID productoId;
        private String productoNombre;
        private Double cantidad;
        private Double precioUnitario;
        private Double subtotal;
        private String color;
        private String talla;
    }
}
