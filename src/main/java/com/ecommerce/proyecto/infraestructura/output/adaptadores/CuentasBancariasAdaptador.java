package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.CuentasBancarias;
import com.ecommerce.proyecto.dominio.repositorios.ICuentasBancariasRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CuentasBancariasJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.CuentasBancariasJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.ICuentasBancariasJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CuentasBancariasAdaptador implements ICuentasBancariasRepositorio {

    private final CuentasBancariasJpaMapper cuentasBancariasMapper;
    private final ICuentasBancariasJpaRepositorio cuentasBancariasRepo;

    @Override
    public List<CuentasBancarias> guardar(List<CuentasBancarias> cuenta) {
        try{
            List<CuentasBancariasJpa> cuentaNueva = cuentasBancariasMapper.aEntidadLista(cuenta);
            return cuentasBancariasMapper.aModeloLista(cuentasBancariasRepo.saveAll(cuentaNueva));
        }catch (Exception e){
            throw new RuntimeException("Error al guardar una cuenta bancaria", e);
        }
    }
}
