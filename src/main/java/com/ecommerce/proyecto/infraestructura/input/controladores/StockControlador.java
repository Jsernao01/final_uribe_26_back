package com.ecommerce.proyecto.infraestructura.input.controladores;

import com.ecommerce.proyecto.adaptadores.puertos.input.StockPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarContenidoStock;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarStockDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockControlador {

    private final StockPuerto stockPuerto;

    @PostMapping("/guardar/{idProducto}")
    public ResponseEntity<?> guardarStock(@RequestBody List<GuardarStockDto> list, @PathVariable("idProducto")UUID idProducto){
        try{
            return ResponseEntity.ok(stockPuerto.guardarStock(list, idProducto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @DeleteMapping("/eliminar/{idStock}")
    public ResponseEntity<?> eliminarStock(@PathVariable UUID idStock){
        try{
            return ResponseEntity.ok(stockPuerto.eliminarStock(idStock));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("/actualizarCantidad")
    public ResponseEntity<?> guardarProducto(@RequestBody List<ActualizarContenidoStock> list){
        try{
            return ResponseEntity.ok(stockPuerto.actualizarCantidad(list));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}
