package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.CuentasBancariasPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCuentaBancariaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/cuentasBancarias")
@RequiredArgsConstructor
public class CuentasBancariasControlador {

    private final CuentasBancariasPuerto cuentasBancariasPuerto;

    @PutMapping("actualizar/{idCuenta}")
    public ResponseEntity<?> actualizarCuentaBancaria(@RequestBody GuardarCuentaBancariaDto cuenta, @PathVariable("idCuenta")UUID idCuenta){
        try {
            return ResponseEntity.ok(cuentasBancariasPuerto.actualizarCuentaBancaria(cuenta, idCuenta));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("eliminar/{idCuenta}")
    public ResponseEntity<?> eliminarCuentaBancaria(@PathVariable("idCuenta") UUID idCuenta){
        try {
            return ResponseEntity.ok(cuentasBancariasPuerto.eliminarCuentaBancaria(idCuenta));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("guardar/{idCliente}")
    public ResponseEntity<?> guardarCuentaBancaria(@RequestBody List<GuardarCuentaBancariaDto> dtoList, @PathVariable UUID idCliente){
        try {
            return ResponseEntity.ok(cuentasBancariasPuerto.guardarCuentasBancarias(dtoList, idCliente));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
