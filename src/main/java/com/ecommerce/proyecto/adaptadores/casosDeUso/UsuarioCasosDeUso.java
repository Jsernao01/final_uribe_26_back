package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.UsuariosMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.CuentasBancariasPuerto;
import com.ecommerce.proyecto.adaptadores.puertos.input.UsuariosPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarUsuarioDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CuentaBancariaDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.UsuarioDto;
import com.ecommerce.proyecto.dominio.enums.Roles;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import com.ecommerce.proyecto.dominio.repositorios.IUsuariosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioCasosDeUso implements UsuariosPuerto {

    private final UsuariosMapper usuariosMapper;
    private final IUsuariosRepositorio usuariosRepo;
    private final CuentasBancariasPuerto cuentasBancariasPuerto;
    @Override
    public UsuarioDto guardarUsuario(GuardarUsuarioDto usuario, String rol) {
        try {
            Usuarios usuarioNoAsignado = usuariosMapper.deGuardarUsuario(usuario);
            usuarioNoAsignado.setRol(Roles.valueOf(rol.toUpperCase()));
            Usuarios nuevoUsuario = usuariosRepo.guardar(usuarioNoAsignado);

            List<CuentaBancariaDto> cuentasBancarias = cuentasBancariasPuerto.guardarCuentasBancarias(usuario.getCuentasBancarias(), nuevoUsuario);

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
                    .contrasena(nuevoUsuario.getContrasena())
                    .cuentasBancarias(cuentasBancarias)
                    .build();
        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
    }
}
