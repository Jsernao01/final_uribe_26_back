package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.CuentasBancarias;
import com.ecommerce.proyecto.dominio.repositorios.ICuentasBancariasRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CuentasBancariasJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.CuentasBancariasJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.ICuentasBancariasJpaRepositorio;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CuentasBancariasAdaptador implements ICuentasBancariasRepositorio {

    private final CuentasBancariasJpaMapper cuentasBancariasMapper;
    private final ICuentasBancariasJpaRepositorio cuentasBancariasRepo;

    @Override
    public CuentasBancarias guardar(CuentasBancarias cuenta) {
        try{
            CuentasBancariasJpa cuentaNueva = cuentasBancariasMapper.aEntidad(cuenta);
            return cuentasBancariasMapper.aModelo(cuentasBancariasRepo.save(cuentaNueva));
        }catch (Exception e){
            throw new RuntimeException("Error al guardar una cuenta bancaria", e);
        }
    }
}
