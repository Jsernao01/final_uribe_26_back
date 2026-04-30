package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarContrasena;
import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosUsuariosDto;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUsuariosRepositorio {
    Usuarios guardar(Usuarios usuario);
    Usuarios actualizar(Usuarios usuario, UUID id);
    Boolean cambiarContrasena(ActualizarContrasena contrasena);
    Usuarios findById(UUID idUsuario);
    Optional<Usuarios> buscarPorCorreo(String correo);
    List<Usuarios> FiltrarUsuarios(FiltrosUsuariosDto filtros);
    boolean eliminar(UUID id);
}
