package com.ecommerce.proyecto.infraestructura.output.persistence.repositorios;

import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.DescuentosJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDescuentoJpaRepositorio extends JpaRepository<DescuentosJpa, UUID> {
    Optional<DescuentosJpa> findByProductoIdAndActivoTrue(UUID idProducto);
}
