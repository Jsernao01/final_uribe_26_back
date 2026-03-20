package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.Ordenes;
import com.ecommerce.proyecto.dominio.repositorios.IOrdenesRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CarritoJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.OrdenesJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.OrdenesJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.IOrdenesJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrdenesAdaptador implements IOrdenesRepositorio {

    private final OrdenesJpaMapper ordenesMapper;
    private final IOrdenesJpaRepositorio ordenesRepo;

    @Override
    public Ordenes guardarOrden(Ordenes orden) {
        try{
            OrdenesJpa ordenesNuevo = ordenesMapper.aEntidad(orden);
            return ordenesMapper.aModelo(ordenesRepo.save(ordenesNuevo));
        }catch (Exception e){
            throw new RuntimeException("Error al guardar una orden");
        }

    }

    @Override
    public Ordenes findByReferencia(String referencia) {
        try{
            OrdenesJpa orden = ordenesRepo.findByReferencia(referencia).orElseThrow();
            return ordenesMapper.aModelo(orden);
        }catch (Exception e){
            return null;
        }
    }
}
