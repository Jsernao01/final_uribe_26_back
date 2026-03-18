package com.ecommerce.proyecto.infraestructura.output.persistence.repositorios;

import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.StockJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IStockRepositorio extends JpaRepository<StockJpa, UUID> {
}
