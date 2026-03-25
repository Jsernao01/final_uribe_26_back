package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.OrdenesPuerto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
