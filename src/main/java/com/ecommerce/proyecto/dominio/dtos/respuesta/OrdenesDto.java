package com.ecommerce.proyecto.dominio.dtos.respuesta;

import com.ecommerce.proyecto.dominio.enums.Estados;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenesDto {

    private String referencia;
    private LocalDateTime fecha;
    private Integer precioTotal;
    private String  estado;
    private String nombreCliente;
    private String tipoDocumento;
    private String documento;

}
