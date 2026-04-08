package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.CuentasBancarias;
import com.ecommerce.proyecto.dominio.repositorios.ICuentasBancariasRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CuentasBancariasJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.CuentasBancariasJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.ICuentasBancariasJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CuentasBancarias findById(UUID idCuenta) {
        try{
            return cuentasBancariasMapper.aModelo(cuentasBancariasRepo.findById(idCuenta).orElseThrow());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CuentasBancarias actualizar(CuentasBancarias cuenta, UUID idCuenta) {
        try {
            CuentasBancariasJpa cuentaActualizada = cuentasBancariasRepo.findById(idCuenta).orElseThrow();
            if (cuenta.getCuenta() != null) cuentaActualizada.setCuenta(cuenta.getCuenta());
            if (cuenta.getTipoCuenta() != null) cuentaActualizada.setTipoCuenta(cuenta.getTipoCuenta());
            if (cuenta.getNombreBanco() != null) cuentaActualizada.setNombreBanco(cuenta.getNombreBanco());

            return cuentasBancariasMapper.aModelo(cuentasBancariasRepo.save(cuentaActualizada));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Boolean eliminar(UUID id) {
        try {
            cuentasBancariasRepo.delete(cuentasBancariasRepo.findById(id).orElseThrow());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
