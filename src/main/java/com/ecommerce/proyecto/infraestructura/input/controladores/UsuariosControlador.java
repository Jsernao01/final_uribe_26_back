package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.UsuariosPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarContrasena;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarUsuarioDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosUsuariosDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarUsuarioDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosControlador {

    private final UsuariosPuerto usuariosPuerto;

    @PostMapping("/admin/crear/{rol}")
    public ResponseEntity<?> crearUsuario(@RequestBody GuardarUsuarioDto dto, @PathVariable("rol") String rol){
        try{
            return ResponseEntity.ok(usuariosPuerto.guardarUsuario(dto, rol));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/admin/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuario(@RequestBody ActualizarUsuarioDto dto, @PathVariable("id") UUID id){
        try {
            return ResponseEntity.ok(usuariosPuerto.actualizarUsuario(dto, id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/perfil/cambiar-contrasena")
    public ResponseEntity<?> actualizarContrasena(@RequestBody ActualizarContrasena dto){
        try {
            return ResponseEntity.ok(usuariosPuerto.actualizarContrasena(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/admin/listar")
    public ResponseEntity<?> listarUsuarios(@RequestBody(required = false) FiltrosUsuariosDto filtros){
        try {
            if (filtros == null) filtros = new FiltrosUsuariosDto();
            return ResponseEntity.ok(usuariosPuerto.FiltrarUsuarios(filtros));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable("id") UUID id){
        try {
            return ResponseEntity.ok(usuariosPuerto.obtenerPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/admin/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("id") UUID id){
        try {
            return ResponseEntity.ok(usuariosPuerto.eliminarUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
