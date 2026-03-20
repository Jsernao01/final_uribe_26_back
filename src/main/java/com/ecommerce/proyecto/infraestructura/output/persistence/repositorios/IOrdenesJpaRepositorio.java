package com.ecommerce.proyecto.infraestructura.output.persistence.repositorios;

import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.OrdenesJpa;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
public interface IOrdenesJpaRepositorio extends JpaRepository<OrdenesJpa, UUID>{

    Optional<OrdenesJpa> findByReferencia(String referencia);
}


