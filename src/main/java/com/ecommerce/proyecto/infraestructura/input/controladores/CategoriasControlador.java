package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.CategoriasPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCategoriaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriasControlador {

    private final CategoriasPuerto categoriasPuerto;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarCategorias(@RequestBody GuardarCategoriaDto dto){
        try {
            return ResponseEntity.ok(categoriasPuerto.guardarCategoria(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("eliminar/{idCategoria}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable("idCategoria") UUID idCategoria){
        try {
            return ResponseEntity.ok(categoriasPuerto.eliminarCategoria(idCategoria));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
