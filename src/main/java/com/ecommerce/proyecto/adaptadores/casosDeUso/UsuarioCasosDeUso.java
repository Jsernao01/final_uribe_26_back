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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioCasosDeUso implements UsuariosPuerto {

    private static final Logger log = LoggerFactory.getLogger(UsuarioCasosDeUso.class);
    private final UsuariosMapper usuariosMapper;
    private final IUsuariosRepositorio usuariosRepo;
    private final CuentasBancariasPuerto cuentasBancariasPuerto;
    private final PasswordEncoder codificarContrasena;

    @Override
    public UsuarioDto guardarUsuario(GuardarUsuarioDto usuario, String rol) {
        try {
            Usuarios usuarioNoAsignado = usuariosMapper.deGuardarUsuario(usuario);
            log.info("nacimiento entrada: {}: salida: {}",usuario.getNacimiento(), usuarioNoAsignado.getNacimiento());
            usuarioNoAsignado.setRol(Roles.valueOf(rol.toUpperCase()));
            String contrasena = codificarContrasena.encode(usuario.getContrasena());
            usuarioNoAsignado.setContrasena(contrasena);
            Usuarios nuevoUsuario = usuariosRepo.guardar(usuarioNoAsignado);


            List<CuentaBancariaDto> cuentasBancarias = cuentasBancariasPuerto.guardarCuentasBancarias(usuario.getCuentasBancarias(), nuevoUsuario.getId());

            return UsuarioDto.builder()
                    .id(nuevoUsuario.getId())
                    .nombres(nuevoUsuario.getNombres())
                    .apellidos(nuevoUsuario.getApellidos())
                    .tipoDocumento(nuevoUsuario.getTipoDocumento().getDescripcion())
                    .documento(nuevoUsuario.getDocumento())
                    .correo(nuevoUsuario.getCorreo())
                    .telefono(nuevoUsuario.getTelefono())
                    .nacimiento(nuevoUsuario.getNacimiento())
                    .fechaRegistro(nuevoUsuario.getFechaRegistro())
                    .direccion(nuevoUsuario.getDireccion())
                    .contrasena(contrasena)
                    .cuentasBancarias(cuentasBancarias)
                    .build();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public UsuarioDto actualizarUsuario(ActualizarUsuarioDto usuario, UUID id) {
        try {
            Usuarios usuarioActualizado = usuariosRepo.actualizar(usuariosMapper.deActualizarUsuario(usuario), id);
            return usuariosMapper.deResponseUsuario(usuarioActualizado);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean actualizarContrasena(ActualizarContrasena contrasena) {
        try {
            return usuariosRepo.cambiarContrasena(contrasena);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Usuarios> FiltrarUsuarios(FiltrosUsuariosDto filtros) {
        try {
            return usuariosRepo.FiltrarUsuarios(filtros);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
