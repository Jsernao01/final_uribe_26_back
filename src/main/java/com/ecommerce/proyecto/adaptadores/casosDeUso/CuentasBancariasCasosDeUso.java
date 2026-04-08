package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.CuentasBancariasMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.CuentasBancariasPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCuentaBancariaDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CuentaBancariaDto;
import com.ecommerce.proyecto.dominio.enums.TiposCuentas;
import com.ecommerce.proyecto.dominio.modelos.CuentasBancarias;
import com.ecommerce.proyecto.dominio.modelos.Usuarios;
import com.ecommerce.proyecto.dominio.repositorios.ICuentasBancariasRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IUsuariosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CuentasBancariasCasosDeUso implements CuentasBancariasPuerto {

    private final CuentasBancariasMapper  cuentasBancariasMapper;
    private final ICuentasBancariasRepositorio cuentasBancariasRepo;
    private final IUsuariosRepositorio usuariosRepo;

    @Override
    public List<CuentaBancariaDto> guardarCuentasBancarias(List<GuardarCuentaBancariaDto> cuentas, UUID idCliente) {
        try {
            List<CuentasBancarias> cuentasNoAsignadas = cuentasBancariasMapper.deGuardarCuentasBancariasList(cuentas);
            for (int i = 0; i < cuentas.size(); i++){
                cuentasNoAsignadas.get(i).setTipoCuenta(TiposCuentas.valueOf(cuentas.get(i).getTipoCuenta().toUpperCase()));
            }
            Usuarios usuario = usuariosRepo.findById(idCliente);
            cuentasNoAsignadas.forEach(e-> e.setCliente(usuario));

            List<CuentasBancarias> cuentasAsignadas = cuentasBancariasRepo.guardar(cuentasNoAsignadas);

            List<CuentaBancariaDto> cuentasBancariasResponse = cuentasBancariasMapper.deResponseCuentasBancariasList(cuentasAsignadas);
            for (int i = 0; i < cuentasAsignadas.size(); i++){
                cuentasBancariasResponse.get(i).setTipoCuenta(cuentasAsignadas.get(i).getTipoCuenta().getDescripcion());
            }
            return cuentasBancariasResponse;
        }catch (Exception e){
            throw new RuntimeException("Error al guardar una cuenta bancaria"+e.getMessage());
        }
    }

    @Override
    public CuentaBancariaDto actualizarCuentaBancaria(GuardarCuentaBancariaDto dto, UUID idCuenta) {
        try {
            CuentasBancarias cuentaActualizada = cuentasBancariasMapper.deGuardarCuentasBancarias(dto);
            cuentaActualizada.setTipoCuenta(TiposCuentas.valueOf(dto.getTipoCuenta().toUpperCase()));

            CuentaBancariaDto cuentaResponse = cuentasBancariasMapper.deResponseCuentasBancarias(cuentasBancariasRepo.actualizar(cuentaActualizada, idCuenta));
            cuentaResponse.setTipoCuenta(cuentaActualizada.getTipoCuenta().getDescripcion());
            return cuentaResponse;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar una cuenta bancaria"+e.getMessage());
        }
    }

    @Override
    public Boolean eliminarCuentaBancaria(UUID idCuenta) {
        CuentasBancarias cuenta = cuentasBancariasRepo.findById(idCuenta);
        return cuentasBancariasRepo.eliminar(idCuenta);
    }
}
