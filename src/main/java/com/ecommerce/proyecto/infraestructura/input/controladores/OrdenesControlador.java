package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.OrdenesPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarOrdenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/ordenes")
@RequiredArgsConstructor
public class OrdenesControlador {

    private final OrdenesPuerto ordenesPuerto;

    @PostMapping("/guardar/{idCliente}")
    public ResponseEntity<?> generarOrden(@PathVariable("idCliente")UUID idCliente){
        try {
            return ResponseEntity.ok(ordenesPuerto.crearOrden(idCliente));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/crearOrden/{referencia}")
    public ResponseEntity<?> crearOrden(@PathVariable("referencia") String referencia){
        try {
            return ResponseEntity.ok(ordenesPuerto.guardarOrden(referencia));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/cambiarEstado")
    public ResponseEntity<?> cambiarEstado(@RequestBody ActualizarOrdenDto dto){
        try {
            return ResponseEntity.ok(ordenesPuerto.cambiarEstado(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarOrdenes() {
        try {
            return ResponseEntity.ok(ordenesPuerto.listarOrdenes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
