package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.DescuentosPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarDescuentoDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarDescuentoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/descuentos")
@RequiredArgsConstructor
public class DescuentosControlador {

    private final DescuentosPuerto descuentosPuerto;

    @PostMapping("/crearDescuento")
    public ResponseEntity<?> guardarDescuento(@RequestBody GuardarDescuentoDto dto){
        try {
            return ResponseEntity.ok(descuentosPuerto.guardarDescuento(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscarPorProducto/{idProducto}")
    public ResponseEntity<?> buscarPorProducto(@PathVariable("idProducto")UUID idProducto){
        try {
            return ResponseEntity.ok(descuentosPuerto.buscarDescuentoPorProducto(idProducto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/cambiarActivacion/{idDescuento}")
    public ResponseEntity<?> cambiarActivacion(@PathVariable("idDescuento") UUID idDescuento){
        try {
            return ResponseEntity.ok(descuentosPuerto.cambioDeActivacionDescuento(idDescuento));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idDescuento}")
    public ResponseEntity<?> eliminarDescuento(@PathVariable("idDescuento") UUID idDescuento){
        try {
            return ResponseEntity.ok(descuentosPuerto.eliminarDescuento(idDescuento));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/actualizarDescuento")
    public ResponseEntity<?> actualizarDescuento(@RequestBody ActualizarDescuentoDto dto){
        try {
            return ResponseEntity.ok(descuentosPuerto.actualizarDescuento(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
