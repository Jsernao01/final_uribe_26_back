package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.enums.Estados;
import com.ecommerce.proyecto.dominio.modelos.Ordenes;
import com.ecommerce.proyecto.dominio.repositorios.IOrdenesRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CarritoJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.OrdenesJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.OrdenesJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.IOrdenesJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrdenesAdaptador implements IOrdenesRepositorio {

    private static final Logger log = LoggerFactory.getLogger(OrdenesAdaptador.class);
    private final OrdenesJpaMapper ordenesMapper;
    private final IOrdenesJpaRepositorio ordenesRepo;

    @Override
    public Ordenes guardarOrden(Ordenes orden) {
        try{
            OrdenesJpa ordenesNuevo = ordenesMapper.aEntidad(orden);
            return ordenesMapper.aModelo(ordenesRepo.save(ordenesNuevo));
        }catch (Exception e){
            throw new RuntimeException("Error al guardar una orden: ", e.getCause());
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

    @Override
    public Optional<Ordenes> buscarUltimaReferencia() {
        return ordenesRepo.findTopByOrderByReferenciaDesc().map(ordenesMapper::aModelo);
    }
}
