package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.Carrito;
import com.ecommerce.proyecto.dominio.repositorios.ICarritoRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IOrdenesRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IUsuariosRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CarritoJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.OrdenesJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.UsuariosJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.CarritoJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.ICarritoJpaRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.IOrdenesJpaRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.IUsuariosJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CarritoAdaptador implements ICarritoRepositorio {

    private final CarritoJpaMapper carritoMapper;
    private final ICarritoJpaRepositorio carritoRepo;

    @Override
    public Carrito guardar(Carrito carrito) {
        try {
            return carritoMapper.aModelo(carritoRepo.save(carritoMapper.aEntidad(carrito)));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Carrito findById(UUID id) {
        try {
            return carritoMapper.aModelo(carritoRepo.findById(id).orElseThrow());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Carrito> findAllByReferencia(String referencia) {
        try {
            List<CarritoJpa> carritosJpa = carritoRepo.findAllByReferenciaReferencia(referencia);
            return carritoMapper.aModeloLista(carritosJpa);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void eliminar(Carrito carrito) {
        try {
            carritoRepo.delete(carritoMapper.aEntidad(carrito));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
