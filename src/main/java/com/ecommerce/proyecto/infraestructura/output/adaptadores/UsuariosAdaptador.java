package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarContrasena;
import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosUsuariosDto;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import com.ecommerce.proyecto.dominio.repositorios.IUsuariosRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.UsuariosJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.UsuariosJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.IUsuariosJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.ecommerce.proyecto.infraestructura.output.especificaciones.EspecificacionUsuario.crearEspecificacionUsuarios;

@Repository
@RequiredArgsConstructor
public class UsuariosAdaptador implements IUsuariosRepositorio {

    private final UsuariosJpaMapper usuariosMapper;
    private final IUsuariosJpaRepositorio usuariosRepo;
    private final PasswordEncoder codificarContrasena;

    @Override
    public Usuarios guardar(Usuarios usuario) {
        try {
            UsuariosJpa usuarioGuardar = usuariosMapper.aEntidad(usuario);
            return usuariosMapper.aModelo(usuariosRepo.save(usuarioGuardar));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Usuarios actualizar(Usuarios usuario, UUID id) {
        try{
            UsuariosJpa usuarioJpa = usuariosRepo.findById(id).orElseThrow();

            if (usuario.getNombres()!= null)usuarioJpa.setNombres(usuario.getNombres());
            if (usuario.getApellidos()!= null)usuarioJpa.setApellidos(usuario.getApellidos());
            if (usuario.getCorreo()!= null)usuarioJpa.setCorreo(usuario.getCorreo());
            if (usuario.getTelefono()!= null)usuarioJpa.setTelefono(usuario.getTelefono());
            if (usuario.getDireccion()!= null)usuarioJpa.setDireccion(usuario.getDireccion());

            return usuariosMapper.aModelo(usuariosRepo.save(usuarioJpa));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Boolean cambiarContrasena(ActualizarContrasena actualizarContrasena) {
        try {

            UsuariosJpa usuarioExistente = usuariosRepo.findById(actualizarContrasena.id).orElseThrow();

            if (codificarContrasena.matches(actualizarContrasena.contrasenaVieja, usuarioExistente.getContrasena())){

                usuarioExistente.setContrasena(codificarContrasena.encode(actualizarContrasena.contrasenaNueva));
                usuariosRepo.save(usuarioExistente);
                return true;

            }
            return false;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Usuarios findById(UUID idUsuario) {
        return usuariosMapper.aModelo(usuariosRepo.findById(idUsuario).orElseThrow());
    }

    @Override
    public List<Usuarios> FiltrarUsuarios(FiltrosUsuariosDto filtros) {
        try {
            return usuariosMapper.aModeloLista(usuariosRepo.findAll(crearEspecificacionUsuarios(filtros)));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
