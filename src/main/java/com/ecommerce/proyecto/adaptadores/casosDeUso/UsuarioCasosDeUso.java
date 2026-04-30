package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.UsuariosMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.CuentasBancariasPuerto;
import com.ecommerce.proyecto.adaptadores.puertos.input.UsuariosPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarContrasena;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarUsuarioDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosUsuariosDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarUsuarioDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CuentaBancariaDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.UsuarioDto;
import com.ecommerce.proyecto.dominio.enums.Roles;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import com.ecommerce.proyecto.dominio.repositorios.IUsuariosRepositorio;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioCasosDeUso implements UsuariosPuerto {

    private final UsuariosMapper usuariosMapper;
    private final IUsuariosRepositorio usuariosRepo;
    private final CuentasBancariasPuerto cuentasBancariasPuerto;
    private final PasswordEncoder codificarContrasena;

    @Override
    public UsuarioDto guardarUsuario(GuardarUsuarioDto usuario, String rol) {
        try {
            Usuarios usuarioNoAsignado = usuariosMapper.deGuardarUsuario(usuario);
            usuarioNoAsignado.setRol(Roles.valueOf(rol.toUpperCase()));
            String contrasena = codificarContrasena.encode(usuario.getContrasena());
            usuarioNoAsignado.setContrasena(contrasena);
            Usuarios nuevoUsuario = usuariosRepo.guardar(usuarioNoAsignado);

            List<CuentaBancariaDto> cuentasBancarias = cuentasBancariasPuerto.guardarCuentasBancarias(usuario.getCuentasBancarias(), nuevoUsuario.getId());

            UsuarioDto response = usuariosMapper.deResponseUsuario(nuevoUsuario);
            response.setContrasena(contrasena);
            response.setCuentasBancarias(cuentasBancarias);
            return response;
        }catch (Exception e){
            throw new RuntimeException("Error al guardar un usuario: "+e.getMessage());
        }
    }

    @Override
    public UsuarioDto actualizarUsuario(ActualizarUsuarioDto usuario, UUID id) {
        try {
            Usuarios usuarioActualizado = usuariosRepo.actualizar(usuariosMapper.deActualizarUsuario(usuario), id);
            return usuariosMapper.deResponseUsuario(usuarioActualizado);
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar un usuario: "+e.getMessage());
        }
    }

    @Override
    public boolean actualizarContrasena(ActualizarContrasena contrasena) {
        try {
            return usuariosRepo.cambiarContrasena(contrasena);
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar la contrasena: "+e.getMessage());
        }
    }

    @Override
    public List<Usuarios> FiltrarUsuarios(FiltrosUsuariosDto filtros) {
        try {
            return usuariosRepo.FiltrarUsuarios(filtros);
        }catch (Exception e){
            throw new RuntimeException("Error al filtrar los usuarios: "+ e.getMessage());
        }
    }

    @Override
    public boolean eliminarUsuario(UUID id) {
        try {
            return usuariosRepo.eliminar(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    @Override
    public UsuarioDto obtenerPorId(UUID id) {
        try {
            Usuarios usuario = usuariosRepo.findById(id);
            return usuariosMapper.deResponseUsuario(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el usuario: " + e.getMessage());
        }
    }
}
