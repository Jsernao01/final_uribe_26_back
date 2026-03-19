package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.UsuariosPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarUsuarioDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosControlador {

    private final UsuariosPuerto usuariosPuerto;

    @PostMapping("/guardar/{rol}")
    public ResponseEntity<?> guardarProducto(@RequestBody GuardarUsuarioDto dto, @PathVariable String rol){
        try{
            return ResponseEntity.ok(usuariosPuerto.guardarUsuario(dto, rol));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
