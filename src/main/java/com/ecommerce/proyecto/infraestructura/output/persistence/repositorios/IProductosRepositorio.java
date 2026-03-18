package com.ecommerce.proyecto.infraestructura.output.persistence.repositorios;

import com.ecommerce.proyecto.dominio.modelos.Productos;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.ProductosJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductosRepositorio extends JpaRepository<ProductosJpa, UUID> {
}
