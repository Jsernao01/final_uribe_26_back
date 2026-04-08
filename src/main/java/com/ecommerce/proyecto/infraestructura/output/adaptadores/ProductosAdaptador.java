package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosProductosDto;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import com.ecommerce.proyecto.dominio.repositorios.IProductosRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.ProductosJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.ProductosJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.IProductosJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.ecommerce.proyecto.infraestructura.output.especificaciones.EspecificacionProducto.crearEspecificacionProductos;

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
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Productos findByid(UUID id) {
        try {
            return productosMapper.aModelo(productosRepo.findById(id).orElseThrow());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Productos> filtrarProductos(FiltrosProductosDto filtros) {
        try {
            return productosMapper.aModeloLista(productosRepo.findAll(crearEspecificacionProductos(filtros)));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
