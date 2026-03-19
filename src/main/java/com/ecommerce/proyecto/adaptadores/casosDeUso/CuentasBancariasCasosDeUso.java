package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.CuentasBancariasMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.CuentasBancariasPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCuentaBancariaDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CuentaBancariaDto;
import com.ecommerce.proyecto.dominio.modelos.CuentasBancarias;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import com.ecommerce.proyecto.dominio.repositorios.ICuentasBancariasRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentasBancariasCasosDeUso implements CuentasBancariasPuerto {

    private final CuentasBancariasMapper  cuentasBancariasMapper;
    private final ICuentasBancariasRepositorio cuentasBancariasRepo;

    @Override
    public List<CuentaBancariaDto> guardarCuentasBancarias(List<GuardarCuentaBancariaDto> cuentas, Usuarios cliente) {
        try {
            List<CuentasBancarias> cuentasNoAsignadas = cuentasBancariasMapper.deGuardarCuentasBancariasList(cuentas);
            cuentasNoAsignadas.forEach(e-> e.setCliente(cliente));

            List<CuentasBancarias> cuentasAsignadas = cuentasBancariasRepo.guardar(cuentasNoAsignadas);
            return cuentasAsignadas.stream().map(e-> CuentaBancariaDto.builder()
                    .id(e.getId())
                    .nombreBanco(e.getNombreBanco())
                    .cuenta(e.getCuenta())
                    .tipoCuenta(e.getTipoCuenta().getDescripcion())
                    .build()).toList();
        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
    }
}
