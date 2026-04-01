package com.ecommerce.proyecto.infraestructura.output.persistence.repositorios;

import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CarritoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICarritoJpaRepositorio extends JpaRepository<CarritoJpa, UUID> {

    Optional<CarritoJpa> findTopByOrderByReferenciaDesc();
    List<CarritoJpa> findAllByReferenciaReferencia(String referencia);
}
