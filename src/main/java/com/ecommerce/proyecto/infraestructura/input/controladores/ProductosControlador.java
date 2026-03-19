package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.ProductosPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductosControlador {

    private final ProductosPuerto productosPuerto;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@RequestBody GuardarProductoDto dto){
        try{
            return ResponseEntity.ok(productosPuerto.guardarProducto(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

}
