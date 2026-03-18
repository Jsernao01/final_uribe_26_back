package com.ecommerce.proyecto.infraestructura.output.persistence.repositorios;

import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CategoriasJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICategoriasRepositorio extends JpaRepository<CategoriasJpa, UUID> {
}
