package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.puertos.input.AuthPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.IniciarSesionDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.RespuestaInicioSesionDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.SesionUsuarioDto;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import com.ecommerce.proyecto.dominio.repositorios.IUsuariosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthCasosDeUso implements AuthPuerto {

    private final IUsuariosRepositorio usuariosRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RespuestaInicioSesionDto iniciarSesion(IniciarSesionDto credenciales) {
        Usuarios usuario = usuariosRepositorio
                .buscarPorCorreo(credenciales.getCorreo())
                .orElseThrow(() -> new RuntimeException("No existe un usuario registrado con ese correo."));    

        if (!passwordEncoder.matches(credenciales.getContrasena(), usuario.getContrasena())) {
            throw new RuntimeException("Las credenciales no son validas.");
        }

        return RespuestaInicioSesionDto.builder()
                .mensaje("Inicio de sesion exitoso.")
                .token("sesion-" + UUID.randomUUID())
                .usuario(
                        SesionUsuarioDto.builder()
                                .id(usuario.getId())
                                .nombres(usuario.getNombres())
                                .apellidos(usuario.getApellidos())
                                .correo(usuario.getCorreo())
                                .rol(usuario.getRol().name())
                                .build()
                )
                .build();
    }
}
