package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.SolicitudesPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarSolicitudesDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarSolicitudDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/solicitudes")
@RequiredArgsConstructor
public class SoliitudesControlador {

    private final SolicitudesPuerto solicitudesPuerto;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarSolicitud(@RequestBody GuardarSolicitudDto dto){
        try {
            return ResponseEntity.ok(solicitudesPuerto.guardarSolicitud(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/cambiarEstado")
    public ResponseEntity<?> cambiarEstadoSolicitud(@RequestBody ActualizarSolicitudesDto dto){
        try {
            return ResponseEntity.ok(solicitudesPuerto.actualizarSolicitud(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
