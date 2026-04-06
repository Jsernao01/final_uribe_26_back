package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.Productos;
import com.ecommerce.proyecto.dominio.repositorios.IProductosRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.ProductosJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.ProductosJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.IProductosJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductosAdaptador implements IProductosRepositorio {

    private final ProductosJpaMapper productosMapper;
    private final IProductosJpaRepositorio productosRepo;

    @Override
    public Productos guardar(Productos producto) {
        try{
            ProductosJpa productoNuevo = productosMapper.aEntidad(producto);
            return productosMapper.aModelo(productosRepo.save(productoNuevo));
        }catch (Exception e){
            throw new RuntimeException("Error al guardar un producto: " + e.getMessage());
        }
    }

    @Override
    public Productos findByid(UUID id) {
        try {
            return productosMapper.aModelo(productosRepo.findById(id).orElse(null));
        }catch (Exception e){
            throw new RuntimeException("No se encontro ningun producto con ese id");
        }

    }
}
