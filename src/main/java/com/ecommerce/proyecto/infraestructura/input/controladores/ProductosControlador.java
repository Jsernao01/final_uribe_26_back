package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.ProductosPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosProductosDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/buscar")
    public ResponseEntity<?> filtrarProductos(@RequestBody(required = false) FiltrosProductosDto filtros){
        try{
            if (filtros == null) filtros = new FiltrosProductosDto();
            return ResponseEntity.ok(productosPuerto.filtrarProductos(filtros));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("/cambiarActivacion/{idProducto}")
    public ResponseEntity<?> cambiarActivacion(@PathVariable("idProducto") UUID idProducto){
        try{
            return ResponseEntity.ok(productosPuerto.cambiarActivacionProducto(idProducto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}
