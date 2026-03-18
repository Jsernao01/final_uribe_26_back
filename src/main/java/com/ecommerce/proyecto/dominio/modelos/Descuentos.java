package com.ecommerce.proyecto.dominio.modelos;

import com.ecommerce.proyecto.dominio.enums.TiposDescuento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Descuentos {
    private UUID id;
    private Integer descuento;
    private TiposDescuento tipoDescuento;
    private LocalDateTime fechaCreacion;
    private Boolean activo;
    private String razon;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private Productos idProducto;
    private Usuarios idCliente;
}
