package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.Carrito;
import com.ecommerce.proyecto.dominio.repositorios.ICarritoRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CarritoJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.CarritoJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.ICarritoJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarritoAdaptador implements ICarritoRepositorio {

    private final CarritoJpaMapper carritoMapper;
    private final ICarritoJpaRepositorio carritoRepo;

    @Override
    public List<Carrito> guardar(List<Carrito> carrito) {
        try {
            List<CarritoJpa> carritoGuardado = carritoMapper.aEntidadLista(carrito);
            return carritoMapper.aModeloLista(carritoRepo.saveAll(carritoGuardado));
        }catch (Exception e){
            throw new RuntimeException("Error al guardar el Carrito");
        }
    }
}
