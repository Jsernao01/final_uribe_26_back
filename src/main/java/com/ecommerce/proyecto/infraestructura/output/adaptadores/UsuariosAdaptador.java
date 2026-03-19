package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import com.ecommerce.proyecto.dominio.repositorios.IUsuariosRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.UsuariosJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.UsuariosJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.IUsuariosJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsuariosAdaptador implements IUsuariosRepositorio {

    private final UsuariosJpaMapper usuariosMapper;
    private final IUsuariosJpaRepositorio usuariosRepo;

    @Override
    public Usuarios guardar(Usuarios usuario) {
        try {
            UsuariosJpa usuarioGuardar = usuariosMapper.aEntidad(usuario);
            return usuariosMapper.aModelo(usuariosRepo.save(usuarioGuardar));
        }catch (Exception e){
            throw new RuntimeException("Error al guardar un usuario", e);
        }
    }
}
