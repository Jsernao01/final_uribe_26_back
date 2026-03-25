package com.ecommerce.proyecto.infraestructura.output.persistence.repositorios;

import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.UsuariosJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUsuariosJpaRepositorio extends JpaRepository<UsuariosJpa, UUID> {
    Optional<UsuariosJpa> findById(UUID id);
}
