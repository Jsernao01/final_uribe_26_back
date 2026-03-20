package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.CarritoPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCarritoDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoControlador {

    private final CarritoPuerto carritoPuerto;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarCarrito(@RequestBody List<GuardarCarritoDto> dto){
        try{
            return ResponseEntity.ok(carritoPuerto.guardarCarrito(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

}
