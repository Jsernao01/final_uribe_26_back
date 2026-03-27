package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.CarritoPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarCarritoProductoDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCarritoDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoControlador {

    private final CarritoPuerto carritoPuerto;

    @PostMapping("/guardar/{referencia}")
    public ResponseEntity<?> guardarCarrito(@RequestBody GuardarCarritoDto dto, @PathVariable("referencia") String refencia){
        try{
            return ResponseEntity.ok(carritoPuerto.guardarCarrito(dto, refencia));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("actualizar")
    public ResponseEntity<?> actualizarCarrito(@RequestBody ActualizarCarritoProductoDto dto){
        try {
            return ResponseEntity.ok(carritoPuerto.actualizarCarrito(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("eliminar/{idCarrito}")
    public ResponseEntity<?> eliminarCarrito(@PathVariable("idCarrito") UUID id){
        try {
            return ResponseEntity.ok(carritoPuerto.eliminarCarrito(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
