package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.Descuentos;
import com.ecommerce.proyecto.dominio.repositorios.IDescuentosRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.DescuentosJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.DescuentosJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.IDescuentoJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Repository
@RequiredArgsConstructor
public class DescuentosAdaptador implements IDescuentosRepositorio {

    private final DescuentosJpaMapper descuentosMapper;
    private final IDescuentoJpaRepositorio descuentoRepo;

    @Override
    public Descuentos guardarDescuento(Descuentos descuento) {
        try {
            DescuentosJpa descuentoNuevo = descuentosMapper.aEntidad(descuento);
            return descuentosMapper.aModelo(descuentoRepo.save(descuentoNuevo));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Descuentos> buscarDescuentoPorProductoActivo(UUID idProducto) {
        try{
            Optional<DescuentosJpa> descuento = descuentoRepo.findByProductoIdAndActivoTrue(idProducto);
            return descuento.map(descuentosMapper::aModelo);
        }catch (Exception e){
         throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Descuentos> findById(UUID idDescuento) {
        try {
            return descuentoRepo.findById(idDescuento).map(descuentosMapper::aModelo);
        }catch (Exception e){
            throw new RuntimeException(e.getCause() );
        }
    }

    @Override
    public Boolean eliminarDescuento(UUID idDescuento) {
        try {
            descuentoRepo.deleteById(idDescuento);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
